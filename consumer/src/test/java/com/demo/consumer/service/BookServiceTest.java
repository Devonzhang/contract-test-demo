package com.demo.consumer.service;

import com.demo.consumer.client.BookFeignClient;
import com.demo.consumer.client.request.BookRequestDTO;
import com.demo.consumer.client.response.BookResponseDTO;
import com.demo.consumer.types.Book;
import com.demo.consumer.types.BookInput;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {BookService.class})
class BookServiceTest {
    @Autowired
    BookService bookService;
    @MockBean
    BookFeignClient bookFeignClient;

    private final BookResponseDTO book1 = new BookResponseDTO("test book 1", 2020, UUID.randomUUID().toString());
    private final BookResponseDTO book2 = new BookResponseDTO("test book 2", 2021, UUID.randomUUID().toString());
    private final BookResponseDTO book3 = new BookResponseDTO("test book 3", 2022, UUID.randomUUID().toString());

    private final List<BookResponseDTO> books = List.of(book1, book2, book3);

    @Test
    void should_get_all_books() {
        when(bookFeignClient.getAllBooks()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertThat(result).hasSize(books.size());
    }

    @Test
    void should_add_book() {
        BookInput bookInput = BookInput.newBuilder()
                .title(book1.getTitle())
                .releaseYear(book1.getReleaseYear())
                .build();
        BookRequestDTO bookRequest = new BookRequestDTO(bookInput.getTitle(), bookInput.getReleaseYear());
        when(bookFeignClient.addBook(bookRequest)).thenReturn(book1);

        Book book = bookService.addBook(bookInput);

        AssertionsForClassTypes.assertThat(book.getId()).isEqualTo(book1.getId());
        AssertionsForClassTypes.assertThat(book.getTitle()).isEqualTo(book1.getTitle());
        AssertionsForClassTypes.assertThat(book.getReleaseYear()).isEqualTo(book1.getReleaseYear());
    }
}