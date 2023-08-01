package com.learn.graphql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class BookWithTime {
    private String title;
    private Integer releaseYear;
    private UUID id;
    private LocalDateTime addTime;
}
