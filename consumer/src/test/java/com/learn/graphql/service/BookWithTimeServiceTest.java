package com.learn.graphql.service;

import com.learn.graphql.client.BookFeignClient;
import com.learn.graphql.client.response.BookResponseDTO;
import com.learn.graphql.entity.BookWithTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {BookWithTimeService.class})
class BookWithTimeServiceTest {
    @Autowired
    BookWithTimeService bookWithTimeService;
    @MockBean
    BookFeignClient bookFeignClient;

    private final BookResponseDTO book1 = new BookResponseDTO("test book 1", 2020, UUID.randomUUID());
    private final BookResponseDTO book2 = new BookResponseDTO("test book 2", 2021, UUID.randomUUID());
    private final BookResponseDTO book3 = new BookResponseDTO("test book 3", 2022, UUID.randomUUID());

    private final List<BookResponseDTO> books = List.of(book1, book2, book3);

    private final LocalDateTime time = LocalDateTime.now();

    @BeforeEach
    public void init() {
        try (MockedStatic<LocalDateTime> localDateTimeMockedStatic = Mockito.mockStatic(LocalDateTime.class)) {
            localDateTimeMockedStatic.when(LocalDateTime::now).thenReturn(time);
            Assertions.assertEquals(time, LocalDateTime.now());
        }
    }

    @Test
    void should_add_time_to_book() {
        when(bookFeignClient.getAllBooks()).thenReturn(books);

        List<BookWithTime> result = bookWithTimeService.addTimeForBooks();

        Assertions.assertEquals(3, result.size());
    }

}