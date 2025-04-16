package com.example.NoteAPIPractice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteCreateDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String content;
}
