package com.example.Ecommerce.Services;

import com.example.Ecommerce.DTO.Category;
import com.example.Ecommerce.DTO.CreateRequestDTO;
import com.example.Ecommerce.DTO.FakeStorProductDTO;
import com.example.Ecommerce.Models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FakeStoryProductService implements ProductService {

    private RestTemplate restTemplate ;

    public FakeStoryProductService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
    }
    @Override
    public List<Product> getAllProducts() {

        String url = "https://fakestoreapi.com/products";
        ResponseEntity<FakeStorProductDTO[]> responseEntity = restTemplate.getForEntity(url, FakeStorProductDTO[].class);
        FakeStorProductDTO[] fakeStoreProductDTOs = responseEntity.getBody();

        if (fakeStoreProductDTOs != null) {
            return Arrays.stream(fakeStoreProductDTOs)
                    .map(FakeStorProductDTO::toProduct)
                    .collect(Collectors.toList());
        } else {
            // Handle the case where no products are found or response body is null
            throw new RuntimeException("No products found.");
        }
    }

    @Override
    public Product createProduct(String title, String image, String Description, String category, double price) {
//            title="SmartTv";
//            image="https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg";
//            Description = "Tv is 42inch. it is andriod version";
//            category="TV";
//            price=13980.33;

            FakeStorProductDTO fakeStorProductDTO=new FakeStorProductDTO();
            fakeStorProductDTO.setTitle(title);
            fakeStorProductDTO.setImage(image);
            fakeStorProductDTO.setDescription(Description);
            fakeStorProductDTO.setCategory(category);
            fakeStorProductDTO.setPrice(price);

        FakeStorProductDTO responce =  restTemplate.postForObject("https://fakestoreapi.com/products", fakeStorProductDTO, FakeStorProductDTO.class );
        return responce.toProduct();

    }

    @Override
    public Product getSingleProduct(Long productid) {
        ResponseEntity<FakeStorProductDTO> fs   =  restTemplate.getForEntity( "https://fakestoreapi.com/products/"+
                        productid+"/", FakeStorProductDTO.class );
        return fs.getBody().toProduct();
    }

    public Product updateProduct(Long productID, String title, String image, String Description, String category, double price) {
        ResponseEntity<FakeStorProductDTO> fsps = restTemplate.getForEntity("https://fakestoreapi.com/products/"+ productID, FakeStorProductDTO.class);
        FakeStorProductDTO currentProduct = fsps.getBody();
        if(currentProduct!= null){
            currentProduct.setTitle(title);
            currentProduct.setImage(image);
            currentProduct.setDescription(Description);
            currentProduct.setCategory(category);
            currentProduct.setPrice(price);
            HttpEntity<FakeStorProductDTO> requestEntity = new HttpEntity<>(currentProduct);
            ResponseEntity<FakeStorProductDTO> updatedProductResponse = restTemplate.exchange("https://fakestoreapi.com/products/"+ productID,
                    HttpMethod.PUT, requestEntity, FakeStorProductDTO.class);
            return updatedProductResponse.getBody().toProduct();
        } else {
            throw new RuntimeException("Product not found with id: " + productID);
        }

    }

    public void deleteProduct(Long id){
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
    }

    @Override
    public List<String> getAllCategories() {
        String apiUrl = "https://fakestoreapi.com/products/categories";
        String[] categories = restTemplate.getForObject(apiUrl, String[].class);
        return Arrays.asList(categories);
    }

    public List<Product> getProductsInCategory(String category) {
        String apiUrl = "https://fakestoreapi.com/products/category/" + category;
        Product[] products = restTemplate.getForObject(apiUrl, Product[].class);
        return Arrays.asList(products);
    }



}
