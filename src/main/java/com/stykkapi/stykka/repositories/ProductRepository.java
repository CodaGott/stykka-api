package com.stykkapi.stykka.repositories;

import com.stykkapi.stykka.dtos.ProductDTO;
import com.stykkapi.stykka.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

    void deleteProductByProductName(String productName);

    ProductDTO findProductByProductName (String productName);


}
