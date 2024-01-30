package com.group.libraryapp.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    // domain 생성 시, 해당 Repository는 세트로 생성해준다.

    Optional<Book> findByName(String name);
}
