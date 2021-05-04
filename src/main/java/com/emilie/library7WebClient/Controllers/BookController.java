package com.emilie.library7WebClient.Controllers;

import com.emilie.library7WebClient.Proxy.FeignProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {

    private static final String BOOK_LIST = "bookList";
    private static final String BOOK = "book";
    private static final String LIBRARY = "libraries";
    private static final String BOOK_SEARCH = "bookSearch";

    private static final String CATALOG_VIEW = "catalog";
    private static final String BOOK_DETAILS_VIEW = "bookDetails";

    private final FeignProxy feignProxy;


    @Autowired
    public  BookController(FeignProxy feignProxy){ this.feignProxy = feignProxy;}

    @GetMapping("/index")
    public String retrieveAllBooks(Model model){

        model.addAttribute(BOOK_LIST, feignProxy.getBookList() );
        model.addAttribute( LIBRARY, feignProxy.getLibraryList() );

        /*return CATALOG_VIEW;*/
        return "index";
    }




}
