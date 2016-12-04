### 内嵌web容器
Spring boot starters，特指**spring-boot-starter-web**，默认使用Tomcat作为web容器。

### 使用undertow
spring boot更换web容器非常容易，先把Tomcat exclude掉，然后加上undertow starter（官方支持）。
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```
添加undertow starter依赖。
```
<dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-undertow</artifactId>
 </dependency>
```

### 使用jetty
跟更换undertow操作一样，改为添加jetty starter，同样是官方支持。
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jetty</artifactId>
</dependency>
```
