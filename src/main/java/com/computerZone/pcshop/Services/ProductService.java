package com.computerZone.pcshop.Services;

import com.computerZone.pcshop.DTO.User.Product.ProductCreateDTO;
import com.computerZone.pcshop.DTO.User.Product.ProductResponseDTO;
import com.computerZone.pcshop.Models.Product;
import com.computerZone.pcshop.Models.ProductImageModel;
import com.computerZone.pcshop.Repository.ProductImageRepository;
import com.computerZone.pcshop.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ModelMapper modelMapper;

    // Get All Products
    public List<ProductResponseDTO> getAllProducts() {

        List<Product> products = productRepository.findAll();

        return modelMapper.map(
                products,
                new TypeToken<List<ProductResponseDTO>>() {}.getType()
        );
    }

    // Get Product By ID
    public ProductResponseDTO getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        return modelMapper.map(product, ProductResponseDTO.class);
    }

    // Create Product With Images
    public ProductResponseDTO createProduct(
            ProductCreateDTO dto,
            List<MultipartFile> files
    ) throws Exception {

        Product product =
                modelMapper.map(dto, Product.class);

        product.setAvailable(
                product.getStockQuantity() > 0
        );

        Product savedProduct =
                productRepository.save(product);

        List<ProductImageModel> imageList =
                new ArrayList<>();

        for (MultipartFile file : files) {

            Map uploadResult =
                    cloudinaryService.uploadFile(file);

            ProductImageModel image =
                    new ProductImageModel();

            image.setImageUrl(
                    uploadResult.get("secure_url").toString()
            );

            image.setPublicId(
                    uploadResult.get("public_id").toString()
            );

            image.setProduct(savedProduct);

            imageList.add(image);
        }

        productImageRepository.saveAll(imageList);

        savedProduct.setImages(imageList);

        return modelMapper.map(
                savedProduct,
                ProductResponseDTO.class
        );
    }

    // Delete Product
    public String deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        productRepository.delete(product);

        return "Product deleted successfully";
    }
}