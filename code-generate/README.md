# code-generate
## 基于lombok+mybatis-plus的entity/mapper/service/serviceImpl的代码生成器

# 食用方法
```
git clone git@github.com:Licoy/javas.git
```
修改code-generate/src/main/resources/config.properties中的配置信息：
```
project-directory= #项目地址（精确到src/main/java目录）
entity-package= #entity包名
mapper-package= #mapper包名
service-package= #service包名
service-impl-package= #service-impl包名
# DATABASE CONFIG
db-driver-class=com.mysql.jdbc.Driver #数据库驱动
db-username=root #数据库用户名
db-password=root #数据库密码
db-url=jdbc:mysql://localhost:3306/aikan?useUnicode=yes&characterEncoding=UTF8 #数据库连接url
```
修改完成之后，直接运行CodeGenerate.java中的main()方法即可。