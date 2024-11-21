package com.example.productservice.repos;

import com.example.productservice.models.Product;
import com.example.productservice.projections.ProductTitleAndDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query("select p.title as title, p.description as description from Product p where p.id = :id")
    ProductTitleAndDescription findProductTitleAndDescriptionById(@Param("id") Long id);

    @Query(value = "select title, description from product where id = :id", nativeQuery = true)
    ProductTitleAndDescription findProductTitleAndDescriptionSQL(@Param("id") Long id);
}
