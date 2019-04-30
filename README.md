## 背景
公司初期业务快速发展，产品及运营部门需要很多数据的支撑做数据决策。在开发人员经常遇到产品运营部门提过来的数据提取需求，频繁写sql业务的同时。思考后决定做要一个极其简易版本的能根据sql快速产出报表的工具。

## 截图演示


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

执行工程根目录下的初始化sql 脚本:init.sql

## 启动
```
 mvn package -DskipTests=true
 java -jar /home/admin/heart.jar  -Dfile.encoding=UTF-8  -Duser.timezone=GMT+08  -Djava.io.tmpdir=/home/admin/heart/temp -Xms1024m -Xmx2048m
 ```