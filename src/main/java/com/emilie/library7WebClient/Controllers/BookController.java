package com.emilie.library7WebClient.Controllers;


import com.emilie.library7WebClient.Services.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    private static final String BOOK_LIST = "bookList";
    private static final String BOOK = "book";
    private static final String LIBRARY = "libraries";
    private static final String BOOK_SEARCH = "bookSearch";

    private static final String CATALOG_VIEW = "catalog";
    private static final String BOOK_DETAILS_VIEW = "bookDetails";


    private final BookServiceImpl bookService;


    @Autowired
    public  BookController(BookServiceImpl bookService){
        this.bookService=bookService;
    }

    @GetMapping("/")
    public String retrieveAllBooks(Model model) {
        model.addAttribute(BOOK_LIST, bookService.getBookList() );
        /*model.addAttribute( LIBRARY, feignProxy.getLibraryList() );*/

        /*return CATALOG_VIEW;*/
        return "index";
    }

  /* @GetMapping("/bookDetails")
    public String getById();*/


    @GetMapping("/libraries")
    public String retrieveAllLibraries(Model model){

        model.addAttribute( LIBRARY, bookService.getLibraryList() );

        return "libraryList";
    }




}
