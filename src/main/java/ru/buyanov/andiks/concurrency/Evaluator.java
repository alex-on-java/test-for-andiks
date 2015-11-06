package ru.buyanov.andiks.concurrency;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import ru.buyanov.andiks.concurrency.ArrayEvaluationFactory.ArrayEvaluation;

/**
 * Use all cores to evaluate
 */
public class Evaluator {
    // Expect DI here
    private ArrayEvaluationFactory factory;

    public ArrayEvaluationFactory getFactory() {
        return factory;
    }

    public void setFactory(ArrayEvaluationFactory factory) {
        this.factory = factory;
    }

    /*
        Method uses one by one evaluation, for the case if different tasks
        will take much different time to complete.
         */
    public BigInteger[] evaluate(BigInteger[] data, int p) {
        AtomicInteger index = new AtomicInteger(0);
        ArrayEvaluation arrayEvaluation = factory.createArrayEvaluation(data, p);
        int numberOfThreads = Runtime.getRuntime().availableProcessors() - 1; // -1 because we will use current thread
        ExecutorService threadPool = Executors.newFixedThreadPool(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            threadPool.submit(new CommonIndexWorker(arrayEvaluation, index));
        }
        new CommonIndexWorker(arrayEvaluation, index).run();
        shutdownAndWait(threadPool);
        return arrayEvaluation.getResult();
    }


    /*
    Method splits input array into equal parts, and all threads will work with the same amount of tasks
     */
    public BigInteger[] evaluateBySplit(BigInteger[] data, int p) {
        ArrayEvaluation arrayEvaluation = factory.createArrayEvaluation(data, p);
        int numberOfThreads = Runtime.getRuntime().availableProcessors() - 1; // -1 because we will use current thread
        Bounds[] bounds = generateBounds(data.length, numberOfThreads + 1);
        ExecutorService threadPool = Executors.newFixedThreadPool(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            threadPool.submit(new SubArrayWorker(arrayEvaluation, bounds[i].start, bounds[i].end));
        }
        new SubArrayWorker(arrayEvaluation, bounds[bounds.length - 1].start, bounds[bounds.length - 1].end).run();
        shutdownAndWait(threadPool);
        return arrayEvaluation.getResult();
    }

    /*
    On the one hand we should test this algorithm,
    on the another hand - it is a private method by design,
    and there is no reason (except testing) to make it public
     */
    private Bounds[] generateBounds(int length, int partsNumber) {
        if (length < partsNumber)
            throw new RuntimeException("length < partsNumber");
        int leftover = length % partsNumber;
        int wholeNumber = length / partsNumber;
        Bounds[] result = new Bounds[partsNumber];
        int previousEnd = -1;
        for (int i = 0; i < partsNumber; i++) {
            int end = previousEnd + wholeNumber + (leftover-- > 0 ? 1 : 0);
            result[i] = new Bounds(previousEnd + 1, end);
            previousEnd = end;
        }
        return result;
    }

    private void shutdownAndWait(ExecutorService threadPool) {
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
    }

    private static class Bounds {
        private final int start;
        private final int end;

        public Bounds(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
