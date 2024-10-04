package com.habitoplus.habitoplusback.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import com.habitoplus.habitoplusback.Model.Account;
import com.habitoplus.habitoplusback.Service.AccountService;

@RestController
@RequestMapping("Accounts")
@CrossOrigin(origins = "*" , methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AccountController {
    
    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
    @GetMapping 
    public Account getAccountByEmail(@RequestParam String email) {
        return accountService.getAccountByEmail(email);
    }
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }
    @GetMapping
    public Account addAccount(Account account) {
        return accountService.addAccount(account);
    }
    @GetMapping
    public boolean updateAccount(Account account) {
        return accountService.updateAccount(account);
    }
    @GetMapping
    public boolean deleteAccount(@PathVariable Long id) {
        return accountService.deleteAccount(id);
    }


}
