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

    public CheckoutStatus getCheckoutStatus() {
        return checkoutStatus;
    }

    public void setCheckoutStatus(CheckoutStatus checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }

    public List<CartItem> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<CartItem> errorList) {
        this.errorList = errorList;
    }

    public double getFinalRate() {
        return finalRate;
    }

    public void setFinalRate(double finalRate) {
        this.finalRate = finalRate;
    }

    public CheckoutResponse(CheckoutStatus checkoutStatus, List<CartItem> errorList) {
        this.checkoutStatus = checkoutStatus;
        this.errorList = errorList;
    }
}