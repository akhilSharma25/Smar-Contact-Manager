package com.scm.config;

import com.scm.model.User;
import com.scm.services.impl.SecurityCustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    //user create and login using java code with in memory service
//    mock users without a database:
//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        org.springframework.security.core.userdetails.UserDetails user1 =User.withDefaultPasswordEncoder().username("admin").password("admin123").roles("ADMIN","USER").build();
//        org.springframework.security.core.userdetails.UserDetails user2 =User.withDefaultPasswordEncoder().username("user1").password("user1").roles("USER").build();
//        return  new InMemoryUserDetailsManager(user1,user2);
//    }

    @Autowired
    private SecurityCustomUserDetailService userDetailservice;


    //config of authenticaiton provider
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
        //user detail service ka object
        dao.setUserDetailsService(userDetailservice);
        //password encoder  ka object

        dao.setPasswordEncoder(passwordEncoder());
        return  dao;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests((auth)->{
//            auth.requestMatchers("/home","/signup","/login","/services").permitAll();
            auth.requestMatchers("/user/**").authenticated();
            auth.anyRequest().permitAll();
        });

        //form default login
        httpSecurity.formLogin(Customizer.withDefaults());

       return  httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return  new BCryptPasswordEncoder();
    }


}
