package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Book;
import com.example.demo.Repository.BookRepositoryImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookRepositoryImpl bookRepository;

    @GetMapping("/books")
    public List<Book> index() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Optional<Book> show(@PathVariable Long id) {
        return bookRepository.findById(id);
    }

    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
        return bookRepository.findByKeyword(searchTerm);
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PostMapping("/books/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        Book bookToUpdate = bookRepository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return bookRepository.save(bookToUpdate);
    }

    @DeleteMapping("/books/{id}")
    public boolean delete(@PathVariable long id) {
        bookRepository.delete(id);
        return true;
    }
}