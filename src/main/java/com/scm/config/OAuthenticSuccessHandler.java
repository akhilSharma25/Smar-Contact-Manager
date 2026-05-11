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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

//@Component
//public class OAuthenticSuccessHandler implements AuthenticationSuccessHandler {
//    @Autowired
//    private UserRepo repo;


//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        org.springframework.security.oauth2.core.user.DefaultOAuth2User user =(DefaultOAuth2User)authentication.getPrincipal();
//        String email=user.getAttribute("email").toString();
//        String name=user.getAttribute("name").toString();
//        String picture=user.getAttribute("name").toString();
//
////        create user and save in database
//
//        User user1=new User();
//        user1.setEmail(email);
//        user1.setName(name);
//        user1.setProfilePic(picture);
//        user1.setPassword("password");
//        user1.setUserId(UUID.randomUUID().toString());
//        user1.setProvider(Providers.GOOGLE);
//        user1.setEnabled(true);
//        user1.setEmailVerified(true);
//        user1.setProviderUserId(name);
//        user1.setRoleList(List.of(AppConstants.ROLE_USER));
//        user1.setAbout("This account created is using google");
//
//       User user2= repo.findByEmail(email).orElse(null);
//       if(user2==null){
//           repo.save(user1);
//
//       }
//
//        new DefaultRedirectStrategy().sendRedirect(request,response,"/user/profile");
//    }
//}


@Component
public class OAuthenticSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepo repo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("OAuthenticSuccessHandler: Authentication Successful!");
        // 1. Identify the Provider (Google or GitHub)
        var oauth2User = (DefaultOAuth2User) authentication.getPrincipal();
        String authorizedClientRegistrationId = ((org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId();

//        System.out.println(authorizedClientRegistrationId);
        User userEntity = new User();
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setRoleList(List.of(AppConstants.ROLE_USER));
        userEntity.setEmailVerified(true);
        userEntity.setEnabled(true);
        userEntity.setPassword(new BCryptPasswordEncoder().encode("dummy"));        // 2. Extract Data based on Provider
        if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {
            // Google Logic
            userEntity.setEmail(oauth2User.getAttribute("email").toString());
            userEntity.setName(oauth2User.getAttribute("name").toString());
            userEntity.setProfilePic(oauth2User.getAttribute("picture").toString());
            userEntity.setProviderUserId(oauth2User.getName());
            userEntity.setProvider(Providers.GOOGLE);
            userEntity.setAbout("Account created using Google");
            System.out.println("GOOGLE");


        } else if (authorizedClientRegistrationId.equalsIgnoreCase("github")) {
//            System.out.println(oauth2User.getAttributes());

            Object emailObj = oauth2User.getAttribute("email");
            Object loginObj = oauth2User.getAttribute("login");
            Object avatarObj = oauth2User.getAttribute("avatar_url");
            if (avatarObj != null) {
                userEntity.setProfilePic(avatarObj.toString());
            } else {
                // Agar kuch na mile toh ek default path set kar do
                userEntity.setProfilePic("/images/default-profile.png");
            }
            String email;

            if (emailObj != null) {
                email = emailObj.toString();
            } else {
                email = loginObj.toString() + "@github.com";
            }

            userEntity.setEmail(email);

            if (loginObj != null) {
                userEntity.setName(loginObj.toString());
            }

            if (avatarObj != null) {
                userEntity.setProfilePic(avatarObj.toString());
            }

            userEntity.setProviderUserId(oauth2User.getName());
            userEntity.setProvider(Providers.GITHUB);
            userEntity.setAbout("Account created using GitHub");

        }

        // 3. Save only if user doesn't exist
        User existingUser = repo.findByEmail(userEntity.getEmail()).orElse(null);
        if (existingUser == null) {
            repo.save(userEntity);
        }

        // 4. Redirect to Dashboard
        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }
}