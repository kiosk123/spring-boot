package com.study.boot.controller.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.boot.vo.ExampleVO;

@RestController
public class ExampleController {
    
    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
    
    @GetMapping("/example")
    public ExampleVO example() {
        ExampleVO vo = new ExampleVO(10, "ten");
        return vo;
    }
}
