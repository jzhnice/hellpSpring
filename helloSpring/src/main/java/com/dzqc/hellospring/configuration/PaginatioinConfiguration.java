package com.dzqc.hellospring.configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.dzqc.hellospring.entity.TCoffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

// 分页的配置类
@Configuration
@Slf4j
public class PaginatioinConfiguration {
    @Bean
    public TCoffee initCoffee(){
        TCoffee tc = new TCoffee();
        tc.setName("java岛咖啡");
        tc.setCreateTime(new Date());
        tc.setUpdateTime(new Date());
        tc.setPrice(0);
        log.info("配置类启动了 ,初始化对象 -> {} ",tc );
        return tc;
    }

    /*
    * MybatisPlusInterceptor 是一个mybatisPlus的一个拦截器
    * */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor (){

        MybatisPlusInterceptor  mpi = new MybatisPlusInterceptor();
        mpi.addInnerInterceptor(paginationInnerInterceptor());
        return  mpi ;
    }
    @Bean
public PaginationInnerInterceptor paginationInnerInterceptor(){
        PaginationInnerInterceptor pii = new PaginationInnerInterceptor();
        pii.setDbType(DbType.MYSQL); //这一步不是必须的 ，如果数据源类型只有一种数据源的话可以配置 多种数据源的呢话 默认就行
        return pii;
    }
}

