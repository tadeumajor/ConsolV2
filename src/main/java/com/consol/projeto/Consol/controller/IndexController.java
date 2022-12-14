package com.consol.projeto.Consol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public  String index(){
        return "home";
    }

    @RequestMapping("/faleConosco")
    public  String faleConosco(){
        return "faleConosco";
    }
}
