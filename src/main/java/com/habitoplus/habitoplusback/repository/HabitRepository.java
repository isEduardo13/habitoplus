package com.habitoplus.habitoplusback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habitoplus.habitoplusback.model.Habit;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Integer> {
}