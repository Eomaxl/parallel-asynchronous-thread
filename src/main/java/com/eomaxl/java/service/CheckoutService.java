package com.eomaxl.java.service;

import com.eomaxl.java.domain.checkout.Cart;
import com.eomaxl.java.domain.checkout.CartItem;
import com.eomaxl.java.domain.checkout.CheckoutResponse;
import com.eomaxl.java.domain.checkout.CheckoutStatus;

import java.util.stream.Collectors;
import java.util.*;

import static com.eomaxl.java.util.CommonUtil.stopWatch;
import static com.eomaxl.java.util.LoggerUtil.log;

public class CheckoutService {

    private PriceValidatorService priceValidatorService;

    public CheckoutService(PriceValidatorService priceValidatorService){
        this.priceValidatorService = priceValidatorService;
    }
    public CheckoutResponse checkout(Cart cart){
    stopWatch.start();
    List<CartItem> priceValidationList =    cart.getCartItemList().stream()
                                                .map(cartItem -> {
                                                    boolean isPriceInvalid = priceValidatorService.isCartItemInvalid(cartItem);
                                                    cartItem.setExpired(isPriceInvalid);
                                                    return cartItem;
                                                })
                                                .filter(CartItem::isExpired)
                                                .collect(Collectors.toList());

    if(priceValidationList.size() > 0){
        return new CheckoutResponse(CheckoutStatus.FAILURE,priceValidationList);
    }

    stopWatch.stop();
    log("The time taken was : "+stopWatch.getTime());
    stopWatch.reset();
    return new CheckoutResponse(CheckoutStatus.SUCCESS);
    }


    public CheckoutResponse checkoutWithParallelStream(Cart cart){
        stopWatch.start();
        List<CartItem> priceValidationList =    cart.getCartItemList().parallelStream()
                .map(cartItem -> {
                    boolean isPriceInvalid = priceValidatorService.isCartItemInvalid(cartItem);
                    cartItem.setExpired(isPriceInvalid);
                    return cartItem;
                })
                .filter(CartItem::isExpired)
                .collect(Collectors.toList());

        if(priceValidationList.size() > 0){
            return new CheckoutResponse(CheckoutStatus.FAILURE,priceValidationList);
        }

        stopWatch.stop();
        log("The time taken was : "+stopWatch.getTime());
        return new CheckoutResponse(CheckoutStatus.SUCCESS);
    }

}
