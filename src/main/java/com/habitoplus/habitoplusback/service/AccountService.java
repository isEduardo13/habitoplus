package com.habitoplus.habitoplusback.service;

import java.util.List;

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
import com.habitoplus.habitoplusback.model.Profile;


@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProfileRepository profileRepository;


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
    public Account addAccount(Account account) {
        if (accountRepository.findByEmail(account.getEmail()) != null) {
            throw new UserAlreadyExistsException("User with email " + account.getEmail() + " already exists");
        }
        if (account.getProfile() == null) {
            Profile profile = new Profile();
            profileRepository.save(profile);
            account.setProfile(profile);
        } else {
            Profile profile = account.getProfile();
            profileRepository.save(profile);
        }

        return accountRepository.save(account);
    }


    public Account updateAccount(Account account) {

        if (accountRepository.findById(account.getIdAccount()).isEmpty()) {
            throw new NoSuchElementException();
        }
        Account existingAccount = accountRepository.findById(account.getIdAccount()).get();

        existingAccount.setEmail(account.getEmail());
        existingAccount.setPassword(account.getPassword());
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

    

