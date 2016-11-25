## 读取配置文件

### @Value
其实跟Spring MVC一样可以使用@Value读取配置。

```
@Value("${app.name}")
private String appName;
```

### @ConfigurationProperties
另外可以使用**@ConfigurationProperties**注解，具体代码如下：
application.properties
```
app.name=HelloSpringBoot
app.description=${app.name} is a Spring Boot application
```
java代码
```
@Component
@ConfigurationProperties(prefix="app")
public class AppConfig {

    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
```
使用
```
@Autowired
private AppConfig appConfig;
```

### @Value和@ConfigurationProperties的区别
@Value 是核心容器的一个特性，但是它不提供类型安全的配置。如果配置项是一个集合，建议用POJO和@ConfigurationProperties把它们组合起来。

### 支持yaml
具体可以参考官方文档[spring boot yaml](http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#boot-features-external-config-yaml)。
