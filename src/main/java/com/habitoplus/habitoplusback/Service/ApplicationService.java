package com.habitoplus.habitoplusback.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.Model.Application;
import com.habitoplus.habitoplusback.Repository.ApplicationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ApplicationService {
    @Autowired
	private ApplicationRepository repo;

    //Get requests
	public List<Application> getAll() {
		return repo.findAll();
	}

    public Application getByIdApplication(Integer idApplication) {
		return repo.findById(idApplication).get();
	}

	public void save(Application application) {
		repo.save(application);
	}

	public void delete(Integer idApplication) {
		repo.deleteById(idApplication);
	}

}
