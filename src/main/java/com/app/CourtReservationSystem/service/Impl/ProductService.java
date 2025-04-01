package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.image.ImagePayload;
import com.app.CourtReservationSystem.dto.product.CreateProductPayload;
import com.app.CourtReservationSystem.dto.product.ProductResponse;
import com.app.CourtReservationSystem.dto.product.UpdateProductPayload;
import com.app.CourtReservationSystem.enums.ImageStatus;
import com.app.CourtReservationSystem.exception.ResourceNotFoundException;
import com.app.CourtReservationSystem.mapper.ImageMapper;
import com.app.CourtReservationSystem.mapper.ProductMapper;
import com.app.CourtReservationSystem.model.Image;
import com.app.CourtReservationSystem.model.Product;
import com.app.CourtReservationSystem.repository.CategoryRepository;
import com.app.CourtReservationSystem.repository.ProductRepository;
import com.app.CourtReservationSystem.service.IProductService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService implements IProductService {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ProductMapper productMapper;
    ImageMapper imageMapper;

    @Override
    public ProductResponse getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        return productMapper.toDTO(product);
    }

    @Override
    @Transactional
    public ProductResponse createProduct(CreateProductPayload createProductPayload) {
        Product product = productMapper.toEntity(createProductPayload);
        product.setCategory(categoryRepository.findById(createProductPayload.getCategoryId()).orElse(null));
        
        Image image = this.imageMapper.toEntity(createProductPayload.getImage());
        product.setImageProduct(image);
        
        productRepository.save(product);
        return productMapper.toDTO(product);
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(UpdateProductPayload updateProductPayload, Long id) {
        productRepository.updateProductById(updateProductPayload, id);

        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        ImagePayload imagePayload = updateProductPayload.getImage();

        if (updateProductPayload.getImage() != null) {
            product.getImageProduct().setStatus(ImageStatus.INACTIVE); // SET INACTIVE TO DELETE IN FUTURE BY CRON JOB
            product.setImageProduct(imageMapper.toEntity(imagePayload));
            productRepository.save(product);
        }

        return productMapper.toDTO(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return productMapper.toDTOs(products);
    }
}
