package com.example.productservice.repos;

import com.example.productservice.models.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Sql("/test-category-data.sql")
    void testFindById() {
        Category category = categoryRepo.findById(1L).orElse(null);
        Assertions.assertNotNull(category);
        Assertions.assertEquals("Electronics", category.getTitle());
    }

    @Test
    void testSaveAndFindById() {
        Category category = new Category();
        category.setTitle("Books");

        Category savedCategory = categoryRepo.save(category);
        Category foundCategory = categoryRepo.findById(savedCategory.getId()).orElse(null);

        Assertions.assertNotNull(foundCategory);
        Assertions.assertEquals("Books", foundCategory.getTitle());
    }

    @Test
    @Sql("/test-category-data.sql")
    void testFindAll() {
        java.util.List<Category> categories = categoryRepo.findAll();
        Assertions.assertEquals(2, categories.size());
    }

    @Test
    @Sql("/test-category-data.sql")
    void testDeleteById() {
        categoryRepo.deleteById(1L);
        Category category = categoryRepo.findById(1L).orElse(null);
        Assertions.assertNull(category);
    }
}