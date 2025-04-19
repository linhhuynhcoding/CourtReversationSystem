package com.app.CourtReservationSystem.dbs.seeders;

import com.app.CourtReservationSystem.model.Category;
import com.app.CourtReservationSystem.model.Image;
import com.app.CourtReservationSystem.model.Product;
import com.app.CourtReservationSystem.repository.CategoryRepository;
import com.app.CourtReservationSystem.repository.ProductRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Order(4)
public class ProductSeeder implements CommandLineRunner {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    private final String[] cates = {"BEVERAGE", "FOOD", "SNACK", "SPORT EQUIPMENT"};

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CourtSeeder starting...");
        loadCategoriesData();
        loadProductsData();
    }

    private void loadCategoriesData(){
        List<Category> categories = new ArrayList<>();

        for (String cat : cates) {
            Category category = new Category();
            category.setName(cat);
            categories.add(category);
        }

        categoryRepository.saveAll(categories);
    }

    private void loadProductsData(){
        List<Product> products = new ArrayList<>();

        for (int i = 1; i < 100; i++) {
            Product product = new Product();

            Category category = categoryRepository.findById(Math.round(Math.random() * 100) % 4 + 1).orElse(null);
            Image image = new Image();

            image.setImage_url(String.format("http://localhost:8080/images/%s.png", i));
            image.setHeight(800);
            image.setWidth(800);

            product.setName("Product " + i);
            product.setCategory(category);
            product.setPrice((Math.floor(Math.random() * 100000) % 1000 + 70) * 1000);
            product.setStock(Math.round(Math.random() * 1000));
            product.setBuyTurn(Math.round(Math.random() * 1000));
            product.setImageProduct(image);

            products.add(product);
        }

        productRepository.saveAll(products);
    }

}
