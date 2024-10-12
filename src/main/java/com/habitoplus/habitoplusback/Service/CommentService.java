package com.habitoplus.habitoplusback.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.Model.Comment;
import com.habitoplus.habitoplusback.Model.CommentPK;
import com.habitoplus.habitoplusback.Repository.CommentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository repository;

    public void save(Comment comment) {
        comment.setDateTime(LocalDateTime.now());
        comment.setCommentPK(new CommentPK(comment.getIdGroup(), comment.getIdProfile(), comment.getDateTime()));
        repository.save(comment);
    }

    public void update(Comment comment) {
        comment.setCommentPK(new CommentPK(comment.getIdGroup(), comment.getIdProfile(), comment.getDateTime()));
        repository.save(comment);
    }

    public Comment getById(Integer idGroup, Integer idProfile, LocalDateTime dateTime) {
        CommentPK commentPK = new CommentPK();
        commentPK.setProfile(idProfile);
        commentPK.setGroup(idGroup);
        commentPK.setDateTime(dateTime);
        return repository.findById(commentPK).get();
    }

    public List<Comment> getAll() {
        return repository.findAll();
    }

    public List<Comment> getAll(Integer group){
        return repository.findByIdGroupOrderByDateTimeDesc(group);
    }

    public void delete(Integer idGroup, Integer idProfile, LocalDateTime dateTime) {
        CommentPK commentPK = new CommentPK();
        commentPK.setProfile(idProfile);
        commentPK.setGroup(idGroup);
        commentPK.setDateTime(dateTime);
        repository.deleteById(commentPK);
    }

    public List<Comment> findAllComments(Integer idProfile){
        return repository.findByIdProfile(idProfile);
    }

    public List<Comment> getAll(int page, int pageSize){
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Comment> comments = repository.findAll(pageReq);
        return comments.getContent();
    }
}
