package com.demo.provider.service;

import com.demo.provider.dto.BookRequestDTO;
import com.demo.provider.dto.BookResponseDTO;
import com.demo.provider.entity.Book;
import com.demo.provider.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {BookService.class})
class BookServiceTest {

    @Autowired
    private BookService bookService;
    @MockBean
    private BookRepository bookRepository;

    @Test
    void should_save_book() {
        BookRequestDTO bookToSaveDTO = new BookRequestDTO("test book", 2021);

        when(bookRepository.save(any())).thenReturn(new Book("test book", 2021, UUID.randomUUID()));

        BookResponseDTO bookSaved = bookService.createBook(bookToSaveDTO);

        Assertions.assertEquals(bookToSaveDTO.getTitle(), bookSaved.getTitle());
        Assertions.assertEquals(bookToSaveDTO.getReleaseYear(), bookSaved.getReleaseYear());
//        Assertions.assertEquals(UUID.class, bookSaved.getId().getClass());
    }

}