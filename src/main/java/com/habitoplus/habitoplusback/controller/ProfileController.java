package com.habitoplus.habitoplusback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.habitoplus.habitoplusback.model.Habit;
import com.habitoplus.habitoplusback.model.Note;
import com.habitoplus.habitoplusback.model.Notification;
import com.habitoplus.habitoplusback.model.Profile;
import com.habitoplus.habitoplusback.service.HabitService;
import com.habitoplus.habitoplusback.service.NoteService;
import com.habitoplus.habitoplusback.service.NotificationService;
import com.habitoplus.habitoplusback.service.ProfileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("profiles")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
                RequestMethod.DELETE })
@Tag(name = "Profiles", description = "Provides all the operations related to the profile")
public class ProfileController {

        @Autowired
        private ProfileService profileService;

        @Autowired
        private HabitService habitService;

        @Autowired
        private NotificationService notificationService;

        @Autowired
        private NoteService noteService;

        @GetMapping
        public List<Profile> getAllProfiles() {
                return profileService.getAllProfiles();
        }

        @Operation(summary = "Get habits by profile ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Found habits", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = Habit.class))
                        }),
                        @ApiResponse(responseCode = "400", description = "Invalid profile ID", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "404", description = "Profile not found", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                                        @Content
                        })
        })
        @GetMapping("{idProfile}")
        public List<Habit> getHabitsByProfile(@PathVariable Integer idProfile) {
                return habitService.getHabitsByProfile(idProfile);
        }

        @Operation(summary = "consult notifications by profile")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Notification successfully deleted", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
                        }),
                        @ApiResponse(responseCode = "400", description = "Invalid Notification ID or Profile ID", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "404", description = "Notification not found", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                                        @Content
                        })
        })
        @GetMapping("{idProfile}/notifications")
        public List<Notification> getNotificationsByProfileId(@PathVariable Integer idProfile) {
                return notificationService.getNotificationsByProfileId(idProfile);
        }

        @Operation(summary = "Delete a note by ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Note deleted successfully", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "404", description = "Note not found", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                                        @Content
                        })
        })
        @GetMapping("{idProfile}/notes")
        public List<Note> getNotesByProfileId(@PathVariable Integer idProfile) {
                return noteService.getNotesByProfileId(idProfile);
        }

}
