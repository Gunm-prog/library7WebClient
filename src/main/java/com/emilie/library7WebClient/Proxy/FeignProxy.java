package com.emilie.library7WebClient.Proxy;

import com.emilie.library7WebClient.Entities.Book;
import com.emilie.library7WebClient.Entities.Library;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/*@FeignClient(name = "server-gateway", url = "localhost:9002")*/
@FeignClient(name = "lib7", url = "localhost:9002")
public interface FeignProxy {

    /* === No Authentication needed === */

    @GetMapping("/lib7/api/v1/books/{id}")
    List<Book> getById();

    @GetMapping("/lib7/api/v1/books/catalog")
    List<Book> getBookList();

    @GetMapping("/lib7/api/v1/books/newBook")
    List<Book> save();

    @GetMapping("/lib7/api/v1/libraries/libraryList")
    List<Library> getLibraryList();





}




   /* *//* ================================ *//*

    *//* ================================ *//*

    @GetMapping("/ms-book/consult/book-catalog")
    List<BookBean> listAllBook();

    @GetMapping("/ms-book/consult/book-last")
    List<BookBean> getLastRegisteredBook();

    @GetMapping("/ms-book/consult/search-result")
    List<BookBean> listSearchResult(@SpringQueryMap BookSearchAttribut searchAttribut);

    @GetMapping("/ms-book/consult/{library}/book-catalog")
    List<BookBean> listAllBookOfLibrary(@PathVariable("library") String library);

    @GetMapping("/ms-book/consult/{library}/book-last")
    List<BookBean> getLastRegisteredBookOfLibrary(@PathVariable("library") String library);

    @GetMapping("/ms-book/consult/libraries")
    List<LibraryWithoutBookBean> listAllLibrary();

    @GetMapping("/ms-book/consult/{libraryId}/book/{bookId}")
    BookBean getBookDetail(@PathVariable("bookId") int bookId,
                           @PathVariable("libraryId") int libraryId);

    @PostMapping("/ms-authentication/login")
    String doLogin(@RequestBody UserBean userBean);

    *//* =============================== *//*
    *//* ==== Authentication needed ==== *//*
    *//* =============================== *//*

    @GetMapping("/ms-account/consult/user-info")
    AccountBean getAccountInfo(@RequestHeader(CommonSecurityConfig.HEADER) String accessToken);

    @GetMapping("/ms-loan/consult/loans/{userId}/{loanProperty}")
    List<LoanBean> getUserLoanList(@RequestHeader(CommonSecurityConfig.HEADER) String accessToken,
                                   @PathVariable("userId") int userId,
                                   @PathVariable("loanProperty") String loanProperty);

    @GetMapping("/ms-loan/edit/extend/loan/{loanId}")
    LoanBean extendLoanExpectedReturnDate(
            @RequestHeader(CommonSecurityConfig.HEADER) String accessToken,
            @PathVariable("loanId") int loanId);

    @PostMapping("/ms-consistency-manager/edit/account")
    AccountBean editAccount(@RequestHeader(CommonSecurityConfig.HEADER) String accessToken,
                            @RequestBody AccountBean accountBean);

    @PostMapping("/ms-authentication/refresh")
    String doUpdateToken(@RequestHeader(CommonSecurityConfig.HEADER) String accessToken,
                         @RequestBody UserBean userUpdated);
}

*/