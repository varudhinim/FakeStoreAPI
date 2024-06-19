package com.example.Ecommerce.Controllers;

import com.example.Ecommerce.DTO.Category;
import com.example.Ecommerce.DTO.CreateRequestDTO;
import com.example.Ecommerce.Models.Product;
import com.example.Ecommerce.Services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ProductController {

   private ProductService productService ;
   private RestTemplate restTemplate;


    public ProductController(ProductService ps, RestTemplate restTemplate) {
        this.productService = ps;
        this.restTemplate = restTemplate;
    }

    //creating product
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateRequestDTO createRequest){
        return productService.createProduct(createRequest.getTitle(),
                createRequest.getImage(),createRequest.getDescription(),
                createRequest.getCategory(), createRequest.getPrice());
    }

    //get single product
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long Productid){
        return productService.getSingleProduct(Productid);
    }

    //get All products from list
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    // updating the existing product details
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long Productid , @RequestBody CreateRequestDTO createRequest){
        return productService.updateProduct(Productid, createRequest.getTitle(),createRequest.getImage(),
                createRequest.getDescription(),createRequest.getCategory(), createRequest.getPrice());
    }

    // deleting the existing product from the list
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long Productid){
        try {
            productService.deleteProduct(Productid);
            System.out.println("Product deleted successfully");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // get All categories from the List
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> categories = productService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    // get products from that category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsInCategory(@PathVariable("category") String category) {
        List<Product> products = productService.getProductsInCategory(category);
        return ResponseEntity.ok(products);
    }

}
