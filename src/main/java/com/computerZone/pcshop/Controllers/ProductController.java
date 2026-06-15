package com.computerZone.pcshop.Controllers;

import com.computerZone.pcshop.DTO.User.Product.ProductCreateDTO;
import com.computerZone.pcshop.DTO.User.Product.ProductResponseDTO;
import com.computerZone.pcshop.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAll")
    public List<ProductResponseDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(
            @PathVariable Long id
    ) {
        return productService.getProductById(id);
    }

    @PostMapping(
            value = "/add",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ProductResponseDTO createProduct(
            @ModelAttribute ProductCreateDTO dto,
            @RequestParam("files") List<MultipartFile> files
    ) throws Exception {

        return productService.createProduct(dto, files);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(
            @PathVariable Long id
    ) {
        return productService.deleteProduct(id);
    }
}