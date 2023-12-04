package com.example.pruebantt;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.pruebantt.dtos.ProductDto;
import com.example.pruebantt.entities.Product;
import com.example.pruebantt.repositories.ProductRepository;
import com.example.pruebantt.services.ProductService;

@AutoConfigureMockMvc
@SpringBootTest
public class ProductServiceImplTest {

	@MockBean
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	@Test
	@DisplayName(value = "test method return all products OK")
	void testGetAllProducts() {
		when(productRepository.findAll()).thenReturn(getAllProductsMockData());
		List<ProductDto> products = productService.products();
		assertNotNull(products);
	}

	@Test
	@DisplayName(value = "test method return products by category OK")
	void testGetProductsByCategory() {
		when(productRepository.findAll()).thenReturn(getAllProductsMockData());
		List<ProductDto> products = productService.productsByCategory("Limpieza");
		assertNotNull(products);
	}

	@Test
	@DisplayName(value = "test method save product OK")
	void testSaveProduct() {
		when(productRepository.save(any())).thenReturn(getProductMockData());
		Product product = productService.save(getProductMockData());
		assertNotNull(product);
	}

	private List<Product> getAllProductsMockData() {
		List<Product> productos = new LinkedList<>();
		productos.add(new Product(1L, "Escoba", "Limpieza"));
		productos.add(new Product(2L, "Trapero", "Limpieza"));
		productos.add(new Product(3L, "Varsol", "Limpieza"));
		productos.add(new Product(4L, "Cloro", "Limpieza"));
		productos.add(new Product(5L, "Jabon barra", "Limpieza"));
		productos.add(new Product(6L, "Detergente liquido", "Limpieza"));
		productos.add(new Product(7L, "Detergente polvo", "Limpieza"));
		productos.add(new Product(8L, "Guantes", "Limpieza"));
		productos.add(new Product(9L, "Desengrasante", "Limpieza"));
		productos.add(new Product(10L, "Recogedor", "Limpieza"));

		productos.add(new Product(11L, "Pan", "Alimentos"));
		productos.add(new Product(12L, "Chocolate", "Alimentos"));
		productos.add(new Product(13L, "Lechuga", "Alimentos"));
		productos.add(new Product(14L, "Tomate", "Alimentos"));
		productos.add(new Product(15L, "Cebolla", "Alimentos"));
		productos.add(new Product(16L, "Repollo", "Alimentos"));
		productos.add(new Product(17L, "Coliflor", "Alimentos"));
		productos.add(new Product(18L, "Brocoli", "Alimentos"));
		productos.add(new Product(19L, "Papa", "Alimentos"));
		productos.add(new Product(20L, "Platano", "Alimentos"));

		return productos;
	}

	private Product getProductMockData() {
		return new Product(21L, "Aji", "Alimentos");
	}
}
