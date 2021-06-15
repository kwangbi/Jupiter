package com.yang.jupiter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "dsunni");
        
        if(1==1) {
        	throw new RuntimeException();
        }
        
        return "hello";
    }
}
