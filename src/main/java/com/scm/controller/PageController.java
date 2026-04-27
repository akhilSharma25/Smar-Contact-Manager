package com.scm.controller;

import com.scm.forms.UserForm;
import com.scm.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }
    @GetMapping("/home")
    public  String home(Model model){
        model.addAttribute("name","Substring Technologies");
        model.addAttribute("city","Delhi");

        model.addAttribute("loggedInUser", false);
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", true);
        System.out.println("About page loading");
        return "about";
    }

    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("services page loading");
        return "services";
    }

    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }

    // this is showing login page
    @GetMapping("/login")
    public String login() {
        return new String("login");
    }

    @GetMapping("/signup")
    public String sign(Model model) {
        model.addAttribute("userForm", new UserForm());
        return new String("register");
    }

    //processing register

    @PostMapping("/do-register")
    public  String processRegister(@ModelAttribute UserForm userForm){
        System.out.println("Processing Registration");

        //validate form data
        //save to database

        return "redirect:/home";
    }


}
