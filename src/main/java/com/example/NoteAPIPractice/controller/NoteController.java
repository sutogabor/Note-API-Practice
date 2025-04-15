package com.example.NoteAPIPractice.controller;

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

    @GetMapping
    public Page<Note> getNotes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return service.getAll(page, size, sortBy, direction);
    }

    @GetMapping("/{id}")
    public Note getOne(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    public Note create(@RequestBody @Valid Note note) { return service.create(note); }

    @PutMapping("/{id}")
    public Note update(@PathVariable Long id, @RequestBody @Valid Note note) {
        return service.update(id, note);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }

    @GetMapping("/search")
    public List<Note> findByTitle(@RequestParam String keyword) {
        return service.findByTitle(keyword);
    }
}
