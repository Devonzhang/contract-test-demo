package com.devon.learnnow.controller;

import com.devon.learnnow.dto.BookRequestDTO;
import com.devon.learnnow.entity.Book;
import com.devon.learnnow.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book addBook(@RequestBody BookRequestDTO bookRequestDTO) {
        return bookService.createBook(bookRequestDTO);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }
}
