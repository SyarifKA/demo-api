package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.entities.Category;
import com.example.models.repository.CategoryRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    public Category findOne(Long id) {
        Optional<Category> category = categoryRepo.findById(id);
        if (!category.isPresent()) {
            return null;
        }
        return categoryRepo.findById(id).get();
    }

    public Iterable<Category> findAll() {
        return categoryRepo.findAll();
    }

    public void removeOne(Long id) {
        categoryRepo.deleteById(id);
    }

    public Category findByCategoryName(String name) {
        return categoryRepo.findCategoryByName(name);
    }

    public List<Category> findAllCategoryByName(String name) {
        return categoryRepo.findAllCategoryByName(name);
    }
}
