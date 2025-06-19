package com.example.models.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.example.models.entities.Supplier;

public interface SupplierRepo extends CrudRepository<Supplier, Long> {
    List<Supplier> findByNameContains(String name);
}
