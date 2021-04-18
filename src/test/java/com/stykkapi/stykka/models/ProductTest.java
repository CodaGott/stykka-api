package com.stykkapi.stykka.models;

import com.stykkapi.stykka.repositories.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
@DataMongoTest
class ProductTest {
    Product product;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        product = new Product();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void canCreateProduct(){
        product.setProductName("DozieProduct");
        product.setProductDescription("Real product no fake");
        product.setCategory("Men cloth");
        product.setStoreName("Dozie Enterprises");

        productRepository.save(product);
        assertEquals(productRepository.count(), 1);
    }

    @Test
    void canReadProductById(){

        product.setProductName("DozieProduct");
        product.setProductDescription("Real product no fake");
        product.setCategory("Men cloth");
        product.setStoreName("Dozie Enterprises");

        Product thisProduct = productRepository.save(product);
        Optional<Product> findOptionalUser = productRepository.findById(thisProduct.getProductId());
        assertEquals(findOptionalUser.get().getProductName(), "DozieProduct");
    }

    @Test
    void canUpdateProductInfo(){
        product.setProductName("DozieProduct");
        product.setProductDescription("Real product no fake");
        product.setCategory("Men cloth");
        product.setStoreName("Dozie Enterprises");

        Product thisProduct = productRepository.save(product);
        Optional<Product> findOptionalUser = productRepository.findById(thisProduct.getProductId());

        findOptionalUser.get().setCategory("Women Cloth");

        productRepository.save(findOptionalUser.get());
        assertEquals("Women Cloth", findOptionalUser.get().getCategory());
    }

    @Test
    void canDeleteProduct(){
        Product newProdut = new Product();

        newProdut.setProductName("DozieProduct");
        newProdut.setProductDescription("Real product no fake");
        newProdut.setCategory("Men cloth");
        newProdut.setStoreName("Dozie Enterprises");

        productRepository.save(newProdut);

        productRepository.delete(newProdut);

        assertEquals(productRepository.count(), 6);
    }
}