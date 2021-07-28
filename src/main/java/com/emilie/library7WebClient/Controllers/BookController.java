package com.emilie.library7WebClient.Controllers;



import com.emilie.library7WebClient.Entities.Book;
import com.emilie.library7WebClient.Entities.Copy;
import com.emilie.library7WebClient.Entities.Library;
import com.emilie.library7WebClient.Proxy.FeignProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/books")
public class BookController {

    private static final String BOOK_LIST = "bookList";
    private static final String BOOK = "book";
    private static final String LIBRARY = "libraries";
    private static final String BOOK_SEARCH = "bookSearch";
    private static final String BOOK_SEARCH_RESULT= "bookSearchResult";
    private static final String LIBRARY_LIST = "libraryList";
    private static final String LIBRARY_DETAILS_VIEW = "libraryDetails";
    private static final String CATALOG_VIEW = "catalog";
    private static final String BOOK_DETAILS_VIEW = "bookDetails";
    private static final String REDIRECT_LOGIN_VIEW = "redirect:/login";
    private static final String COPY_ATT = "copy";

    private final FeignProxy feignProxy;


   @Autowired
    public  BookController(FeignProxy feignProxy){
       this.feignProxy=feignProxy;

   }

   @GetMapping("/searchCopies")
   public String BookSearch(Model model){
       model.addAttribute( "copy", new Copy() );
       return BOOK_SEARCH;
   }

   @GetMapping("/searchResult")
   public String BookSearchResult(@ModelAttribute(COPY_ATT) Copy copyInfo, Model model){
       //todo probleme, feign part avec le post methode de ce controller... même si il est declaré comme GET...
       String title = copyInfo.getBookDto().getTitle();
       String isbn = copyInfo.getBookDto().getIsbn();
       String firstName = copyInfo.getBookDto().getAuthorDto().getFirstName();
       String lastName = copyInfo.getBookDto().getAuthorDto().getLastName();
       model.addAttribute("copies", feignProxy.searchCopies(title, isbn, firstName, lastName) );

       return BOOK_SEARCH_RESULT;
   }


   @GetMapping("/details/{id}")
    public String getById(@PathVariable("id") Long id, Model model){
        Book book = feignProxy.getBookById( id);
       System.out.println(book );
        model.addAttribute("book", book);

       return BOOK_DETAILS_VIEW;
   }


}
