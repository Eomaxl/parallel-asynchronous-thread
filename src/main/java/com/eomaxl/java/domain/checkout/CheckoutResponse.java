package com.eomaxl.java.domain.checkout;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CheckoutResponse {

    CheckoutStatus checkoutStatus;
    List<CartItem> errorList = new ArrayList<>();
    double finalRate;

    public CheckoutResponse(CheckoutStatus checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }

    public CheckoutResponse(CheckoutStatus checkoutStatus, List<CartItem> errorList) {
        this.checkoutStatus = checkoutStatus;
        this.errorList = errorList;
    }
}