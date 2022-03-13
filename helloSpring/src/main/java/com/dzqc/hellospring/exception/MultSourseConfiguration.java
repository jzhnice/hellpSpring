//package com.dzqc.hellospring.exception;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
///**
// * @author jzh
// * @date 2022/2/25 8:31
// */
//@Configuration
//@Slf4j
//public class MultSourseConfiguration {
//    @Value("master.datasource.driver.class-name")
//    private  String master_driverName;
//
//    @Value("master.datasource.jdbcUrl")
//    private  String master_url;
//
//    @Value("master.datasource.username")
//    private  String master_username;
//
//    @Value("master.password")
//    private  String master_password;
//
//    @Bean(name = "master")
//
//    public DataSource masterSource(){
//        log.info("drive ->{}",master_driverName);
//        log.info("url ->{}",master_url);
//        log.info("username ->{}",master_username);
//        log.info("password ->{}",master_password);
//        DataSource build = DataSourceBuilder.create().build();
//        return build;
//
//    }
//
////    @Bean(name = "slave")
////    public DataSource slaveSource() {
////
////    }
//
//}
