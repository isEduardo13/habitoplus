package com.habitoplus.habitoplusback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habitoplus.habitoplusback.service.HabitRecommendationService;

import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Recomendations", description = "")
@RequestMapping("recomendations")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET })
public class HabitRecommendationController {

    @Autowired
    private HabitRecommendationService service;

    @Operation(summary = "Get recommendations from OpenAI API")
    @ApiResponse(responseCode = "200", description = "Recommendations retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    @GetMapping("{idProfile}")
    public ResponseEntity<?> getRecommendations(@PathVariable Integer idProfile) {
        String recommendations = service.getRecommendations(idProfile);
        return new ResponseEntity<String>(recommendations, HttpStatus.OK);
    }
}
