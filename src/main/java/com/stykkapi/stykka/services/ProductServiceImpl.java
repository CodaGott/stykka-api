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
    public List<Product> findProductByProductName(String productName)  {
        return productRepository.findProductByProductName(productName);
    }

    @Override
    public Product updateProductInfo(Product updateProduct, String productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        optionalProduct.ifPresent(
                product -> {
                    if(Optional.ofNullable(updateProduct.getProductName()).isPresent())
                        optionalProduct.get().setProductName(updateProduct.getProductName());

                    if(Optional.ofNullable(updateProduct.getProductSpec()).isPresent())
                        optionalProduct.get().setProductSpec(updateProduct.getProductSpec());

                    if(Optional.ofNullable(updateProduct.getPrice()).isPresent())
                        optionalProduct.get().setPrice(updateProduct.getPrice());

                    if(Optional.ofNullable(updateProduct.getProductDescription()).isPresent())
                        optionalProduct.get().setProductDescription(updateProduct.getProductDescription());

                    if(Optional.ofNullable(updateProduct.getCategory()).isPresent())
                        optionalProduct.get().setCategory(updateProduct.getCategory());

                    if(Optional.ofNullable(updateProduct.getQuantity()).isPresent())
                        optionalProduct.get().setQuantity(updateProduct.getQuantity());

                    if(Optional.ofNullable(updateProduct.getSubCategory()).isPresent())
                        optionalProduct.get().setSubCategory(updateProduct.getSubCategory());

                    if(Optional.ofNullable(updateProduct.getReview()).isPresent())
                        optionalProduct.get().setReview(updateProduct.getReview());

                    if(Optional.ofNullable(updateProduct.getSellerName()).isPresent())
                        optionalProduct.get().setSellerName(updateProduct.getSellerName());
                }

        );
        return productRepository.save(updateProduct);
    }

    @Override
    public List<Product> findByProductCategories(String productCategory) {
        return productRepository.findProductByCategory(productCategory);
    }

    @Override
    public List<Product> findByProductPrice(double productPrice) {
        return productRepository.findByPrice(productPrice);
    }


}
