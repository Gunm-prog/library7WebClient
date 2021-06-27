package com.emilie.library7WebClient.Services;

import com.emilie.library7WebClient.Entities.Book;
import com.emilie.library7WebClient.Entities.Library;

import java.util.List;

public interface BookService {

    Book getBookById(Long id);

    List<Book> getBookList();

    List<Library> getLibraryList();


}
