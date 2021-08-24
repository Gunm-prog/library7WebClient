package com.emilie.library7WebClient.Controllers;

import com.emilie.library7WebClient.Entities.User;
import com.emilie.library7WebClient.Entities.UserAccountLogin;
import com.emilie.library7WebClient.Proxy.FeignProxy;
import com.emilie.library7WebClient.Security.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


/**
 * @author gunmm
 */
@Controller
public class LoginController {

    private static final String LIBRARY_ATT = "libraries";
    private static final String USER_ATT = "user";

    private static final String CUSTOMER_ATT = "customer";
    private static final String USERNAME_FIELD = "username";
    private static final String BAD_CREDENTIALS_MSG = "Mauvais login/mot de passe";

    private static final String LOGIN_VIEW = "login";
    private static final String REDIRECT_LOGIN_VIEW = "redirect:/login";
    private static final String REDIRECT_USER_HOME_VIEW = "redirect:/userAccount";

    private final FeignProxy proxy;

    @Autowired
    public LoginController(FeignProxy proxy) {
        this.proxy = proxy;
    }

    @GetMapping("/registration/customer")
    public String getCustomerRegisterForm(Model model) {
        model.addAttribute(USER_ATT, new User());
        return "registerCustomerForm";
    }

    @PostMapping("/registration/customer")
    public String newCustomerAccount(@ModelAttribute(USER_ATT) User newUser){
        newUser.setUsername(newUser.getEmail());
        ResponseEntity<?> response = proxy.newCustomerAccount(newUser);

        return REDIRECT_LOGIN_VIEW;
    }


    @GetMapping(path="/login")
    public String loginForm(Model model) {

        /*model.addAttribute(LIBRARY_ATT, proxy*//**//*.getLibraryList());*/
        model.addAttribute(USER_ATT, new User());

        return LOGIN_VIEW;

    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute(USER_ATT) UserAccountLogin userAccountLogin, Model model,
                            HttpServletResponse response/*, BindingResult bindingResult*/) {
/*
        if (bindingResult.hasErrors()){
            bindingResult.addError(new FieldError(CUSTOMER_ATT, USERNAME_FIELD, BAD_CREDENTIALS_MSG));
            model.addAttribute("user", accountLoginDto);
            return LOGIN_VIEW;
        }
        ResponseEntity responseEntity;
        try {
            responseEntity = feignProxy.login(accountLoginDto);
        } catch (Exception e){
            model.addAttribute("user", accountLoginDto);
            return LOGIN_VIEW;
        }
 */
        /*if (bindingResult.hasErrors()){
            bindingResult.addError(new FieldError(CUSTOMER_ATT, USERNAME_FIELD, BAD_CREDENTIALS_MSG));
            model.addAttribute("user", userAccountLogin);
            return LOGIN_VIEW;
        }*/
        try {
            String jwtToken = proxy.login(userAccountLogin);
            Cookie cookie = JwtTokenUtils.generateCookie( jwtToken );
            cookie.setMaxAge(3600);
            response.addCookie( cookie );

            return REDIRECT_USER_HOME_VIEW;
        } catch (Exception e){
            model.addAttribute( "error", "Mauvais login/mot de pass" );
            model.addAttribute("user", userAccountLogin);
            return LOGIN_VIEW;
        }

      /*  String jwtToken = proxy.login(userAccountLogin);
        if (jwtToken == null) {

            bindingResult.addError( new FieldError( USER_ATT, USERNAME_FIELD, BAD_CREDENTIALS_MSG ) );
            model.addAttribute( USER_ATT, userAccountLogin );

            return LOGIN_VIEW;
        }else {*/

            //Add token to response in a cookie
           /* Cookie cookie = JwtTokenUtils.generateCookie( jwtToken );
            cookie.setMaxAge(3600);
            response.addCookie( cookie );

            return REDIRECT_USER_HOME_VIEW;*/
      /*  }*/

    }

    @GetMapping("/logout")
    public String logoutUser(HttpServletResponse response) {

        JwtTokenUtils.clear(response);

        return REDIRECT_LOGIN_VIEW;
    }

}
