### 项目介绍

   paper是基于springcloud和vue微服务，前后端分离的后台管理框架，可以作为springcloud和vue的入门学习框架，亦可以作为一个基础脚手架进行二次开发
   
### 所用技术
后端：
   springcloud（eureka+zuul）
   springMVC
   springboot
   mybatis
   mybatis-plus自动生成代码
   Oauth2
   druid数据库连接池
   全局异常捕捉处理（ExceptionHandler）
   fastDFS
   docker
前端：
   vue
   Element UI

### 使用说明


1.新建数据库，导数数据库脚本（paper-admin的根目录下）,修改paper-admin的数据库用户密码，修改redis端口和用户名密码

2.将paper-file模块下的fastdfs_client.conf替换为个人搭建的fastdfs系统下的client.conf文件，并更名

3.启动redis,在fastdfs部署环境下按照官方提示依次启动tracker、storage、nginx

4.启动paper-server 注册中心

5.启动paper-zuul 网关

6.启动paper-base 系统日志管理微服务

7.启动paper-admin 权限微服务

8.启动paper-file 文件上传下载微服务

9.启动前端项目 https://gitee.com/lcg0124/paper-view.git
### 系统截图
![输入图片说明](https://github.com/akaigin/papermanage_springcloud/tree/master/asserts/login.png "屏幕截图.png")
