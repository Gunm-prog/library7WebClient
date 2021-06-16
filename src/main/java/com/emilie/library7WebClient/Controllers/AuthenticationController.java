/*
package com.emilie.library7WebClient.Controllers;

import com.emilie.library7WebClient.Entities.User;
import com.emilie.library7WebClient.Entities.UserAccountLogin;
import com.emilie.library7WebClient.Proxy.FeignProxy;
import com.emilie.library7WebClient.Security.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AuthenticationController {

    private final FeignProxy feignProxy;
    private static final String LOGIN_VIEW = "login";
    private static final String HOME_VIEW = "home";
    private static final String REGISTRATION_VIEW = "signIn";
    private static final String REDIRECT_LOGIN_VIEW = "redirect:/login";
    private static final String REDIRECT_HOME_VIEW = "redirect:/home";
    private static final String USER_ATT = "user";
    private static final String USERNAME_FIELD = "username";
    private static final String BAD_CREDENTIALS_MSG = "Mauvais login/mot de passe";

    @Autowired
    public AuthenticationController(FeignProxy feignProxy) {
        this.feignProxy = feignProxy;
    }


}

*/
