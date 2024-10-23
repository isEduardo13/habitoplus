package com.habitoplus.habitoplusback.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import java.util.Optional;

import com.habitoplus.habitoplusback.exception.UserAlreadyExistsException;
import com.habitoplus.habitoplusback.model.Account;
import com.habitoplus.habitoplusback.service.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("accounts")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE})
@Tag(name = "Accounts", description = "Provides all the operations related to the account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Operation(summary = "Create a new account")
    @ApiResponse(responseCode = "201", description = "Account created successfully", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))})
    @PostMapping
    public ResponseEntity<?> createAccount(
            @RequestBody Account account,
            @RequestParam boolean isNotMinor,
            @RequestParam(required = false) String thanksCode
    ) {
        try {
            Account createdAccount = accountService.addAccount(account, isNotMinor, thanksCode);

            return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);

        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            e.printStackTrace(); // Imprimir detalles del error en los logs
            return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Get all accounts")
    @ApiResponse(responseCode = "200", description = "Return all accounts", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Account.class)))})
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @Operation(summary = "Get all accounts with pagination")
    @ApiResponse(responseCode = "200", description = "Returns the accounts by pages ", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Account.class)))})
    @GetMapping("/paginated/{page}/{pageSize}")
    public List<Account> getAllAccountsWithPaginated(@PathVariable int page, @PathVariable int pageSize) {
        return accountService.getAllAccountsWithPaginated(page, pageSize);
    }

    @Operation(summary = "Get account by Email")
    @ApiResponse(responseCode = "200", description = "Return account by email", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))})
    @GetMapping("/byEmail/{email}")
    public ResponseEntity<Account> getAccountByEmail(@PathVariable String email) {
        Optional<Account> account = accountService.getAccountByEmail(email);
        return new ResponseEntity<Account>(HttpStatus.OK);
    }

    @Operation(summary = "Get account by Id")
    @ApiResponse(responseCode = "200", description = "Return account by id", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))})
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable int id) {
        Account account = accountService.getAccountById(id);
        return new ResponseEntity<Account>(account, HttpStatus.OK);
    }

    @Operation(summary = "Update account")
    @ApiResponse(responseCode = "200", description = "Return updated account", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))})
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable int id, @RequestBody Account account) {
        account.setIdAccount(id);
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
