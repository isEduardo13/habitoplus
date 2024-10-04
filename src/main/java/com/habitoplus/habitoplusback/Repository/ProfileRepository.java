package com.habitoplus.habitoplusback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habitoplus.habitoplusback.Model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {


}
