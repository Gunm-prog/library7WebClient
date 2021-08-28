package com.emilie.library7WebClient.Controllers;

import com.emilie.library7WebClient.Proxy.FeignProxy;
import com.emilie.library7WebClient.Security.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoanController {


    private static final String REDIRECT_LOGIN_VIEW="redirect:/login";
    private static final String REDIRECT_USER_ACCOUNT_VIEW="redirect:/userAccount";

    private final FeignProxy feignProxy;

    @Autowired
    public LoanController(FeignProxy feignProxy) {
        this.feignProxy=feignProxy;
    }


    @PostMapping("/extendLoan/{id}")
    public String extendLoan(@PathVariable(value="id") Long id,
                             @CookieValue(value=JwtProperties.HEADER, required=false) String accessToken) {
        if (accessToken == null)
            return REDIRECT_LOGIN_VIEW;
        ResponseEntity<?> response=feignProxy.extendLoan( id, accessToken );
        return REDIRECT_USER_ACCOUNT_VIEW;
    }
}

