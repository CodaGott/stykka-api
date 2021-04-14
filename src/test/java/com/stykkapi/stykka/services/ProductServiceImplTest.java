package com.stykkapi.stykka.services;

import com.stykkapi.stykka.categories.Category;
import com.stykkapi.stykka.dtos.ProductDTO;
import com.stykkapi.stykka.exceptions.ProductException;
import com.stykkapi.stykka.models.Product;
import com.stykkapi.stykka.repositories.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ProductServiceImplTest {

    Product product = new Product();

    ProductDTO productDTO;

    @Autowired
    ProductRepository productRepository;

    Category category;

    @Autowired
    ProductService productService;

    @BeforeEach
    void setUp() {
        productDTO = new ProductDTO();
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
        try {
            productService.addProduct(productDTO);

            assertEquals(productRepository.count(), 8);
        }catch (ProductException e){
            e.getLocalizedMessage();
        }

    }


    @Test
    void deleteByProductName(){
        productDTO.setProductName("product name");
        productDTO.setProductDescription("My product good geh");
        productDTO.setProductSpec("spec");
        productDTO.setPrice(44.9);
        productDTO.setQuantity(7);
        productDTO.setCategory("for guys");
        productDTO.setSubCategory("grown up");
        productDTO.setReview("review");
        productDTO.setSellerName("Seller Name");

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

        assertEquals(5, productService.findByProductCategories(Category.MEN).size());
    }

    @Test
    void findByProductPrice(){
        try{
            assertEquals(7, productService.findByProductPrice(44.9).size());
        }catch (ProductException e){
            e.getLocalizedMessage();
        }

    }

    @Test
    void productCanBeCreated(){
        productDTO.setProductName("name of product");
        productDTO.setProductDescription("My product");
        productDTO.setProductSpec("spec");
        productDTO.setPrice(44.9);
        productDTO.setQuantity(7);
        productDTO.setProductCategory(Category.MEN);
        productDTO.setSubCategory("sub");
        productDTO.setReview("my review");
        productDTO.setSellerName("mr seller");
        productDTO.setCategory("string category");

        try{
            productService.addProduct(productDTO);
            assertEquals(9, productRepository.count());
        }catch (ProductException e){
            e.getLocalizedMessage();
        }


    }

    @Test
    void canFindAllProducts(){
        for (Product products : productService.findAllProducts()){
            System.out.println(products.getProductCategory());
        }
    }

    @Test
    void canUpdateProduct(){
        productDTO.setProductName("this is a test");
        productDTO.setProductDescription("My product");
        productDTO.setProductSpec("spec");
        productDTO.setPrice(8);
        productDTO.setQuantity(7);
        productDTO.setProductCategory(Category.MEN);
        productDTO.setSubCategory("sub");
        productDTO.setReview("my review");
        productDTO.setSellerName("mr seller");
        productDTO.setCategory("string category");
//        productService.updateProductInfo(productService.addProduct(productDTO), "606e47a340c4cc711c9d8436");
    }
}