package com.habitoplus.habitoplusback.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.Model.GroupMember;
import com.habitoplus.habitoplusback.Repository.GroupMemberRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GroupMemberService {
    @Autowired
	private GroupMemberRepository repo;

    //Add users from group
    public void save(GroupMember member) {
		repo.save(member);
	}

    //Get users from group
    public List<GroupMember> getAll() {
		return repo.findAll();
	}

    //Get member from group by id
    public GroupMember getByIdGroupMember(Integer idGroupMember) {
		return repo.findById(idGroupMember).get();
	}

    //Delete member from group
	public void delete(Integer idGroupMember) {
		repo.deleteById(idGroupMember);
	}
}
