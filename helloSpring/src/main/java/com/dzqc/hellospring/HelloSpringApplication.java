package com.dzqc.hellospring;

import com.dzqc.hellospring.entity.TCoffee;
import com.dzqc.hellospring.service.Bootservice;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Iterator;
import java.util.List;
import java.util.Set;


@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.dzqc.hellospring.mapper")
@Slf4j
@EnableScheduling   // 开启springboot定时任务开关
public class HelloSpringApplication implements CommandLineRunner  {

    @Autowired
    private Bootservice service;
    //    public static final 常量
//    private static  final Logger log = LoggerFactory.getLogger(HelloSpringApplication.class);

   /**
    * redis整合
    * */
    @Autowired
    private RedisTemplate redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        service.showMPData();

//        service.showData();
//

//        service.showMPData();
//        log.info("=========================");
//
//       try{
//           service.insertRecordThenRollback();
//       }catch (Exception e){
//           log.info("捕获到异常，会滚开始");
//           service.showData();
//       }
//        service.insertMPRecurd();
//
//        service.deleteMP();
//
//        service.updateMP();
//        service.showMPData();
//        System.out.println("==============");
//        service.updateMP();
//        service.showMPData();
//
//        service.batchUpdateMPRecord();
//        service.deleteMP();

//        service.deleteMP();

//         以下是作业
//        for (int i = 0; i < 50; i++) {
//            service.insertMPRecurd(i);
//        }
//        service.dtxg();
//        service.dtxg();
//        service.plsc();
//        service.idsc();
//        service.dtcx();


//        log.info("数据源Mysql进行查询");
//        service.slaveData();
//        service.showMPDataPageination(0, 15);
//        log.info("数据源mysql2进行查询");
//        service.updateMP();
//        service.showMPDataPageination(1,20);
//        service.slaveData();
//        log.warn("启动类中打印初始化的对象 ->  {} ",tCoffee);


//        Set class3 = redisTemplate.opsForSet().members("class3");
//        log.error("class3 -> {} ", class3);
//        Iterator iterator = class3.iterator();
//        while (iterator.hasNext()){
//         log.info("studentName -> {}",iterator.next());
//        }

//        TCoffee coffee = new TCoffee();
//        coffee.setId(1);
//        coffee.setName("榛果拿铁");
//        coffee.setPrice(888);


//        redisTemplate.opsForHash().put("coffee","榛果拿铁",coffee);
//        List<Object> coffees = redisTemplate.opsForHash().values("coffee");
//        coffees.forEach(o -> log.info("咖啡名 -> {}" ,o ));
//        Set coffee1 = redisTemplate.opsForHash().keys("coffee");
//        Iterator iterator = coffee1.iterator();
//        while (iterator.hasNext()){
//            log.info("hkeys ->{}",iterator.next());
//        }
    }


}