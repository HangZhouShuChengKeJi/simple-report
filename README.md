## 背景
 - 公司初期业务快速发展，产品及运营部门需要很多数据的支撑做数据决策。但是技术资源有限
 - 为了尽可能在有限的技术条件下，提供产品和运营部门数据提取功能
 
## 项目目标
 - 写SQL就成生成报表页面 （已完成）
 - 能配置查询条件，支持各种字段查询，包括时间区间等（已完成）
 - 支持报表数据导出成EXCEL（已完成）
 - 支持业务告警需求，比如某个指标没有达标，则发告警邮件等 （规划中）
 - 支持图表展示（规划中）
 - 支持数据分析任务流（规划中）

## 使用说明

[查看使用说明](doc/useage.md)


## 架构依赖
springboot
tomcat

## 安装
修改配置
src/main/resources/application.properties
```
server.tomcat.basedir=
```

修改数据库配置
src/main/resources/datasource.properties
```
datasource.main.driverClassName=com.mysql.jdbc.Driver
datasource.main.url=jdbc:mysql://${ip}:${port}/${schemaName}?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true
datasource.main.username=root
datasource.main.password=123456
datasource.main.initialSize=2
datasource.main.maxTotal=10
```
也可以不修改，直接用命令行参数传入
比如启动参数增加：
```
 --datasource.main.url=jdbc:mysql://数据库ip:数据库端口/ctb?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true --datasource.main.username=数据库用户名 --datasource.main.password=数据库密码
```

执行工程根目录下的初始化sql 脚本:init.sql

## 启动
```
 mvn package -DskipTests=true
 java -jar /home/admin/heart.jar  -Dfile.encoding=UTF-8  -Duser.timezone=GMT+08  -Djava.io.tmpdir=/home/admin/heart/temp -Xms1024m -Xmx2048m
 ```
