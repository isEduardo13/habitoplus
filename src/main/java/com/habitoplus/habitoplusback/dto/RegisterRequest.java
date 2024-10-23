package com.habitoplus.habitoplusback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String email;
    private String password;
    private String name;
    private String age;
    private String gender;
    private String numberPhone;
    private String preferences;
    private String description;
    private String lastName;
    private String USername;
    private String birthDate;
}
