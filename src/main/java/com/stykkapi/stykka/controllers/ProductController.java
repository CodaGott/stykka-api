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
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(value = "/addProduct/seller{sellerEmail}")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO, @PathVariable String sellerEmail){
        try{
            productService.addProduct(productDTO, sellerEmail);
            return new ResponseEntity<>("Product added successfully!", HttpStatus.CREATED);
        }catch (ProductException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@RequestBody Product updateProduct, @PathVariable String productId){
        try{
            productService.updateProductInfo(updateProduct, productId);
            return new ResponseEntity<>("information updated successfully!", HttpStatus.OK);
        }catch (ProductException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/searchByPrice")
    public ResponseEntity<?> searchByPrice(@RequestParam double productPrice){
        try{
            productService.findByProductPrice(productPrice);
            return new ResponseEntity<>("Search result(s) \n " + productService.findByProductPrice(productPrice), HttpStatus.FOUND);
        }catch (ProductException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NO_CONTENT);
        }
    }
}
