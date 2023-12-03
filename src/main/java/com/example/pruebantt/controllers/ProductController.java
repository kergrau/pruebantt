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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/all")
	@Operation(description = "Return all products", summary = "Return all available products")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Return all", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Error not exepected", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<?> products() {
		List<ProductDto> products = productService.products();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/by-category")
	@Operation(description = "Return products by category", summary = "Return products by categories")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Return by category", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Error not exepected", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<?> productsByCategory(@RequestParam String category) {
		List<ProductDto> products = productService.productsByCategory(category);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@PostMapping("/create")
	@Operation(description = "Save a product", summary = "Save product")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Save product", content = {
					@Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", description = "Error not exepected", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<?> save(@RequestBody Product product) {
		Product productSaved = productService.save(product);
		return new ResponseEntity<>(productSaved, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	@Operation(description = "Update product", summary = "Update product by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Update product", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
			@ApiResponse(responseCode = "404", description = "Product not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
			@ApiResponse(responseCode = "500", description = "Error not exepected", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<?> update(@RequestParam Long id, @RequestBody ProductDto product) {
		Product productUpdated = productService.update(id, product);
		return new ResponseEntity<>(productUpdated, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	@Operation(description = "Delete product", summary = "Delete product by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Delete product", content = {
					@Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", description = "Error not exepected", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<?> update(@RequestParam Long id) {
		productService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
