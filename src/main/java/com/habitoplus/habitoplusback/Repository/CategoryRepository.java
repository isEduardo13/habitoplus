package com.habitoplus.habitoplusback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habitoplus.habitoplusback.Model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{  
} 
