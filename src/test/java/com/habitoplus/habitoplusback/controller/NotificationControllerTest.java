package com.habitoplus.habitoplusback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.habitoplus.habitoplusback.dto.NotificationDTO;
import com.habitoplus.habitoplusback.model.Notification;
import com.habitoplus.habitoplusback.model.Profile;
import com.habitoplus.habitoplusback.service.NotificationService;
import com.habitoplus.habitoplusback.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;





@SpringBootTest
@AutoConfigureMockMvc
public class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    @MockBean
    private ProfileService profileService;

    @Test
    void testCreateNotification() throws Exception {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setMessage("Test message");
        notificationDTO.setType("info");
        notificationDTO.setDate(LocalDateTime.now());
        notificationDTO.setIsRead(false);
        notificationDTO.setIdProfile(1);

        Profile profile = new Profile();
        profile.setIdProfile(1);

        when(profileService.getProfileById(1)).thenReturn(profile);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        mockMvc.perform(post("/notifications/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(notificationDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetNotificationsByProfileId() throws Exception {
        Notification notification = new Notification();
        notification.setId(1);
        notification.setMessage("Test message");

        when(notificationService.getNotificationsByProfileId(1))
                .thenReturn(Arrays.asList(notification));

        mockMvc.perform(get("/notifications/profile/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].message").value("Test message"));
    }

    @Test
    void testDeleteNotificationByProfile() throws Exception {
        int notificationId = 1;
        int profileId = 1;

        doNothing().when(notificationService).deleteNotificationByProfile(notificationId, profileId);

        mockMvc.perform(delete("/notifications/{notificationId}/profile/{profileId}", notificationId, profileId))
                .andExpect(status().isNoContent());
    }
}