package com.eomaxl.java.service;

import com.eomaxl.java.domain.Inventory;
import com.eomaxl.java.domain.ProductOption;

import java.util.concurrent.CompletableFuture;

import static com.eomaxl.java.util.CommonUtil.delay;

public class InventoryService {
    public Inventory addInventory(ProductOption productOption) {
        delay(500);
        return Inventory.builder()
                .count(2).build();

    }

    public CompletableFuture<Inventory> addInventory_CF(ProductOption productOption) {

        return CompletableFuture.supplyAsync(() -> {
            delay(500);
            return Inventory.builder()
                    .count(2).build();
        });

    }
}