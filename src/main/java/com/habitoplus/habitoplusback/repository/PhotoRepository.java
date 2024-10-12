package com.habitoplus.habitoplusback.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.habitoplus.habitoplusback.model.Photo;

public interface PhotoRepository extends MongoRepository<Photo, String> {

}
