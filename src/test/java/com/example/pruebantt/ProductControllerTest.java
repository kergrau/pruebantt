package com.example.pruebantt;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.example.pruebantt.controllers.ProductController;
import com.example.pruebantt.dtos.ProductDto;
import com.example.pruebantt.services.ProductService;

@AutoConfigureMockMvc
@SpringBootTest
public class ProductControllerTest {

	@MockBean
	private ProductService productService;

	@Autowired
	private ProductController productController;

	@Test
	@DisplayName(value = "test method getClients OK")
	void testGetClientsOk() {
		when(productService.products()).thenReturn(getAllProductsMockData());
		ResponseEntity<?> products = productController.products();
		assertNotNull(products);
	}

	private List<ProductDto> getAllProductsMockData() {
		List<ProductDto> productos = new LinkedList<>();
		productos.add(new ProductDto("Escoba", "Limpieza"));
		productos.add(new ProductDto("Trapero", "Limpieza"));
		productos.add(new ProductDto("Varsol", "Limpieza"));
		productos.add(new ProductDto("Cloro", "Limpieza"));
		productos.add(new ProductDto("Jabon barra", "Limpieza"));
		productos.add(new ProductDto("Detergente liquido", "Limpieza"));
		productos.add(new ProductDto("Detergente polvo", "Limpieza"));
		productos.add(new ProductDto("Guantes", "Limpieza"));
		productos.add(new ProductDto("Desengrasante", "Limpieza"));
		productos.add(new ProductDto("Recogedor", "Limpieza"));

		productos.add(new ProductDto("Pan", "Alimentos"));
		productos.add(new ProductDto("Chocolate", "Alimentos"));
		productos.add(new ProductDto("Lechuga", "Alimentos"));
		productos.add(new ProductDto("Tomate", "Alimentos"));
		productos.add(new ProductDto("Cebolla", "Alimentos"));
		productos.add(new ProductDto("Repollo", "Alimentos"));
		productos.add(new ProductDto("Coliflor", "Alimentos"));
		productos.add(new ProductDto("Brocoli", "Alimentos"));
		productos.add(new ProductDto("Papa", "Alimentos"));
		productos.add(new ProductDto("Platano", "Alimentos"));

		return productos;
	}
}
