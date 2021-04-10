package com.stykkapi.stykka.models;

import com.stykkapi.stykka.categories.Category;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "product")
@NoArgsConstructor
public class Product {

    @Id
    private String productId;
    private String productName;
    private String productDescription;
    private Category productCategory;
    private double price;
    private Integer quantity;
    private String productSpec;
    private String category;
    private String subCategory;
    private String review;
    private String sellerName;
}
