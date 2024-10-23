package com.habitoplus.habitoplusback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.habitoplus.habitoplusback.model.Note;
import com.habitoplus.habitoplusback.model.Profile;
import com.habitoplus.habitoplusback.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    private ObjectMapper objectMapper;
    private Note testNote;
    private Profile testProfile;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        testProfile = new Profile();
        testProfile.setIdProfile(1);

        testNote = new Note();
        testNote.setIdNote(1);
        testNote.setProfile(testProfile);
        testNote.setTitle("Test Title");
        testNote.setContent("Test Content");
        testNote.setDate(LocalDateTime.now());
    }

    @Test
    void testRegisterNote_Success() throws Exception {
        doNothing().when(noteService).addNote(any());

        mockMvc.perform(post("/notes/registerNote")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testNote)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Saved successfully"));
    }

    @Test
    void testRegisterNote_BadRequest() throws Exception {
        testNote.setTitle(null); 

        mockMvc.perform(post("/notes/registerNote")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testNote)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetAllNotes_Success() throws Exception {
        when(noteService.getAllNotes()).thenReturn(Arrays.asList(testNote));

        mockMvc.perform(get("/notes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value("Test Title"))
                .andExpect(jsonPath("$[0].content").value("Test Content"));
    }

    @Test
    void testGetAllNotes_EmptyList() throws Exception {
        when(noteService.getAllNotes()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/notes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void testGetNotesByProfile_Success() throws Exception {
        when(noteService.getNotesByProfileId(eq(1))).thenReturn(Arrays.asList(testNote));

        mockMvc.perform(get("/profiles/1/notes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value("Test Title"));
    }

    @Test
    void testGetNotesByProfile_NotFound() throws Exception {
        when(noteService.getNotesByProfileId(eq(999))).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/profiles/999/notes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void testUpdateNote_Success() throws Exception {
        testNote.setTitle("Updated Title");
        doNothing().when(noteService).updateNoteById(any());

        mockMvc.perform(put("/notes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testNote)))
                .andExpect(status().isOk())
                .andExpect(content().string("Updated successfully"));
    }

    @Test
    void testUpdateNote_BadRequest() throws Exception {
        testNote.setTitle(null);

        mockMvc.perform(put("/notes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testNote)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void testDeleteNote_NotFound() throws Exception {
        doThrow(new RuntimeException("Note not found")).when(noteService).deleteNote(eq(999));

        mockMvc.perform(delete("/notes/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

}