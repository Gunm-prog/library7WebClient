package com.emilie.library7WebClient.Controllers;


import com.emilie.library7WebClient.Entities.Book;
import com.emilie.library7WebClient.Entities.Copy;
import com.emilie.library7WebClient.Entities.Library;
import com.emilie.library7WebClient.Proxy.FeignProxy;
import com.emilie.library7WebClient.Security.JwtProperties;
import com.emilie.library7WebClient.Security.JwtTokenUtils;
import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
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
   public String BookSearch(@CookieValue(value=JwtProperties.HEADER, required=false) String accessToken, Model model){
       if (accessToken != null){
           int userId =JwtTokenUtils.getUserIdFromJWT( accessToken );
           model.addAttribute( "currentUserId", userId );
       }
       model.addAttribute( "copy", new Copy() );
       List<Library> libraries = feignProxy.getLibraryList();
       model.addAttribute( "libraries", libraries );
       return BOOK_SEARCH;
   }


   @GetMapping("/searchResult")
   public String BookSearchResult(@CookieValue(value=JwtProperties.HEADER, required=false) String accessToken, @ModelAttribute(COPY_ATT) Copy copyInfo, Model model){
       if (accessToken != null){
           int userId = JwtTokenUtils.getUserIdFromJWT( accessToken );
           model.addAttribute( "currentUserId", userId );
       }
       Long libraryId = copyInfo.getLibraryDto().getLibraryId();
       String title = !copyInfo.getBookDto().getTitle().equals("") ? copyInfo.getBookDto().getTitle() : null ; //comme un bloc if else
       String isbn = !copyInfo.getBookDto().getIsbn().equals("") ? copyInfo.getBookDto().getIsbn() : null;
       String firstName = !copyInfo.getBookDto().getAuthorDto().getFirstName().equals("") ? copyInfo.getBookDto().getAuthorDto().getFirstName() : null;
       String lastName = !copyInfo.getBookDto().getAuthorDto().getLastName().equals("") ? copyInfo.getBookDto().getAuthorDto().getLastName() : null;
       List<Book> books = feignProxy.searchBooks(libraryId,title, isbn, firstName, lastName);


       for(Book book : books){
           for(Copy copy : book.getCopyDtos()){
               boolean isAlreadyListed = false;
               int libKey = 0;
               for (int i = 0; i<book.getLibraries().size(); i++){
                   if (book.getLibraries().get( i ).getLibraryId().equals( copy.getLibraryDto().getLibraryId() )){
                       isAlreadyListed = true;
                       libKey = i;
                   }
               }
               if (!isAlreadyListed){
                   Library library1 = new Library();
                   library1.setLibraryId( copy.getLibraryDto().getLibraryId() );
                   library1.setName( copy.getLibraryDto().getName() );
                   book.getLibraries().add( library1 );
                   libKey = book.getLibraries().lastIndexOf(library1);
               }

               Library lib = book.getLibraries().get(libKey);
               if (copy.isAvailable()){
                   lib.setNbAvailableCopies( lib.getNbAvailableCopies()+1 );
               }
               lib.setNbCopies( lib.getNbCopies() + 1 );
           }
       }

       model.addAttribute("books", books );

       return BOOK_SEARCH_RESULT;
   }


   @GetMapping("/details/{id}")
    public String getById(@CookieValue(value=JwtProperties.HEADER, required=false) String accessToken, @PathVariable("id") Long id, Model model){
       if (accessToken != null){
           int userId = JwtTokenUtils.getUserIdFromJWT( accessToken );
           model.addAttribute( "currentUserId", userId );
       }
       Book book = feignProxy.getBookById( id);
       System.out.println(book );
        model.addAttribute("book", book);

       return BOOK_DETAILS_VIEW;
   }


}
