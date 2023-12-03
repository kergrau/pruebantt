package com.example.pruebantt.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.pruebantt.dtos.ProductDto;
import com.example.pruebantt.entities.Product;
import com.example.pruebantt.enums.ECustomError;
import com.example.pruebantt.exceptions.CustomException;
import com.example.pruebantt.repositories.ProductRepository;
import com.example.pruebantt.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	@Cacheable("products")
	public List<ProductDto> products() {
		return productRepository.findAll().stream()
				.map(product -> new ProductDto(product.getName(), product.getCategory())).toList();
	}

	@Override
	public List<ProductDto> productsByCategory(String category) {
		return productRepository.findByCategory(category).stream()
				.map(product -> new ProductDto(product.getName(), product.getCategory())).toList();
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product update(Long id, ProductDto product) {
		Product newProduct = productRepository.findById(id)
				.orElseThrow(() -> new CustomException(ECustomError.PRODUCT_NOT_FOUND, HttpStatus.NOT_FOUND));
		newProduct.setName(product.getName());
		newProduct.setCategory(product.getCategory());
		return productRepository.save(newProduct);
	}

	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}
}
