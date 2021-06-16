package com.emilie.library7WebClient.model.Entities.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class Account {

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String password;


}


