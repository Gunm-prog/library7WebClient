package com.emilie.library7WebClient.Services;

import com.emilie.library7WebClient.Entities.User;

public interface UserService {

    User getUserById(Long id);

    User getSignupForm();
}
