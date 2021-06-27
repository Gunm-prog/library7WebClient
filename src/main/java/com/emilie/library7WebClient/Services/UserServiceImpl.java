
package com.emilie.library7WebClient.Services;

import com.emilie.library7WebClient.Entities.User;
import com.emilie.library7WebClient.Proxy.FeignProxy;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{


    private final FeignProxy feignProxy;

    public UserServiceImpl(FeignProxy feignProxy) {
        this.feignProxy=feignProxy;
    }

    @Override
    public com.emilie.library7WebClient.model.Entities.user.User getUserById (Long id){
        return this.feignProxy.getUserById(id);
    }

    @Override
    public User getSignupForm() {
        return null;
    }


}

