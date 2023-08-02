package com.demo.consumer.service;

import com.demo.consumer.client.BookFeignClient;
import com.demo.consumer.types.Book;
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
                        bookDTO.getReleaseYear(), bookDTO.getId().toString())
        ).collect(Collectors.toList());
    }

}
