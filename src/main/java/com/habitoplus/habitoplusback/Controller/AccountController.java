package com.habitoplus.habitoplusback.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import com.habitoplus.habitoplusback.Model.Account;
import com.habitoplus.habitoplusback.Service.AccountService;


@RestController
@RequestMapping("accounts")
@CrossOrigin(origins = "*" , methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AccountController {
    
    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
    @GetMapping("/query")
    public Account getAccountByEmail(@RequestParam String email) {
        return accountService.getAccountByEmail(email);
    }
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable int id) {
        return accountService.getAccountById(id);
    }
    @PostMapping
    public Account addAccount( @RequestBody Account account) {
        return accountService.addAccount(account);
    }
    @PutMapping
    public boolean updateAccount(Account account) {
        return accountService.updateAccount(account);
    }
    @PutMapping("/{id}")
    public boolean deleteAccount(@PathVariable int id) {
        return accountService.deleteAccount(id);
    }


}
