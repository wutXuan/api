#### 项目简介

基于Spring Boot + React的API接口调用平台 

- 普通用户：注册登录，查看接口、测试接口，开通接口调用权限，调用接口
- 管理员：上线接口、下线接口、新增接口，可视化分析接口调用情况

#### 项目特点

- API网关统一鉴权
- API签名认证
- 手写客户端SDK
- RPC框架构建分布式应用
- OpenAPI规范简化开发

#### 项目架构

![1](https://github.com/wutXuan/api/blob/master/项目架构.png)


#### 技术选型

##### 前端

- React 18
- Ant Design Pro 5 脚手架
- Umi 4 前端框架
- OpenAPI规范

##### 后端

- Spring Boot 2.7
- MYSQL 8.0
- Mybatis-Plus及Maybatis X
- Spring Boot Starter(SDK开发)
- Dubbo
- Nacos
- SpringCloud Gateway

#### 启动方式

##### 前端

- api-front:进入文件夹目录，安装依赖并启动

```shell
npm install
npm run dev
```

##### 后端

- api-back: 7529端口，后端接口管理
- api-client-sdk:客户端SDK，用户引用该SDK对API签名进行认证并调用接口
- api-gateway: 8090端口，请求转发，计数，鉴权等
- api-interface: 8123端口，提供接口服务