package com.group.libraryapp.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    // domain 생성 시, 해당 Repository는 세트로 생성해준다.
}
