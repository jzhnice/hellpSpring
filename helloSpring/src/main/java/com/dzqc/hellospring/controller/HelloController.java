package com.dzqc.hellospring.controller;
import com.dzqc.hellospring.entity.TCoffee;
import com.dzqc.hellospring.service.Bootservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class HelloController {
    @Autowired
    private Bootservice service;


    @RequestMapping("/hello")
    public ModelAndView hello(HttpServletRequest req){
        List<TCoffee> tCoffees = service.showMPData();
        log.info("server返回数据成功 -> {}" ,tCoffees);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/helloThymeleat");
        modelAndView.addObject("cofs",tCoffees);
        Date data = new Date();
        int seconds = data.getSeconds();
        String href = "";
        if (seconds % 3 ==0){
            href = "https://www.bilibili.com";
        }else  if (seconds % 3 == 1){
            href = "https://www.acfun.cn";
        }else  if (seconds % 3 == 2){
            href = "https://www.youtube.com";
        }
        modelAndView.addObject("hrefs", href);
        return modelAndView;
    }

}
