## 创建一个hello world！的web服务程序

- 1.1 点击File -> New -> Project,选择Java,点击Next
- 1.2 填写Group、Artifact信息,选择java版本
- 1.3  编写pom.xml文件,主要是继承spring-boot-starter-parent,并添加spring-boot-starter-web依赖
```在pom.xml中添加Spring Boot依赖:
   <parent>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-parent</artifactId>
   <version>2.3.7.RELEASE</version>
   </parent>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```
- 1.4 打开View -> Tool Windows -> Maven,点击Reload All Maven Projects
- 1.5 项目在src/main/java下创建controller包,在包中创建一个Controller类:
```java
   @RestController
   public class HelloController {

   @RequestMapping("/hello")
   public String hello() {
   return "hello world";
   }

}
```

- 1.6 在src/main/java下创建启动类,添加@SpringBootApplication注解:
```java  
   @SpringBootApplication
   public class Application {
   public static void main(String[] args) {
   SpringApplication.run(Application.class, args);
   }
   }
```
- 1.7 点击顶部菜单栏的箭头图标运行启动类中的main方法,然后在浏览器访问localhost:8080/hello验证。

主要是手动添加Spring Boot依赖,并通过Maven窗口加载依赖。其他步骤跟Spring Initializr创建项目类似。

记得选择合适的Spring Boot版本,示例中是2.3.7。