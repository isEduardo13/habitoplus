package com.habitoplus.habitoplusback.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAccount;
}

