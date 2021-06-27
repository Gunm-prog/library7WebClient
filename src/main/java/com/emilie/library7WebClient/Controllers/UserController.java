package com.emilie.library7WebClient.Controllers;


import com.emilie.library7WebClient.Proxy.FeignProxy;
import com.emilie.library7WebClient.Security.JwtProperties;
import com.emilie.library7WebClient.Services.UserService;
import com.emilie.library7WebClient.model.Entities.user.User;
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
    private final FeignProxy proxy;



    @Autowired
    public UserController(FeignProxy proxy) { this.proxy = proxy;}

    @GetMapping("/test")
    public String test(){
        return USER_ACCOUNT_VIEW;
    }

    @GetMapping("/userAccount")
    public String userAccount(@CookieValue(value=JwtProperties.HEADER, required=false) String accessToken, Model model){
        System.out.println("userAccountController");
        System.out.println(JwtProperties.HEADER);
        System.out.println(accessToken);
        if (accessToken == null) return REDIRECT_LOGIN_VIEW;
        User user = proxy.getLoggedUser( accessToken );
        model.addAttribute("userInfos", user );
        return USER_ACCOUNT_VIEW;

        /*List<LoanBean> userLoans = proxy.getUserLoanList(accessToken,accountInfo.getId(),loanProperty);*/

    }
}

 /* @Autowired
    private final UserService userService;*/


    /*public UserController(UserService userService) {
        this.userService=userService;
    }
*/


/* @GetMapping("/register")
    public String getSignupForm( Model model){
        model.addAttribute( USER_ATT , new );
    }
*/

   /* @GetMapping("/userAccount")
    public String getUserById( Model model){
        model.addAttribute(USER_ACCOUNT_VIEW, userService.getUserById( id )  );
        return USER_ACCOUNT_VIEW;
    }*/