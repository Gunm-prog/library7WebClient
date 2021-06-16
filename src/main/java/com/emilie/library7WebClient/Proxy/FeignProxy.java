package com.emilie.library7WebClient.Proxy;

import com.emilie.library7WebClient.Entities.Book;
import com.emilie.library7WebClient.Entities.Library;
import com.emilie.library7WebClient.Entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

/*@FeignClient(name = "server-gateway", url = "localhost:9002")*/
@FeignClient(name = "bookFeignClient", url = "localhost:8181")
public interface FeignProxy {

    /* === No Authentication needed === */


    /* ===Book ===*/
    @GetMapping("/api/v1/books/{id}")
    Book getById(@PathVariable Long id);

    @GetMapping("/api/v1/books/catalog")
    List<Book> getBookList();

    @PostMapping("/api/v1/books/newBook")
    Book save();

    /* ===Library ===*/
    @GetMapping("/api/v1/libraries/libraryList")
    List<Library> getLibraryList();

    @GetMapping("/api/v1/libraries/{id}")
    Library getLibraryById(@PathVariable Long id);

    /* ===User ===*/

    @GetMapping("/api/v1/users/{id}")
    User getUserById(@PathVariable Long id);

    @PostMapping("/api/v1/users/login")


}


