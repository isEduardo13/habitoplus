package com.habitoplus.habitoplusback.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.habitoplus.habitoplusback.Model.Account;

@Repository

public interface AccountRepository extends JpaRepository<Account, Integer> {
Account findByEmail(String email);

}