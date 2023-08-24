package com.demo.provider.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class BookResponseDTO {
    private String title;
    private Integer releaseYear;
    private UUID id;
}
