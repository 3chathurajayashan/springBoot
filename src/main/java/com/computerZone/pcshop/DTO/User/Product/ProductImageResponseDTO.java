package com.computerZone.pcshop.DTO.User.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageResponseDTO {

    private Long id;

    private String imageUrl;

    private String publicId;
}
