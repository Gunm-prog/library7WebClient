/*
package com.emilie.library7WebClient.Services;

import com.emilie.library7WebClient.Entities.Book;
import com.emilie.library7WebClient.Entities.User;
import com.emilie.library7WebClient.Proxy.FeignProxy;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{


    private final FeignProxy feignProxy;

    public UserServiceImpl(FeignProxy feignProxy) {
        this.feignProxy=feignProxy;
    }

    public User getUserById (Long id){
        return this.feignProxy.getUserById(id);
    }
}
*/
