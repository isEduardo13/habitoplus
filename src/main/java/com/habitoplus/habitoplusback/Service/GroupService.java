package com.habitoplus.habitoplusback.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.Model.Group;
import com.habitoplus.habitoplusback.Repository.GroupRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GroupService {
    @Autowired
	private GroupRepository repo;

	public List<Group> getAll() {
		return repo.findAll();
	}

    public Group getByIdGroup(Integer idGroup) {
		return repo.findById(idGroup).get();
	}

	public void save(Group group) {
		repo.save(group);
	}

	public void delete(Integer idGroup) {
		repo.deleteById(idGroup);
	}
    
}
