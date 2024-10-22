package com.habitoplus.habitoplusback.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.Model.Photo;
import com.habitoplus.habitoplusback.Repository.PhotoRepository;

import java.util.List;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public Photo getPhotoById(String id) {
        return photoRepository.findById(id).orElse(null);
    }

    public Photo addPhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    public Photo updatePhoto(String id, Photo photo) {
        if (photoRepository.existsById(id)) {
            photo.setId(id);
            return photoRepository.save(photo);
        }
        return null;
    }

    public void deletePhoto(String id) {
        photoRepository.deleteById(id);
    }
}