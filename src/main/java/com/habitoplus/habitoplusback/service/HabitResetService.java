package com.habitoplus.habitoplusback.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.habitoplus.habitoplusback.model.Habit;
import com.habitoplus.habitoplusback.model.Streak;
import com.habitoplus.habitoplusback.repository.HabitRepository;
import com.habitoplus.habitoplusback.repository.StreakRepository;

public class HabitResetService {

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private StreakRepository streakRepository;

    @Scheduled(cron = "0 0 0 * * ?")  // Tarea programada para ejecutarse a medianoche todos los días
    public void resetHabitStatusesAndCompleteHabits() {
        // Obtener todos los hábitos
        List<Habit> allHabits = habitRepository.findAll();

        // Reiniciar el estado de todos los hábitos a 'false'
        allHabits.forEach(habit -> {
            habit.setStatus(false);
        });

        habitRepository.saveAll(allHabits);

        // Obtener todas las rachas
        List<Streak> allStreaks = streakRepository.findAll();

        // Reiniciar el contador de hábitos completados
        allStreaks.forEach(streak -> {
            streak.setCompleteHabits(0);
        });

        streakRepository.saveAll(allStreaks);
    }
}
