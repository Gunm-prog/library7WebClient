package com.emilie.library7WebClient.Controllers;


import com.emilie.library7WebClient.Entities.Book;
import com.emilie.library7WebClient.Proxy.FeignProxy;
import com.emilie.library7WebClient.Services.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

//@RestController
@Controller
@RequestMapping("/book")
public class BookController {

    private static final String BOOK_LIST = "bookList";
    private static final String BOOK = "book";
    private static final String LIBRARY = "libraries";
    private static final String BOOK_SEARCH = "bookSearch";
    private static final String LIBRARY_LIST = "libraryList";
    private static final String LIBRARY_DETAILS_VIEW = "libraryDetails";
    private static final String CATALOG_VIEW = "catalog";
    private static final String BOOK_DETAILS_VIEW = "bookDetails";


    private final BookServiceImpl bookService;



    @Autowired
    public  BookController(BookServiceImpl bookService){
        this.bookService=bookService;

    }

    @GetMapping("/bookList")
    public String retrieveAllBooks(Model model) {
        model.addAttribute(BOOK_LIST, bookService.getBookList() );
        /*model.addAttribute( LIBRARY, feignProxy.getLibraryList() );*/

        /*return CATALOG_VIEW;*/
        return "bookList";
    }

   @GetMapping("/bookDetails/{id}")
    public String getById(@PathVariable("id") Long id, Model model){
        Book book = bookService.getBookById( id);
        model.addAttribute("book", book);

       return BOOK_DETAILS_VIEW;
   }


    @GetMapping("/libraryList")
    public String getAllLibraries(Model model){

        model.addAttribute( LIBRARY_LIST, bookService.getLibraryList() );

        return LIBRARY_LIST;
    }

    @GetMapping("/libraryDetails/{id}")
    public String getLibraryById (@PathVariable("id") Long id, Model model){
        model.addAttribute( LIBRARY_DETAILS_VIEW, bookService.getLibraryById( id ) );
        return LIBRARY_DETAILS_VIEW;
    }


}
