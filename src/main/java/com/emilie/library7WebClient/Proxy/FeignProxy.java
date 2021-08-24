package com.emilie.library7WebClient.Proxy;


import com.emilie.library7WebClient.Entities.*;
import com.emilie.library7WebClient.Security.JwtProperties;
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

    @GetMapping("/api/v1/books/{id}")
    Book getById();

    @PostMapping("/api/v1/books/newBook")
    Book save();

    @GetMapping("/api/v1/books/search")
    List<Book> searchBooks(@RequestParam(value = "libraryId", required=false) Long libraryId,
                           @RequestParam(value = "title", required = false) String title,
                           @RequestParam(value="isbn", required=false) String isbn,
                           @RequestParam(value="firstname", required=false) String firstName,
                           @RequestParam(value="lastname", required=false) String lastName);


    /* ===Library ===*/
    @GetMapping("/api/v1/libraries/libraryList")
    List<Library> getLibraryList();

    @GetMapping("/api/v1/libraries/{id}")
    Library getLibraryById(@PathVariable Long id);

    /* ===User ===*/


    @PostMapping("/register/customer")
    ResponseEntity<?> newCustomerAccount(@RequestBody User newUser);

    @PostMapping("/register/employee")
    ResponseEntity<?> newEmployeeAccount(@RequestBody User newUser);

    @GetMapping("/api/v1/users/{id}")
    User getUserById(@PathVariable Long id);

    @PutMapping("/api/v1/users/update")
    User updateUser(@RequestHeader(JwtProperties.HEADER) String accessToken,@RequestBody User user );

    @PostMapping("/authenticate")
    String login(@RequestBody UserAccountLogin accountDto);

    @GetMapping("/api/v1/users/userAccount")
    User getLoggedUser(@RequestHeader(JwtProperties.HEADER) String accessToken);

    @PutMapping("/api/v1/loans/extendLoan/{id}")
    ResponseEntity<?> extendLoan(@PathVariable(value="id") Long id,
                                 @RequestHeader(JwtProperties.HEADER) String accessToken);



    /* ===Loan ===*/
   /* @GetMapping("api/v1/loans/loanList/{userId}/{userLoans}")
    List<Loan> findLoansByUserId(@RequestHeader(JwtProperties.HEADER) String accessToken,
                               @PathVariable("userId") Long id,
                               @PathVariable("userLoans") String userLoans);*/


}


