package com.habitoplus.habitoplusback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habitoplus.habitoplusback.model.Streak;

@Repository
public interface StreakRepository extends JpaRepository<Streak, Integer>{
}
