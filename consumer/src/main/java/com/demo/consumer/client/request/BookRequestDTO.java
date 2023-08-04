package com.demo.consumer.client.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BookRequestDTO {
    private String title;
    private Integer releaseYear;
}
