# cache-spring-boot-starter

cache Spring Book Edition

[中文](./README_zh_CN.md)

Support function:
- [x] Cache  
- [X] ClearCache  

# Cache： 
 Provide Level 1 JVM Cache And Level 2 Redis Cache  
 Can be used in cluster  
 
# ClearCache:  
 Provide Clear Cache Annotation   
 
## Quick Start

  Modify redis.conf  
  Modified As notify-keyspace-events "Ex"  

```xml
<!--Adding dependencies to pom. XML-->
        <dependency>
            <groupId>com.github.thierrysquirrel</groupId>
            <artifactId>cache-spring-boot-starter</artifactId>
            <version>1.2.1.4-RELEASE</version>
        </dependency>
``` 

 ### configuration file
 
 ```properties
 ## application.properties
spring.redis.host="127.0.0.1"  Redis Address
 ```

# Cache 

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

# ClearCache

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
 
![Russian flag](https://user-images.githubusercontent.com/49895274/190372870-c3ae28ca-af94-4b8b-8403-98924209d80a.png)

 
