package com.habitoplus.habitoplusback.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.habitoplus.habitoplusback.dto.CommentWithProfileDTO;
import com.habitoplus.habitoplusback.dto.GroupDTO;
import com.habitoplus.habitoplusback.dto.GroupMemberWithProfileDTO;
import com.habitoplus.habitoplusback.dto.ProfileDTO;
import com.habitoplus.habitoplusback.dto.RequestWithProfileDTO;
import com.habitoplus.habitoplusback.enums.Role;
import com.habitoplus.habitoplusback.exception.InvalidCredentialsException;
import com.habitoplus.habitoplusback.model.Comment;
import com.habitoplus.habitoplusback.model.Group;
import com.habitoplus.habitoplusback.model.GroupMember;
import com.habitoplus.habitoplusback.model.Profile;
import com.habitoplus.habitoplusback.model.Request;
import com.habitoplus.habitoplusback.repository.GroupRepository;
import com.habitoplus.habitoplusback.repository.ProfileRepository;

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

	@Autowired
	private ModelMapper modelMapper;

	public List<Group> getAll() {
		return repository.findAll();
	}

	public List<Group> getAll(int page, int pageSize) {
		PageRequest pageReq = PageRequest.of(page, pageSize);
		Page<Group> groups = repository.findAll(pageReq);
		return groups.getContent();
	}

	public Group getById(Integer idGroup) {
		if(repository.findById(idGroup).isEmpty())
			throw new NoSuchElementException();
		
		return repository.findById(idGroup).get();
	}

	public void save(GroupDTO groupDTO, Integer idProfile) {
		Profile profile = profileRepository.findById(idProfile).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Profile with ID " + idProfile + " not found."));

		Group group = this.convertToEntity(groupDTO);
		group.setCreationDate(new Date());
		group = repository.save(group);

		GroupMember member = new GroupMember();
		member.setGroup(group);
		member.setProfile(profile);
		member.setRole(Role.ADMIN);

		memberService.save(memberService.convertToDTO(member));
	}

	public void save(GroupDTO groupDTO, Integer idGroup, Integer idAdmin) {
		if (!memberService.validateGroupAdmin(idGroup, idAdmin, Role.ADMIN))
			throw new InvalidCredentialsException("Unauthorized action");

		Group auxGroup = repository.findById(idGroup).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group with ID " + idGroup + " not found."));

		auxGroup.setName(groupDTO.getName());
		auxGroup.setDescription(groupDTO.getDescription());
		auxGroup.setGroupType(groupDTO.getGroupType());

		repository.save(auxGroup);
	}

	public void delete(Integer idGroup, Integer idAdmin) {
		if (!memberService.validateGroupAdmin(idGroup, idAdmin, Role.ADMIN))
			throw new InvalidCredentialsException("Unauthorized action");

		repository.deleteById(idGroup);
	}

	public List<GroupMemberWithProfileDTO> getGroupMembers(Integer idGroup) {
		Group group = repository.findById(idGroup).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group with ID " + idGroup + " not found."));
		List<GroupMember> members = group.getMembers();
		return members.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	public List<GroupMemberWithProfileDTO> getGroupMembers(Integer idGroup, int page, int pageSize) {
		List<GroupMember> members = memberService.getAll(idGroup, page, pageSize);
		return members.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	public List<CommentWithProfileDTO> getComments(Integer idGroup) {
		List<Comment> comments = commentService.getAll(idGroup);
		return comments.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());

	}

	public List<CommentWithProfileDTO> getComments(Integer idGroup, int page, int pagesize) {
		List<Comment> comments = commentService.getAll(idGroup, page, pagesize);
		return comments.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());

	}

	public List<RequestWithProfileDTO> getRequests(Integer idGroup, Integer idAdmin) {
		if (!memberService.validateGroupAdmin(idGroup, idAdmin, Role.ADMIN))
			throw new InvalidCredentialsException("Unauthorized action");
		List<Request> requests = requestService.getAll(idGroup);
		return requests.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	public List<RequestWithProfileDTO> getRequests(Integer idGroup, Integer idAdmin, int page, int pageSize) {
		if (!memberService.validateGroupAdmin(idGroup, idAdmin, Role.ADMIN))
			throw new InvalidCredentialsException("Unauthorized action");

		List<Request> requests = requestService.getAll(idGroup, page, pageSize);
		return requests.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	public GroupMemberWithProfileDTO convertToDTO(GroupMember groupMember) {
		return modelMapper.map(groupMember, GroupMemberWithProfileDTO.class);
	}

	public RequestWithProfileDTO convertToDTO(Request request) {
		return modelMapper.map(request, RequestWithProfileDTO.class);
	}

	public CommentWithProfileDTO convertToDTO(Comment comment) {
		CommentWithProfileDTO cwpDTO = modelMapper.map(comment, CommentWithProfileDTO.class);
		cwpDTO.setProfile(
				profileRepository.findById(comment.getIdProfile())
						.map(this::convertToDTO)
						.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
								"Profile with ID " + comment.getIdProfile() + " not found.")));
		return cwpDTO;
	}

	public GroupDTO convertToDTO(Group group) {
		return modelMapper.map(group, GroupDTO.class);
	}

	public ProfileDTO convertToDTO(Profile profile) {
		return modelMapper.map(profile, ProfileDTO.class);
	}

	public Group convertToEntity(GroupDTO groupDTO) {
		return modelMapper.map(groupDTO, Group.class);
	}

}
