package com.demo.consumer.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookRequestDTO {
    private String title;
    private Integer releaseYear;
}
