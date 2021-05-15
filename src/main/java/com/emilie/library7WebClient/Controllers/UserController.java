/*
package com.emilie.library7WebClient.Controllers;


import com.emilie.library7WebClient.Services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final String HOME_VIEW = "home";
    private static final String LOGIN_VIEW = "login";
    private static final String SIGN_UP_FORM_VIEW = "signUpForm";
    private static final String SIGN_UP_SUCCESS_VIEW = "signupSuccess";
    private static final String USER_ACCOUNT_VIEW = "userAccount";
    private static final String USER_ATT = "user";

    @Autowired
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService=userService;
    }


   */
/* @GetMapping("/signup")
    public String getSignupForm( Model model){
        model.addAttribute( USER_ATT , new );
    }
*//*

    @GetMapping("/userAccount/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model){
        model.addAttribute(USER_ACCOUNT_VIEW, userService.getUserById( id )  );
        return USER_ACCOUNT_VIEW;
    }
}
*/
