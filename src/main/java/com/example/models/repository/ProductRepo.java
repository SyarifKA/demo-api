package com.example.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.models.entities.Product;

public interface ProductRepo extends CrudRepository<Product, Long> {

    List<Product> findByNameContains(String name);

}