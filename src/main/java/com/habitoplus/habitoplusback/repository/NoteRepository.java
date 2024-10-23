package com.habitoplus.habitoplusback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habitoplus.habitoplusback.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    
}
