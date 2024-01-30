package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @JoinColumn(nullable = false)
    @ManyToOne //(아래의) 내가 다수이고, 너가 한 개라는 의미 (N:1 관계)
    private User user;

    private String bookName;

    private boolean isReturn; //boolean으로 처리 시, tinyint에 대해 잘 매핑이 된다!

    protected UserLoanHistory() {}

    public UserLoanHistory(User user, String bookName, boolean isReturn) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = isReturn;
    }

    public void doReturn() {
        this.isReturn = true;
    }
}
