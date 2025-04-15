package com.example.NoteAPIPractice.controller;

import com.example.NoteAPIPractice.model.Note;
import com.example.NoteAPIPractice.service.NoteService;
import jakarta.validation.Valid;
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
    public List<Note> getAll() { return service.getAll(); }

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

    @GetMapping("/search?query=term")
    public List<Note> findByTitle(String keyword) {
        return service.findByTitle(keyword);
    }
}
