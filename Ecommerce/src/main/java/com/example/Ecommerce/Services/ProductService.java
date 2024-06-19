package com.example.Ecommerce.Services;

import com.example.Ecommerce.DTO.Category;
import com.example.Ecommerce.Models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productid);
    List<Product> getAllProducts();
    Product createProduct(String title, String image, String Description, String category, double price);
    Product updateProduct(Long ProductId, String title, String image, String Description, String category, double price);
    void deleteProduct(Long ProductId);
    List<String> getAllCategories();
    List<Product> getProductsInCategory(String category);

}
