### profile
开发，测试，线上，一般不同的环境会有不同的变量值，例如http端口等。
Spring Boot支持根据环境更换配置。

Yaml示例
```
server:
    port: 9090
---

spring:
    profiles: dev
server:
    port: 9091

---

spring:
    profiles: prod
server:
    port: 9092
```

Properties示例
分为3个文件，命名规则为application-${profile}.properties，分别是application.properties（这个默认是default）
```
server.port=9090
```
application-dev.properties
```
server.port=9091
```

application-prod.properties
```
server.port=9092
```

### 运行
```
java -jar -Dspring.profiles.active=dev demo-0.0.1-SNAPSHOT.jar
```
> 使用一个-D参数**spring.profiles.active**启动应用程序（记着把它放到main类或jar文件之前

maven方式可以设置run.profiles来运行：
```
mvn spring-boot:run -Drun.profiles=dev
```
