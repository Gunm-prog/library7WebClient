/*
package com.emilie.library7WebClient.Proxy;


import com.emilie.library7WebClient.Entities.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="bookService"*/
/*, fallbackFactory = UserClientFallbackFactory.class*//*
)
public interface BookClient {

    @RequestMapping(
            method= RequestMethod.GET,
            path = "/lib7/api/v1/books/catalog")
    List<Book> getBookList();

}*/
