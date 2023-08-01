package com.learn.graphql.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDTO {
    private String title;
    private Integer releaseYear;
    private UUID id;
}
