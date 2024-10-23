package com.habitoplus.habitoplusback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.habitoplus.habitoplusback.dto.HabitDTO;
import com.habitoplus.habitoplusback.enums.Priority;
import com.habitoplus.habitoplusback.model.Category;
import com.habitoplus.habitoplusback.model.Habit;
import com.habitoplus.habitoplusback.model.Profile;
import com.habitoplus.habitoplusback.model.Streak;
import com.habitoplus.habitoplusback.repository.HabitRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HabitService {
    @Autowired
    private HabitRepository repo;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private StreakService streakService;

    @Autowired
    private ProfileService profileService;

    public List<Habit> getAll() {
        return repo.findAll();
    }

    public Habit getById(Integer idHabit) {
        return repo.findById(idHabit).get();
    }

    public List<Habit> getHabitsByProfile(Integer idProfile) {
        Profile profile = profileService.getProfileById(idProfile);
        return profile.getHabits();
    }

    public void save(HabitDTO habitDTO) {
        Habit habit = new Habit();
        habit.setDescription(habitDTO.getDescription());
        habit.setStatus(false);
        habit.setPriority(habitDTO.getPriority());
        habit.setHabit_name(habitDTO.getHabitName());
        Category category = categoryService.getById(habitDTO.getCategoryId());
        habit.setCategory(category);
        Streak streak = streakService.getByStreaktId(habitDTO.getStreakId());
        habit.setStreak(streak);
        Profile profile = profileService.getProfileById(habitDTO.getProfileId());
        habit.setProfile(profile);
        repo.save(habit);
    }

    public void save(Integer idHabit, HabitDTO habitDTO) {
        Habit habitTemp = repo.findById(idHabit).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Habit with ID " + idHabit + " not found"));
        if (habitTemp != null) {
            Category category = categoryService.getById(habitDTO.getCategoryId());
            Streak streak = streakService.getByStreaktId(habitDTO.getStreakId());
            Profile profile = profileService.getProfileById(habitDTO.getProfileId());
            habitTemp.setCategory(category);
            habitTemp.setDescription(habitDTO.getDescription());
            habitTemp.setPriority(habitDTO.getPriority());
            habitTemp.setStreak(streak);
            habitTemp.setProfile(profile);
            habitTemp.setHabit_name(habitDTO.getHabitName());
            repo.save(habitTemp);
        }
    }

    public void save(Integer idHabit, Boolean status) {
        Habit habit = repo.findById(idHabit)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Habit with ID " + idHabit + " not found"));
        habit.setStatus(status);
        repo.save(habit);
    }

    public void delete(Integer idHabit) {
        repo.deleteById(idHabit);
    }

    public List<Habit> getHabitsByCategory(Integer idProfile, Integer idCategory) {
        return repo.findHabitsByCategory(idProfile, idCategory);
    }

    public List<Habit> getHabitsByPriority(Integer idProfile, Priority priority) {
        return repo.findHabitsByPriority(idProfile, priority);
    }
    
}