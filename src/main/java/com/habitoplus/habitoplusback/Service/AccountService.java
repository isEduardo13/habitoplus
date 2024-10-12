package com.habitoplus.habitoplusback.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.habitoplus.habitoplusback.Repository.AccountRepository;
import com.habitoplus.habitoplusback.Repository.ProfileRepository;

import jakarta.transaction.Transactional;



import com.habitoplus.habitoplusback.Model.Account;
import com.habitoplus.habitoplusback.Model.Profile;


@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProfileRepository profileRepository;



    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(int id) {
        return accountRepository.findById(id).orElse(null);
    }
    public Account getAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }
    
    @Transactional
    public Account addAccount(Account account) {
        if (account.getProfile() == null) {
            Profile profile = new Profile();
            profile.Inicializar();
            profileRepository.save(profile);
            account.setProfile(profile);
        } else {
            Profile profile = account.getProfile();
            profileRepository.save(profile);
        }

        return accountRepository.save(account);
    }

    public boolean updateAccount(Account account) {
        Account existingAccount = accountRepository.findById(account.getAccount_id()).orElse(null);
        existingAccount.setEmail(account.getEmail());
        existingAccount.setPassword(account.getPassword());
        existingAccount.setStatus(account.isStatus());  
        return true;
    }

    public boolean deleteAccount(int id) {
        Account existingAccount = accountRepository.findById(id).orElse(null);
        if (existingAccount != null) {
            existingAccount.setStatus(false);
            accountRepository.save(existingAccount);
            return true;
        }
        return false;
    }
}

    

