package com.emilie.library7WebClient.Controllers;


import com.emilie.library7WebClient.Proxy.FeignProxy;
import com.emilie.library7WebClient.Security.JwtProperties;
import com.emilie.library7WebClient.Entities.User;
import com.emilie.library7WebClient.Security.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CookieValue;

@Controller
public class UserController {

    private static final String HOME_VIEW = "home";
    private static final String LOGIN_VIEW = "login";
    private static final String LOAN_LIST_ATT = "loans";
    private static final String SIGN_UP_FORM_VIEW = "registerForm";
    private static final String REDIRECT_LOGIN_VIEW = "redirect:/login";
    private static final String USER_ACCOUNT_VIEW = "userAccount";
    private static final String USER_ATT = "user";

    private final FeignProxy feignProxy;

    @Autowired
    public UserController(FeignProxy feignProxy ) {
        this.feignProxy=feignProxy;
    }

    @GetMapping("/test")
    public String test(){
        return USER_ACCOUNT_VIEW;
    }


    @GetMapping("/userAccount")
    public String userAccount(@CookieValue(value=JwtProperties.HEADER, required=false) String accessToken, Model model){

        if (accessToken == null) return REDIRECT_LOGIN_VIEW;
        User user = feignProxy.getLoggedUser( accessToken );
        System.out.println(user);
        model.addAttribute("userInfos", user );
        model.addAttribute( "currentUserId", JwtTokenUtils.getUserIdFromJWT( accessToken ) );
        return USER_ACCOUNT_VIEW;


    }
}

