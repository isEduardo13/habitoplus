package com.habitoplus.habitoplusback.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.habitoplus.habitoplusback.Repository.AccountRepository;
import com.habitoplus.habitoplusback.Repository.ProfileRepository;
import jakarta.transaction.Transactional;
import java.util.NoSuchElementException;

import com.habitoplus.habitoplusback.Exception.UserAlreadyExistsException;
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

    public List<Account> getAllAccountsWithPaginated(int page ,int pageSize) {
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
    public Account getAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public List<Account> getAccountsByEmail(String email) {
        return accountRepository.getAccoutsByEmail(email);
    }
    
    @Transactional
    public Account addAccount(Account account)  {
        if (accountRepository.findByEmail(account.getEmail()) != null) {
            throw new UserAlreadyExistsException("User with email " + account.getEmail() + " already exists");   
        }
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


    public Account updateAccount(Account account) {

        if (accountRepository.findById(account.getAccount_id()).isEmpty()) {
            throw new NoSuchElementException();     
        }
        Account existingAccount = accountRepository.findById(account.getAccount_id()).get();
        
        existingAccount.setEmail(account.getEmail());
        existingAccount.setPassword(account.getPassword());
        existingAccount.setStatus(account.isStatus());  
        
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

    

