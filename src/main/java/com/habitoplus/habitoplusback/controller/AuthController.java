package com.habitoplus.habitoplusback.controller;

import com.habitoplus.habitoplusback.dto.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.habitoplus.habitoplusback.dto.ForgotPasswordRequest;
import com.habitoplus.habitoplusback.dto.LoginRequest;
import com.habitoplus.habitoplusback.dto.RegisterRequest;
import com.habitoplus.habitoplusback.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@Tag(name = "Auth Controller", description = "Provides all the operations related to the authentication, creation of accounts, login, logout, etc.")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "login")
        @ApiResponse(responseCode = "200", description = "Return token", content = {
            @Content(mediaType = "application/json")})
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequest loginRequest) {

        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
    }
        @Operation(summary = "register")
        @ApiResponse(responseCode = "201", description = "Return account", content = {
            @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = RegisterRequest.class)))})
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequest request) {
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

    @Operation(summary = "forgot-password")
        @ApiResponse(responseCode = "200", description = "Return message", content = {
            @Content(mediaType = "application/json")})
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        authService.forgotPassword(request);
        return new ResponseEntity<>("Password reset code sent to email", HttpStatus.OK);
    }
    @Operation(summary = "logout")
        @ApiResponse(responseCode = "200", description = "Return message ", content = {
            @Content(mediaType = "application/json")})
    @PostMapping("/logout/{id}")
    public ResponseEntity<String> logout(@PathVariable Integer id) {
        authService.logout(id);
        return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
    }
}


