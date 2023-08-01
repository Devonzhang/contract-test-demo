package com.demo.provider.api;

import com.demo.provider.entity.Book;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureDataMongo
class BaseApiTest {
    @LocalServerPort
    int springTestRandomServerPort = 0;
    @Autowired
    MongoTemplate mongoTemplate;
    private final Book book1 = new Book("test book 1", 2021, UUID.randomUUID());
    private final Book book2 = new Book("test book 2", 2022, UUID.randomUUID());

    @BeforeEach
    public void setUp() {
        RestAssured.port = springTestRandomServerPort;
        mongoTemplate.save(book1);
        mongoTemplate.save(book2);
    }

    @Test
    void should_return_with_status_code_200() {
        given().
                when().
                get("/books").
                then().
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON).
                and().
                body("[0].releaseYear", equalTo(2021));
    }
}
