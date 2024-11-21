package com.example.productservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Category extends BaseModel {
//    String title;
    String description;
//    @ManyToMany
//    List<Product> productList;
//    @OneToMany(fetch = FetchType.EAGER)
//    List<Product> productList;
}
