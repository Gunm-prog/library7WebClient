package com.emilie.library7WebClient.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class Book {

    private Long id;
    private String title;
    private String isbn;
    private String summary;
    private Author authorDto;
    private Set<Copy> copies;


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", summary='" + summary + '\'' +
                ", author=" + authorDto +
                ", copies=" + copies +
                '}';
    }
}
