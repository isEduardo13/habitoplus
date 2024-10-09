package com.habitoplus.habitoplusback.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.Model.Category;
import com.habitoplus.habitoplusback.Model.Habit;
import com.habitoplus.habitoplusback.Repository.CategoryRepository;
import com.habitoplus.habitoplusback.Repository.HabitRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HabitService {
    @Autowired
    private HabitRepository repo;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Habit> getAll() {
        return repo.findAll();
    }

    public Habit getByHabitId(Integer idHabit) {
        return repo.findById(idHabit).get();
    }

    public void save(Habit habit) {
        // Verificar si la categoría ya existe
        Category category = habit.getCategory_id();
        if (category.getCategory_id() != null) {
            category = categoryRepository.findById(category.getCategory_id())
                    .orElseThrow(() -> new NoSuchElementException("Category not found"));
        }

        habit.setCategory_id(category);
        repo.save(habit);
    }

    public void update(Integer idHabit, Habit habit) {
        if (repo.findById(idHabit).isPresent()) {
            repo.save(habit);
        }
    }

    public void updateStatus(Integer idHabit, boolean status) {
        if (repo.findById(idHabit).isPresent()) {
            Habit habit = repo.findById(idHabit).get();
            habit.setStatus(status);
            repo.save(habit);
        }
    }

    public void deleteHabit(Integer idHabit) {
        repo.deleteById(idHabit);
    }

}