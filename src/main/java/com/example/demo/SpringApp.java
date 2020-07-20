package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
public class SpringApp {

    @GetMapping("/math/pi")
    public String piGet(){
        return "3.141592653589793";
    }
    
    @GetMapping("/hello")
    public String helloGet(){
        return "hello get";
    }

    @PostMapping("/hello")
    public String helloPost(){
        return "hello post";
    }

    @DeleteMapping("/hello")
    public String helloDelete(){
        return "hello Delete";
    }

    @PutMapping("/hello")
    public String helloPut(){
        return "hello Put ";
    }

    @PatchMapping("/hello")
    public String helloPatch(){
        return "hello Patch";
    }
}

