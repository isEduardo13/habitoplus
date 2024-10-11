package com.habitoplus.habitoplusback.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.habitoplus.habitoplusback.Model.Category;
import com.habitoplus.habitoplusback.Model.Habit;
import com.habitoplus.habitoplusback.Model.Profile;
import com.habitoplus.habitoplusback.Model.Streak;
import com.habitoplus.habitoplusback.Repository.CategoryRepository;
import com.habitoplus.habitoplusback.Repository.HabitRepository;
import com.habitoplus.habitoplusback.Repository.ProfileRepository;
import com.habitoplus.habitoplusback.Repository.StreakRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HabitService {
    @Autowired
    private HabitRepository repo;

    @Autowired
    private StreakRepository sRepo;

    @Autowired
    private ProfileRepository pRepo;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Habit> getAll() {
        return repo.findAll();
    }

    public Habit getByHabitId(Integer idHabit) {
        return repo.findById(idHabit).get();
    }

    public void save(Habit habit) {
        repo.save(habit);
    }
    

    public void update(Integer idHabit, Habit habit) {
        // Buscar el hábito existente
        Habit existingHabit = repo.findById(idHabit)
                .orElseThrow(() -> new NoSuchElementException("Habit with ID " + idHabit + " not found"));

        // Actualizar solo los campos no nulos del objeto recibido
        if (habit.getDescription() != null) {
            existingHabit.setDescription(habit.getDescription());
        }
        if (habit.getStatus() != null) {
            existingHabit.setStatus(habit.getStatus());
        }
        if (habit.getPriority() != null) {
            existingHabit.setPriority(habit.getPriority());
        }
        if (habit.getHabit_name() != null) {
            existingHabit.setHabit_name(habit.getHabit_name());
        }

        // Actualizar la categoría si se proporciona
        if (habit.getCategory() != null && habit.getCategory().getIdCategory() != null) {
            Category category = categoryRepository.findById(habit.getCategory().getIdCategory())
                    .orElseThrow(() -> new NoSuchElementException("Category not found"));
            existingHabit.setCategory(category);
        }

        // Actualizar la racha si se proporciona
        if (habit.getStreak() != null && habit.getStreak().getIdStreak() != null) {
            Streak streak = sRepo.getById(habit.getStreak().getIdStreak());
            if (streak != null) {
                existingHabit.setStreak(streak);
            }
        }

        // Actualizar el perfil si se proporciona
        if (habit.getProfile() != null && habit.getProfile().getIdProfile() != 0) {
            Profile profile = pRepo.getById(habit.getProfile().getIdProfile());
            if (profile != null) {
                existingHabit.setProfile(profile);
            }
        }

        // Guardar el hábito actualizado
        repo.save(existingHabit);
    }

    public void update(Integer idHabit, Boolean status) {
        Habit habit = repo.findById(idHabit)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Habit with ID " + idHabit + " not found"));

        habit.setStatus(status);

        repo.save(habit);
    }

    public void deleteHabit(Integer idHabit) {
        repo.deleteById(idHabit);
    }

}