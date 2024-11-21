package com.example.productservice.services;

import com.example.productservice.models.Product;
import com.example.productservice.projections.ProductTitleAndDescription;
import com.example.productservice.repos.CategoryRepo;
import com.example.productservice.repos.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SelfProductService")
public class SelfProductService implements ProductService {

    ProductRepo productRepo;
    CategoryRepo categoryRepo;

    public SelfProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Product getProductById(Long id)  {
        ProductTitleAndDescription productTitleAndDescription = productRepo.findProductTitleAndDescriptionById(id);
        System.out.println("Projection: " + productTitleAndDescription.getTitle() + " description : " + productTitleAndDescription.getDescription());
        return productRepo.findById(id).get();
//        return productRepo.findProductTitleAndDescriptionById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
//        Category category = product.getCategory();
//        if(category.getId() == null) {
//            Category savedCategory = categoryRepo.save(category);
////            product.setCategory(savedCategory);
//        }
        return productRepo.save(product);
    }
}
