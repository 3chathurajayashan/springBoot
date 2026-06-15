package com.computerZone.pcshop.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //cloudinary url
    private String imageUrl;

    //cloudinary public id
    private String publicId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
