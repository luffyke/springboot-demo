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

### 日志
对不同的环境进行不同的日志控制，例如一般dev是debug level，prod是info level。分别对logback，log4j和log4j2进行profile配置。
#### logback
logback-spring.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <include resource="org/springframework/boot/logging/logback/base.xml" />

    <appender name="DAILY_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <FileNamePattern>logs/app.%d{yyyy-MM-dd-HH-mm}.log</FileNamePattern>
        </rollingPolicy>
        <encoder>
            <Pattern>${FILE_LOG_PATTERN}</Pattern>
        </encoder>
    </appender>

    <springProfile name="dev">
        <logger name="org.smartx.demo" level="DEBUG">
            <appender-ref ref="DAILY_FILE"/>
        </logger>
    </springProfile>

    <springProfile name="prod">
        <logger name="org.smartx.demo" level="INFO">
            <appender-ref ref="DAILY_FILE"/>
        </logger>
    </springProfile>

</configuration>
```

#### log4j
pom.xml
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j</artifactId>
</dependency>
```
logging.properties
```
# LOG4J配置
log4j.category.org.smartx.demo=${logging.level.org.smartx.demo}, demofile
# com.didispace下的日志输出
log4j.appender.demofile=org.apache.log4j.DailyRollingFileAppender
log4j.appender.demofile.file=logs/app.log
log4j.appender.demofile.DatePattern='.'yyyy-MM-dd
log4j.appender.demofile.layout=org.apache.log4j.PatternLayout
log4j.appender.demofile.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} %5p %c{1}:%L ---- %m%n
```
application-dev.properties
```
logging.level.org.smartx.demo=DEBUG
```

application-prod.properties
```
logging.level.org.smartx.demo=INFO
```

#### log4j2
pom.xml
跟log4j类似，不过依赖改成**spring-boot-starter-log4j2**。
可以通过application-{profile}.properties的logging.config进行控制。
application-dev.properties
```
logging.config=log4j2-dev.xml
```

```
logging.config=log4j2-prod.xml
```
