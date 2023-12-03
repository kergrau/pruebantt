package com.example.pruebantt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pruebantt.dtos.ProductDto;
import com.example.pruebantt.entities.Product;
import com.example.pruebantt.services.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/all")
	public ResponseEntity<?> products() {
		List<ProductDto> products = productService.products();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/by-category")
	public ResponseEntity<?> productsByCategory(@RequestParam String category) {
		List<ProductDto> products = productService.productsByCategory(category);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Product product) {
		Product productSaved = productService.save(product);
		return new ResponseEntity<>(productSaved, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestParam Long id, @RequestBody ProductDto product) {
		Product productUpdated = productService.update(id, product);
		return new ResponseEntity<>(productUpdated, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<?> update(@RequestParam Long id) {
		productService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
