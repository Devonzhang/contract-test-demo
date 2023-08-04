package com.demo.consumer.datamutator;

import com.demo.consumer.service.BookService;
import com.demo.consumer.types.Book;
import com.demo.consumer.types.BookInput;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import lombok.RequiredArgsConstructor;

@DgsComponent
@RequiredArgsConstructor
public class BookDataMutator {

    private final BookService bookService;

    @DgsMutation
    public Book addBook(@InputArgument BookInput bookInput) {
        return bookService.addBook(bookInput);
    }
}
