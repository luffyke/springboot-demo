### 热部署
#### 添加springload依赖
pom.xml
```
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>springloaded</artifactId>
</dependency>

<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <executions>
        <execution>
            <goals>
                <goal>repackage</goal>
            </goals>
        </execution>
    </executions>
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>springloaded</artifactId>
            <version>1.2.6.RELEASE</version>
        </dependency>
    </dependencies>
</plugin>
```

运行程序。

```
➜  ~ curl localhost:9090
Hello Spring Boot!
```

修改Java代码
```
@RequestMapping("/")
public String index() {
    return "Hello Spring Boot!" + "\n" + "Hello Again";
}
```
再次请求。
```
➜  ~ curl localhost:9090
Hello Spring Boot!
Hello Again%
```


#### 使用IDEA问题
IDEA默认没自动编译，所以修改代码后不会立即生效，需要手动build一下，快捷键command+shift+G
