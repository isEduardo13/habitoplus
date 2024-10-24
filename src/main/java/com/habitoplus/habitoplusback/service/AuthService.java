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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.habitoplus.habitoplusback.exception.InvalidCredentialsException;
import com.habitoplus.habitoplusback.exception.UserAlreadyExistsException;
import com.habitoplus.habitoplusback.model.Account;
import com.habitoplus.habitoplusback.model.Profile;
import com.habitoplus.habitoplusback.repository.AccountRepository;
import com.habitoplus.habitoplusback.repository.PhotoRepository;
import com.habitoplus.habitoplusback.repository.ProfileRepository;
import com.habitoplus.habitoplusback.repository.StreakRepository;
import com.habitoplus.habitoplusback.dto.ForgotPasswordRequest;
import com.habitoplus.habitoplusback.dto.RegisterRequest;
import com.habitoplus.habitoplusback.model.Pixela;
import com.habitoplus.habitoplusback.model.Streak;
import com.habitoplus.habitoplusback.repository.PixelaRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PixelaService pixelaService;

    private final PhotoRepository photoRepository;

    private final AccountRepository accountRepository;

    private final ProfileRepository profileRepository;

    private final StreakRepository streakRepository;

    private final JsonWebTokenService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final PixelaRepository pixelaRepository;

    public AuthResponseDTO login(LoginRequest request) {
        if (!accountRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Account user = accountRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.getToken(user);

        return AuthResponseDTO.builder().token(token).build();
    }

    public AuthResponseDTO register(RegisterRequest request, boolean isNotMinor, String thanksCode) {
        // Verificar si el correo ya existe en el sistema
        if (accountRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with email " + request.getEmail() + " already exists");
        }

        // Crear la cuenta (Account)
        Account account = Account.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(RoleAccounts.USER)
                .status(true)
                .build();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDate = LocalDate.now().format(dateFormatter);
        String formattedLastConnection = LocalDateTime.now().format(dateTimeFormatter);

        // Crear el perfil (Profile)
        Profile profile = Profile.builder()
                .age(request.getAge())
                .dateOfRegistration(formattedDate)
                .status(true)
                .lastConnection(formattedLastConnection)
                .gender(request.getGender())
                .numberPhone(request.getNumberPhone())
                .preferences(request.getPreferences())
                .description(request.getDescription())
                .lastName(request.getLastName())
                .name(request.getName())
                .username(request.getUSername())
                .birthDate(request.getBirthDate())
                .build();

        // Primero guarda el perfil en la base de datos
        profileRepository.save(profile);

        // Crear el streak (racha) después de haber guardado el perfil
        Streak streak = new Streak();
        streak.setConsecutiveDays(0);
        streak.setStartDate(null);
        streak.setEndDate(null);
        streak.setProfile(profile);

        // Ahora guarda el streak
        streakRepository.save(streak);

        // Asocia el streak al perfil y guarda el perfil actualizado
        profile.setStreak(streak);
        profileRepository.save(profile);

        account.setProfile(profile);

        // Resto de tu lógica para Pixela
        String pixelaToken = generatePixelaToken();

        boolean pixelaCreated = pixelaService.createPixelaAccount(request.getEmail(), pixelaToken, isNotMinor, thanksCode);
        if (pixelaCreated) {
            Account savedAccount = accountRepository.save(account);

            Pixela pixela = new Pixela();
            pixela.setUsername(request.getEmail());
            pixela.setToken(pixelaToken);
            pixela.setAccount(savedAccount);
            pixelaRepository.save(pixela);
        } else {
            throw new UserAlreadyExistsException("User with email " + request.getEmail() + " already exists");
        }

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

    private String generatePixelaToken() {
        return "vtoken2889hsjwi";
    }

}
