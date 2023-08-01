package com.demo.provider.service;

import com.demo.provider.dto.BookRequestDTO;
import com.demo.provider.entity.Book;
import com.demo.provider.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(BookRequestDTO bookRequestDTO) {
        return bookRepository.save(new Book(bookRequestDTO.getTitle(), bookRequestDTO.getReleaseYear(), UUID.randomUUID()));
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> findAllBooksByReleaseYearAfter(Integer year) {
        return bookRepository.findAllByReleaseYearAfter(year);
    }

    public List<Book> findBookByTitleRegex(String regex) {
        return bookRepository.findByTitleRegex(regex);
    }

    public Optional<Book> findBookById(String id) {
        return bookRepository.findById(UUID.fromString(id));
    }
}
