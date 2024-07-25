## 博客介绍




<p align="center">
  基于 SpringBoot3 + Vue3 开发前后端分离个人博客系统
</p>

## 项目部分截图

### 前台

![前台首页](https://github.com/ahuangyaw/blog/raw/master/img/frontend.jpg)

![前台中心](https://gitee.com/kuailemao/ruyu-blog/raw/master/img/%E5%89%8D%E5%8F%B0%E4%B8%AD%E5%BF%83.jpg)

![前台文章](https://gitee.com/kuailemao/ruyu-blog/raw/master/img/%E5%89%8D%E5%8F%B0%E6%96%87%E7%AB%A0.jpg)

### 后台

![后台发布文章](https://gitee.com/kuailemao/ruyu-blog/raw/master/img/%E5%90%8E%E5%8F%B0%E5%8F%91%E5%B8%83%E6%96%87%E7%AB%A0.jpg)

![后台文章列表](https://gitee.com/kuailemao/ruyu-blog/raw/master/img/%E5%90%8E%E5%8F%B0%E6%96%87%E7%AB%A0%E5%88%97%E8%A1%A8.jpg)

## 在线体验地址
**注意：在线预览地址可能与仓库代码不同步，以仓库为主**
> 服务器比较垃圾，随机可能崩掉，发现了会进行维护，且看且珍惜

**前台博客：** kuailemao.xyz

**后台管理：** blog.kuailemao.xyz

**测试账号：** Test，**密码：** 123456

**ps:** 测试账号功能不代表系统所有功能，有些权限过高模块不方便在线展示

**Gitee地址：** https://gitee.com/kuailemao/ruyu-blog

**Github地址：** https://github.com/kuailemao/Ruyu-Blog

**接口文档：** [API文档 (kuailemao.xyz)](http://kuailemao.xyz:8088/doc.html#/home)

**欢迎各位提交 PR ，一起改进项目**

## 运行环境

### 后端：

|   名称   | 环境  |
| :------: | :---: |
|  MySQL   |  8.0  |
|  Redis   | 7.2.3 |
| RabbitMQ | 最新  |
|  minio   | 最新  |
|   JDK    |  17   |

**前端：**

| 名称 |  环境   |
| :--: | :-----: |
| pnpm | 8.12.0  |
| node | 16.17.0 |

## 项目特点

* 前端参考了众多优秀博客大佬设计，页面美观，响应式布局
* 后台管理基于 Antdv Pro 后台通用框架二次开发
* 前后端分离，Docker Compose 一键部署
* 采用 RABC 权限模型，使用 SpringSecurity 进行权限管理
* 支持动态权限修改、动态菜单和路由
* 文章、分类、标签、时间轴、树洞、留言板、聊天、友链等模块
* 站长介绍、公告、电子时钟、随机文章、每日鸡汤、网站资讯
* 支持代码高亮、图片预览、黑夜模式、点赞、收藏、评论等功能
* 评论支持在线预览、Markdown、表情包
* 发送友链申请、通过等自动发送邮件提醒
* 接入第三方 gitee、github登录，减少注册成本
* 文章编辑使用 Markdown 编辑器
* 实现日志管理（操作、登录），服务监控、用户、菜单、角色、权限管理
* 使用 自己搭建 minio 进行图片存储（避免了使用第三方对象存储被刷流量问题）
* 使用 Spring Aop + Redis 对接口进行了限流处理（每分钟）,后端使用 JSR 303 对参数校验，使用 Spring Aop + RabbitMQ 对后台操作日志处理
* 采用 Restful 风格的 API，注释完善，后端代码使用了大量 stream 流编程方式，代码非常美观
* ……

## 技术介绍

**前台前端（博客）：** Vue3 + Pinia +  Vue Router + TypeScript + Axios + Element Plus + Echarts……

**后台启动（管理）：** Vue3 + Pinia +  Vue Router + TypeScript + Axios + Antdv Pro + Ant Design Vue……

**后端：** JDK17 + SpringBoot3 + SpringSecurity + Mysql + Redis + Quartz  + RabbitMQ + Minio + Mybatis-Plus + Nginx + Docker……

**其他：** Gitee、Github 第三方登录

## 运行环境

### 推荐

> 最低 2 核 4 G

**我的：** 腾讯云 2 核 2 G  4 M * 2  （穷）

**系统：** **OpenCloudOS**

**前端：** Docker   **后端：** jenkins

## 后续计划（有空）

> 最近比较忙，过段时间有空了才能进行，还望理解

- [x] 持续优化前台响应式
- [x] 新增用户设置、支持修改邮箱、头像、昵称...
- [x] 重构移动端首页
- [x] 重构移动端文章页面
- [ ] 实现后台导入导出
- [ ] 实现前台搜索
- [x] 前台添加更加有趣的效果
- [x] 前台音乐播放器
- [ ] 后台图片资源管理模块
- [x] 找出并修复一些隐藏的bug
