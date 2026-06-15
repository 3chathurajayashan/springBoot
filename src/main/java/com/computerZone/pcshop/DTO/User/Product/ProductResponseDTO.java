package com.computerZone.pcshop.DTO.User.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Integer stockQuantity;

    private String brand;

    private String category;

    private Boolean available;

    private List<ProductImageResponseDTO> images;
}
