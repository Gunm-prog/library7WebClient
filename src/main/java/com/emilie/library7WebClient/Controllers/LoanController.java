/*
package com.emilie.library7WebClient.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class LoanController {

    private LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService=loanService;
    }

    @Autowired
    public void LoanController(LoanService loanService){
        this.loanService = loanService;

        @GetMapping("/listAllLoans")
        public List<Loans> listAllLoans(){
            return loanService.getLoanList();
        }

        @GetMapping("/loans/{userId}/{userLoans}")
        public List<Loans> LoansList(@PathVariable Long id, @PathVariable String loans){
            return loanService.getLoanList(userId, loans);

        }


    }
}
*/
