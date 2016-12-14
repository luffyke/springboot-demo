### 配置
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```
> 这里只使用jdbc来操作数据库，其他方式如JPA，Spring Data或者Hibernate不在这里累述，具体参考官网。

```
spring.datasource.url=jdbc:mysql://hostip/test
spring.datasource.username=username
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```

> **spring-boot-starter-jdbc**和**spring-boot-starter-data-jpa** starter会自动添加tomcat-jdbc数据库连接池。
> **spring.datasource.driver-class-name**大多数情况下可以不配置，Spring Boot会自动**spring.datasource.url**找打相关数据库驱动。

所以这里可以添加tomcat-jdbc配置。
```
# Number of ms to wait before throwing an exception if no connection is available.
spring.datasource.tomcat.max-wait=10000

# Maximum number of active connections that can be allocated from this pool at the same time.
spring.datasource.tomcat.max-active=50

# Validate the connection before borrowing it from the pool.
spring.datasource.tomcat.test-on-borrow=true
```

### 代码
```
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String findNameById(Integer id) {
        return jdbcTemplate.queryForObject("select name from user where id = ?", new Object[]{id}, String.class);
    }
}
```

```
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public String findNameById(Integer id) {
        return userDao.findNameById(id);
    }
}
```

```
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/get")
    public String get(@RequestParam(value = "id") Integer id) {
        String name = userService.findNameById(id);
        if (null != name) {
            return "user name is " + name;
        } else {
            return "User not found";
        }
    }
}
```
数据库
```
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into user values(1, 'luffyke');
```

### 测试
```
➜  ~ curl http://localhost:9090/user/get\?id\=1
user name is luffyke%
```
