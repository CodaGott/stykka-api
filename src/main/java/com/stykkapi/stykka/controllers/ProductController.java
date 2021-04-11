package com.stykkapi.stykka.controllers;


import com.stykkapi.stykka.dtos.ProductDTO;
import com.stykkapi.stykka.exceptions.ProductException;
import com.stykkapi.stykka.models.Product;
import com.stykkapi.stykka.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(value = "/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO){
        try{
            productService.addProduct(productDTO);
            return new ResponseEntity<>("Product added successfully!", HttpStatus.CREATED);
        }catch (ProductException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/updateProductInfo")
    public ResponseEntity<?> updateProduct(@RequestBody Product updateProduct, String productId){
        try{
            productService.updateProductInfo(updateProduct, productId);
            return new ResponseEntity<>("information updated successfully!", HttpStatus.OK);
        }catch (ProductException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/searchByPrice")
    public ResponseEntity<?> searchByPrice(@RequestBody double productPrice){
        try{
            productService.findByProductPrice(productPrice);
            return new ResponseEntity<>("Search result(s)", HttpStatus.FOUND);
        }catch (ProductException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NO_CONTENT);
        }
    }
}
