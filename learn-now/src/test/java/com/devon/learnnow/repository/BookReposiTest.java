package com.devon.learnnow.repository;

import com.devon.learnnow.entity.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class BookRepositoryTest {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private BookRepository bookRepository;

    private final String collectionName = "book";
    private final Book book1 = new Book("test book 1", 2021, UUID.randomUUID());
    private final Book book2 = new Book("test book 2", 2022, UUID.randomUUID());

    @BeforeEach
    public void init() {
        mongoTemplate.dropCollection(collectionName);
        mongoTemplate.createCollection(collectionName);
        mongoTemplate.save(book1);
        mongoTemplate.save(book2);
    }

    @Test
    void should_return_all_books() {
        List<Book> books = bookRepository.findAll();

        Assertions.assertEquals(2, books.size());
    }

    @Test
    void should_save_book() {
        Book bookToSave = new Book("test book 3", 2023, UUID.randomUUID());

        Book book = bookRepository.save(bookToSave);

        Assertions.assertEquals(bookToSave.getTitle(), book.getTitle());
        Assertions.assertEquals(bookToSave.getReleaseYear(), book.getReleaseYear());
        Assertions.assertEquals(bookToSave.getId(), book.getId());
    }

    @Test
    void should_delete_all_books() {
        bookRepository.deleteAll();
        List<Book> books = bookRepository.findAll();

        Assertions.assertEquals(0, books.size());
    }

    @Test
    void should_find_book_by_id() {
        Optional<Book> book = bookRepository.findById(book1.getId());

        Assertions.assertEquals(book1.getTitle(), book.get().getTitle());
        Assertions.assertEquals(book1.getReleaseYear(), book.get().getReleaseYear());
        Assertions.assertEquals(book1.getId(), book.get().getId());
    }

    @Test
    void should_find_book_by_release_year_after() {
        List<Book> books = bookRepository.findAllByReleaseYearAfter(2021);

        Assertions.assertEquals(1, books.size());
        Assertions.assertEquals(book2.getTitle(), books.get(0).getTitle());
        Assertions.assertEquals(book2.getReleaseYear(), books.get(0).getReleaseYear());
        Assertions.assertEquals(book2.getId(), books.get(0).getId());
    }

    @Test
    void should_find_book_by_title_regex() {
        List<Book> books = bookRepository.findByTitleRegex("2");

        Assertions.assertEquals(1, books.size());
        Assertions.assertEquals(book2.getTitle(), books.get(0).getTitle());
        Assertions.assertEquals(book2.getReleaseYear(), books.get(0).getReleaseYear());
        Assertions.assertEquals(book2.getId(), books.get(0).getId());
    }

    @AfterEach
    public void teardown() {
        mongoTemplate.dropCollection(collectionName);
    }

}