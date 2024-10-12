package com.habitoplus.habitoplusback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habitoplus.habitoplusback.model.Request;
import com.habitoplus.habitoplusback.model.RequestPK;

@Repository
public interface RequestRepository extends JpaRepository<Request, RequestPK>{
    List<Request> findByGroupIdGroupOrderByDateRequestDesc(Integer idGroup);
}
