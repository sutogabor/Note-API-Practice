package com.example.NoteAPIPractice.service;

import com.example.NoteAPIPractice.dto.NoteDTO;
import com.example.NoteAPIPractice.model.Note;
import com.example.NoteAPIPractice.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    @Autowired
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Page<NoteDTO> getAll(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Note> notesPage = noteRepository.findAll(pageable);

        return notesPage.map(note -> {
            NoteDTO dto = new NoteDTO();
            dto.setId(note.getId());
            dto.setTitle(note.getTitle());
            dto.setContent(note.getContent());
            return dto;
        });
    }

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
