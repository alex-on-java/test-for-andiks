package ru.buyanov.andiks.concurrency;

import ru.buyanov.andiks.concurrency.ArrayEvaluationFactory.ArrayEvaluation;

/**
 * Get the start and the end indexes, and evaluate all of them
 */
public class SubArrayWorker implements Runnable {
    private final ArrayEvaluation arrayEvaluation;
    private final int start;
    private final int end;

    public SubArrayWorker(ArrayEvaluationFactory.ArrayEvaluation arrayEvaluation, int start, int end) {
        this.arrayEvaluation = arrayEvaluation;
        this.start = start;
        this.end = end;
    }

    public void run() {
        for (int i = start; i <= end; i++) {
            arrayEvaluation.eval(i);
        }
    }
}
