package com.devon.learnnow.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String title;
    private Integer releaseYear;
    @Id
    private UUID id;
}
