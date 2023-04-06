package com.anggiat.ProductService;


import com.anggiat.ProductService.Service.ProductService;
import com.anggiat.ProductService.dto.ProductResponse;
import com.anggiat.ProductService.model.Product;
import com.anggiat.ProductService.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ProductServiceApplicationTests {
		@Autowired
		private ProductService productService;
		@MockBean
		private ProductRepository productRepository;
		@Test
		public void createProduct(){
				String name = "Iphone 14";
				String description = "Smartphone Apple";
				BigDecimal price = BigDecimal.valueOf(100000);

			Product product = Product.builder()
					.name(name)
					.description(description)
					.price(price)
					.build();

			Mockito.when(productRepository.save(product)).thenReturn(product);

		}

		@Test
		public  void getAllProducts(){
			String name = "Iphone 14";
			String description = "Smartphone Apple";
			BigDecimal price = BigDecimal.valueOf(100000);
			List<Product> products = new ArrayList<Product>();
			Product product = Product.builder()
					.name(name)
					.description(description)
					.price(price)
					.build();
			products.add(product);
			Mockito.when(productRepository.findAll()).thenReturn(products);
			List<ProductResponse> productResponses =  products.stream().map(this::mapToProductResponse).toList();
			assertEquals(productResponses , productService.getAllProducts());
		}

	private ProductResponse mapToProductResponse(Product product) {
		return  ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}


}
