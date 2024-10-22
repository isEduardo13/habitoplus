package com.habitoplus.habitoplusback.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.habitoplus.habitoplusback.model.Comment;
import com.habitoplus.habitoplusback.model.CommentPK;
import com.habitoplus.habitoplusback.repository.CommentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private GroupMemberService memberService;

    public List<Comment> getAll(Integer idGroup) {
        return repository.findByIdGroupOrderByDateTimeDesc(idGroup);
    }

    public List<Comment> getAll(Integer idGroup, int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Comment> comments = repository.findByIdGroup(idGroup, pageReq);
        return comments.getContent();
    }

    public Comment getById(Integer idGroup, Integer idProfile, LocalDateTime dateTime) {
        return repository.findById(new CommentPK(idGroup, idProfile, dateTime)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Comment with ID group " + idGroup + ", ID profile " + idProfile + " and date time " + dateTime
                                + " not found."));
    }

    public void save(Comment comment) {
        if (memberService.getById(comment.getIdGroup(), comment.getIdProfile()) == null)
            throw new NoSuchElementException("Group member with ID group " + comment.getIdGroup() + "and ID profile "
                    + comment.getIdProfile() + " not found.");

        comment.setDateTime(LocalDateTime.now());
        comment.setCommentPK(new CommentPK(comment.getIdGroup(), comment.getIdProfile(), comment.getDateTime()));
        repository.save(comment);
    }

    public void save(Comment comment, Integer idGroup, Integer idProfile, LocalDateTime dateTime) {
        if (memberService.getById(idGroup, idProfile) == null)
            throw new NoSuchElementException("Group member with ID group " + comment.getIdGroup() + "and ID profile "
                    + comment.getIdProfile() + " not found.");
        Comment auxComment = repository.findById(new CommentPK(idGroup, idProfile, dateTime)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Comment with ID group " + idGroup + ", ID profile " + idProfile + " and date time " + dateTime
                                + " not found."));

        comment.setIdGroup(auxComment.getIdGroup());
        comment.setIdProfile(auxComment.getIdProfile());
        comment.setDateTime(auxComment.getDateTime());
        comment.setCommentPK(
                new CommentPK(auxComment.getIdGroup(), auxComment.getIdProfile(), auxComment.getDateTime()));
        repository.save(comment);
    }

    public void delete(Integer idGroup, Integer idProfile, LocalDateTime dateTime) {
        if (memberService.getById(idGroup, idProfile) == null)
            throw new NoSuchElementException("Group member with ID group " + idGroup + "and ID profile "
                    + idProfile + " not found.");
        
        repository.deleteById(new CommentPK(idGroup, idProfile, dateTime));
    }

}
