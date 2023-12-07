package com.eomaxl.java.service;

import com.eomaxl.java.domain.Review;
import static com.eomaxl.java.util.CommonUtil.delay;


public class ReviewService {
    public Review retrieveReviews(String productId){
        delay(1000);
        return new Review(200,4.5);
    }
}
