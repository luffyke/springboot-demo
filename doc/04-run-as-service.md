## 运行后台spring boot服务
> 本文是centos环境

### 配置
#### maven配置
```
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <executable>true</executable>
    </configuration>
</plugin>
```
#### 打包
```
mvn clean package
```
#### 上传到服务器
```
scp target/springboot-demo-0.0.1-SNAPSHOT.jar user@hostip:~/
```

### 创建服务
#### 添加运行权限
```
ssh user@host
chmod +x springboot-demo-0.0.1-SNAPSHOT.jar
```
#### 创建软链接
```
sudo ln -s /home/user/springboot-demo-0.0.1-SNAPSHOT.jar /etc/init.d/boot
```
> 创建软链的时候第一个参数是目标的完整路径

### 测试
```
/etc/init.d/boot start
```
#### 日志
```
tail -f /var/log/boot.log
```
#### 测试
```
curl host:9090
Hello Spring Boot!
Hello Again%
```
#### 停止
```
/etc/init.d/boot stop
```
