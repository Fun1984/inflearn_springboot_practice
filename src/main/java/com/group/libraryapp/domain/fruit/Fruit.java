package com.group.libraryapp.domain.fruit;

import jakarta.persistence.*;

// MyBatis이기에 @Entity가 불필요
@Entity
public class Fruit {
    @Id // 테이블에서 id에 해당함
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected Fruit() {}

    public Fruit(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Column(nullable = false, length = 20, name = "name")
    private String name;

    private int price;

    public String getName() {return name;}
    public int getPrice() {return price;}
}
