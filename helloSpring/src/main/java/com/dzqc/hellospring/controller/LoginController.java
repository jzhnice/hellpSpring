package com.dzqc.hellospring.controller;

import com.dzqc.hellospring.service.Bootservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author jzh
 * @date 2022/3/10 10:44
 */

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private Bootservice service;
    @RequestMapping(value = "")
    public ModelAndView login(String cofName, Integer cofPrice){
    Map<String,Object> map = service.cofLogin(cofName, cofPrice);
    boolean result = (Boolean) map.get("isSuccess");

    ModelAndView mv = new ModelAndView();
    mv.addObject("cof",map.get("cof"));
    if (result){
        mv.setViewName("/helloThymeleat");
    }else {
        mv.setViewName("/index");
    }
    return mv;
}
}
