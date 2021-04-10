package com.stykkapi.stykka.services;

import com.stykkapi.stykka.categories.Category;
import com.stykkapi.stykka.dtos.ProductDTO;
import com.stykkapi.stykka.exceptions.ProductException;
import com.stykkapi.stykka.repositories.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ProductServiceImplTest {

    ProductDTO productDTO;

    @Autowired
    ProductRepository productRepository;

    Category category;

    @Autowired
    ProductService productService;

    @BeforeEach
    void setUp() {
        productDTO = new ProductDTO("", "", "",0.0, 0, "",
                "", "", "" );
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void productCanBeDeleted(){
        productDTO.setProductName("product name");
        productDTO.setProductDescription("My product good geh");
        productDTO.setProductSpec("spec");
        productDTO.setPrice(44.9);
        productDTO.setQuantity(7);
        productDTO.setCategory("for guys");
        productDTO.setSubCategory("grown up");
        productDTO.setReview("review");
        productDTO.setSellerName("Seller Name");

        productService.addProduct(productDTO);

        assertEquals(productRepository.count(), 8);
    }

    @Test
    void deleteByProductName(){
        productDTO.setProductName("product name");
        productDTO.setProductDescription("My product good geh");
        productDTO.setProductSpec("spec");
        productDTO.setPrice(44.9);
        productDTO.setQuantity(7);
        productDTO.setCategory("for guys");
//        productDTO.setProductCategory(Category.MEN);
        productDTO.setSubCategory("grown up");
        productDTO.setReview("review");
        productDTO.setSellerName("Seller Name");

//        productRepository.save(productDTO);

        productService.deleteProductByName(productDTO.getProductName());

        assertEquals(7, productRepository.count());
    }

    @Test
    void findProductByName()  {

            assertEquals(3, productService.findProductByProductName("product name").size());
    }

    @Test
    void deleteProductById(){
        try {
            productService.deleteByProductId("606e477a230f326b88831112");
            assertEquals(productRepository.count(), 7);
        }
        catch(ProductException e){
            e.getLocalizedMessage();
        }
    }

    @Test
    void findByCategories(){
        productDTO.setProductName("product name");
        productDTO.setProductDescription("My product good geh");
        productDTO.setProductSpec("spec");
        productDTO.setPrice(44.9);
        productDTO.setQuantity(7);
//        productDTO.setProductCategory(Category.MEN);

//        productRepository.save(productDTO);
//        assertEquals(1, productService.findByProductCategories(Category.MEN).size());
    }

    @Test
    void findByProductPrice(){
        assertEquals(3, productService.findByProductPrice(44.9).size());
    }
}