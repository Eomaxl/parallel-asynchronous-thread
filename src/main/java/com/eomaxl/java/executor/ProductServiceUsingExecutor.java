package com.eomaxl.java.executor;

import com.eomaxl.java.domain.Product;
import com.eomaxl.java.domain.ProductInfo;
import com.eomaxl.java.domain.Review;
import com.eomaxl.java.service.ProductInfoService;
import com.eomaxl.java.service.ProductService;
import com.eomaxl.java.service.ReviewService;
import com.eomaxl.java.thread.ProductServiceUsingThread;

import java.util.concurrent.*;

import static com.eomaxl.java.util.CommonUtil.stopWatch;
import static com.eomaxl.java.util.LoggerUtil.log;

public class ProductServiceUsingExecutor {
    static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());  // return the number of core present in my system
    private ProductInfoService productInfoService;
    private ReviewService reviewService;

    public ProductServiceUsingExecutor(ProductInfoService productInfoService, ReviewService reviewService){
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }

    public Product retrieveProductDetails(String productId) throws ExecutionException, InterruptedException {
        stopWatch.start();

        // Callable will retrieve the result from the completionQueue. It is going to return a future object
        Future<ProductInfo> productInfoFuture = executorService.submit(()-> productInfoService.retrieveProductInfo(productId));
        Future<Review> reviewFuture = executorService.submit(()-> reviewService.retrieveReviews(productId));

        ProductInfo productInfo = productInfoFuture.get();
        Review review = reviewFuture.get();

        stopWatch.stop();
        log("Total time taken : "+stopWatch.getTime());
        return new Product(productId, productInfo, review);
    }

    public static void main(String[] args) throws InterruptedException {
        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService  = new ReviewService();
        ProductServiceUsingThread productService = new ProductServiceUsingThread(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        log("Product is :"+product);
        executorService.shutdown();
    }
}
