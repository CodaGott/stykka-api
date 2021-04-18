package com.stykkapi.stykka.dtos;

import com.stykkapi.stykka.categories.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
public class ProductDTO {

    @NotNull
    private String productName;
    @NotNull
    private String productDescription;
    @NotNull
    private String productSpec;
    @NotNull
    private Category productCategory;
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
    private String storeName;
}
