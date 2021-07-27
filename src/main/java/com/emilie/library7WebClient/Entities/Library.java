package com.emilie.library7WebClient.Entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class Library {

    private Long id;
    private String name;
    private String phoneNumber;
    private Set<Copy> copies;
    private Address addressDto;

}
