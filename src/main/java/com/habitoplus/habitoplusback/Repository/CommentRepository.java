package com.habitoplus.habitoplusback.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.habitoplus.habitoplusback.Model.Comment;
import com.habitoplus.habitoplusback.Model.CommentPK;

@Repository
public interface CommentRepository extends MongoRepository<Comment, CommentPK>{
    List<Comment> findByIdGroup(Integer idGroup);
    Page<Comment> findByIdGroup(Integer idGroup,  Pageable pageable);
    List<Comment> findByIdProfile(Integer idProfile);
    List<Comment> findByIdGroupOrderByDateTimeDesc(Integer idGroup);
}
