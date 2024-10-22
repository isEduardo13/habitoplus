package com.habitoplus.habitoplusback.Service;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.habitoplus.habitoplusback.Exception.InvalidCredentialsException;
import com.habitoplus.habitoplusback.Model.GroupMember;
import com.habitoplus.habitoplusback.Model.GroupMemberPK;
import com.habitoplus.habitoplusback.Repository.GroupMemberRepository;
import com.habitoplus.habitoplusback.dto.GroupMemberDTO;
import com.habitoplus.habitoplusback.enums.Role;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GroupMemberService {
	@Autowired
	private GroupMemberRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	public GroupMember convertToEntity(GroupMemberDTO memberDTO) {
		return modelMapper.map(memberDTO, GroupMember.class);
	}

	public GroupMemberDTO convertToDTO(GroupMember member) {
		return modelMapper.map(member, GroupMemberDTO.class);
	}

	public void save(GroupMemberDTO memberDTO) {
		GroupMember member = this.convertToEntity(memberDTO);
		member.setUnionDate(new Date());
		repository.save(member);

	}

	public void save(GroupMemberDTO memberDTO, Integer idAdmin) {
		if (!repository.existsByGroupIdGroupAndProfileIdProfileAndRole(memberDTO.getGroup().getIdGroup(), idAdmin,
				Role.ADMIN))
			throw new InvalidCredentialsException("Unauthorized action");

		GroupMember member = this.convertToEntity(memberDTO);
		
		member.setUnionDate(new Date());
		repository.save(member);

	}

	public List<GroupMember> getAll() {
		return repository.findAll();
	}

	public GroupMember getById(Integer idGroup, Integer idProfile) {
		return repository.findById(new GroupMemberPK(idGroup, idProfile)).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Group member with ID group " + idGroup + " and ID profile " + idProfile + " not found."));
	}

	public void delete(Integer idGroup, Integer idProfile) {
		if (repository.existsByGroupIdGroupAndProfileIdProfileAndRole(idGroup, idProfile, Role.ADMIN)) {
			GroupMember oldestMember = repository.findFirstNonAdminByGroupIdGroupOrderByUnionDateAsc(idGroup);
			oldestMember.setRole(Role.ADMIN);
			repository.save(oldestMember);
		}

		repository.deleteById(new GroupMemberPK(idGroup, idProfile));

		// if(!repository.existsGroupMembersByGroupId(idGroup))
		
	}

	public void delete(Integer idGroup, Integer idProfile, Integer idAdmin) {
		if (!repository.existsByGroupIdGroupAndProfileIdProfileAndRole(idGroup, idAdmin, Role.ADMIN))
			throw new InvalidCredentialsException("Unauthorized action");

		if (repository.existsByGroupIdGroupAndProfileIdProfileAndRole(idGroup, idProfile, Role.ADMIN)) {
			GroupMember oldestMember = repository.findFirstNonAdminByGroupIdGroupOrderByUnionDateAsc(idGroup);
			oldestMember.setRole(Role.ADMIN);
			repository.save(oldestMember);
		}

		repository.deleteById(new GroupMemberPK(idGroup, idProfile));
	}

	public List<GroupMember> getAll(Integer idGroup, int page, int pageSize) {
		PageRequest pageReq = PageRequest.of(page, pageSize);
		Page<GroupMember> members = repository.findMembersByIdGroup(idGroup, pageReq);
		return members.getContent();
	}

	public boolean validateGroupAdmin(Integer idGroup, Integer idProfile, Role role) {
		return repository.existsByGroupIdGroupAndProfileIdProfileAndRole(idGroup, idProfile, role);
	}
}
