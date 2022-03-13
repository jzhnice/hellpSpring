package com.dzqc.hellospring;

import com.dzqc.hellospring.service.Bootservice;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootTest
@Slf4j
class HelloSpringApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;

//    @Test
//    void contextLoads() {
//        List class3 = redisTemplate.opsForList().range("class3", 0, 99);
//        log.error("class3 -> {} ", class3);
//        class3.forEach(s -> log.info("studentName -?{}", s));
//    }

}
