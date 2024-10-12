package com.habitoplus.habitoplusback.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.habitoplus.habitoplusback.Model.Streak;
import com.habitoplus.habitoplusback.Service.StreakService;
import com.habitoplus.habitoplusback.Dto.StreakDTO;


@RestController
@RequestMapping("/streaks")
@CrossOrigin(origins="*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class StreakController {
    @Autowired
    private StreakService streakService;

    @GetMapping
    public ResponseEntity<List<Streak>> getAllStreaks(){
        List<Streak> streaks = streakService.getAllStreaks();
        return ResponseEntity.ok(streaks);
    }
    //crear una nueva racha
    @PostMapping
    public ResponseEntity<?> createStreak(@RequestBody Streak streak){
        streakService.createStreak(streak);
        System.out.println(streak.toString());
        return new ResponseEntity<String>("Saved record", HttpStatus.OK);
    }
    //obtener racha por id
    @GetMapping("/{idStreak}")
    public ResponseEntity<StreakDTO> getStreak(@PathVariable("idStreak") int idStreak){
        StreakDTO streakDTO = streakService.getStreak(idStreak);
        if(streakDTO != null){
            return ResponseEntity.ok(streakDTO);
        }
        return ResponseEntity.notFound().build();
    }

    //reiniciar racha a 0 si el se detecta que el usuario no ha cumplido sus habitos en un solo dia
    @PutMapping("/{idStreak}")
    public ResponseEntity<Void> putStreak(@PathVariable("idSreak") int idStreak){
        streakService.putStreak(idStreak);
        return ResponseEntity.noContent().build();
    }
}
