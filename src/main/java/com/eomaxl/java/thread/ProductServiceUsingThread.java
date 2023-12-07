package com.eomaxl.java.thread;

import com.eomaxl.java.domain.Product;
import com.eomaxl.java.domain.ProductInfo;
import com.eomaxl.java.domain.Review;
import com.eomaxl.java.service.ProductInfoService;
import com.eomaxl.java.service.ReviewService;

import static com.eomaxl.java.util.CommonUtil.stopWatch;
import static com.eomaxl.java.util.LoggerUtil.log;

public class ProductServiceUsingThread {
    private ProductInfoService productInfoService;
    private ReviewService reviewService;


    public ProductServiceUsingThread(ProductInfoService productInfoService, ReviewService reviewService){
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }

    public Product retrieveProductDetails(String productId) throws InterruptedException{
        stopWatch.start();
        Runnable productInfoRunnable = new ProductInfoRunnable(productId);
        Thread productInfoThread = new Thread(productInfoRunnable);

        Runnable reviewRunnable = new ReviewRunnable(productId);
        Thread reviewThread = new Thread(reviewRunnable);

        // Start the thread. This will start the functionality up and running as background tasks.
        productInfoThread.start();
        reviewThread.start();

        // Now the thread have to wait until the execution of this functionality is completed.
        productInfoThread.join();
        reviewThread.join();

        // Once the join is completed the runnable is executed and the result is readily accepted.
        // to get the result
        ProductInfo productInfo =  ((ProductInfoRunnable) productInfoRunnable).getProductInfo();
        Review review = ((ReviewRunnable) reviewRunnable).getReview();

        stopWatch.stop();
        log("Total time taken : "+stopWatch.getTime());
        return new Product(productId,productInfo,review);
    }

    public static void main(String[] args) throws InterruptedException {
        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService  = new ReviewService();
        ProductServiceUsingThread productService = new ProductServiceUsingThread(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        log("Product is :"+product);
    }

    private class ProductInfoRunnable implements Runnable {
        private ProductInfo productInfo;
        private String productId;
        public ProductInfoRunnable (String productId){
            this.productId = productId;
        }

        public ProductInfo getProductInfo(){
            return productInfo;
        }

        @Override
        public void run(){

        }
    }

    private class ReviewRunnable implements Runnable {
        private String productId;
        private Review review;

        public ReviewRunnable(String productId){
            this.productId = productId;
        }

        public Review getReview(){
            return review;
        }

        @Override
        public void run(){
            review = reviewService.retrieveReviews(productId);
        }
    }
}
