package com.eomaxl.java.service;

import com.eomaxl.java.domain.checkout.Cart;
import com.eomaxl.java.domain.checkout.CheckoutResponse;
import com.eomaxl.java.domain.checkout.CheckoutStatus;
import com.eomaxl.java.util.DataSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {
    PriceValidatorService priceValidatorService = new PriceValidatorService();
    CheckoutService checkoutService = new CheckoutService(priceValidatorService);

    @Test
    void checkout_6_times() {
        //given
        Cart cart = DataSet.createCart(6);
        // when
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);
        CheckoutResponse checkoutResponse1 = checkoutService.checkoutWithParallelStream(cart);

        // then
        assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());
    }
}