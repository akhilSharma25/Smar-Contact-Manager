package com.scm.helper;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SessionHelper {

    public  static  void removeMessage(){
        try {
            HttpSession httpSession=((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
               httpSession.removeAttribute("message");
        } catch (Exception e) {
            System.out.println("Session Helper"+e);
        }
    }

}
