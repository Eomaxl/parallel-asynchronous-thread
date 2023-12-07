package com.eomaxl.java.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo {
    private String productId;
    private List<ProductOption> productOptions;
}
