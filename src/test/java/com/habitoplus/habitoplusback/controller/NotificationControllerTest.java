package com.habitoplus.habitoplusback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.habitoplus.habitoplusback.dto.NotificationDTO;
import com.habitoplus.habitoplusback.model.Notification;
import com.habitoplus.habitoplusback.service.NotificationService;
import com.habitoplus.habitoplusback.service.ProfileService;
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
public class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    @MockBean
    private ProfileService profileService;

    private ObjectMapper objectMapper;
    private NotificationDTO testNotificationDTO;
    private Notification testNotification;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        testNotificationDTO = new NotificationDTO();
        testNotificationDTO.setMessage("Test message");
        testNotificationDTO.setType("info");
        testNotificationDTO.setDate(LocalDateTime.now());
        testNotificationDTO.setIsRead(false);
        testNotificationDTO.setIdProfile(1);

        testNotification = new Notification();
        testNotification.setId(1);
        testNotification.setMessage("Test message");
        testNotification.setType("info");
        testNotification.setDate(LocalDateTime.now());
        testNotification.setIsRead(false);
    }

    @Test
    void testCreateNotification_Success() throws Exception {
        doNothing().when(notificationService).addNotification(any(NotificationDTO.class));

        mockMvc.perform(post("/notifications/registerNotificaton")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testNotificationDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Saved successfully"));
    }

    @Test
    void testCreateNotification_BadRequest() throws Exception {
        testNotificationDTO.setMessage(null); 

        mockMvc.perform(post("/notifications/registerNotificaton")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testNotificationDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetNotificationsByProfileId_Success() throws Exception {
        when(notificationService.getNotificationsByProfileId(1))
                .thenReturn(Arrays.asList(testNotification));

        mockMvc.perform(get("/profiles/{idProfile}/notifications", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].message").value("Test message"))
                .andExpect(jsonPath("$[0].type").value("info"));
    }

    @Test
    void testGetNotificationsByProfileId_EmptyList() throws Exception {
        when(notificationService.getNotificationsByProfileId(999))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/profiles/{idProfile}/notifications", 999))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void testDeleteNotification_Success() throws Exception {
        doNothing().when(notificationService).deleteNotification(1);

        mockMvc.perform(delete("/notifications/{idNotification}", 1))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted Successfully"));
    }

    @Test
    void testDeleteNotification_NotFound() throws Exception {
        doThrow(new RuntimeException("Notification not found"))
                .when(notificationService).deleteNotification(999);

        mockMvc.perform(delete("/notifications/{idNotification}", 999))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testGetAllNotifications_Success() throws Exception {
        when(notificationService.getAllNotifications())
                .thenReturn(Arrays.asList(testNotification));

        mockMvc.perform(get("/notifications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].message").value("Test message"))
                .andExpect(jsonPath("$[0].type").value("info"));
    }

    @Test
    void testGetAllNotifications_EmptyList() throws Exception {
        when(notificationService.getAllNotifications())
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/notifications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

   


}