package com.computerZone.pcshop.Repository;



import com.computerZone.pcshop.Models.ProductImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository
        extends JpaRepository<ProductImageModel, Long> {
}