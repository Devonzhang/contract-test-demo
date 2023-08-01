package com.learn.graphql.datafetcher;

import com.learn.graphql.entity.BookWithTime;
import com.learn.graphql.service.BookWithTimeService;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {DgsAutoConfiguration.class, BooksDataFetcher.class})
class BooksDataFetcherTest {
    @Autowired
    DgsQueryExecutor dgsQueryExecutor;
    @MockBean
    BookWithTimeService bookWithTimeService;

    private final BookWithTime book1 = new BookWithTime("test book 1", 2021, UUID.randomUUID(), LocalDateTime.now());
    private final BookWithTime book2 = new BookWithTime("test book 2", 2022, UUID.randomUUID(), LocalDateTime.now());
    private final List<BookWithTime> books = List.of(book1, book2);

    @BeforeEach
    public void init() {
        when(bookWithTimeService.addTimeForBooks()).thenAnswer(invocation -> books);
    }

    @Test
    void should_return_book_titles() {
        when(bookWithTimeService.addTimeForBooks()).thenAnswer(invocation -> books);

        List<String> bookTitles = dgsQueryExecutor.executeAndExtractJsonPath(
                " { books { title releaseYear }}",
                "data.books[*].title");

        assertEquals(2, bookTitles.size());
    }

    @Test
    void should_return_book_releaseYears() {
        when(bookWithTimeService.addTimeForBooks()).thenAnswer(invocation -> books);

        List<String> bookTitles = dgsQueryExecutor.executeAndExtractJsonPath(
                " { books { title releaseYear }}",
                "data.books[*].releaseYear");

        assertEquals(2, bookTitles.size());
    }
}