package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {

    @GetMapping(path = "/teste")
    public @ResponseBody String getMethodName() {
        return "Hello";
    }

    @RequestMapping("/welcome")
    public String welcome(Model model) {

        model.addAttribute("testando", "Dale Verdão");

        return "welcome";
    }
    
}
