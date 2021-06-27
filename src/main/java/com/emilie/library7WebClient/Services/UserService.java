package com.emilie.library7WebClient.Services;

import com.emilie.library7WebClient.Entities.User;

public interface UserService {

    com.emilie.library7WebClient.model.Entities.user.User getUserById(Long id);

    User getSignupForm();


}
