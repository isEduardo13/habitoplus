package com.habitoplus.habitoplusback.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.Model.GroupMember;
import com.habitoplus.habitoplusback.Model.Request;
import com.habitoplus.habitoplusback.Repository.RequestRepository;
import com.habitoplus.habitoplusback.enums.Status;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RequestService {
    @Autowired
	private RequestRepository repo;

	@Autowired
	private GroupMemberService memberService;

    //Get requests
	public List<Request> getAll() {
		return repo.findAll();
	}

    public Request getByIdRequest(Integer idRequest) {
		return repo.findById(idRequest).get();
	}

	public void save(Request request) {
		repo.save(request);
	}

	public void delete(Integer idRequest) {
		repo.deleteById(idRequest);
	}

	public void acceptUser(Integer idRequest){
		Request auxRequest = repo.findById(idRequest).get();
		Request request = auxRequest;
		request.setStatus(Status.ADMITTED);

		GroupMember gm = new GroupMember();
		gm.setProfile(request.getProfile());
		gm.setGroup(request.getGroup());
		gm.setRole("member");
		gm.setUnionDate(new Date());
		memberService.save(gm);
	}
}
