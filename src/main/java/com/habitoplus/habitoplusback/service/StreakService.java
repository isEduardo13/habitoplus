package com.habitoplus.habitoplusback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.model.Streak;
import com.habitoplus.habitoplusback.repository.StreakRepository;
import com.habitoplus.habitoplusback.dto.StreakDTO;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StreakService {
    @Autowired
    private StreakRepository streakRepository;

    // obtener todas las rachas
    public List<Streak> getAllStreaks() {
        return streakRepository.findAll();
    }
    
    public void createStreak(Streak streak) {
        streakRepository.save(streak);
    }
    
    // al consultar solo se extraen los dias consecutivos de racha por un id
    public StreakDTO getStreak(int idStreak){
        Optional<Streak> optionalStreak = streakRepository.findById(idStreak);
        if (optionalStreak.isPresent()) {
            Streak streak = optionalStreak.get();
            // Usando el constructor de Streak
            return new StreakDTO(streak.getStartDate(), streak.getConsecutiveDays());
        }
        return null;
    }

    // al tener un dia en el que no se cumplan objetivos la racha se actualiza a 0
    // automaticamente
    public void putStreak(int id) {
        Streak streak = streakRepository.findById(id).orElse(null);
        if (streak != null) {
            streak.setConsecutiveDays(0);
            streakRepository.save(streak);
        }
    }

    public Streak getByStreaktId(Integer idStreak) {
        return streakRepository.findById(idStreak).get();
    }

}
