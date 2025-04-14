package com.example.NoteAPIPractice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NoteDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private LocalDate creationDate;
}
