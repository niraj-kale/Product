package com.example.productservice.repos;

import com.example.productservice.models.Product;
import com.example.productservice.projections.ProductTitleAndDescription;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    @Sql("/test-data.sql")
    void testFindProductTitleAndDescriptionById() {
        ProductTitleAndDescription projection = productRepo.findProductTitleAndDescriptionById(1L);
        Assertions.assertNotNull(projection);
        Assertions.assertEquals("Test Product", projection.getTitle());
        Assertions.assertEquals("Test Description", projection.getDescription());
    }

    @Test
    @Sql("/test-data.sql")
    void testFindProductTitleAndDescriptionSQL() {
        ProductTitleAndDescription projection = productRepo.findProductTitleAndDescriptionSQL(1L);
        Assertions.assertNotNull(projection);
        Assertions.assertEquals("Test Product", projection.getTitle());
        Assertions.assertEquals("Test Description", projection.getDescription());
    }

    @Test
    void testSaveAndFindById() {
        Product product = new Product();
        product.setTitle("New Product");
        product.setDescription("New Description");
        product.setPrice(20.0);

        Product savedProduct = productRepo.save(product);
        Product foundProduct = productRepo.findById(savedProduct.getId()).orElse(null);

        Assertions.assertNotNull(foundProduct);
        Assertions.assertEquals("New Product", foundProduct.getTitle());
        Assertions.assertEquals("New Description", foundProduct.getDescription());
        Assertions.assertEquals(20.0, foundProduct.getPrice());
    }
}