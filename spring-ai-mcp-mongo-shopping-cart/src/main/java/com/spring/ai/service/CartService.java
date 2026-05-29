package com.spring.ai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.ai.model.Product;
import com.spring.ai.repository.ProductRepository;

@Service
public class CartService {

	private final ProductRepository repository;

	public CartService(ProductRepository repository) {
		this.repository = repository;
	}

	public Product addProduct(Product product) {
		return repository.save(product);
	}

	public List<Product> getAllProducts() {
		return repository.findAll();
	}

	public String removeProduct(String id) {

		repository.deleteById(id);

		return "Product removed successfully";
	}

	public double checkout() {

		return repository.findAll().stream().mapToDouble(product -> product.getPrice() * product.getQuantity()).sum();
	}
}
