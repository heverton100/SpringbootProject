package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.PessoaEntity;
import com.example.demo.repository.PessoaRepository;


@Controller
public class HomeController {

    @Autowired
    private PessoaRepository pessoaR;

    @GetMapping(path = "/teste")
    public @ResponseBody String getMethodName() {
        return "Hello";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
    
    @GetMapping(path="/all")
    public @ResponseBody Iterable <PessoaEntity> getPessoas() {
        return pessoaR.findAll();
    }
}
