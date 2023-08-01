package com.demo.provider.pact;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import com.demo.provider.entity.Book;
import com.demo.provider.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@Provider("BooksProvider")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@PactBroker(url = "http://localhost:80")
class BookProviderPactTest {
    @Autowired
    BookRepository repository;

    @BeforeEach
    public void setupTestTarget(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", 8084));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("books exist")
    public void setupBook() {
        System.out.println("a book exists");
        repository.save(new Book("book 1", 2020, UUID.randomUUID()));
    }
}