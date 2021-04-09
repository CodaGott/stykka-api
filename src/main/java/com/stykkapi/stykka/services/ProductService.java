package com.stykkapi.stykka.services;

import com.stykkapi.stykka.dtos.ProductDTO;
import com.stykkapi.stykka.exceptions.ProductException;
import com.stykkapi.stykka.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAllProducts();

    Optional<Product> findProductByProductId(String productId);

    void deleteByProductId(String productId) throws ProductException;

    void deleteProductByName(String productName);

    void addProduct(ProductDTO product);

    ProductDTO findProductByProductName (String productName) throws ProductException;


}
