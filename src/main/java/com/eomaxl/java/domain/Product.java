package com.eomaxl.java.domain;

import lombok.NonNull;

public class Product {

    @NonNull
    private String productId;
    @NonNull
    private ProductInfo productInfo;
    @NonNull
    private Review review;

    public Product(String productId, ProductInfo productInfo,Review review){
        this.productId = productId;
        this.productInfo = productInfo;
        this.review = review;
    }
}
