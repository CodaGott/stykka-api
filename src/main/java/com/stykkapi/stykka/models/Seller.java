package com.stykkapi.stykka.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "sellers")
@NoArgsConstructor
@RequiredArgsConstructor
public class Seller{
    @Id
    private String sellerId;
    private String sellerFirstName;
    private String sellerLastName;
    private String sellerEmail;
    private String sellerPassword;
    private String storeName;
    private String bankName;
    private String accountNumber;
//    private List<Product> listOfProduct;
}
