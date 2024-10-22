package com.habitoplus.habitoplusback.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import com.habitoplus.habitoplusback.Model.Habit;
import com.habitoplus.habitoplusback.Repository.HabitRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HabitService {
    @Autowired
    private HabitRepository repo;


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
        Habit habitTemp = repo.findById(idHabit).orElse(null);
        if (habitTemp != null) {
            habit.setIdHabit(idHabit);
            repo.save(habit);
        }
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