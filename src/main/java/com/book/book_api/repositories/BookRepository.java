package com.book.book_api.repositories;

import com.book.book_api.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BookRepository extends JpaRepository<Book,Long> {
}
