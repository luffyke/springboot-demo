### 简介
Spring Boot是快速开发Spring应用的框架，内嵌了Servlet容器（默认是Tomcat），直接运行Main方法部署应用。

官网：[Spring Boot](http://projects.spring.io/spring-boot/)

### 代码
我采用了Java+Maven来开发Hello world程序，其他选择是Java+Gradle。

#### pom.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.4.2.RELEASE</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.smartx</groupId>
    <artifactId>springboot-demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```
spring-boot-starter-parent是提供了默认的Maven配置。
spring-boot-starter-web依赖是开发Web应用的，它包含了默认的web应用依赖。

#### Java代码
```
@SpringBootApplication
public class HelloMain {

    public static void main(String[] args) {
        SpringApplication.run(HelloMain.class, args);
    }

}

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Hello Spring Boot";
    }

}
```

直接运行Main方法就可以启动Spring Boot程序了，默认端口是8080。

```
➜  ~ curl localhost:8080
Hello Spring Boot
```

### 可执行Jar包
```
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```
spring-boot-maven-plugin包含<executions>配置去绑定<repackage>目标，这一部分是由spring-boot-starter-parent默认提供的。

接下来直接运行Jar包就可以启动应用。

```
mvn package && java -jar target/springboot-demo-0.0.1-SNAPSHOT.jar
```

### 修改默认HTTP端口
在application.properties中设置server.port就可以修改默认的HTTP端口。

```
server.port=8081
```

重新运行应用，使用curl测试。

```
➜  ~ curl localhost:8081
Hello Spring Boot
```
