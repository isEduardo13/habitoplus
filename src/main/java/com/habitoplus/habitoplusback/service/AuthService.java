package com.habitoplus.habitoplusback.service;

import com.habitoplus.habitoplusback.dto.AuthResponseDTO;
import com.habitoplus.habitoplusback.dto.LoginRequest;
import com.habitoplus.habitoplusback.enums.Role;
import com.habitoplus.habitoplusback.enums.RoleAccounts;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AuthService {

    private final AccountRepository accountRepository;

    private final ProfileRepository profileRepository;

    private final JsonWebTokenService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public AuthResponseDTO login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Account user = accountRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.getToken(user);

        return AuthResponseDTO.builder().token(token).build();
    }

    public AuthResponseDTO register(RegisterRequest request) {
        if (accountRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with email " + request.getEmail() + " already exists");
        }
        Account account = Account.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(RoleAccounts.USER)
                .status(true)
                .build();

        Profile profile = new Profile();
        profile.Inicializar();
        profileRepository.save(profile);
        account.setProfile(profile);

        accountRepository.save(account);
        return AuthResponseDTO.builder()
                .token(jwtService.getToken(account))
                .build();
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
