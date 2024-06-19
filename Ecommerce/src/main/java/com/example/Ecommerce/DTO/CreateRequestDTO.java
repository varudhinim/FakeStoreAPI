package com.example.Ecommerce.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRequestDTO {

    private String title;
    private Double price;
    private String category;
    private String description;
    private String image;
}
