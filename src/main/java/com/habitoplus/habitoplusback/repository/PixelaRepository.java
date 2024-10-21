package com.habitoplus.habitoplusback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habitoplus.habitoplusback.model.Pixela;

@Repository
public interface PixelaRepository extends JpaRepository<Pixela, Integer>{

}
