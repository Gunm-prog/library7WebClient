package com.emilie.library7WebClient.Controllers;

import com.emilie.library7WebClient.Entities.User;
import com.emilie.library7WebClient.Entities.UserAccountLogin;
import com.emilie.library7WebClient.Proxy.FeignProxy;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.emilie.library7WebClient.Security.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * @author gunmm
 */
@Controller
public class LoginController {

    private static final String LIBRARY_ATT = "libraries";
    private static final String USER_ATT = "user";

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
                            HttpServletResponse response, BindingResult bindingResult) {

        String jwtToken = proxy.login(userAccountLogin);
        System.out.println(jwtToken);
        if (jwtToken == null) {

            bindingResult.addError( new FieldError( USER_ATT, USERNAME_FIELD, BAD_CREDENTIALS_MSG ) );
            model.addAttribute( USER_ATT, userAccountLogin );

            return LOGIN_VIEW;
        }else {

            //Add token to response in a cookie
            Cookie cookie = JwtTokenUtils.generateCookie( jwtToken );
            response.addCookie( cookie );

            return REDIRECT_USER_HOME_VIEW;
        }

    }

    @GetMapping("/logout")
    public String logoutUser(HttpServletResponse response) {

        JwtTokenUtils.clear(response);

        return REDIRECT_LOGIN_VIEW;
    }

}
