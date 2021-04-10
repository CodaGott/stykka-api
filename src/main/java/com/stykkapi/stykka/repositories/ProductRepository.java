package com.stykkapi.stykka.repositories;

import com.stykkapi.stykka.categories.Category;
import com.stykkapi.stykka.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    void deleteProductByProductName(String productName);

    List<Product> findProductByProductName (String productName);

    List<Product> findProductByCategory(Category productCategory);

    List<Product> findByPrice(double productPrice);
}
