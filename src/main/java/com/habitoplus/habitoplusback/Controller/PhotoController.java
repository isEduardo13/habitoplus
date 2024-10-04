package com.habitoplus.habitoplusback.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.habitoplus.habitoplusback.Model.Photo;
import com.habitoplus.habitoplusback.Service.PhotoService;

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
