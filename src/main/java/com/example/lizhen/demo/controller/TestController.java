package com.example.lizhen.demo.controller;

import com.example.lizhen.demo.bean.Aaaa;
import com.example.lizhen.demo.service.AaaaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class TestController {

    @Autowired
    AaaaService aaaaService;

    @ResponseBody
    @GetMapping("/aaaa")
    public Aaaa getAaaaByFdId(@RequestParam("fdId") String fdId){
        Aaaa a=aaaaService.getAaaa(fdId);
        log.info("sssssssssss"+a);
        return aaaaService.getAaaa(fdId);
    };
}
