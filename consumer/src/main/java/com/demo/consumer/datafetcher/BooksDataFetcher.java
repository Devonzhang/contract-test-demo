package com.demo.consumer.datafetcher;

import com.demo.consumer.service.BookService;
import com.demo.consumer.types.Book;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DgsComponent
@RequiredArgsConstructor
public class BooksDataFetcher {
    private final BookService bookService;

    @DgsQuery
    public List<Book> books(@InputArgument String titleFilter) {
        return bookService.getAllBooks();
    }
}

