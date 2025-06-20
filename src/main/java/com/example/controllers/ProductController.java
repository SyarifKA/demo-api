package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ResponseData;
import com.example.models.entities.Product;
import com.example.models.entities.Supplier;
import com.example.services.ProductServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @PostMapping
    public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, Errors errors) {
        ResponseData<Product> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productServices.save(product));
        responseData.setMessages(List.of("Create product Success!"));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Product> findAll() {
        return productServices.findAll();
    }

    @GetMapping("/{id}")
    public Product findOne(@PathVariable("id") Long id) {
        return productServices.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Product>> update(@Valid @RequestBody Product product, Errors errors) {
        ResponseData<Product> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productServices.save(product));
        responseData.setMessages(List.of("Update product success!"));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id) {
        productServices.removeOne(id);
    }

    @GetMapping("/search")
    public List<Product> findByName(@RequestParam("name") String name) {
        return productServices.findByName(name);
    }

    @PostMapping("/{id}")
    public void addSupplier(@RequestBody Supplier supplier, @PathVariable("id") Long productId) {
        productServices.addSupplier(supplier, productId);
    }
}
