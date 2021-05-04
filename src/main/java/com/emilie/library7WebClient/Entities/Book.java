package com.emilie.library7WebClient.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class Book {

    private Long bookId;
    private String title;
    private String isbn;
    private String summary;
    private Author author;
    private Set<Copy> copies;
}
