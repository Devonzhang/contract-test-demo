package com.demo.provider.service;

import com.demo.provider.dto.BookRequestDTO;
import com.demo.provider.dto.BookResponseDTO;
import com.demo.provider.entity.Book;
import com.demo.provider.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        Book book = bookRepository.save(new Book(bookRequestDTO.getTitle(), bookRequestDTO.getReleaseYear(), UUID.randomUUID()));
        return BookResponseDTO.builder()
//                .id(book.getId())
                .releaseYear(book.getReleaseYear())
                .title(book.getTitle())
                .build();
    }

    public List<BookResponseDTO> findAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> BookResponseDTO.builder()
//                        .id(book.getId())
                        .title(book.getTitle())
                        .releaseYear(book.getReleaseYear())
                        .build())
                .collect(Collectors.toList());
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
