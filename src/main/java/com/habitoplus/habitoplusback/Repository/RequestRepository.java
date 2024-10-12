package com.habitoplus.habitoplusback.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habitoplus.habitoplusback.Model.Request;
import com.habitoplus.habitoplusback.Model.RequestPK;

@Repository
public interface RequestRepository extends JpaRepository<Request, RequestPK>{
    List<Request> findByGroupIdGroupOrderByDateRequestDesc(Integer idGroup);
}
