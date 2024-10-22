package com.habitoplus.habitoplusback.controller;
import java.util.List;


import com.habitoplus.habitoplusback.dto.NotificationDTO;
import com.habitoplus.habitoplusback.model.Category;
import com.habitoplus.habitoplusback.model.Profile;
import com.habitoplus.habitoplusback.service.ProfileService;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.habitoplus.habitoplusback.model.Notification;
import com.habitoplus.habitoplusback.service.NotificationService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@Tag(name = "Notifications", description = "API for handling notifications")
@RequestMapping("notifications")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    ProfileService profileService;

    @PostMapping("/create")
    @Operation(summary = "Create a new notification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Notification created successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = NotificationDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid notification data", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                    @Content
            })
    })
    public ResponseEntity<?> createNotification(@Valid @RequestBody NotificationDTO notificationDTO) {
        Profile profile = profileService.getProfileById(notificationDTO.getIdProfile());
        Notification notification = new Notification();
        notification.setMessage(notificationDTO.getMessage());
        notification.setType(notificationDTO.getType());
        notification.setDate(notificationDTO.getDate());
        notification.setIsRead(notificationDTO.getIsRead());
        notification.setProfile(profile);

        notificationService.addNotification(notification);

        return ResponseEntity.status(HttpStatus.CREATED).body(notificationDTO);
    }
    @Operation(summary = "Get all notifications by profile ID")
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "200", description = "Found notifications", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Category.class)))
                    }),
                    @ApiResponse(responseCode = "204", description = "No Notifications found"),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = {
                            @Content
                    }),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                            @Content
                    })
            })
    @GetMapping("/profile/{profileId}")
    public List<Notification> getNotificationsByProfileId(@PathVariable int profileId) {
        return notificationService.getNotificationsByProfileId(profileId);
    }

    @Operation(summary = "Delete a notification by ID", description = "This endpoint deletes a notification by its ID.", responses = {
            @ApiResponse(responseCode = "204", description = "Notification deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Notification not found")
    })
    @DeleteMapping("/{notificationId}/profile/{profileId}")
    @Transactional
    public ResponseEntity<Void> deleteNotificationByProfile(@PathVariable int notificationId, @PathVariable int profileId) {
        notificationService.deleteNotificationByProfile(notificationId, profileId);
        return ResponseEntity.noContent().build();
    }


}
