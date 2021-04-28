package com.jun.study.springboot.web;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Getter
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
