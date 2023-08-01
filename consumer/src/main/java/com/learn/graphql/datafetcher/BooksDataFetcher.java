package com.learn.graphql.datafetcher;

import com.learn.graphql.entity.BookWithTime;
import com.learn.graphql.service.BookWithTimeService;
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

