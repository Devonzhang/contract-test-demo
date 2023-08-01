package com.learn.graphql.client.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {
    private String title;
    private Integer releaseYear;
}
