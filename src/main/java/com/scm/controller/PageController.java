package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/home")
    public  String home(Model model){
        model.addAttribute("name","Substring Technologies");
        model.addAttribute("city","Delhi");

        model.addAttribute("loggedInUser", false);
        return "home";
    }
}
