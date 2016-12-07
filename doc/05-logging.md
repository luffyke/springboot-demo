### 日志

#### 默认
spring boot在所有内部使用[Commons Logging](http://commons.apache.org/proper/commons-logging/)，但是默认配置也提供了对常用日志的支持，如：[Java Utils Logging](http://docs.oracle.com/javase/7/docs/api/java/util/logging/package-summary.html)，[log4j](http://logging.apache.org/log4j/1.2/)，[log4j2](http://logging.apache.org/log4j/2.x/)，[logback](http://logback.qos.ch/)。每种Logger都可以通过配置使用控制台或者文件输出日志内容。

日志的默认输入方式（java -jar或者mvn spring-boot:run）是直接打印在控制台。
使用服务运行（/etc/init.d/demo start）日志是放在/var/log/demo中的。

### 格式
默认输出
```
2016-11-30 10:10:23.345  INFO 24804 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/8.5.6
```
输出内容元素具体如下：
- 时间日期 — 精确到毫秒
- 日志级别 — ERROR, WARN, INFO, DEBUG or TRACE
- 进程ID
- 分隔符 — --- 标识实际日志的开始
- 线程名 — 方括号括起来（可能会截断控制台输出）
- Logger名 — 通常使用源代码的类名
- 日志内容

### 控制台
在Spring Boot中默认配置了ERROR、WARN和INFO级别的日志输出到控制台。

我们可以通过两种方式切换至DEBUG级别：
- 在运行命令后加入--debug标志，如：$ java -jar myapp.jar --debug
- 在application.properties中配置debug=true，该属性置为true的时候，核心Logger（包含嵌入式容器、hibernate、spring）会输出更多内容，但是你自己应用的日志并不会输出为DEBUG级别。

### 文件
Spring Boot默认配置只会输出到控制台，并不会记录到文件中，但是我们通常生产环境使用时都需要以文件方式记录。

若要增加文件输出，需要在application.properties中配置logging.file或logging.path属性。

- logging.file，设置文件，可以是绝对路径，也可以是相对路径。如：logging.file=my.log
- logging.path，设置目录，会在该目录下创建spring.log文件，并写入日志内容，如：logging.path=/var/log

> 日志文件会在10Mb大小的时候被截断，产生新的日志文件，默认级别为：ERROR、WARN、INFO

### 日志级别
在Spring Boot中只需要在application.properties中进行配置完成日志记录的级别控制。

配置格式：```logging.level.*=LEVEL```

- logging.level：日志级别控制前缀，*为包名或Logger名
- LEVEL：选项TRACE, DEBUG, INFO, WARN, ERROR, FATAL, OFF

举例：
- logging.level.org.smartx=DEBUG：org.smartx包下所有class以DEBUG级别输出
- logging.level.root=WARN：root日志以WARN级别输出。

### 自定义日志配置
由于日志服务一般都在ApplicationContext创建前就初始化了，它并不是必须通过Spring的配置文件控制。因此通过系统属性和传统的Spring Boot外部配置文件依然可以很好的支持日志控制和管理。

根据不同的日志系统，你可以按如下规则组织配置文件名，就能被正确加载：

- Logback：logback-spring.xml, logback-spring.groovy, logback.xml, logback.groovy
- Log4j：log4j-spring.properties, log4j-spring.xml, log4j.properties, log4j.xml
- Log4j2：log4j2-spring.xml, log4j2.xml
- JDK (Java Util Logging)：logging.properties

> Spring Boot官方推荐优先使用带有-spring的文件名作为你的日志配置（如使用logback-spring.xml，而不是logback.xml）

### 自定义输出格式
在Spring Boot中可以通过在application.properties配置如下参数控制输出格式：
- logging.pattern.console：定义输出到控制台的样式（不支持JDK Logger）
- logging.pattern.file：定义输出到文件的样式（不支持JDK Logger）

### logback扩展
Spring Boot包含了一些Logback扩展可以帮助我们进行高级配置，你可以在logback-spring.xml配置文件使用这些配置。

> 因为logback.xml加载的比较早，你不能在标准的logback.xml中使用这些配置，你需要在logback-spring.xml中或者定义一个logging.config属性中使用。

#### profile配置

<springProfile>标签允许你可选的包含或者排除基于激活的Spring profiles的配置，<configuration>元素里面的任何位置都是支持Profile sections的， 使用name属性标识哪个profile使用配置，多个profiles可以使用逗号隔开表示。
```
<springProfile name="staging">
    <!-- configuration to be enabled when the "staging" profile is active -->
</springProfile>
<springProfile name="dev, staging">
    <!-- configuration to be enabled when the "dev" or "staging" profiles are active -->
</springProfile>
<springProfile name="!production">
    <!-- configuration to be enabled when the "production" profile is not active -->
</springProfile>
```

#### Environment配置
Spring Boot 也可以采用<springProperty> 标签来获取在Spring Environment 中定义的属性。也就是说可以在logback 配置文件中获取在application.properties 中定义的变量。通常它也能直接工作在logback 的<property> 标签中。具体配置如下：
```
<springProperty scope="context" name="fluentHost" source="myapp.fluentd.host"
        defaultValue="localhost"/>
<appender name="FLUENT" class="ch.qos.logback.more.appenders.DataFluentAppender">
    <remoteHost>${fluentHost}</remoteHost>
    ...
</appender>
```
