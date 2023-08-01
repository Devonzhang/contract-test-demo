package com.learn.graphql.client;

import com.learn.graphql.client.request.BookRequestDTO;
import com.learn.graphql.client.response.BookResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "book-api",
        url = "http://localhost:8081")
public interface BookFeignClient {
    @GetMapping("/books")
    List<BookResponseDTO> getAllBooks();

    @PostMapping("/books")
    BookResponseDTO addBook(BookRequestDTO book);
}
