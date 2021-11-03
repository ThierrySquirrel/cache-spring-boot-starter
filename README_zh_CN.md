# cache-spring-boot-starter

缓存 SpringBoot 版

[English](./README.md)

支持功能：
- [x] 缓存  
- [X] 清除缓存  

# 缓存： 
 提供一级JVM缓存,二级redis缓存  
 可集群使用  
 
# 清除缓存:  
 提供清除缓存注解  
 
## Quick Start

  修改redis.conf  
  修改内容为 notify-keyspace-events "Ex"  

```xml
<!--在pom.xml中添加依赖-->
        <dependency>
            <groupId>com.github.thierrysquirrel</groupId>
            <artifactId>cache-spring-boot-starter</artifactId>
            <version>1.2.0.8-RELEASE</version>
        </dependency>
``` 

 ### 配置文件
 
 ```properties
 ## application.properties
spring.redis.host="127.0.0.1"  redis地址
 ```

# 缓存 

 ```java
@RestController
public class CacheRestController {
    @GetMapping("/cache")
    @Cache
    public String hello(@CacheParameter @RequestParam String interfaceName,@RequestParam String versionNo,@CacheParameter @RequestParam long timeStamp) {
        return "hello";
    }
}
 ```

# 清除缓存

 ```java
@RestController
public class ClearCacheRestController {
    @GetMapping("/cache")
    @ClearCache
    public String hello(@CacheParameter @RequestParam String interfaceName,@RequestParam String versionNo,@CacheParameter @RequestParam long timeStamp) {
        return "world";
    }
}
 ```