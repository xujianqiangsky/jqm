<p align="center">
    <img src="https://jqm.plus/logo.svg" height="120" alt="jqm"/>
</p>
<p align="center">
    <img src="https://img.shields.io/badge/jqm-1.0.0-success.svg" alt="jqm 1.0.0"/>
    <img src="https://img.shields.io/badge/Spring%20Cloud-2023.0.3-blue.svg" alt="Spring Cloud 2023.0.3"/>
    <img src="https://img.shields.io/badge/Spring%20Cloud%20Alibaba-2023.0.1.2-blue.svg" alt="Spring Cloud Alibaba 2023.0.1.2"/>
    <img src="https://img.shields.io/badge/Spring%20Boot-3.3.3-blue.svg" alt="Spring Boot 3.3.3"/>
    <img src="https://img.shields.io/badge/Sa--Token-1.39.0-blue.svg" alt="Sa-Token 1.39.0"/>
    <img src="https://img.shields.io/github/license/xujianqiangsky/jqm" alt="Apache-2.0 License"/>
    <img src="https://img.shields.io/github/stars/xujianqiangsky/jqm" alt="Github Stars"/>
</p>

## 项目介绍

基于 SpringCloud & Alibaba、SpringBoot、Sa-Token 的微服务权限管理系统

### 核心依赖

| 依赖 | 版本    |
|:---|:------|
| Spring Boot | 3.3.3 |
| Spring Cloud | 2023.0.3 |
| Spring Cloud Alibaba | 2023.0.1.2 |
| Sa-Token | 1.39.0 |
| Mybatis Plus | 3.5.7 |

### 模块说明

```lua
jqm
├── jqm-admin -- 后台管理模块
├── jqm-api -- 共享接口模块
├── jqm-auth -- 身份认证模块
├── jqm-bom -- jqm bom
└── jqm-common -- 共享模块
    ├── jqm-common-bom -- jqm common bom
    ├── jqm-common-core -- 共享工具类
    ├── jqm-common-mybatis -- Mybatis Plus 共享配置
    ├── jqm-common-security -- Sa Token 共享权限配置
    └── jqm-common-web -- Web 共享配置
├── jqm-gateway -- 网关模块
└── jqm-register -- Nacos
```