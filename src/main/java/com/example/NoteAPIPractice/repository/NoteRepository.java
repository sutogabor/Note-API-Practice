package com.example.NoteAPIPractice.repository;

import com.example.NoteAPIPractice.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository <Note, Long> {
}
