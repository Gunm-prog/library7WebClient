package com.emilie.library7WebClient.Controllers;



import com.emilie.library7WebClient.Entities.Copy;
import com.emilie.library7WebClient.Proxy.FeignProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;


@Controller
@RequestMapping("/book")
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
   public String getBookSearch(Model model){
       model.addAttribute( "copy", new Copy() );
       return BOOK_SEARCH;
   }

   @PostMapping("/searchResult")
   public String getBookSearchResult(@ModelAttribute(COPY_ATT) Copy copyInfo, Model model){
       //todo probleme, feign part avec le post methode de ce controller... même si il est declaré comme GET...
      //model.addAttribute("copies", feignProxy.searchCopies(copyInfo) );

       //todo test a retirer
       ArrayList copies = new ArrayList();
      model.addAttribute("copies", copies );

       return BOOK_SEARCH_RESULT;
   }

  @GetMapping("/bookList")
    public String retrieveAllBooks(Model model) {
        model.addAttribute(BOOK_LIST, feignProxy.getBookList() );
        /*model.addAttribute( LIBRARY, feignProxy.getLibraryList() );*/

        /*return CATALOG_VIEW;*/
        return "bookList";
    }

   /*@GetMapping("/bookDetails/{id}")
    public String getById(@PathVariable("id") Long id, Model model){
        Book book = feignProxy.getBookById( id);
        model.addAttribute("book", book);

       return BOOK_DETAILS_VIEW;
   }

    @GetMapping("/libraryList")
    public String getAllLibraries(Model model){

        model.addAttribute( LIBRARY_LIST, feignProxy.getLibraryList() );

        return LIBRARY_LIST;
    }

    @GetMapping("/libraryDetails/{id}")
    public String getLibraryById (@PathVariable("id") Long id, Model model){
        model.addAttribute( LIBRARY_DETAILS_VIEW, feignProxy.getLibraryById( id ) );
        return LIBRARY_DETAILS_VIEW;
    }*/
}
