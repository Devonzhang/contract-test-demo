package com.learn.graphql.client;

import com.learn.graphql.dto.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "book-api",
        url = "http://localhost:8081")
public interface BookFeignClient {
    @GetMapping("/books")
    List<BookDTO> getAllBooks();
}
