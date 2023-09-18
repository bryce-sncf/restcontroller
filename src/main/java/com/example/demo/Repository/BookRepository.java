package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.Book;

public interface BookRepository {

    Book save(Book book);

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Book update(Book book);

    void delete(Long id);

    List<Book> findByKeyword(String keyword);

}
