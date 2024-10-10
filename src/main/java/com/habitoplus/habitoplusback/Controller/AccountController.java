package com.habitoplus.habitoplusback.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@CrossOrigin(origins = "*" , methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Tag(name = "Accounts", description = "Provides all the operations related to the account")
public class AccountController {
    
    @Autowired
    private AccountService accountService;

    @Operation(summary = "Get all accounts")
    @ApiResponse(responseCode = "200", description = "Return all accounts", content = {
            @Content(mediaType = "application/json", array =@ArraySchema(schema = @Schema(implementation = Account.class)))})
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @Operation(summary = "Get account by Email")
        @ApiResponse(responseCode = "200", description = "Return account by email", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))})
    @GetMapping("/byEmail/{email}")
    public ResponseEntity<Account> getAccountByEmail(@PathVariable String email) {
        Account account= accountService.getAccountByEmail(email);
        return new ResponseEntity<Account>(account, HttpStatus.OK);
    }
    @Operation(summary = "Get account by Id")
    @ApiResponse(responseCode = "200", description = "Return account by id", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))})
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable int id) {
        Account account= accountService.getAccountById(id);
        return new ResponseEntity<Account>(account, HttpStatus.OK);
    }

    @Operation(summary = "Update account")
    @ApiResponse(responseCode = "200", description = "Return updated account", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))})
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable int id, @RequestBody Account account) {
        account.setAccount_id(id); 
        Account updatedAccount = accountService.updateAccount(account);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }
     @Operation(summary = "Delete account")
    @ApiResponse(responseCode = "204", description = "Account deleted successfully")
    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable int id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
