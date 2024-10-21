package com.habitoplus.habitoplusback.service;

import com.habitoplus.habitoplusback.dto.AuthResponseDTO;
import com.habitoplus.habitoplusback.dto.LoginRequest;
import com.habitoplus.habitoplusback.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.habitoplus.habitoplusback.exception.InvalidCredentialsException;
import com.habitoplus.habitoplusback.exception.UserAlreadyExistsException;
import com.habitoplus.habitoplusback.model.Account;
import com.habitoplus.habitoplusback.model.Profile;
import com.habitoplus.habitoplusback.repository.AccountRepository;
import com.habitoplus.habitoplusback.repository.ProfileRepository;
import com.habitoplus.habitoplusback.dto.ForgotPasswordRequest;
import com.habitoplus.habitoplusback.dto.RegisterRequest;

@Service
public class AuthService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private JsonWebTokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponseDTO login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails user = accountRepository.findByEmail(request.getEmail()).orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));
        String token = tokenService.getToken(user);
        return AuthResponseDTO.builder().token(token).build();
    }

    public AuthResponseDTO register(RegisterRequest request) {
        if (accountRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with email " + request.getEmail() + " already exists");
        }
        String PasswordEncoder = passwordEncoder.encode(request.getPassword());
        Account account = Account.builder()
                .email(request.getEmail())
                .password(PasswordEncoder)
                .role(Role.USER)
                .status(true)
                .build();


            Profile profile = new Profile();
            profile.Inicializar();
            profileRepository.save(profile);
            account.setProfile(profile);
        

        accountRepository.save(account);
        return AuthResponseDTO.builder().token(tokenService.getToken(account)).build();
    }
      
        

        public void forgotPassword(ForgotPasswordRequest request) {
            String email = request.getEmail();
            System.out.println("Buscando cuenta con email: " + email);
            Optional<Account> account = accountRepository.findByEmail(email);
            if (account.isPresent()) {
                System.out.println("Cuenta encontrada: " + account.get().getEmail());
                // send email with password reset code
            } else {
                System.out.println("Cuenta no encontrada para el email: " + email);
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