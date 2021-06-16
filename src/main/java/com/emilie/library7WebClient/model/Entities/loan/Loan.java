package com.emilie.library7WebClient.model.Entities.loan;

import com.emilie.library7WebClient.Entities.Copy;
import com.emilie.library7WebClient.Entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Loan {

    private Long id;
    private Date loanStartDate;
    private Date loanEndDate;
    private boolean extended;
    private Copy copy;
    private User user;
}