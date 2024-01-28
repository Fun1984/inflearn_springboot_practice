package com.group.libraryapp.service.book;

import com.group.libraryapp.repository.book.BookMemoryRepository;
import com.group.libraryapp.repository.book.BookMySqlRepository;
import com.group.libraryapp.repository.book.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 인스턴스화든, 추상화든 결국은 한 번 수정하게 되면은 파일갯수만큼 증가한다. 이를 방지하기 위한 것이 스프링 컨테이너임.
    public void saveBook() {
        bookRepository.saveBook();
    }
}
