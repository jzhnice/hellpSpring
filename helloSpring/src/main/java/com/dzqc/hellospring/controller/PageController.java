package com.dzqc.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jzh
 * @date 2022/3/10 11:08
 */
@Controller
@RequestMapping("/")
public class PageController {
    @GetMapping
    public String index(){
        return "/index";
    }
}
