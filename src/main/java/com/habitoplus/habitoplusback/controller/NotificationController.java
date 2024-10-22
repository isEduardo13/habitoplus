package com.habitoplus.habitoplusback.controller;

import java.util.List;

import com.habitoplus.habitoplusback.dto.NotificationDTO;
import com.habitoplus.habitoplusback.model.Category;
import com.habitoplus.habitoplusback.service.ProfileService;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

        @PostMapping("/registerNotificaton")
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
                notificationService.addNotification(notificationDTO);
                return new ResponseEntity<String>("Saved successfully", HttpStatus.CREATED);

       }

        @Operation(summary = "Get all notifications by profile ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Found notifications", content = {
                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Category.class)))
                        }),
                        @ApiResponse(responseCode = "204", description = "No Notifications found", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "400", description = "Bad request", content = {
                                        @Content
                        }),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                                        @Content
                        })
        })
        @GetMapping
        public List<Notification> getAllNotifications() {
                return notificationService.getAllNotifications();
        }

        @Operation(summary = "Delete a notification by ID")
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
        @DeleteMapping("/{idNotification}")
        public ResponseEntity<?> deleteNotification(@PathVariable Integer idNotification) {
                notificationService.deleteNotification(idNotification);
                return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
        }
        

}
