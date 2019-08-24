package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
	@Autowired
	AppConfig config;
	
	@Autowired
	FilterConfig filterConfig;

    @GetMapping("/admin/hello")
    String test() {
        return "hello test";
    }
    
    @GetMapping("/alternative/hello")
    String test2() {
        return "hello test 2";
    }
    
    @GetMapping("/admin/addNewPort")
    ResponseEntity<String> createNewPort(@RequestParam Integer port, @RequestParam String context) {
    	if (port == null || port < 1) {
    		return new ResponseEntity<>("Invalid Port" + port, HttpStatus.BAD_REQUEST);
    	}
    	config.addPort("http", "localhost", port, false);
    	if (context != null && context.length() > 0) {
    		config.addContextAllowed(filterConfig, port, context);
    	}
    	
        return new ResponseEntity<>("Added port:" + port, HttpStatus.OK);
    }
}