package com.javase.Java8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Created by 周杰伦 on 2018/5/7.
 */
public class CompletableFuture使用 {
    public static void main(String[] args) {

    }
    public Future<Double> getPriceAsync(final String product) {
        final CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            double price = calculatePrice(product);
            futurePrice.complete(price);  //完成后使用complete方法，设置future的返回值
        }).start();
        return futurePrice;
    }
    class Work{
        void parse(){

        }
    }
//    public List<String> findPriceAsync(String product) {
//        List tasks = new ArrayList();
//        List<CompletableFuture<String>> priceFutures = tasks.stream()
//                .map(task -> CompletableFuture.supplyAsync(() -> task.getPrice(product),executor))
//                .map(future -> future.thenApply(Work::parse))
//                .map(future -> future.thenCompose(work -> CompletableFuture.supplyAsync(() -> Count.applyCount(work), executor)))
//                .collect(Collectors.toList());
//
//        return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
//    }

    private double calculatePrice(String product) {
        return 1;
    }
}
