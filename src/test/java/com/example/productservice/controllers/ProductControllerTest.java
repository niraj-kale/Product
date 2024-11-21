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
}