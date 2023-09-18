package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Book;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private List<Book> books = new ArrayList<>();

    @Override
    public Book save(Book book) {
        book.setId((long) (books.size() + 1));
        books.add(book);
        return book;
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();
    }

    @Override
    public Book update(Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(book.getId())) {
                books.set(i, book);
                return book;
            }
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        books.removeIf(b -> b.getId().equals(id));
    }

    @Override
    public List<Book> findByKeyword(String keyword) {
        return books.stream()
                .filter(b -> b.getTitle().contains(keyword)
                        || b.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }

}