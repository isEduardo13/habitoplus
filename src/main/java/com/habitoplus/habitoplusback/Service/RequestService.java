package com.habitoplus.habitoplusback.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.Model.GroupMember;
import com.habitoplus.habitoplusback.Model.Request;
import com.habitoplus.habitoplusback.Model.RequestPK;
import com.habitoplus.habitoplusback.Repository.RequestRepository;
import com.habitoplus.habitoplusback.enums.Role;
import com.habitoplus.habitoplusback.enums.Status;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RequestService {
    @Autowired
	private RequestRepository repository;

	@Autowired
	private GroupMemberService memberService;

	public List<Request> getAll() {
		return repository.findAll();
	}

	public List<Request> getAll(Integer idGroup){
		return repository.findByGroupIdGroupOrderByDateRequestDesc(idGroup);
	}

    public Request getById(Integer idGroup, Integer idProfile) {
		RequestPK requestPK = new RequestPK();
		requestPK.setGroup(idGroup);
		requestPK.setProfile(idProfile);
		return repository.findById(requestPK).get();
	}

	public void save(Request request) {
		request.setDateRequest(new Date());
		repository.save(request);
	}

	public void delete(Integer idGroup, Integer idProfile) {
		RequestPK requestPK = new RequestPK();
		requestPK.setGroup(idGroup);
		requestPK.setProfile(idProfile);
		repository.deleteById(requestPK);
	}

	public void acceptUser(Integer idGroup, Integer idProfile){
		RequestPK requestPK = new RequestPK();
		requestPK.setGroup(idGroup);
		requestPK.setProfile(idProfile);
		Request auxRequest = repository.findById(requestPK).get();
		Request request = auxRequest;
		request.setStatus(Status.ADMITTED);

		GroupMember gm = new GroupMember();
		gm.setProfile(request.getProfile());
		gm.setGroup(request.getGroup());
		gm.setRole(Role.MEMBER);
		gm.setUnionDate(new Date());
		memberService.save(gm);
	}

	public List<Request> getAll(int page, int pageSize){
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Request> requests = repository.findAll(pageReq);
        return requests.getContent();
    }
	
}
