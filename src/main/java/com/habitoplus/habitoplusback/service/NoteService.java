package com.habitoplus.habitoplusback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.dto.NoteDTO;
import com.habitoplus.habitoplusback.model.Note;
import com.habitoplus.habitoplusback.model.Profile;
import com.habitoplus.habitoplusback.repository.NoteRepository;

@Service
public class NoteService {
    
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private ProfileService profileService;

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }
    
    public List<Note> getNotesByProfileId(int profileId) {
        Profile profile = profileService.getProfileById(profileId);
        return profile.getNotes();
    }

    public void deleteNote(int idNote) {
        noteRepository.deleteById(idNote);
    }

    public void addNote(NoteDTO noteDTO) {
        Note note = new Note();
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        note.setDate(noteDTO.getDate());
        
        Profile profile = profileService.getProfileById(noteDTO.getIdProfile());
        note.setProfile(profile);
        noteRepository.save(note);
    }
    public void updateNoteById(NoteDTO noteDTO) {
        Note note = noteRepository.findById(noteDTO.getIdNote())
            .orElseThrow(() -> new IllegalArgumentException("Note not found with id: " + noteDTO.getIdNote()));
        
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        note.setDate(noteDTO.getDate());
        noteRepository.save(note);
    }
}
