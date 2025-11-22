package com.dreamwalk.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hi")
    public String sayHi() {
        return "Hello from DreamWalk via Spring!";
    }
}
