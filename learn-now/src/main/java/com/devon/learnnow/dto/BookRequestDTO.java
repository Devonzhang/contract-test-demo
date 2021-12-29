package com.devon.learnnow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {
    private String title;
    private Integer releaseYear;
}
