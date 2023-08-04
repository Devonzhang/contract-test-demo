package com.demo.consumer.datamutator;

import com.demo.consumer.datafetcher.BooksDataFetcher;
import com.demo.consumer.service.BookService;
import com.demo.consumer.types.Book;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {DgsAutoConfiguration.class, BooksDataFetcher.class})
class BookDataMutatorTest {

    @Autowired
    DgsQueryExecutor dgsQueryExecutor;
    @MockBean
    BookService bookService;

    private final Book book = new Book("test book", 2021, UUID.randomUUID().toString());

    @Test
    void should_return_added_book() {
        when(bookService.addBook(any())).thenAnswer(invocation -> book);

        String bookAdded = dgsQueryExecutor.executeAndExtractJsonPath("mutation($bookInput:BookInput){\n" +
                        "    addBook(bookInput: $bookInput){\n" +
                        "        id\n" +
                        "        title\n" +
                        "        releaseYear\n" +
                        "    }\n" +
                        "}",
                "data.addBook",
                Map.of("bookInput", Map.of("title", "test book", "releaseYear", 2021)));

        assertThat(bookAdded).isNull();
    }

}