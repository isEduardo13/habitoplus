package com.habitoplus.habitoplusback.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.Model.Streak;
import com.habitoplus.habitoplusback.Repository.StreakRepository;
import com.habitoplus.habitoplusback.dto.StreakDTO;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StreakService {
    @Autowired
    private StreakRepository streakRepository;
    //obtener todas las rachas
    public List<Streak> getAllStreaks(){
        return streakRepository.findAll();
    }
    
    public void createStreak(Streak streak){
        streakRepository.save(streak);
    }
    
    public StreakDTO getStreak(int idStreak){
        Optional<Streak> optionalStreak = streakRepository.findById(idStreak);
        if (optionalStreak.isPresent()) {
            Streak streak = optionalStreak.get();
            // Usando el constructor de Streak
            return new StreakDTO(streak.getStartDate(), streak.getConsecutiveDays());
        }
        return null; 
    }
    // al tener un dia en el que no se cumplan objetivos la racha se actualiza a 0 automaticamente
    public void putStreak(int id) {
        Streak streak = streakRepository.findById(id).orElse(null);
        if (streak != null) {
            streak.setConsecutiveDays(0);
            streakRepository.save(streak);
        }
    }

}
