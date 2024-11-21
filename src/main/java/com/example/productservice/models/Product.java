package com.example.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    String description;
    Double price;
    @ManyToOne(cascade = CascadeType.PERSIST)
    Category category;
    int qty;

//    @ManyToMany(mappedBy = "productList")
//    List<Category> categoryList;

//    @ManyToOne
//    @JoinColumn
//    Category category;

}
