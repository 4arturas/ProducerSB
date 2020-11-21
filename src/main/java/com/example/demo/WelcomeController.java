package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController
{
    @RequestMapping("/greeting")
    public String greeting()
    {
        return "Hello";
    }
}
