package com.habitoplus.habitoplusback.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.enums.RoleAccounts;
import com.habitoplus.habitoplusback.exception.UserAlreadyExistsException;
import com.habitoplus.habitoplusback.model.Account;
import com.habitoplus.habitoplusback.model.Pixela;
import com.habitoplus.habitoplusback.model.Profile;
import com.habitoplus.habitoplusback.model.Streak;
import com.habitoplus.habitoplusback.repository.AccountRepository;
import com.habitoplus.habitoplusback.repository.PixelaRepository;
import com.habitoplus.habitoplusback.repository.ProfileRepository;
import com.habitoplus.habitoplusback.repository.StreakRepository;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;

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

    @Transactional
    public Account addAccount(Account account, boolean isNotMinor, String thanksCode) {
        Optional<Account> existingAccount = accountRepository.findByEmail(account.getEmail());

        if (existingAccount.isPresent()) {
            throw new UserAlreadyExistsException("User with email " + account.getEmail() + " already exists");
        }

        if (account.getStatus() == null) {
            account.setStatus(true); 
        }

        if (account.getRole() == null) {
            account.setRole(RoleAccounts.USER); 
        }

        Profile profile;
        if (account.getProfile() == null) {
            profile = new Profile();
            profile.Inicializar(); 
            profileRepository.save(profile);
            account.setProfile(profile);
        } else {
            profile = account.getProfile();
            profileRepository.save(profile);
        }

        Streak streak = new Streak();
        streak.setConsecutiveDays(0);
        streak.setStartDate(null);
        streak.setEndDate(null);
        streak.setProfile(profile);
        streakRepository.save(streak);

        String pixelaToken = generatePixelaToken();
        boolean pixelaCreated = pixelaService.createPixelaAccount(account.getEmail(), pixelaToken, isNotMinor, thanksCode);
        if (pixelaCreated) {
            Account savedAccount = accountRepository.save(account);

            Pixela pixela = new Pixela();
            pixela.setUsername(account.getEmail()); 
            pixela.setToken(pixelaToken);
            pixela.setAccount(savedAccount);
            pixelaRepository.save(pixela);

            profile.setStreak(streak);

            return savedAccount;
        } else {
            throw new RuntimeException("Failed to create Pixela account for user: " + account.getEmail());
        }
    }

    private String generatePixelaToken() {
        return "thisissecret";
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
