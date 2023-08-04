package com.demo.consumer.client;

import com.demo.consumer.client.request.BookRequestDTO;
import com.demo.consumer.client.response.BookResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "book-api",
        url = "${feign.url}")
public interface BookFeignClient {
    @GetMapping("/books")
    List<BookResponseDTO> getAllBooks();

    @PostMapping("/books")
    BookResponseDTO addBook(BookRequestDTO book);
}
