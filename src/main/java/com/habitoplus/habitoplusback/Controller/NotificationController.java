package com.habitoplus.habitoplusback.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.habitoplus.habitoplusback.Model.Notification;
import com.habitoplus.habitoplusback.Service.NotificationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema; // Importación añadida
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;

@RestController
@RequestMapping("notification")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Operation(summary = "Get all notifications")
    @ApiResponse(responseCode = "200", description = "Found notifications", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Notification.class))) })
    @GetMapping // Cambiado de @Mapping a @RequestMapping
    public List<Notification> getAll() {
        return notificationService.getAll();
    }
    @Operation(summary = "Get notification by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found notification", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Notification.class))}),
        @ApiResponse(responseCode = "404", description = "Notification not found", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
       Notification notification = notificationService.getById(id);
       return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Notification notification) {
        notificationService.save(notification);
        return new ResponseEntity<String>("Saved record", HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody Notification notification, @PathVariable int id) {
        Notification n = notificationService.getById(id);
        n.setId(n.getId());
        notificationService.save(n);
        return new ResponseEntity<String>("Updated record", HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        notificationService.delete(id);
        return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
    }

}