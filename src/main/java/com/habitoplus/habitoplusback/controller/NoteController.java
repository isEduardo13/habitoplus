package com.habitoplus.habitoplusback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.habitoplus.habitoplusback.dto.NoteDTO;
import com.habitoplus.habitoplusback.model.Note;
import com.habitoplus.habitoplusback.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("notes")
@CrossOrigin(origins = "*")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/registerNote")
    @Operation(summary = "Create a new note")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Note created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid note data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> createNote(@Valid @RequestBody NoteDTO noteDTO) {
        noteService.addNote(noteDTO);
        return new ResponseEntity<>("Saved successfully", HttpStatus.CREATED);
    }
    @Operation(summary = "Get all notes by profile ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found notes"),
        @ApiResponse(responseCode = "404", description = "Notes not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<Note> getAllNotes(){
        return noteService.getAllNotes();
        
    }
    @Operation(summary = "Delete a note by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Note deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Note not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{idNote}")
    public ResponseEntity<String> deleteNoteById(@PathVariable int idNote){
        noteService.deleteNote(idNote);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }
    @Operation(summary = "Update a note by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Note updated successfully"),
        @ApiResponse(responseCode = "404", description = "Note not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping ("/{updateNote}")
    public ResponseEntity<String> updateNoteById(@Valid @RequestBody NoteDTO noteDTO){
        noteService.updateNoteById(noteDTO);
        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }
    
}
