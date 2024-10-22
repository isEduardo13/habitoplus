package com.habitoplus.habitoplusback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.habitoplus.habitoplusback.model.Habit;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Integer> {
    @Query("SELECT h FROM Habit h WHERE h.profile.idProfile = :idProfile AND h.category.idCategory = :idCategory")
    List<Habit> findHabitsByCategory(@Param("idProfile") Integer idProfile, @Param("idCategory") Integer idCategory);    
}