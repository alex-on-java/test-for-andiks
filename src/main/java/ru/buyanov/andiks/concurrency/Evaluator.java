package ru.buyanov.andiks.concurrency;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Use all cores to evaluate
 */
public class Evaluator {
    // Expect DI here
    private ExtLib extLib;


    public ExtLib getExtLib() {
        return extLib;
    }

    public void setExtLib(ExtLib extLib) {
        this.extLib = extLib;
    }

    /*
    Method uses one by one evaluation, for the case if different tasks
    will take much different time to complete.
     */
    public BigInteger[] evaluate(BigInteger[] data, int p) {
        BigInteger[] result = new BigInteger[data.length];
        AtomicInteger index = new AtomicInteger(0);
        int numberOfCores = Runtime.getRuntime().availableProcessors(); // use logical cores
        ExecutorService threadPool = Executors.newFixedThreadPool(numberOfCores);
        for (int i = 0; i < numberOfCores; i++) {
            threadPool.submit(new Worker(new Data(data, result, p, index), extLib));
        }
        threadPool.shutdown();
        try {
            /*
             Wait for all work is done
             Cause we just refactoring method for cores utilisation,
             we couldn't use any async patterns without changing clients code
              */
            threadPool.awaitTermination(2, TimeUnit.MINUTES); // this constant should be changeable
                                                              // without rebuilding the project
        } catch (InterruptedException e) {
            /*
            We either could rethrow exception
            or return all we have calculated before interruption.
            Without any information, I choose second one
            and because I don't want to add more dependencies like loggers for test task,
            I just use e.printStackTrace();
             */
            e.printStackTrace();
        }
        return result;
    }
}
