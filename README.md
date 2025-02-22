<h1 align="center">DataLoom</h1>
<p align="center"><strong>DataLoom是一个为用户提供智能化、可视化的数据分析平台 🛠</strong></p>
<div align="center">
<a target="_blank" href="https://github.com/Hardork/hwqbi-backend">
    <img alt="" src="https://github.com/Hardork/hwqbi-backend/badge/star.svg?theme=gvp"/>
</a>
<a target="_blank" href="https://github.com/Hardork/hwqbi-backend">
    <img alt="" src="https://img.shields.io/github/stars/Hardork/hwqbi-backend.svg?style=social&label=Stars"/>
</a>
    <img alt="Maven" src="https://raster.shields.io/badge/Maven-3.8.1-red.svg"/>
<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
        <img alt="" src="https://img.shields.io/badge/JDK-1.8+-green.svg"/>
</a>
    <img alt="SpringBoot" src="https://raster.shields.io/badge/SpringBoot-2.7+-green.svg"/>
<a href="https://github.com/Hardork/hwqbi-backend" target="_blank">
    <img src='https://img.shields.io/github/forks/Hardork/hwqbi-backend' alt='GitHub forks' class="no-zoom">
</a>
<a href="https://github.com/Hardork/hwqbi-backend" target="_blank"><img src='https://img.shields.io/github/stars/Hardork/hwqbi-backend' alt='GitHub stars' class="no-zoom">
</a>
</div>

