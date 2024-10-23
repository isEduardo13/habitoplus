package com.habitoplus.habitoplusback.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.habitoplus.habitoplusback.model.Comment;
import com.habitoplus.habitoplusback.model.CommentPK;

@Repository
public interface CommentRepository extends MongoRepository<Comment, CommentPK>{
    List<Comment> findByIdGroup(Integer idGroup);
    Page<Comment> findByIdGroup(Integer idGroup,  Pageable pageable);
    List<Comment> findByIdProfile(Integer idProfile);
    List<Comment> findByIdGroupOrderByDateTimeDesc(Integer idGroup);
}
