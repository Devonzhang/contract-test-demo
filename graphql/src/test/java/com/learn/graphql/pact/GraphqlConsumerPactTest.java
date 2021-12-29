package com.learn.graphql.pact;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactFolder;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.pactfoundation.consumer.dsl.LambdaDsl.newJsonArrayMinLike;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "BooksProvider")
@PactFolder("target/pacts")
public class GraphqlConsumerPactTest {
    private Map headers = new HashMap();

    @BeforeEach
    public void setUp(MockServer mockServer) {
        Assertions.assertNotNull(mockServer);
    }

    @Pact(provider = "BooksProvider", consumer = "GraphqlConsumer")
    RequestResponsePact getAllBooks(PactDslWithProvider builder) {
        headers.put("Content-Type", "application/json");
        return builder.given("books exist")
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
        //Given
        HttpUriRequest request = new HttpGet(mockServer.getUrl() + "/books");

        //When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        //Then
        Assertions.assertEquals(200, httpResponse.getStatusLine().getStatusCode());
    }
}
