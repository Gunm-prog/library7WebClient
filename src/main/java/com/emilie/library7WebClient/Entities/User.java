package com.emilie.library7WebClient.Entities;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    private Long id;
    private String userName;
    private String email;
    private String password;
    private String lastName;
    private String firstName;
    private Long clientNumber;


}
