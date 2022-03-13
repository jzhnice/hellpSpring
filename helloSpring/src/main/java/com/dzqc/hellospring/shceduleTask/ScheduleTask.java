package com.dzqc.hellospring.shceduleTask;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dzqc.hellospring.entity.TCoffee;
import com.dzqc.hellospring.mapper.TCofferMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.security.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author jzh
 * @date 2022/2/28 14:52
 */
@Component  //用来装配 项目中非业务类。业务类包含(Controller service mapper )
@Slf4j
public class ScheduleTask {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TCofferMapper mapper;
    /**
     *  Seconds 秒   Minutes 分        Hours 时     Month月中的某天  月  周几   年
     * */
//    Seconds Minutes Hours DayofMonth Month DayofWeek Year
//Seconds Minutes Hours DayofMonth Month DayofWeek

    /**
     * Seconds:可出现", - * /"四个字符，有效范围为0-59的整数
     * Minutes:可出现", - * /"四个字符，有效范围为0-59的整数
     * Hours:可出现", - * /"四个字符，有效范围为0-23的整数
     * DayofMonth:可出现", - * / ? L W C"八个字符，有效范围为0-31的整数
     * w表示有效工作日
     * Month:可出现", - * /"四个字符，有效范围为1-12的整数或JAN-DEc
     * DayofWeek:可出现", - * / ? L C #"八个字符，有效范围为1-7的整数或SUN-SAT两个范围。
     * 1表示星期天，2表示星期一， 3 周二 4  周三  5 周四  6 周五 7周六 依次类推
     * Year:可出现", - * /"四个字符，有效范围为1970-2099年
     */
    // 秒  分  时  天 月 周  年
    // LW 每个月的最后一个工作日
    // # 确定每月的第几天

    // */number 每当到了 被number 整除的时候 才会执行一次

    //每个30秒新增一条
//    @Scheduled(cron = "*/30 * * * * *")
//    public void task() {
//        int seconds = new Date().getSeconds();
//
//        log.info("时间到了该新增了,{}", seconds);
//        TCoffee tCoffee = new TCoffee();
//        Integer id = tCoffee.getId();
//        jdbcTemplate.execute("INSERT INTO T_COFFEE (NAME ,PRICE,CREATE_TIME,UPDATE_TIME) " +
//                "VALUES ('新增',19,NOW(),NOW())");
//    }
//
//    //  每个十秒查询一次
//    @Scheduled(cron = "*/10 * * * * *")
//    public void selectone() {
//        log.info("时间到了，该查询主要了");
//        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM T_COFFEE");
//        maps.forEach(row -> log.info("select: {} ", row.toString()));
//    }
//
//    //      每个55秒查询一次
//    @Scheduled(cron = "55 * * * * *")
//    @DS("slave")
//    public void selecttwo() {
//        log.info("时间到了，该查询了次要表了");
//        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM app_info");
//        maps.forEach(row -> log.info("select: {} ", row.toString()));
//    }
//
//    //更新次要表的时间
//    @Scheduled(cron = "0 */1 * * * *")
//    @DS("slave")
//    public void update() {
//        int minutes = new Date().getMinutes();
//        log.info("时间到了，该更新了次要表了: {} ",minutes);
//        jdbcTemplate.execute("update app_info SET creationDate = NOW()");
//    }
//（1）：写一个每分钟执行一次的task，并输出一行日志信息
//    @Scheduled(cron = "0 */1 * * * *")
//    public void task1() {
//        int minutes = new Date().getMinutes();
//        log.info("正在执行每分钟执行一次的task 时间是{}",minutes);
//    }
//
//    //（2）：写一个每三秒执行一次的task ， 输出当前秒数
//    @Scheduled(cron = "*/3 * * * * *")
//    public void task2() {
//        int seconds = new Date().getSeconds();
//        log.info("正在执行每三秒执行一次的task 时间是{}",seconds);
//    }
//
//
//
//    //（3）：写一个在每分钟第 25 - 40 秒间执行的task ， 输出 当前时间戳
//    @Scheduled(cron = "25-40 * * * * *")
//    public void task3() {
//        int seconds = new Date().getSeconds();
//        long time = new Date().getTime();
//        log.info("正在执行25-40执行一次的task 执行时间是{} 时间戳为{}" ,seconds,time);
//    }
//
//    //（4）：写一个在每分钟的第 12秒 ， 17秒 ， 47秒，59秒执行的task ， 输出 一行日志信息
//    @Scheduled(cron = "12,17,47,59 * * * * *")
//    public void task4() {
//        int seconds = new Date().getSeconds();
//        log.info("正在执行每分钟的12 17 47 59 秒 执行一次的task 执行时间是{}",seconds);
//
////    }
//    @Scheduled(cron = "*/5 * * *  * *")
//    private void zhouce() {
//        int minutes = new Date().getSeconds();
//        log.info("每分钟执行12次 当前时间为 {}", minutes);
//    }
}
