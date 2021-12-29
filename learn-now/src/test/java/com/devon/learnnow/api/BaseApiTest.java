package com.devon.learnnow.api;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

public class BaseApiTest {
    @Test
    public void should_return_with_status_code_200() {
        RestAssured.baseURI = "http://localhost:8081";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/books");
        System.out.println(response.getBody());
    }
}
