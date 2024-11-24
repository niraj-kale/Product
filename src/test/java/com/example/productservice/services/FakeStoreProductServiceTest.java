package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class FakeStoreProductServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FakeStoreProductService fakeStoreProductService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProductById_Success() {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(1L);
        fakeStoreProductDto.setTitle("Test Product");
        fakeStoreProductDto.setPrice(10.0);
        fakeStoreProductDto.setDescription("Test Description");

        when(restTemplate.getForObject(eq("https://fakestoreapi.com/products/1"), eq(FakeStoreProductDto.class)))
                .thenReturn(fakeStoreProductDto);

        try {
            Product product = fakeStoreProductService.getProductById(1L);

            assertNotNull(product);
            assertEquals(1L, product.getId());
            assertEquals("Test Product", product.getTitle());
            assertEquals(10.0, product.getPrice());
            assertEquals("Test Description", product.getDescription());
        } catch (ProductNotFoundException e) {
            fail("Product not found");
        }
//        Product product = fakeStoreProductService.getProductById(1L);
    }

    @Test
    void testGetProductById_NotFound() {
        when(restTemplate.getForObject(eq("https://fakestoreapi.com/products/1"), eq(FakeStoreProductDto.class)))
                .thenReturn(null);

        assertThrows(ProductNotFoundException.class, () -> fakeStoreProductService.getProductById(1L));
    }

    @Test
    void testGetAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = new FakeStoreProductDto[2];
        FakeStoreProductDto product1 = new FakeStoreProductDto();
        product1.setId(1L);
        product1.setTitle("Product 1");
        product1.setPrice(10.0);
        product1.setDescription("Description 1");

        FakeStoreProductDto product2 = new FakeStoreProductDto();
        product2.setId(2L);
        product2.setTitle("Product 2");
        product2.setPrice(20.0);
        product2.setDescription("Description 2");

        fakeStoreProductDtos[0] = product1;
        fakeStoreProductDtos[1] = product2;

        when(restTemplate.getForObject(eq("https://fakestoreapi.com/products"), eq(FakeStoreProductDto[].class)))
                .thenReturn(fakeStoreProductDtos);

        List<Product> products = fakeStoreProductService.getAllProducts();

        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Product 1", products.get(0).getTitle());
        assertEquals("Product 2", products.get(1).getTitle());
    }

    @Test
    void testReplaceProduct() {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(1L);
        fakeStoreProductDto.setTitle("Updated Product");
        fakeStoreProductDto.setPrice(15.0);
        fakeStoreProductDto.setDescription("Updated Description");

        when(restTemplate.execute(eq("https://fakestoreapi.com/products/1"), eq(HttpMethod.PUT), any(RequestCallback.class), any(ResponseExtractor.class)))
                .thenReturn(ResponseEntity.ok(fakeStoreProductDto));

        Product product = new Product();
        product.setId(1L);
        product.setTitle("Updated Product");
        product.setPrice(15.0);
        product.setDescription("Updated Description");

        Product updatedProduct = fakeStoreProductService.replaceProduct(1L, product);

        assertNotNull(updatedProduct);
        assertEquals(1L, updatedProduct.getId());
        assertEquals("Updated Product", updatedProduct.getTitle());
        assertEquals(15.0, updatedProduct.getPrice());
        assertEquals("Updated Description", updatedProduct.getDescription());
    }
}