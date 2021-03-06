# 黑马旅游网

## 项目介绍

`MySQL` `Redis` `Nginx` `Servlet` `Jquery` `Filter` 

- **项目描述**：
	是一个完整的旅游线路网站。主要功能是实现了用户登录注册、线路查询、分页查询、用户收藏、热门景点排序等模块，在其中完成了前端构建,后端开发，linux服务器搭建
- **主要内容**：
- 采用了jquery的ajax异步请求原理进行前后端交互json数据
- 基于Bootscript前端框架构前端项目
- 使用JDBC连接mysql数据库，使用Javamail实现注册校验
 - 采用Nginx做反向代理、以及搭建Tomcat服务器集群，方式解决前台网页高并发问题

## 依赖环境

  - jdk1.8,maven,MySQL
  - 注意事项

    在数据库中创建名为`travel`数据库,然后运行项目的根目录下的sql脚本，记得在`druid.properties`改数据库配置信息
    数据库用户名root，密码123

## 使用技术

#####  Web层

a)     Servlet：前端控制器

b)    html：视图

c)     Filter：过滤器

d)    BeanUtils：数据封装

e)     Jackson：json序列化工具

#####  Service层

f)     Javamail：java发送邮件工具

g)     Redis：nosql内存数据库

h)    Jedis：java的redis客户端

#####  Dao层

i)      Mysql：数据库

j)     Druid：数据库连接池

k)     JdbcTemplate：jdbc的工具
