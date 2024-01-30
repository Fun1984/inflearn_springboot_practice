package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id // 테이블에서 id에 해당함
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 테이블의 설정 : autoincrement
    private Long id = null;// bigint

    @Column(nullable = false, length = 20, name = "name") // name varchar(20) // db테이블 컬럼명, 각종 설정 적용.
    private String name;

    private Integer age; //굳이 조건이나 컬럼명이 다른 게 아니라면, @가 필요없다.

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) //(아래의) 내가 1이고, 너가 다수이다. //(mappedBy = "user") 주인이 아닌 쪽에 설정함. 주인을 설정해줘야 데이터가 저장됨. fetch = FetchType.EAGER를 쓰면 지연로딩 없이 다 가져옴.
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    protected User() {

    }

    public User(String name, Integer age) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다", name));
        }
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }

    public Integer getAge() {
        return age;
    }

    public Long getId() { return id; }

    public void updateName(String name) {
        this.name = name;
    }

    public void loanBook(String bookName) {
        this.userLoanHistories.add(new UserLoanHistory(this, bookName));
    }

    public void returnBook(String bookName) {
        UserLoanHistory targetHistory = this.userLoanHistories.stream()
                .filter(history -> history.getBookName().equals(bookName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        targetHistory.doReturn();
    }

}
