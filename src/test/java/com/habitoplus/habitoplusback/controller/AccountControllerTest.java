package com.habitoplus.habitoplusback.controller;

import com.habitoplus.habitoplusback.model.Account;
import com.habitoplus.habitoplusback.service.AccountService;
import com.habitoplus.habitoplusback.service.JsonWebTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private JsonWebTokenService jsonWebTokenService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAccounts() throws Exception {
        // Arrange
        Account account1 = new Account();
        account1.setIdAccount(1);
        account1.setEmail("test1@example.com");

        Account account2 = new Account();
        account2.setIdAccount(2);
        account2.setEmail("test2@example.com");

        List<Account> accounts = Arrays.asList(account1, account2);
        when(accountService.getAllAccounts()).thenReturn(accounts);

        // Act & Assert
        mockMvc.perform(get("/accounts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].email").value("test1@example.com"))
                .andExpect(jsonPath("$[1].email").value("test2@example.com"));
    }

    // Agrega más pruebas unitarias para otros métodos del controlador
}