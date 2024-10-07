package com.habitoplus.habitoplusback.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.Model.Group;
import com.habitoplus.habitoplusback.Model.GroupMember;
import com.habitoplus.habitoplusback.Repository.GroupRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GroupService {
	
    @Autowired
	private GroupRepository groupRepository;

	@Autowired
	private RequestService requestService;

	@Autowired
	private GroupMemberService memberService;

	public List<Group> getAll() {
		return groupRepository.findAll();
	}

    public Group getByIdGroup(Integer idGroup) {
		return groupRepository.findById(idGroup).get();
	}

	public void save(Group group) {
		groupRepository.save(group);
	}

	public void delete(Integer idGroup) {
		groupRepository.deleteById(idGroup);
	}

	public List<GroupMember> getGroupMembers(Integer idGroup){
		return groupRepository.findById(idGroup).get().getMembers();
	}
	
	
    
}
