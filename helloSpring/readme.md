# 2022 02 22总结
springBoot的项目创建
第一种：官网提供的spring boot手脚架可以快速搭建springboot项目
<a hrof=""https://start.spring.io/">
第二种
使用idea的spring Initializr 快速搭建一个springBoot项目

springBoot的配置文件
application.properties  优先级更高

application.yml 优先级较低  但是编写简便 文件排版整洁可读性强

springBoot引入lombok组件

```xml
   <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.22</version>
        </dependency>
```
lomcak 是一个渐变的代码代理插件，可以帮开发者快捷生成构造器getter setter 等代码

springBoot 配置日志组件
```yml

logging:
  charset:
     // 日志的字符集设置
    console: utf-8
  file:
     //日志文件的文件名
    name: myLog.log
    //日志文件输出路径
    path: log
  pattern:
     //日志输出等级 
     //ERROR  > WARN  >  INFO  >  DEBUG  >  ALL
     
    level: DEBUG
    //日志输出的时间格式
    dateformat: yyyy-MM-DD HH:mm:ss
mybatis-plus:
  mapper-locations: classpath:**/*Mapper.xml

```
springBoot 默认继承的日志组件时logBack
##spring Boot 集成数据源
> H2Database,是springBoot内部自带的一个轻量级的数据库，分为两种存储状况
1. >内存存储方式，在项目启动时候创建数据库，在项目关闭时自动销毁
   > URL ：jdbc:h2:mem:~baseName
2. > 2：持久化存储，在数据库被创建后会一直存在，就算项目关闭仍然存在于本地的内存中
   > URL:jdbc:h2~baseName
   


h2 的应用环境更多是在测试环境下 
**springboot输出日志**
>添加 lombok、SLF4J 依赖
>lombok 可以使用 @SLF4J注解完成入职的快速输出
> SLF4J 可以使用 ```private static final Logger log = LoggerFactory.getLogger(xxx.class)```

**DRUID连接池**
> 配置连接池属性,达到且换连接池的目的

**YML**
>缩进要严格，优先级低于properties配置文件

##springPlus的配置类

#SpringBoot
事务管理
配置简单 只需要在启动类上加上注解'@EnableTransactionManagement'
在需要进行异常捕获得方法上假声‘@Transcational() 或 在注解上声明要捕获的异常类型‘@Transactional(rollbackFor = NUllprinterException.class, XXXException.class)’
springBoot 整合MyBatisPlus
**mybatisPlus**
```xml
<!-- mubatisplus依赖项 -->
<dependency>
<groupId>com.baomidou</groupId>
<artifactId>mybatis-plus-boot-starter</artifactId>
<version>3.5.1</version>
</dependency>
``

**mybatisplus wrapper 的使用**
> QueryWrapper 和 UpdateWarpper
这三个方法展示查询条件的
> eq方法是用来设置查询条件相等的方法

> lg设置小于查询条件的方法

> ge设置大于查询条件的方法

>set  update独有 设置一条数据的值等于

**mybatisplus 分页插件**
*首先要获取mybatisPLUS的依赖项*

mybatisplus 多数据源配置
依赖插件
```xml
<dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
            <version>3.5.1</version>
        </dependency>
```
mapper 代码
   ```java
@Repository
@Mapper
public interface TCofferMapper extends BaseMapper<TCoffee> {
}

```
启动类需要的注解
```java
@MapperScan("com.dzqc.hellospring.mapper")

```

在application.yml || application.properties
```yaml
spring:
  dataSource:
    dynamic:
      primary: master
      strict: false
      datasource:
        mayster:
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://localhost:3306/cof
          username: root
          password: 123456

        slave:
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://localhost:3306/appinfodb
          username: root
          password: 123456
```
调用数据源信息 需要@DS("数据源名称)


@Repository、@Service、@Controller 和 @Component 将类标识为Bean
1、被@Component注解的POJO类将自动被Spring识别并注册到Spring容器中，且自动支持自动装配。

2、被@Repository注解的POJO类表示DAO层实现。

3、被@Service注解的POJO类表示Service层实现，从而见到该注解就想到Service层实现，使用方式和@Component相同。

4、被@Controller注解的类表示Web层实现，从而见到该注解就想到Web层实现，使用方式和@Component相同。
springboot 定时任务

springboot热部署
热部署 就是应用程序正在运行的时候对应用本身进行升级并进行控制


##springboot 整合thymeleaf模板引擎