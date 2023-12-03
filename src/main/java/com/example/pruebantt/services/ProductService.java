package com.example.pruebantt.services;

import java.util.List;

import com.example.pruebantt.dtos.ProductDto;
import com.example.pruebantt.entities.Product;

public interface ProductService {

	List<ProductDto> products();

	List<ProductDto> productsByCategory(String category);

	Product save(Product product);

	Product update(Long id, ProductDto product);

	void deleteById(Long id);
}
