package com.stykkapi.stykka.services;

import com.stykkapi.stykka.dtos.ProductDTO;
import com.stykkapi.stykka.exceptions.ProductException;
import com.stykkapi.stykka.models.Product;
import com.stykkapi.stykka.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return null;
    }

    @Override
    public Optional<Product> findProductByProductId(String productId) {
        if(productRepository.findById(productId).isPresent()) {
            return productRepository.findById(productId);
        }else
            throw new NoSuchElementException("Product doesn't exist");
    }

    @Override
    public void deleteByProductId(String productId) throws ProductException {
        if(productRepository.findById(productId).isPresent()){
            productRepository.deleteById(productId);
        }else
            throw new ProductException("Product not found");

    }

    @Override
    public void deleteProductByName(String productName) {
//        Optional<Product> optionalProduct = productRepository.findById(productName);
        if (productRepository.findById(productName).isPresent()){
            productRepository.deleteProductByProductName(productName);
        }

    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        Product product = new Product();

        product.setProductName(productDTO.getProductName());
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductSpec(productDTO.getProductSpec());
        product.setProductSpec(productDTO.getProductSpec());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setCategory(productDTO.getCategory());
        product.setSubCategory(productDTO.getSubCategory());
        product.setReview(productDTO.getReview());

        productRepository.save(product);

    }

    @Override
    public ProductDTO findProductByProductName(String productName) throws ProductException {
        if(productRepository.findById(productName).isPresent()){
            return findProductByProductName(productName);
        }else
            throw new ProductException("Product not found");

    }
}
