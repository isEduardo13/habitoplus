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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("accounts")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@Tag(name = "Accounts", description = "Provides all the operations related to the account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Operation(summary = "Get all accounts")
    @ApiResponse(responseCode = "200", description = "Return all accounts", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Account.class))) })
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
    public Account addAccount(@RequestBody Account account) {
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
