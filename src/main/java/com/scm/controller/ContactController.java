package com.scm.controller;

import com.scm.forms.ContactForm;
import com.scm.helper.Helper;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.model.Contacts;
import com.scm.model.User;
import com.scm.repo.UserRepo;
import com.scm.services.ContactService;
import com.scm.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    @Autowired
private ContactService service;

    @Autowired
    private UserRepo repo;
    @RequestMapping("/add")
    // add contact page: handler
    public String addContactView(Model model) {
        ContactForm contactForm = new ContactForm();

        contactForm.setFavorite(true);
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }


    @PostMapping("/add")
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult bindingResult, Authentication authentication, HttpSession session,Model model){

        if(bindingResult.hasErrors()){
            return "user/add_contact";
        }
        String username= Helper.getEmailOfLoggedInUser(authentication);
        Optional<User> user=repo.findByEmail(username);
        Contacts contacts =new Contacts();
        contacts.setName(contactForm.getName());
        contacts.setFavorite(contactForm.isFavorite());
        contacts.setEmail(contactForm.getEmail());
        contacts.setAddress(contactForm.getAddress());
        contacts.setDescription(contactForm.getDescription());
        contacts.setPhoneNumber(contactForm.getPhoneNumber());
        contacts.setUser(user.get());
        contacts.setWebsiteLink(contactForm.getWebsiteLink());
        contacts.setLinkedLink(contactForm.getLinkedInLink());

        System.out.println(contacts);
        service.save(contacts);
        Message mes=Message.builder().content("Contact Save Successfully").type(MessageType.green).build();
        session.setAttribute("message",mes);
        return "redirect:/user/contacts/add";

    }


}
