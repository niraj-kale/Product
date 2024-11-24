package com.example.productservice.controllers;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    ProductController productController;

    @MockBean(name = "SelfProductService")
    ProductService productService;


    @Test
    void getProductById() throws ProductNotFoundException {
        // Arrange
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        product.setTitle("test product");

        when(productService.getProductById(id)).thenReturn(product);

        // Act
        Product p = productController.getProductById(id).getBody();

        // Assert
        Assertions.assertEquals("test product", p.getTitle());
    }

    @Test
    void getProductByIdThrowsException() throws ProductNotFoundException {
        // Arrange
        Long id = 2L;
        Product product = new Product();
        product.setId(1L);
        product.setTitle("test product");

        when(productService.getProductById(1L))
                .thenThrow(ProductNotFoundException.class);

        // Act & Assert

        Assertions.assertThrows(ProductNotFoundException.class, () -> productController.getProductById(id));
    }

    @Test
    void getAllProducts() {
        // Arrange
        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("test product 1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("test product 2");

        when(productService.getAllProducts()).thenReturn(java.util.List.of(product1, product2));

        // Act
        java.util.List<Product> products = productController.getAllProducts();

        // Assert
        Assertions.assertEquals(2, products.size());
        Assertions.assertEquals("test product 1", products.get(0).getTitle());
        Assertions.assertEquals("test product 2", products.get(1).getTitle());
    }

    @Test
    void replaceProduct() {
        // Arrange
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        product.setTitle("test product");

        when(productService.replaceProduct(id, product)).thenReturn(product);

        // Act
        Product p = productController.replaceProduct(id, product);

        // Assert
        Assertions.assertEquals("test product", p.getTitle());
    }

    @Test
    void createProduct() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setTitle("test product");

        when(productService.createProduct(product)).thenReturn(product);

        // Act
        Product p = productController.createProduct(product);

        // Assert
        Assertions.assertEquals("test product", p.getTitle());
    }
}