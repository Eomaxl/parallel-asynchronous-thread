package com.eomaxl.java.domain.checkout;

public class CartItem {

    private Integer itemId;
    private Integer quantity;
    private boolean isExpired;
    private String itemName;
    private double rate;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public CartItem(Integer itemId, String itemName, Integer quantity,  double rate,boolean isExpired) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.isExpired = isExpired;
        this.itemName = itemName;
        this.rate = rate;
    }
}
