package com.habitoplus.habitoplusback.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.Model.Profile;
import com.habitoplus.habitoplusback.Repository.ProfileRepository;


@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

   public List<Profile> getAllProfiles() {
       return profileRepository.findAll();
   }


}
