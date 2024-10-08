package com.habitoplus.habitoplusback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habitoplus.habitoplusback.Model.Habit;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Integer> {
}