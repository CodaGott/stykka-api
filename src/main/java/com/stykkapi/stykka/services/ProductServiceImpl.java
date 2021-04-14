package com.stykkapi.stykka.services;

import com.stykkapi.stykka.categories.Category;
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
        return productRepository.findAll();
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
        product.setProductCategory(productDTO.getProductCategory());

        productRepository.save(product);

    }

    @Override
    public List<Product> findProductByProductName(String productName)  {
        return productRepository.findProductByProductName(productName);
    }

    @Override
    public void updateProductInfo(Product updateProduct, String productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
//        if (optionalProduct.get().equals(productId)){
//            return;
//        }

        optionalProduct.ifPresent(
                product -> {
                    if(updateProduct.getProductName() != null)
                        product.setProductName(updateProduct.getProductName());

                    if(updateProduct.getProductSpec() != null)
                        product.setProductSpec(updateProduct.getProductSpec());

                    if(updateProduct.getPrice() != -1)
                        product.setPrice(updateProduct.getPrice());

                    if(updateProduct.getProductDescription() != null)
                        product.setProductDescription(updateProduct.getProductDescription());

                    if(updateProduct.getProductCategory() != null)
                        product.setProductCategory(updateProduct.getProductCategory());

                    if(!updateProduct.getQuantity().equals(-1) || updateProduct.getQuantity() != null)
                        product.setQuantity(updateProduct.getQuantity());

                    if(updateProduct.getSubCategory() != null)
                        product.setSubCategory(updateProduct.getSubCategory());

                    if(updateProduct.getReview() != null)
                        product.setReview(updateProduct.getReview());

                    if(updateProduct.getSellerName() != null)
                        product.setSellerName(updateProduct.getSellerName());
                }

        );
        productRepository.save(updateProduct);
    }

    @Override
    public List<Product> findByProductCategories(Category productCategory) {
//        Optional<Product> optionalProductCategory = productRepository.findById(productCategory);
        return productRepository.findProductByProductCategory(productCategory);
    }

    @Override
    public List<Product> findByProductPrice(double productPrice) {

        return productRepository.findByPrice(productPrice);
    }
}
