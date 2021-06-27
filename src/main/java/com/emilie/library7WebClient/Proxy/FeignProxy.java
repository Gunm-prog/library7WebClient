package com.emilie.library7WebClient.Proxy;

import com.emilie.library7WebClient.Entities.Book;
import com.emilie.library7WebClient.Entities.Library;
import com.emilie.library7WebClient.model.Entities.user.User;
import com.emilie.library7WebClient.Security.JwtProperties;
import com.emilie.library7WebClient.model.Entities.loan.Loan;
import com.emilie.library7WebClient.model.Entities.user.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/*@FeignClient(name = "server-gateway", url = "localhost:9002")*/
@FeignClient(name = "bookFeignClient", url = "localhost:8181")
public interface FeignProxy {

    /* === No Authentication needed === */


    /* ===Book ===*/
    @GetMapping("/api/v1/books/{id}")
    Book getBookById(@PathVariable Long id/*, @RequestHeader("Authorization") String accessToken*/);

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

    @PostMapping("/authenticate")
    String login(@RequestBody AccountDto accountDto);

    @GetMapping("api/v1/users/userAccount")
    User getLoggedUser(@RequestHeader(JwtProperties.HEADER) String accessToken);


    /* ===Loan ===*/
   /* @GetMapping("api/v1/loans/loanList/{userId}/{userLoans}")
    List<Loan> findLoansByUserId(@RequestHeader(JwtProperties.HEADER) String accessToken,
                               @PathVariable("userId") Long id,
                               @PathVariable("userLoans") String userLoans);*/


}


