package plus.jqm.oss.controller;

/*
 * Copyright 2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.PostPolicy;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.jqm.common.core.domain.Result;
import plus.jqm.oss.domain.dto.PresignedDTO;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * 对象存储模块
 *
 * @author xujianqiang
 * @date 2024/11/02
 */
@Tag(name = "对象存储模块")
@RestController
@RequestMapping(value = "/oss")
public class OSSController {
    private final MinioClient minioClient;

    public OSSController(MinioClient minioClient) {this.minioClient = minioClient;}

    @Operation(summary = "生成预签名 URL")
    @SaCheckPermission("sys:oss:signed")
    @PostMapping("/presigned")
    public Result<Map<String, String>> presigned(@RequestBody PresignedDTO presignedDTO) {
        // Create new post policy for 'jqm' with 10 minutes expiry from now.
        PostPolicy policy = new PostPolicy(presignedDTO.getBucketName(), ZonedDateTime.now().plusMinutes(10));
        // Add condition that 'key' (object name) equals to 'my-objectname'.
        policy.addEqualsCondition("key", presignedDTO.getKey());
        // Add condition that 'Content-Type' starts with 'image/'.
        policy.addStartsWithCondition("Content-Type", "image/");
        // Add condition that 'content-length-range' is between 2kiB to 2MiB.
        policy.addContentLengthRangeCondition(2 * 1024, 2 * 1024 * 1024);

        Map<String, String> formData = getPresignedPostFormData(presignedDTO.getBucketName(), policy);
        formData.put("key", presignedDTO.getKey());
        return Result.success(formData);
    }

    @Operation(summary = "生成用户头像预签名 URL")
    @PostMapping("/presigned/avatar")
    public Result<Map<String, String>> presignedByAvatar(@RequestBody PresignedDTO presignedDTO) {
        String filename = presignedDTO.getFilename();
        if (Objects.isNull(filename) || filename.isEmpty()) {
            return Result.success(Map.of());
        }
        String extension = filename.substring(filename.lastIndexOf("."));
        String bucketName = "jqm";
        String key = "avatar/" + UUID.randomUUID() + extension;
        // Create new post policy for 'jqm' with 10 minutes expiry from nowvv.
        PostPolicy policy = new PostPolicy(bucketName, ZonedDateTime.now().plusMinutes(10));
        // Add condition that 'key' (object name) equals to 'my-objectname'.
        policy.addEqualsCondition("key", key);
        // Add condition that 'Content-Type' starts with 'image/'.
        policy.addStartsWithCondition("Content-Type", "image/");
        // Add condition that 'content-length-range' is between 2kiB to 2MiB.
        policy.addContentLengthRangeCondition(2 * 1024, 2 * 1024 * 1024);

        Map<String, String> formData = getPresignedPostFormData(bucketName, policy);
        formData.put("key", key);
        return Result.success(formData);
    }

    private Map<String, String> getPresignedPostFormData(String bucketName, PostPolicy policy) {
        BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder()
                .bucket(bucketName)
                .build();
        try {
            if (minioClient.bucketExists(bucketExistsArgs)) {
                return minioClient.getPresignedPostFormData(policy);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Map.of();
    }
}