> 作者：[老山羊](https://github.com/Hardork)



## 项目背景
<strong>
    传统数据可视化平台存在一些明显的缺点，例如需要用户具备一定的技术知识，因此对用户的技术要求较高。一次完整的数据可视化过程包括数据清洗、数据分析、数据可视化等多个步骤，操作起来较为复杂。DataLoom区别与传统的数据可视化平台，在用户上传数据源（支持MySQL、API、Excel等），系统即自动从元数据中识别出高价值数据，由LLM大模型进行自动化分析，也可支持用户自定义分析目标。
</strong>


## 项目功能介绍

- **智能图表分析**，上传excel数据文件，以及分析诉求即可，获取数据图表与AI分析报告
- **历史分析管理**，用户可以查看并管理历史分析报告
- **数据协同**，用户可以将上传的数据集进行共享进行数据的协作开发
- **AI分析助手**，接入科大讯飞AI大模型，自定义分析助手，让分析更加便捷、智能、多元化
- **积分获取与消费**，积分获取，用户可通过每日签到获取积分，也可通过购买获取积分；积分消费，使用智能图表分析与AI分析助手聊天都会消耗积分




## 项目选型 🎯

### **后端**
- Spring Boot 2.7.0
- Spring MVC
- Spring Cloud Alibaba 2021.0.4.0
- Nacos 注册、配置中心
- MySQL 数据库
- RabbitMQ 消息队列
- Redis 缓存
- 腾讯云COS存储
- Swagger + Knife4j 接口文档
- Jakarta.Mail 邮箱通知、验证码
- Apache Commons Lang3 工具类
- MyBatis-Plus 及 MyBatis X 自动生成
- Hutool、Apache Common Utils 等工具库

### 前端

- React 18

- Ant Design Pro 5.x 脚手架

- Ant Design & Procomponents 组件库

- Umi 4 前端框架

- OpenAPI 前端代码生成
## 网站导航 🧭

- [**DataLoom 后端 🏘️**](https://github.com/Hardork/hwqbi-backend)
- [**DataLoom 前端 🏘**️](https://github.com/Hardork/hwqbi-frontend)


## 目录结构 📑

| src目录                                                         | 描述          |
|---------------------------------------------------------------|-------------|
| **[annotation](./src/main/java/com/hwq/dataloom/annotation)** | 自定义注释目录     |
| **[aop](./src/main/java/com/hwq/dataloom/aop)**               | aop目录       |
| **[dataloomzmq](./src/main/java/com/hwq/dataloom/bizmq)**     | 消息队列目录      |
| **[common](./src/main/java/com/hwq/dataloom/common)**         | 公共模块目录      |
| **[config](./src/main/java/com/hwq/dataloom/config)**               | 公共配置目录      |
| **[constant](./src/main/java/com/hwq/dataloom/constant)**           | 常量目录        |
| **[controller](./src/main/java/com/hwq/dataloom/controller)**       | 接口目录        |
| **[exception](./src/main/java/com/hwq/dataloom/exception)**         | 自定义异常目录     |
| **[job](./src/main/java/com/hwq/dataloom/job)**                     | 定时任务目录      |
| **[manager](./src/main/java/com/hwq/dataloom/manager)**             | 服务、中间件目录    |
| **[mapper](./src/main/java/com/hwq/dataloom/mapper)**               | mapper目录    |
| **[model](./src/main/java/com/hwq/dataloom/model)**                 | 模型目录        |
| **[service](./src/main/java/com/hwq/dataloom/service)**             | service目录   |
| **[utils](./src/main/java/com/hwq/dataloom/utils)**                 | 工具包目录       |
| **[websocket](./src/main/java/com/hwq/dataloom/websocket)**         | websocket目录 |

## 项目流程 🗺️
**智能图表分析**
![img.png](img/img.png)

### 架构设计
![img.png](img/structure.png)

## 快速启动 🚀

### 前端

环境要求：Node.js >= 16

安装依赖：

```bash
yarn or npm install
```

启动：

```bash
yarn run dev or npm run start:dev
```

部署：

```bash
yarn build or npm run build
```

### 后端

1. 执行sql目录下create_table.sql
2. 更改配置文件application.yml中打上xxx的属性值



## 功能展示 ✨

### 智能分析

![image](https://github.com/Hardork/hwqbi-backend/assets/100034835/c2637e62-d95e-4a97-b43d-ef6c06508630)


### 历史分析
![image](https://github.com/Hardork/hwqbi-backend/assets/100034835/c68afbd2-dc99-44d6-9e01-493ef2a5c5a6)


### 分析详情
![image](https://github.com/Hardork/hwqbi-backend/assets/100034835/c03b92b8-9649-4ac3-b541-8651e0ab8249)


### AI分析助手创建
![image](https://github.com/Hardork/hwqbi-backend/assets/100034835/e8fb204e-dad8-429b-a33f-7e52e2be79e8)
![image](https://github.com/Hardork/hwqbi-backend/assets/100034835/b52b69ab-b6e8-42f2-b987-c828b8efeca7)



### AI辅助分析
![image](https://github.com/Hardork/hwqbi-backend/assets/100034835/14b8f4ac-633e-4285-925b-d5f9c5964cd2)



### 积分购买
![image](https://github.com/Hardork/hwqbi-backend/assets/100034835/8d2ca529-6118-47c3-bfcb-2256080d1116)

</br>
</br>

![image](https://github.com/Hardork/hwqbi-backend/assets/100034835/f9296fa4-09f8-4349-b87e-5f58a228117e)


### 订单管理
![image](https://github.com/Hardork/hwqbi-backend/assets/100034835/f3730ed7-54d2-4dd5-b101-39df645e6c4c)


### 个人中心
![image](https://github.com/Hardork/hwqbi-backend/assets/100034835/71c61edd-8e9c-47fe-b94b-d3322029cae5)


## 协议
[MIT](https://choosealicense.com/licenses/mit)

## 源码贡献
如果你发现项目中的任何问题或错误，或者想要对项目进行改进，可以通过以下方式报告问题或提交Pull Request：

- 打开一个Issue，详细描述你的问题或需求。
- 如果你有修复问题的代码，可以创建一个Pull Request，在新分支上修改代码，并将代码合并到主分支。

感谢你对项目的贡献！


