package com.scm.config;

import com.scm.helper.AppConstants;
import com.scm.model.Providers;
import com.scm.model.User;
import com.scm.repo.UserRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class OAuthenticSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserRepo repo;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        org.springframework.security.oauth2.core.user.DefaultOAuth2User user =(DefaultOAuth2User)authentication.getPrincipal();
        String email=user.getAttribute("email").toString();
        String name=user.getAttribute("name").toString();
        String picture=user.getAttribute("name").toString();

//        create user and save in database

        User user1=new User();
        user1.setEmail(email);
        user1.setName(name);
        user1.setProfilePic(picture);
        user1.setPassword("password");
        user1.setUserId(UUID.randomUUID().toString());
        user1.setProvider(Providers.GOOGLE);
        user1.setEnabled(true);
        user1.setEmailVerified(true);
        user1.setProviderUserId(name);
        user1.setRoleList(List.of(AppConstants.ROLE_USER));
        user1.setAbout("This account created is using google");

       User user2= repo.findByEmail(email).orElse(null);
       if(user2==null){
           repo.save(user1);

       }

        new DefaultRedirectStrategy().sendRedirect(request,response,"/user/profile");
    }
}
