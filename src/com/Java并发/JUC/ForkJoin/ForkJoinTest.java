package com.Java并发.JUC.ForkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Created by 周杰伦 on 2018/5/23.
 */
public class ForkJoinTest {
    public static void main(String[] args) {
        //分裂计算1到100
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask countTask = new CountTask(1, 100);
        Future future = forkJoinPool.submit(countTask);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    static class CountTask extends RecursiveTask<Integer> {
        int start;
        int end;
        CountTask (int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        protected Integer compute() {
            int sum = 0;
            if (end - start < 10) {
                for (int i = start;i <= end;i ++) {
                    sum += i;
                }
            }else {
                int mid = (start + end)/2 ;
                CountTask left = new CountTask(start, mid);
                CountTask right = new CountTask(mid + 1, end);
                left.fork();
                right.fork();

                int leftRes = left.join();
                int rightRes = right.join();
                sum += leftRes + rightRes;
            }
            return sum;
        }
    }
}
