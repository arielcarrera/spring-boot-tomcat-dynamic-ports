package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @GetMapping("/test/hello")
    String test() {
        return "hello test";
    }
    
    @GetMapping("/alternative/hello")
    String test2() {
        return "hello test 2";
    }
}