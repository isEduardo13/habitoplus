package com.habitoplus.habitoplusback.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.habitoplus.habitoplusback.dto.HabitDTO;
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

    public Habit getByHabitId(Integer idHabit) {
        return habitRepository.findById(idHabit).get();
    }

    public List<Habit> getHabitsByProfile(Integer idProfile) {
        Profile profile = profileService.getProfileById(idProfile);
        return profile.getHabits();
    }

    public void saveHabit(HabitDTO habitDTO) {
        Category category = categoryService.getByCategoryId(habitDTO.getCategoryId());
        
        Profile profile = profileService.getProfileById(habitDTO.getProfileId());
        
    
        Streak streak = profile.getStreak();
        
    
        Habit habit = new Habit();
        habit.setDescription(habitDTO.getDescription());
        habit.setStatus(habitDTO.getStatus() != null ? habitDTO.getStatus() : false); // Si no se especifica, por defecto es 'false'
        habit.setPriority(habitDTO.getPriority());
        habit.setHabit_name(habitDTO.getHabitName());
        habit.setCategory(category);
        habit.setStreak(streak);
        habit.setProfile(profile);
        habit.setLastCompletedDate(null);
    
        habitRepository.save(habit);
    }

    public void updateHabit(Integer idHabit, HabitDTO habitDTO) {
        Habit habitTemp = habitRepository.findById(idHabit).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Habit with ID " + idHabit + " not found"));
        if (habitTemp != null) {
            Category category = categoryService.getByCategoryId(habitDTO.getCategoryId());
            Streak streak = streakService.getByStreaktId(habitDTO.getStreakId());
            Profile profile = profileService.getProfileById(habitDTO.getProfileId());
            habitTemp.setCategory(category);
            habitTemp.setDescription(habitDTO.getDescription());
            habitTemp.setStatus(habitDTO.getStatus());
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
        // Obtener el hábito por su ID
        Habit habit = habitRepository.findById(idHabit)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Habit not found"));

        // Obtener el perfil asociado al hábito
        Profile habitProfile = habit.getProfile();

        // Verificar si el hábito pertenece al perfil proporcionado
        if (!habitProfile.getIdProfile().equals(idProfile)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                "Habit does not belong to the provided profile. Habit profile ID: " + habitProfile.getIdProfile() + 
                " , Provided profile ID: " + idProfile);
        }

        // Actualizar el estado del hábito
        habit.setStatus(status);

        // Obtener el streak asociado al perfil del hábito
        Streak streak = habit.getStreak();

        if (status) {
            // Verificar si completeHabits es null, y si lo es, inicializarlo a 0
            if (streak.getCompleteHabits() == null) {
                streak.setCompleteHabits(0);
            }

            // Incrementar la cantidad de hábitos completados
            streak.setCompleteHabits(streak.getCompleteHabits() + 1);

            // Si se completaron 5 hábitos
            if (streak.getCompleteHabits() >= 5) {
                // Incrementar los días consecutivos
                streak.setConsecutiveDays(streak.getConsecutiveDays() + 1);
                streak.setEndDate(new Date());  // Actualizar la fecha de fin

                // Reiniciar el contador de hábitos completados
                streak.setCompleteHabits(0);
            }
        }

        // Guardar el estado actualizado del streak
        streakService.createStreak(streak);

        // Guardar el hábito actualizado
        habitRepository.save(habit);
    }

}