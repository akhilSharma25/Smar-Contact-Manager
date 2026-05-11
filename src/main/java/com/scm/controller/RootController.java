package com.scm.controller;

import com.scm.helper.Helper;
import com.scm.model.User;
import com.scm.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@ControllerAdvice
public class RootController {
    @Autowired
    private UserRepo repo;
    @ModelAttribute
    public void addLoggedInUserInfromation(Authentication authentication, Model model){
        // 1. Check karo authentication null toh nahi (Logout/Public pages ke liye)
        if (authentication == null) {
            return;
        }
        String username= Helper.getEmailOfLoggedInUser(authentication);
        System.out.println("Fetching data for: " + username);
        System.out.println(username);
        Optional<User> user = repo.findByEmail(username);
        // 2. Database se user fetch kiya (taaki details profile page par dikh sakein)
        if (user.isPresent()) {
            model.addAttribute("loggedInUser", user.get());
        } else {
            // Agar user DB mein nahi hai (e.g. first time OAuth login process)
            // toh null avoid karne ke liye attribute add hi mat karo ya placeholder daalo
            System.out.println("User not found in database for email: " + username);
            model.addAttribute("loggedInUser", null);

        }
        // 3. Model mein add kiya taaki Thymeleaf ise use kar sake
//        model.addAttribute("loggedInUser", user.get());

    }

}
