package com.demo.consumer.service;

import com.demo.consumer.client.BookFeignClient;
import com.demo.consumer.client.request.BookRequestDTO;
import com.demo.consumer.client.response.BookResponseDTO;
import com.demo.consumer.types.Book;
import com.demo.consumer.types.BookInput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookFeignClient bookFeignClient;

    public BookService(BookFeignClient bookFeignClient) {
        this.bookFeignClient = bookFeignClient;
    }

    public List<Book> getAllBooks() {
        return bookFeignClient.getAllBooks().stream().map(bookDTO ->
                new Book(bookDTO.getTitle(),
                        bookDTO.getReleaseYear(), bookDTO.getId())
        ).collect(Collectors.toList());
    }

    public Book addBook(BookInput bookInput) {
        BookResponseDTO bookResponse = bookFeignClient.addBook(BookRequestDTO.builder()
                .title(bookInput.getTitle())
                .releaseYear(bookInput.getReleaseYear())
                .build());
        return Book.newBuilder()
                .id(bookResponse.getId())
                .title(bookResponse.getTitle())
                .releaseYear(bookResponse.getReleaseYear())
                .build();
    }

}
