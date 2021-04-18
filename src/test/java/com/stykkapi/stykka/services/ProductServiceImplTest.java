package com.stykkapi.stykka.services;

import com.stykkapi.stykka.categories.Category;
import com.stykkapi.stykka.dtos.ProductDTO;
import com.stykkapi.stykka.exceptions.ProductException;
import com.stykkapi.stykka.models.Product;
import com.stykkapi.stykka.models.Seller;
import com.stykkapi.stykka.repositories.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

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
        Seller seller = new Seller();
        seller.setSellerEmail("seller email");

        try {
            productService.addProduct(productDTO, seller.getSellerEmail());

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
        Seller seller = new Seller();
        seller.setSellerEmail("Selleremail@gmail.com");

        try{
            productService.addProduct(productDTO, seller.getSellerEmail());
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
        Optional<Product> optionalProduct = productRepository.findById("6076a5b25e27987ff3d475cf");
        Product product = new Product();
        product.setProductName("The");
        try{
            productService.updateProductInfo(product, "6076a5b25e27987ff3d475cf");
            assertEquals("The", optionalProduct.get().getProductName());
        }catch (ProductException e){
            e.getLocalizedMessage();
        }
    }

    @Test
    void testing(){
        Product sellerForProduct = new Product();
        Seller seller = new Seller();
        sellerForProduct.setStoreName("the seller");

    }
}