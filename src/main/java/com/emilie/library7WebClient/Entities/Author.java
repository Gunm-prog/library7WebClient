package com.emilie.library7WebClient.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class Author {

    private Long authorId;
    private String firstName;
    private String lastName;
    private Set<Book> books;
}
