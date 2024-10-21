package com.habitoplus.habitoplusback.repository;

    import org.springframework.stereotype.Repository;

import java.util.List;
    import java.util.Optional;

    import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.habitoplus.habitoplusback.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByEmail(String email);
    @Query(value ="SELECT a FROM Account a WHERE a.email = :email")
    List<Account> getAccoutsByEmail(@Param("email")String email);
    
}
