package com.example.Ecommerce.DTO;

import com.example.Ecommerce.Models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStorProductDTO {

    private int id;
    private String title;
    private Double price;
    private String category;
    private String description;
    private String image;

    public Product toProduct(){
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setPrice(price);
        product.setCategory(category);
        product.setDescription(description);
        product.setImage(image);
        return product;
    }
}


