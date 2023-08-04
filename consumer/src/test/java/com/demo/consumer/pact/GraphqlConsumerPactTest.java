package com.demo.consumer.pact;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactFolder;
import com.demo.consumer.client.BookFeignClient;
import com.demo.consumer.client.request.BookRequestDTO;
import com.demo.consumer.client.response.BookResponseDTO;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.pactfoundation.consumer.dsl.LambdaDsl.newJsonArrayMinLike;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "BooksProvider", port = "8081")
@PactFolder("target/pacts")
@RequiredArgsConstructor
@SpringBootTest
class GraphqlConsumerPactTest {

    private final Map<String, String> headers = new HashMap<>();

    @Autowired
    private BookFeignClient bookFeignClient;


    @BeforeEach
    public void setUp(MockServer mockServer) {
        Assertions.assertThat(mockServer).isNotNull();
    }

    @Pact(provider = "BooksProvider", consumer = "GraphqlConsumer")
    RequestResponsePact getAllBooks(PactDslWithProvider builder) {
        headers.put("Content-Type", "application/json");
        return builder.given("get all books")
                .uponReceiving("get all books")
                .method("GET")
                .path("/books")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body(newJsonArrayMinLike(1, array ->
                        array.object(object -> {
                            object.stringType("title", "book 1");
                            object.numberType("releaseYear", 2020);
                            object.stringType("id", "30411681-ae72-4f39-9f6e-1b50c123b928");
                        })
                ).build())
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "getAllBooks")
    void get_books(MockServer mockServer) throws IOException {
        List<BookResponseDTO> allBooks = bookFeignClient.getAllBooks();

        assertThat(allBooks).hasSize(1);
    }

    @Pact(provider = "BooksProvider", consumer = "GraphqlConsumer")
    RequestResponsePact addBook(PactDslWithProvider builder) {
        headers.put("Content-Type", "application/json");
        return builder.given("add a book")
                .uponReceiving("add a book")
                .method("POST")
                .path("/books")
                .bodyWithSingleQuotes("{\n" +
                        "    \"title\":\"FOLK MED ANGEST\",\n" +
                        "    \"releaseYear\":2021\n" +
                        "}\n")
                .willRespondWith()
                .status(201)
                .headers(headers)
                .body(new PactDslJsonBody()
                        .stringType("id", "8b0fe22b-640b-47fd-a794-edfdb429243b")
                        .stringType("title", "FOLK MED ANGEST")
                        .integerType("releaseYear", 2021))
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "addBook")
    void add_book(MockServer mockServer) throws IOException {
        BookResponseDTO book = bookFeignClient.addBook(BookRequestDTO.builder()
                .title("FOLK MED ANGEST")
                .releaseYear(2021)
                .build());

        assertThat(book.getId()).isEqualTo("8b0fe22b-640b-47fd-a794-edfdb429243b");
        assertThat(book.getTitle()).isEqualTo("FOLK MED ANGEST");
        assertThat(book.getReleaseYear()).isEqualTo(2021);
    }
}
