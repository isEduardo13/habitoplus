package com.habitoplus.habitoplusback.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.Exception.InvalidCredentialsException;
import com.habitoplus.habitoplusback.Exception.UserAlreadyExistsException;
import com.habitoplus.habitoplusback.Model.Account;
import com.habitoplus.habitoplusback.Model.Profile;
import com.habitoplus.habitoplusback.Repository.AccountRepository;
import com.habitoplus.habitoplusback.Repository.ProfileRepository;
import com.habitoplus.habitoplusback.dto.ForgotPasswordRequest;
import com.habitoplus.habitoplusback.dto.RegisterRequest;

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

    public RegisterRequest register(RegisterRequest request) {
        if (accountRepository.findByEmail(request.getEmail()) != null) {
            throw new UserAlreadyExistsException("User with email " + request.getEmail() + " already exists");
        }
        Account account = new Account();
        account.setEmail(request.getEmail());
        account.setPassword(request.getPassword());
        account.setStatus(true);
            Profile profile = new Profile();
            profile.Inicializar();
            profileRepository.save(profile);
            account.setProfile(profile);
        

        accountRepository.save(account);
        return request;
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

    public void logout(Integer id) {
        Optional<Profile> optionalProfile = profileRepository.findById(id);
        Profile profileValid = optionalProfile.orElseThrow(() -> new NoSuchElementException("Profile not found"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = LocalDateTime.now().format(formatter);
        profileValid.setLastConnection(formattedDate);

        profileRepository.save(profileValid);
    }

}