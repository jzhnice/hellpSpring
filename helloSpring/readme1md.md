#SpringBoot

###**SpringBoot的项目创建**

**第一种**
    官网提供的SpringBoot手脚架可以快速搭建SpringBoot项目。
    <a href="https://start.spring.io/"> springboot 官网手脚架  </a>

**第二种**
    使用IDEA 的 Spring Initializr 快速搭建一个SpringBoot项目。


### SpringBoot 的配置文件
application.properties   优先级更高

application.yml          优先级较低，但是编写简便，文件排版整洁，可读性强


### SpringBoot引入lombok组件
```xml
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.22</version>
        </dependency>
```
lombok是一个简便的代码代理插件，可以帮开发者快捷生成 构造器，getter ，setter等代码、、、

### SpringBoot 配置日志组件
```yaml
logging:
  charset:
#    日志的字符集
    console: utf-8
  file:
#    日志文件输出路径
    path: log
#    日志文件文件名
    name: myLog.log
  pattern:
#    日志输出等级 ERROR > WARN > INFO > DEBUG >ALL
    level: DEBUG
#    日志输出的时间格式
    dateformat: yyyy-MM-DD HH:mm:ss
```
SpringBoot默认集成的日志组件是 logBack


### SpringBoot事务管理
配置简单，只需要在 启动类上 加上注解 `@EnableTransactionManagement`

在需要进行异常捕获的方法上加上 `@Transcational()` 或在注解上声明要捕获的异常类型 `@Transcational(rollBackFor = NullPrinterException.class,XXXException.class)`

###SpringBoot配置类



# SpringBoot 整合 MyBatisPlus

MyBatisPlus

```xml
        <!--  MyBatisPlus依赖  -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.5.1</version>
        </dependency>
```

mybatisPlus 配置
```yaml
mybatis-plus:
  mapper-locations: classpath:**/*Mapper.xml
```

mapper代码
```java
@Repository
@Mapper
public interface TCoffeeMapper extends BaseMapper<TCoffee> {
}

```
启动类需要的注解： `@MapperScan("mapper包路径")`





**MyBaitsPlus Wrapper的使用**
> `QueryWrapper` 和 `UpdateWrapper`
> 
> eq方法是用来设置等于查询条件的方法
> 
> le 设置小于查询条件的方法
> 
> ge 设置大于查询条件的方法
> 
> set update独有 ， 设置一条数据值

```java
class p{
    public void showMPDataPagination(int currentPage , int pageSize){

        log.info("开始查询第 {} 页 的数据 , 页面大小为 {}" , currentPage , pageSize);

        IPage<TCoffee> page = new Page<>(currentPage , pageSize);

        IPage<TCoffee> tCoffeeIPage = mapper.selectPage(page, new QueryWrapper<TCoffee>());

        tCoffeeIPage.getRecords().forEach(row -> log.info("row > {}" , row));

    }

    public void batchUpdateMPRecord() throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        TCoffee tc = new TCoffee();
        tc.setName("焦糖拿铁");
        tc.setPrice(4800);
        tc.setCreateTime(format.parse("2022-03-22 14:58:00"));

        UpdateWrapper<TCoffee> tCoffeeUpdateWrapper = new UpdateWrapper<>();
        int result = mapper.update(tc, tCoffeeUpdateWrapper.between("id" , 2 , 4)
                .eq("id" , 3));

        log.info("受影响行数 -> {}" , result);

    }
}
```

**MyBatisPlus 分页插件**

*首先要获取MyBaitsPlus的依赖项*
```xml
        <!--  MyBatisPlus依赖  -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.5.1</version>
        </dependency>
```

分页插件配置类
```java
@Configuration
@Slf4j
public class PaginationConfiguration {

    /**
     * mybatisPlusInterceptor , 是mybatisPlus的一个拦截器
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mpi = new MybatisPlusInterceptor();
        mpi.addInnerInterceptor(paginationInnerInterceptor());
        return mpi;
    }

    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor(){
        PaginationInnerInterceptor pii = new PaginationInnerInterceptor();
        pii.setDbType(DbType.MYSQL); // 这一句不是必须的 ， 如果数据源类型只有一种数据源的话就可以配置，多种数据源的话，默认就行
        return pii;
    }

}

class p {
    public void showMPDataPagination(int currentPage , int pageSize){

        log.info("开始查询第 {} 页 的数据 , 页面大小为 {}" , currentPage , pageSize);

        IPage<TCoffee> page = new Page<>(currentPage , pageSize);

        IPage<TCoffee> tCoffeeIPage = mapper.selectPage(page, new QueryWrapper<TCoffee>());

        tCoffeeIPage.getRecords().forEach(row -> log.info("row > {}" , row));

    }
}

```


**MyBatisPlus 多数据源配置**

多数据源依赖项
```xml

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
            <version>${mp.version}</version>
        </dependency>
```

application.yml | application.properties
```yaml
spring:
  datasource:
    dynamic:
      primary: master
      strict: false
      datasource:
        master:
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://localhost:3306/3_cof
          username: root
          password: root

        slave:
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://localhost:3306/smbms
          username: root
          password: root
```


调用数据源信息 @DS("数据源名称")


**SpringBoot 定时任务**
@EnableScheduling
@Scheduled(cron = "")

>     /**
     * 秒        分       时   月中的某天   月     周几      年
     * Seconds Minutes Hours DayofMonth Month DayofWeek Year或
     *  秒        分       时   月中的某天   月     周几
     * Seconds Minutes Hours DayofMonth Month DayofWeek
     */


    /**
     * Seconds:可出现", - * /"四个字符，有效范围为0-59的整数
     * Minutes:可出现", - * /"四个字符，有效范围为0-59的整数
     * Hours:可出现", - * /"四个字符，有效范围为0-23的整数
     * DayofMonth:可出现", - * / ? L W C"八个字符，有效范围为0-31的整数
     *              W 表示有效工作日 ，
     * Month:可出现", - * /"四个字符，有效范围为1-12的整数或JAN-DEc
     * DayofWeek:可出现", - * / ? L C #"八个字符，有效范围为1-7的整数或SUN-SAT两个范围。
     *                  1表示星期天，2表示星期一， 3 周二 ， 4 周三 ， 5周四 ， 6 周五 ， 7 周六
     * Year:可出现", - * /"四个字符，有效范围为1970-2099年
     */
    //   */number   每当到了 被 number 整除的数字时 才会执行一次
    //  corn = " 秒 分 时 天 月 周 年   "
    //           0 30 20 25 5 *

    // LW : 每个月最后一个工作日
    // # : 确定每月的第几天 5#3 每月的第三个星期四


**SpringBoot热部署**

**热部署**：就是应用程序正在运行的时候，对应用程序本身进行升级，但是不需要重新启动。

```xml
        <!--  springboot开发工具依赖，经常用来解决springboot的热部署问题  -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
        </dependency>
```

![img.png](img.png)
![img_1.png](img_1.png)