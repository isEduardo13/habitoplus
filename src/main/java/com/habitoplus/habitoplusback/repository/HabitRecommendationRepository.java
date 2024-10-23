package com.habitoplus.habitoplusback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habitoplus.habitoplusback.model.HabitRecommendation;

@Repository
public interface HabitRecommendationRepository extends JpaRepository<HabitRecommendation, Integer> {

}
