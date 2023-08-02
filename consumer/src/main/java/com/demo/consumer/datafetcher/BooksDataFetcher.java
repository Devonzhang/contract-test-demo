package com.demo.consumer.datafetcher;

import com.demo.consumer.service.BookService;
import com.demo.consumer.types.Book;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;

@DgsComponent
public class BooksDataFetcher {
    private final BookService bookService;

    public BooksDataFetcher(BookService bookService) {
        this.bookService = bookService;
    }

    @DgsQuery
    public List<Book> books(@InputArgument String titleFilter) {
        return bookService.getAllBooks();
    }
}

