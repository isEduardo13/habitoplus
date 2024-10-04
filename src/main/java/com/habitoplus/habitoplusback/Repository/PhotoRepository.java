package com.habitoplus.habitoplusback.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.habitoplus.habitoplusback.Model.Photo;

public interface PhotoRepository extends MongoRepository<Photo, String> {

}
