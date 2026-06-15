package com.computerZone.pcshop.DTO.User.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDTO {

    private String name;

    private String description;

    private Double price;

    private Integer stockQuantity;

    private String brand;

    private String category;
}