package com.example.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello() {
        return "hello spring";
    }

    @GetMapping("pr")
    public String prTest() {
        return "pr test";
    }


}
