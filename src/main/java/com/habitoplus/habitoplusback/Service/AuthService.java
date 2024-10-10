package com.habitoplus.habitoplusback.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.NoSuchElementException;

import com.habitoplus.habitoplusback.Dto.ForgotPasswordRequest;
import com.habitoplus.habitoplusback.Exception.InvalidCredentialsException;
import com.habitoplus.habitoplusback.Exception.UserAlreadyExistsException;
import com.habitoplus.habitoplusback.Model.Account;
import com.habitoplus.habitoplusback.Model.Profile;
import com.habitoplus.habitoplusback.Repository.AccountRepository;
import com.habitoplus.habitoplusback.Repository.ProfileRepository;

@Service
public class AuthService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProfileRepository profileRepository;

    public String login(String email, String password) {
        Account account = accountRepository.findByEmail(email);
        if (account != null && account.getPassword().equals(password)) {
            return "generated-token";
        } else {
            throw new InvalidCredentialsException("Email or password incorrect");
        }
    }

    public Account register(Account account) {
         if (accountRepository.findByEmail(account.getEmail()) != null) {
            throw new UserAlreadyExistsException("Email already registered");   
        }
        return accountRepository.save(account);
        }

        public void forgotPassword(ForgotPasswordRequest request) {
            String email = request.getEmail();
            System.out.println("Buscando cuenta con email: " + email); // Mensaje de depuración
            Account account = accountRepository.findByEmail(email);
            if (account != null) {
                System.out.println("Cuenta encontrada: " + account.getEmail()); // Mensaje de depuración
                // send email with password reset code
            } else {
                System.out.println("Cuenta no encontrada para el email: " + email); // Mensaje de depuración
                throw new InvalidCredentialsException("Email not found");
            }
        }

        public void logout( Integer id) {
        Profile profileValid = profileRepository.findById(id).get();
        if (profileValid == null) {
            throw new NoSuchElementException("Profile not found");
            
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = LocalDate.now().format(formatter);
        profileValid.setLastConnection(formattedDate);
        profileRepository.save(profileValid);

        
    }
}