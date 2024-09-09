package plus.jqm.common.security.autoconfigure;

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

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.SameTokenInvalidException;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.same.SaSameUtil;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import plus.jqm.common.core.constant.code.CommonErrorCode;
import plus.jqm.common.core.domain.Result;
import plus.jqm.common.security.constant.AuthErrorCode;
import plus.jqm.common.security.support.StpInterfaceImpl;

/**
 * Sa-Token 权限认证配置类
 *
 * @author xujianqiang
 * @date 2024/09/07
 */
@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class JQMSaTokenAutoConfiguration implements WebMvcConfigurer {
    private static final Logger LOG = LoggerFactory.getLogger(JQMSaTokenAutoConfiguration.class);

    @Autowired
    ObjectMapper objectMapper;

    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                // 拦截地址
                .addInclude("/**")
                .setAuth(obj -> {
                    // 登录校验
                    SaRouter.match("/**", "/auth/login", r -> StpUtil.checkLogin());
                    // 校验 Same-Token 身份凭证 https://sa-token.cc/doc.html#/micro/same-token
                    SaSameUtil.checkCurrentRequestToken();
                })
                .setError(error -> {
                    SaHolder.getResponse().setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
                    try {
                        if (error instanceof NotLoginException exception) {
                            return objectMapper.writeValueAsString(Result.failure(AuthErrorCode.NOT_LOGIN));
                        }
                        if (error instanceof SameTokenInvalidException exception) {
                            LOG.error("msg: {}", exception.getMessage(), exception);
                            return objectMapper.writeValueAsString(Result.failure(AuthErrorCode.INVALID_SAME_TOKEN));
                        }
                        return objectMapper.writeValueAsString(Result.failure(CommonErrorCode.SYSTEM_EXECUTION_ERROR));
                    } catch (JsonProcessingException ignored) {}
                    LOG.error("msg: {}", error.getMessage(), error);
                    return Result.failure(CommonErrorCode.SYSTEM_EXECUTION_ERROR);
                })
                .setBeforeAuth(r -> {
                    SaHolder.getResponse()
                            // 是否可以在 iframe显示视图： DENY=不可以 | SAMEORIGIN=同域下可以 | ALLOW-FROM uri 指定域名下可以
                            .setHeader("X-Frame-Options", "SAMEORIGIN")
                            // 是否启用浏览器默认 XSS 防护： 0=禁用 | 1=启用 | 1; mode=block 启用, 并在检查到 XSS 攻击时，停止渲染页面
                            .setHeader("X-XSS-Protection", "1; mode=block")
                            // 禁用浏览器内容嗅探
                            .setHeader("X-Content-Type-Options", "nosniff");
                });
    }

    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForSimple();
    }

    @Bean
    public StpInterfaceImpl stpInterfaceImpl() {
        return new StpInterfaceImpl();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**");
    }
}
