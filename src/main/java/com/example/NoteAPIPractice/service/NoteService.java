package com.example.NoteAPIPractice.service;

import com.example.NoteAPIPractice.model.Note;
import com.example.NoteAPIPractice.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAll() { return noteRepository.findAll(); }

    public Note getById(Long id) { return noteRepository.findById(id).orElseThrow(); }

    public Note create(Note note) { return noteRepository.save(note); }

    public Note update(Long id, Note updatedNote) {
        Note note = noteRepository.findById(id).orElseThrow();
        note.setTitle(updatedNote.getTitle());
        note.setAuthor(updatedNote.getAuthor());
        note.setContent(updatedNote.getContent());
        note.setCreatedAt(updatedNote.getCreatedAt());
        return noteRepository.save(note);
    }

    public void delete(Long id) { noteRepository.deleteById(id); }

    public List<Note> findByTitle(String keyword) {
        return noteRepository.findAll().stream()
                .filter(note -> note.getTitle() != null && note.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
