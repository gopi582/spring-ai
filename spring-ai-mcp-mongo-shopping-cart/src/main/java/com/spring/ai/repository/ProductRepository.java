package com.spring.ai.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.ai.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
