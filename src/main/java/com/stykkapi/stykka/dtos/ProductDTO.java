package com.stykkapi.stykka.dtos;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
//@NoArgsConstructor
public class ProductDTO {

    @NotNull
    private String productName;
    @NotNull
    private String productDescription;
    @NotNull
    private String productSpec;
    @NotNull
    private double price;
    @NotNull
    private Integer quantity;
    @NotNull
    private String category;
    @NotNull
    private String subCategory;
    @NotNull
    private String review;
    @NotNull
    private String sellerName;
}
