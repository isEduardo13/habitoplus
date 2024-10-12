package com.habitoplus.habitoplusback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habitoplus.habitoplusback.model.Category;
import com.habitoplus.habitoplusback.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    public List<Category> getAll() {
        return categoryRepo.findAll();
    }

    public Category getByCategoryId(Integer idCategory) {
        return categoryRepo.findById(idCategory).get();
    }
}
