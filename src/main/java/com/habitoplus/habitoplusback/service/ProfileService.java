package com.habitoplus.habitoplusback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.model.Profile;
import com.habitoplus.habitoplusback.repository.ProfileRepository;


@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

   public List<Profile> getAllProfiles() {
       return profileRepository.findAll();
   }

   public String UploadImage(Profile profile) {
       Profile existingProfile = profileRepository.findById(profile.getIdProfile()).orElse(null);
       if (existingProfile != null) {
           existingProfile.setPhoto(p);
           profileRepository.save(existingProfile);
           return "Image uploaded successfully";
       } else {
           return "Profile not found";
       }

    public Profile addProfile(Profile profile) {
         return profileRepository.save(profile);
    }

    public Profile getProfileById(int id) {
        return profileRepository.findById(id).orElse(null);
    }


}
