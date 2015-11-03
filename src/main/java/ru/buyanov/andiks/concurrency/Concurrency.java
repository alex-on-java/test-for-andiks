package ru.buyanov.andiks.concurrency;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Use all cores to eval
 */
public class Concurrency {
    private static final int NUMBER_OF_CORES = 4;

    public static void main(String[] args) {
        BigInteger[] source = new BigInteger[] {
                new BigInteger("10"),
                new BigInteger("101"),
                new BigInteger("102"),
                new BigInteger("103"),
                new BigInteger("1041"),
                new BigInteger("1042"),
                new BigInteger("1043"),
                new BigInteger("1044"),
                new BigInteger("1045")
        };
        BigInteger[] result = new BigInteger[source.length];
        int exp = 10;
        AtomicInteger index = new AtomicInteger(0);
        Data data = new Data(source, result, exp, index);
        ExtLib extLib = new ExtLib();

        ExecutorService threadPool = Executors.newFixedThreadPool(NUMBER_OF_CORES);
        for (int i = 0; i < NUMBER_OF_CORES; i++) {
            threadPool.submit(new Worker(data, extLib));
        }
        threadPool.shutdown();

        try {
            // Wait for all work is done
            threadPool.awaitTermination(2, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            // Let's see, how much of work is done
            e.printStackTrace();
        }

        // Print results
        for (int i = 0; i < source.length; i++) {
            System.out.printf("%s -> %s %n", source[i], result[i]);
        }
    }
}
