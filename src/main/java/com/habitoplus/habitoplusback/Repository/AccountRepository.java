package com.habitoplus.habitoplusback.Repository;

import org.springframework.stereotype.Repository;
import com.habitoplus.habitoplusback.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
