package com.habitoplus.habitoplusback.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.dto.CommentWithProfileDto;
import com.habitoplus.habitoplusback.dto.GroupMemberWithProfileDto;
import com.habitoplus.habitoplusback.dto.ProfileDto;
import com.habitoplus.habitoplusback.dto.RequestWithProfileDto;
import com.habitoplus.habitoplusback.model.Comment;
import com.habitoplus.habitoplusback.model.Group;
import com.habitoplus.habitoplusback.model.GroupMember;
import com.habitoplus.habitoplusback.model.Profile;
import com.habitoplus.habitoplusback.model.Request;
import com.habitoplus.habitoplusback.repository.GroupRepository;
import com.habitoplus.habitoplusback.repository.ProfileRepository;
import com.habitoplus.habitoplusback.enums.Role;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GroupService {

	@Autowired
	private GroupRepository repository;

	@Autowired
	private CommentService commentService;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private GroupMemberService memberService;

	@Autowired
	private RequestService requestService;

	public GroupMemberWithProfileDto convertToProfileDto(GroupMember groupMember) {
        ProfileDto profileDto = new ProfileDto(
                groupMember.getProfile().getIdProfile(),
                groupMember.getProfile().getName()
        );
        return new GroupMemberWithProfileDto(profileDto, groupMember.getRole(), groupMember.getUnionDate());
    }

	public RequestWithProfileDto convertRequest(Request request) {
        ProfileDto profileDto = new ProfileDto(
                request.getProfile().getIdProfile(),
                request.getProfile().getName()
        );
        return new RequestWithProfileDto(profileDto, request.getDateRequest(), request.getStatus());
    }

	public CommentWithProfileDto convertComment(Comment comment) {
		Profile profile = profileRepository.findById(comment.getIdProfile()).get();
        ProfileDto profileDto = new ProfileDto(
                profile.getIdProfile(),
                profile.getName()
        );
        return new CommentWithProfileDto(profileDto, comment.getContent(), comment.getDateTime());
    }

	public List<Group> getAll() {
		return repository.findAll();
	}

	public Group getById(Integer idGroup) {
		Group group = repository.findById(idGroup).get();
		return group;
	}

	public void save(Group group, Integer idProfile) {
		group.setCreationDate(new Date());
		Group savedGroup = repository.save(group);

		Profile profile = profileRepository.findById(idProfile).get();

		GroupMember member = new GroupMember();
		member.setGroup(savedGroup);
		member.setProfile(profile);
		member.setRole(Role.ADMIN);

		memberService.save(member);
	}

	public void update(Group group, Integer idGroup){
		Group auxGroup = repository.findById(idGroup).get();
		group.setIdGroup(auxGroup.getIdGroup());
		group.setCreationDate(auxGroup.getCreationDate());
		repository.save(group);
	}

	public void delete(Integer idGroup) {
		repository.deleteById(idGroup);
	}

	public List<GroupMemberWithProfileDto> getGroupMembers(Integer idGroup) {
		List<GroupMember> members = repository.findById(idGroup).get().getMembers();
		return members.stream()
				.map(this::convertToProfileDto)
				.collect(Collectors.toList());
	}

	public List<CommentWithProfileDto> getComments(Integer idGroup) {
		List<Comment> comments = commentService.getAll(idGroup);
		return comments.stream()
				.map(this::convertComment)
				.collect(Collectors.toList());
	}

	public List<RequestWithProfileDto> getRequests(Integer idGroup) {
		List<Request> requests = requestService.getAll(idGroup);
		return requests.stream()
				.map(this::convertRequest)
				.collect(Collectors.toList());
	}

	public List<Group> getAll(int page, int pageSize){
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Group> groups = repository.findAll(pageReq);
        return groups.getContent();
    }
}
