package com.juc.multiThread;

/**
 * Producer Consumer model
 */
public class HelloProducerConsumer {

    public static void main(String[] args) {
        final ProductCache<Integer> cache = new ProductCache<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Producer producer = new Producer(cache);
                producer.start();
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                Consumer consumer = new Consumer(cache);
                consumer.start();
            }
        }).start();
    }

    static class ProductCache<T> {
        // used to stored one product instance

        T product;
        boolean isPoped = true;

        final Object lock = new Object();

        public T pop() {
            synchronized (lock) {
                if (isPoped) {
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("   pop " + product);
                isPoped = true;
                lock.notify();
                return product;
            }

        }

        public void put(T t) {
            synchronized (lock) {
                if (!isPoped) {
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("put " + t);
                this.product = t;
                isPoped = false;
                lock.notify();
            }
        }
    }

    static class Producer {

        private ProductCache<Integer> productCache;

        public Producer(ProductCache<Integer> productCache) {
            this.productCache = productCache;
        }

        public void start() {
            Integer i = 0;
            while (i < 10) {
                productCache.put(i++);
            }
        }
    }

    static class Consumer {
        private ProductCache<Integer> productCache;

        public Consumer(ProductCache<Integer> productCache) {
            this.productCache = productCache;
        }

        public void start() {
            while (true) {
                productCache.pop();
            }
        }
    }

}