package com.habitoplus.habitoplusback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.habitoplus.habitoplusback.model.Photo;
import com.habitoplus.habitoplusback.service.PhotoService;

import java.util.List;

@RestController
@RequestMapping("photos")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

  @GetMapping
    public List<Photo> getAllPhotos() {
        return photoService.getAllPhotos();
    }
    @PostMapping
    public Photo addPhoto(@RequestBody Photo photo) {
        return photoService.addPhoto(photo);

    }
    
}
