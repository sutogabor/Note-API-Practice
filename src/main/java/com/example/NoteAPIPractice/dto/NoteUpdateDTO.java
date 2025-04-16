package com.example.NoteAPIPractice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteUpdateDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
