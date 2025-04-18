package com.example.NoteAPIPractice.controller;

import com.example.NoteAPIPractice.dto.NoteCreateDTO;
import com.example.NoteAPIPractice.dto.NoteDTO;
import com.example.NoteAPIPractice.dto.NoteUpdateDTO;
import com.example.NoteAPIPractice.model.Note;
import com.example.NoteAPIPractice.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    private boolean isValidSortField(String sortBy) {
        return sortBy.equals("title") || sortBy.equals("createdAt") || sortBy.equals("author");
    }

    @GetMapping
    public Page<NoteDTO> getNotes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        if (!isValidSortField(sortBy)) {
            throw new IllegalArgumentException("Invalid sort field: " + sortBy);
        }

        return service.getAll(page, size, sortBy, direction);
    }

    @GetMapping("/{id}")
    public Note getOne(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    public Note create(@RequestBody @Valid NoteCreateDTO dto) { return service.create(dto); }

    @PutMapping("/{id}")
    public Note update(@PathVariable Long id, @RequestBody @Valid NoteUpdateDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }

    @GetMapping("/search")
    public List<Note> findByTitle(@RequestParam String keyword) {
        return service.findByTitle(keyword);
    }
}
