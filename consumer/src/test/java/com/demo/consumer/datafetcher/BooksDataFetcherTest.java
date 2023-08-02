package com.demo.consumer.datafetcher;

import com.demo.consumer.service.BookService;
import com.demo.consumer.types.Book;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {DgsAutoConfiguration.class, BooksDataFetcher.class})
class BooksDataFetcherTest {
    @Autowired
    DgsQueryExecutor dgsQueryExecutor;
    @MockBean
    BookService bookService;

    private final Book book1 = new Book("test book 1", 2021, UUID.randomUUID().toString());
    private final Book book2 = new Book("test book 2", 2022, UUID.randomUUID().toString());
    private final List<Book> books = List.of(book1, book2);

    @BeforeEach
    public void init() {
        when(bookService.getAllBooks()).thenAnswer(invocation -> books);
    }

    @Test
    void should_return_book_titles() {
        when(bookService.getAllBooks()).thenAnswer(invocation -> books);

        List<String> bookTitles = dgsQueryExecutor.executeAndExtractJsonPath(
                " { books { title releaseYear }}",
                "data.books[*].title");

        assertEquals(2, bookTitles.size());
    }

    @Test
    void should_return_book_releaseYears() {
        when(bookService.getAllBooks()).thenAnswer(invocation -> books);

        List<String> bookTitles = dgsQueryExecutor.executeAndExtractJsonPath(
                " { books { title releaseYear }}",
                "data.books[*].releaseYear");

        assertEquals(2, bookTitles.size());
    }
}