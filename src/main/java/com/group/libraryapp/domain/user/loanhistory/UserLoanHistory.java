package com.group.libraryapp.domain.user.loanhistory;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private long userId;

    private String bookName;

    private boolean isReturn; //boolean으로 처리 시, tinyint에 대해 잘 매핑이 된다!

    protected UserLoanHistory() {}

    public UserLoanHistory(long userId, String bookName, boolean isReturn) {
        this.userId = userId;
        this.bookName = bookName;
        this.isReturn = isReturn;
    }
}
