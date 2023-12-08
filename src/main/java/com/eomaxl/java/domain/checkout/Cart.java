package com.eomaxl.java.domain.checkout;

import java.util.List;

public class Cart {
    private Integer cardId;

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    private List<CartItem> cartItemList;
}
