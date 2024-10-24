package com.habitoplus.habitoplusback.service;

import java.util.Date;
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
    private HabitRepository habitRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private StreakService streakService;

    @Autowired
    private ProfileService profileService;

    public List<Habit> getAll() {
        return habitRepository.findAll();
    }

    public Habit getById(Integer idHabit) {
        return habitRepository.findById(idHabit).get();
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
        habitRepository.save(habit);
    }

    public void updateHabit(Integer idHabit, HabitDTO habitDTO) {
        Habit habitTemp = habitRepository.findById(idHabit).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
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
            habitRepository.save(habitTemp);
        }
    }

    public void update(Integer idHabit, Boolean status) {
        Habit habit = habitRepository.findById(idHabit)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Habit with ID " + idHabit + " not found"));
        habit.setStatus(status);
        habitRepository.save(habit);
    }

    public void deleteHabit(Integer idHabit) {
        habitRepository.deleteById(idHabit);
    }

    public void updateHabitStatus(Integer idHabit, Integer idProfile, Boolean status) {
        Habit habit = habitRepository.findById(idHabit)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Habit not found"));
    
        Profile habitProfile = habit.getProfile();
    
        if (!habitProfile.getIdProfile().equals(idProfile)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                "Habit does not belong to the provided profile. Habit profile ID: " + habitProfile.getIdProfile() + 
                " , Provided profile ID: " + idProfile);
        }
    
        habit.setStatus(status);
    
        Streak streak = habit.getStreak();
    
        if (status) {
            if (streak.getCompleteHabits() == null) {
                streak.setCompleteHabits(0);
            }
    
            streak.setCompleteHabits(streak.getCompleteHabits() + 1);
            if (streak.getStartDate() == null) {
                streak.setStartDate(new Date());
            }
    
            if (streak.getCompleteHabits() >= 5) {
                streak.setConsecutiveDays(streak.getConsecutiveDays() + 1);
                streak.setCompleteHabits(0);
            }
        } else {
            if (streak.getCompleteHabits() == 0) {
                streak.setEndDate(new Date());  
                streak.setStartDate(null);     
                streak.setConsecutiveDays(0);  
            }
        }
    
        streakService.createStreak(streak);
        habitRepository.save(habit);
    }

    public List<Habit> getHabitsByCategory(Integer idProfile, Integer idCategory) {
        return habitRepository.findHabitsByCategory(idProfile, idCategory);
    }

    public List<Habit> getHabitsByPriority(Integer idProfile, Priority priority) {
        return habitRepository.findHabitsByPriority(idProfile, priority);
    }
    
}