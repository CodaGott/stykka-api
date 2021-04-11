package com.stykkapi.stykka.controllers;


import com.stykkapi.stykka.dtos.ProductDTO;
import com.stykkapi.stykka.exceptions.ProductException;
import com.stykkapi.stykka.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


}
