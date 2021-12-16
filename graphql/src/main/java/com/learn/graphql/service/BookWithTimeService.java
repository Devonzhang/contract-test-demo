package com.learn.graphql.service;

import com.learn.graphql.client.BookFeignClient;
import com.learn.graphql.entity.BookWithTime;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookWithTimeService {
    private final BookFeignClient bookFeignClient;

    public BookWithTimeService(BookFeignClient bookFeignClient) {
        this.bookFeignClient = bookFeignClient;
    }

    public List<BookWithTime> addTimeForBooks() {
        return bookFeignClient.getAllBooks().stream().map(bookDTO ->
                new BookWithTime(bookDTO.getTitle(),
                        bookDTO.getReleaseYear(), bookDTO.getId(),
                        LocalDateTime.now())
        ).collect(Collectors.toList());
    }
}
