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
//import org.springframework.security.access.prepost.PreAuthorize;

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

    @GetMapping("/registration/employee")
    public String getEmployeeRegisterForm(Model model) {

        model.addAttribute(USER_ATT, new User());
        return "registerEmployeeForm";
    }

    @PostMapping("/registration/customer")
    public String newCustomerAccount(@ModelAttribute(USER_ATT) User newUser, Model model){
        newUser.setUsername(newUser.getEmail());
        /*Address address = new Address();
        address.setNumber(2);
        address.setStreet("Allée des acacias");
        address.setZipCode("59160");
        address.setCity("Lomme");
        newUser.setAddress(address);*/
        ResponseEntity<?> response = proxy.newCustomerAccount(newUser);

        return REDIRECT_LOGIN_VIEW;
    }

    //TODO changer redirection vers futur dashboard ADMIN pour gérer les employés
    @PostMapping("/registration/employee")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public String newEmployeeAccount(@ModelAttribute(USER_ATT) User newUser, Model model){
        newUser.setUsername(newUser.getEmail());

        /*Address address = new Address();
        address.setNumber(2);
        address.setStreet("Allée des acacias");
        address.setZipCode("59160");
        address.setCity("Lomme");
        newUser.setAddress(address);*/

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

       /* System.out.println(accountDto);
        // Authenticate user and receive token
        String responseEntity;
        *//*ResponseEntity<?> responseEntity;*//*
        try {
            //try authentication
            responseEntity = proxy.login(accountDto);
        } catch (Exception e){
            //bad authentication
            bindingResult.addError(new FieldError(USER_ATT, USERNAME_FIELD, BAD_CREDENTIALS_MSG));
            model.addAttribute(USER_ATT, accountDto);
            return LOGIN_VIEW;
        }*/
        //good authentication

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



      /*  //extract token from responseEntity
        String jwtToken = responseEntity*//*.getHeaders().getFirst("Authorization").replace("Bearer ", "")*//*;
        System.out.println(jwtToken);
        // Add token to response in a cookie
        Cookie cookie = JwtTokenUtils.generateCookie(jwtToken);
        response.addCookie(cookie);*/

        /*return REDIRECT_USER_HOME_VIEW;*/
       /* model.addAttribute(LIBRARY_ATT, proxy.getLibraryList());
        // Authenticate user and receive token
        String jwtToken = proxy.login(user);
        if (jwtToken == null) {
            bindingResult.addError(new FieldError(USER_ATT, USERNAME_FIELD, BAD_CREDENTIALS_MSG));
            model.addAttribute(USER_ATT, user);
            return LOGIN_VIEW;
        } else {
            // Add token to response in a cookie
            Cookie cookie = JwtTokenUtils.generateCookie(jwtToken);
            response.addCookie(cookie);
            return REDIRECT_USER_HOME_VIEW;
        }*/

    }

    @GetMapping("/logout")
    public String logoutUser(HttpServletResponse response) {

        JwtTokenUtils.clear(response);

        return REDIRECT_LOGIN_VIEW;
    }

}
