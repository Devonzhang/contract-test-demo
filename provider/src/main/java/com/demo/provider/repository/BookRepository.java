package com.demo.provider.repository;

import com.demo.provider.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public
interface BookRepository extends MongoRepository<Book, UUID> {
    @Override
    List<Book> findAll();

    @Override
    void deleteAll();

    @Override
    Optional<Book> findById(UUID id);

    @Override
    Book save(Book book);

    List<Book> findAllByReleaseYearAfter(Integer year);

    List<Book> findByTitleRegex(String regex);
}
