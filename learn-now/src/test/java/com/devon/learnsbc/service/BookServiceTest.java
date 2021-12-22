package com.devon.learnsbc.service;

import com.devon.learnsbc.dto.BookRequestDTO;
import com.devon.learnsbc.entity.Book;
import com.devon.learnsbc.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {BookService.class})
public class BookServiceTest {

    @Autowired
    private BookService bookService;
    @MockBean
    private BookRepository bookRepository;

    @Test
    public void should_save_book() {
        BookRequestDTO bookToSaveDTO = new BookRequestDTO("test book", 2021);

        when(bookRepository.save(any())).thenReturn(new Book("test book", 2021, UUID.randomUUID()));

        Book bookSaved = bookService.createBook(bookToSaveDTO);

        Assertions.assertEquals(bookToSaveDTO.getTitle(), bookSaved.getTitle());
        Assertions.assertEquals(bookToSaveDTO.getReleaseYear(), bookSaved.getReleaseYear());
        Assertions.assertEquals(UUID.class, bookSaved.getId().getClass());
    }

}