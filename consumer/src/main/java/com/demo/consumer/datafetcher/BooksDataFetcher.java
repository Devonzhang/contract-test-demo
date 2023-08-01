package com.demo.consumer.datafetcher;

import com.demo.consumer.entity.BookWithTime;
import com.demo.consumer.service.BookWithTimeService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;

@DgsComponent
public class BooksDataFetcher {
    private final BookWithTimeService bookWithTimeService;

    public BooksDataFetcher(BookWithTimeService bookWithTimeService) {
        this.bookWithTimeService = bookWithTimeService;
    }

    @DgsQuery
    public List<BookWithTime> books(@InputArgument String titleFilter) {
        return bookWithTimeService.addTimeForBooks();
    }
}

