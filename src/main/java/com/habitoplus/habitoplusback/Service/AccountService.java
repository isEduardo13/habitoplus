package com.habitoplus.habitoplusback.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.habitoplus.habitoplusback.Repository.AccountRepository;
import com.habitoplus.habitoplusback.Model.Account;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }
    public Account getAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }
    
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    public boolean updateAccount(Account account) {
        Account existingAccount = accountRepository.findById(account.getAccount_id()).orElse(null);
        existingAccount.setEmail(account.getEmail());
        existingAccount.setPassword(account.getPassword());
        existingAccount.setStatus(account.isStatus());
        return true;
    }

    public boolean deleteAccount(Long id) {
        Account existingAccount = accountRepository.findById(id).orElse(null);
        if (existingAccount != null) {
            existingAccount.setStatus(false);
            accountRepository.save(existingAccount);
            return true;
        }
        return false;
    }
}

    

