package ru.buyanov.andiks.concurrency;

import ru.buyanov.andiks.concurrency.ArrayEvaluationFactory.ArrayEvaluation;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Uses common index to get currentIndex to evaluate
 */
public class CommonIndexWorker implements Runnable {
    private final ArrayEvaluation arrayEvaluation;
    private final AtomicInteger index;

    public CommonIndexWorker(ArrayEvaluation arrayEvaluation, AtomicInteger index) {
        this.arrayEvaluation = arrayEvaluation;
        this.index = index;
    }

    public void run() {
        while (arrayEvaluation.eval(index.getAndIncrement())) {}
    }
}
