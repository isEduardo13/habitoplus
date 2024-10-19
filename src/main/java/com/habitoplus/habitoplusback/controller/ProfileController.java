package com.habitoplus.habitoplusback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.habitoplus.habitoplusback.model.Profile;
import com.habitoplus.habitoplusback.service.ProfileService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("profiles")
@CrossOrigin(origins = "*" , methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Tag(name = "Profiles", description = "Provides all the operations related to the profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    
    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @GetMapping("{id}")
    public ResponseEntity<Profile> getProfileById(Integer id) {
        Profile profile = profileService.getProfileById(id);
       return new ResponseEntity<Profile>(profile,HttpStatus.OK);
    }
    

    

}
