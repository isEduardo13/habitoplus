package com.habitoplus.habitoplusback.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.dto.GroupDto;
import com.habitoplus.habitoplusback.dto.GroupMemberWithGroupDto;
import com.habitoplus.habitoplusback.model.GroupMember;
import com.habitoplus.habitoplusback.model.GroupMemberPK;
import com.habitoplus.habitoplusback.repository.GroupMemberRepository;
import com.habitoplus.habitoplusback.enums.Role;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GroupMemberService {
	@Autowired
	private GroupMemberRepository repository;

    public GroupMemberWithGroupDto convertToGroupDto(GroupMember groupMember) {
        GroupDto groupDto = new GroupDto(
                groupMember.getGroup().getIdGroup(),
                groupMember.getGroup().getName(),
                groupMember.getGroup().getDescription(),
                groupMember.getGroup().getGroupType()
        );
        return new GroupMemberWithGroupDto(groupDto, groupMember.getRole(), groupMember.getUnionDate());
    }

	public void save(GroupMember member) {
		member.setUnionDate(new Date());
		repository.save(member);
	}

	public List<GroupMember> getAll() {
		return repository.findAll();
	}

	public GroupMember getById(Integer idGroup, Integer idProfile) {
		GroupMemberPK groupMemberPK = new GroupMemberPK();
		groupMemberPK.setGroup(idGroup);
		groupMemberPK.setProfile(idProfile);
		return repository.findById(groupMemberPK).get();
	}

	public void delete(Integer idGroup, Integer idProfile) {
		GroupMemberPK groupMemberPK = new GroupMemberPK();
		groupMemberPK.setGroup(idGroup);
		groupMemberPK.setProfile(idProfile);

		if(repository.existsByGroupIdGroupAndProfileIdProfileAndRole(idGroup, idProfile, Role.ADMIN)){
			GroupMember oldestMember = repository.findFirstByGroupIdGroupOrderByUnionDateAsc(idGroup);
			oldestMember.setRole(Role.ADMIN);
			repository.save(oldestMember);
		}

		repository.deleteById(groupMemberPK);
	}

	public List<GroupMember> getAll(int page, int pageSize){
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<GroupMember> members = repository.findAll(pageReq);
        return members.getContent();
    }

}
