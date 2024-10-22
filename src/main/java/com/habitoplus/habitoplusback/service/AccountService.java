package com.habitoplus.habitoplusback.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.habitoplus.habitoplusback.repository.AccountRepository;
import com.habitoplus.habitoplusback.repository.ProfileRepository;
import jakarta.transaction.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.habitoplus.habitoplusback.exception.UserAlreadyExistsException;
import com.habitoplus.habitoplusback.model.Account;
import com.habitoplus.habitoplusback.model.Pixela;
import com.habitoplus.habitoplusback.model.Profile;
import com.habitoplus.habitoplusback.model.Streak;
import com.habitoplus.habitoplusback.repository.AccountRepository;
import com.habitoplus.habitoplusback.repository.PixelaRepository;
import com.habitoplus.habitoplusback.repository.ProfileRepository;
import com.habitoplus.habitoplusback.repository.StreakRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private StreakRepository streakRepository;
    @Autowired
    private PixelaService pixelaService;
    @Autowired
    private PixelaRepository pixelaRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> getAllAccountsWithPaginated(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Account> pageResult = accountRepository.findAll(pageRequest);
        return pageResult.getContent();
    }

    public Account getAccountById(int id) {
        if (accountRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException();


        }
        return accountRepository.findById(id).get();
    }

    public Optional<Account> getAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public List<Account> getAccountsByEmail(String email) {
        return accountRepository.getAccoutsByEmail(email);
    }

    public Account addAccount(Account account, boolean isNotMinor, String thanksCode) {
        // Verificar si el correo ya existe en el sistema
        if (accountRepository.findByEmail(account.getEmail()) != null) {
            throw new UserAlreadyExistsException("User with email " + account.getEmail() + " already exists");
        }

        // Crear o reutilizar el perfil
        Profile profile;
        if (account.getProfile() == null) {
            profile = new Profile();
            profile.Inicializar(); // Método que inicializa el perfil con valores predeterminados
            profileRepository.save(profile);
            account.setProfile(profile);
        } else {
            profile = account.getProfile();
            profileRepository.save(profile);
        }

        // Crear un nuevo Streak (rachas)
        Streak streak = new Streak();
        streak.setConsecutiveDays(0);
        streak.setStartDate(null);
        streak.setEndDate(null);
        streak.setProfile(profile);
        streakRepository.save(streak);

        // Generar token para la cuenta de Pixela
        String pixelaToken = generatePixelaToken();

        // Intentar crear la cuenta de Pixela a través del servicio
        boolean pixelaCreated = pixelaService.createPixelaAccount(account.getEmail(), pixelaToken, isNotMinor, thanksCode);
        if (pixelaCreated) {
            // Guardar la cuenta de la aplicación si Pixela fue creada exitosamente
            Account savedAccount = accountRepository.save(account);

            // Crear y asociar Pixela a la cuenta
            Pixela pixela = new Pixela();
            pixela.setUsername(account.getEmail()); // El username en Pixela se basa en el email
            pixela.setToken(pixelaToken);
            pixela.setAccount(savedAccount);
            pixelaRepository.save(pixela);

            // Asociar el streak al perfil de la cuenta
            profile.setStreak(streak);

            return savedAccount;
        } else {
            // Si la cuenta de Pixela no se pudo crear, lanza una excepción
            throw new RuntimeException("Failed to create Pixela account for user: " + account.getEmail());
        }
    }

    // Método para generar un token de Pixela (esto puede ser dinámico si lo prefieres)
    private String generatePixelaToken() {
        // Aquí puedes generar un token más seguro si lo necesitas
        return "vtoken2889hsjwi";
    }


    public Account updateAccount(Account account) {

        if (accountRepository.findById(account.getIdAccount()).isEmpty()) {
            throw new NoSuchElementException();
        }
        if (accountRepository.findById(account.getIdAccount()).isEmpty()) {
            throw new NoSuchElementException();
        }
        Account existingAccount = accountRepository.findById(account.getIdAccount()).get();


        existingAccount.setEmail(account.getEmail());
        existingAccount.setPassword(account.getPassword());
        existingAccount.setStatus(account.getStatus());

        existingAccount.setStatus(account.getStatus());

        return accountRepository.save(existingAccount);
    }

    public boolean deleteAccount(int id) {


        if (accountRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException();
        }
        Account existingAccount = accountRepository.findById(id).get();

        if (existingAccount != null) {
            existingAccount.setStatus(false);
            accountRepository.save(existingAccount);
            return true;
        }
        return false;
    }


    public boolean DefinitiveDeleteAccount(int id) {

        if (accountRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException();
        }
        Account existingAccount = accountRepository.findById(id).get();
        profileRepository.delete(existingAccount.getProfile());
        accountRepository.delete(existingAccount);
        return true;
    }
}