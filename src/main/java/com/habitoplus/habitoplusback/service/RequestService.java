package com.habitoplus.habitoplusback.service;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.habitoplus.habitoplusback.dto.RequestDTO;
import com.habitoplus.habitoplusback.enums.Role;
import com.habitoplus.habitoplusback.enums.Status;
import com.habitoplus.habitoplusback.exception.InvalidCredentialsException;
import com.habitoplus.habitoplusback.model.GroupMember;
import com.habitoplus.habitoplusback.model.Request;
import com.habitoplus.habitoplusback.model.RequestPK;
import com.habitoplus.habitoplusback.repository.RequestRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RequestService {
	@Autowired
	private RequestRepository repository;

	@Autowired
	private GroupMemberService memberService;

	@Autowired
	private ModelMapper modelMapper;

	public Request convertToEntity(RequestDTO requestDTO) {
		return modelMapper.map(requestDTO, Request.class);
	}

	public RequestDTO convertToDTO(Request request) {
		return modelMapper.map(request, RequestDTO.class);
	}

	public List<Request> getAll(Integer idGroup) {
		return repository.findByGroupIdGroupOrderByDateRequestDesc(idGroup);
	}

	public List<Request> getAll(Integer idGroup, int page, int pageSize) {
		PageRequest pageReq = PageRequest.of(page, pageSize);
		Page<Request> requests = repository.findRequestsByIdGroup(idGroup, pageReq);
		return requests.getContent();
	}

	public Request getById(Integer idGroup, Integer idProfile) {
		return repository.findById(new RequestPK(idGroup, idProfile)).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Request with ID group " + idGroup + " and ID profile" + idProfile + " not found."));
	}

	public void save(RequestDTO requestDTO) {
		Request request = this.convertToEntity(requestDTO);
		request.setDateRequest(new Date());
		repository.save(request);
	}

	public void acceptUser(Integer idGroup, Integer idProfile, Integer idAdmin) {
		if (!memberService.validateGroupAdmin(idGroup, idAdmin, Role.ADMIN))
			throw new InvalidCredentialsException("Unauthorized action");

		Request request = repository.findById(new RequestPK(idGroup, idProfile)).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Request with ID group " + idGroup + " and ID profile" + idProfile + " not found."));

		request.setStatus(Status.ADMITTED);
		repository.save(request);

		GroupMember gm = new GroupMember();
		gm.setProfile(request.getProfile());
		gm.setGroup(request.getGroup());
		gm.setRole(Role.MEMBER);
		gm.setUnionDate(new Date());
		memberService.save(memberService.convertToDTO(gm));
	}

	public void delete(Integer idGroup, Integer idProfile) {
		repository.deleteById(new RequestPK(idGroup, idProfile));
	}

}
