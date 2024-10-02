package com.grandblue.ai.repository;


import com.grandblue.ai.entity.Product;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductRepository extends ListCrudRepository<Product, String> { }
