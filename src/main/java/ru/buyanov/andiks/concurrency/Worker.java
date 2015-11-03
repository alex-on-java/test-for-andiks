package ru.buyanov.andiks.concurrency;

/**
 * Uses ExtLib.eval(a,p) to power elements of the given array and save result
 */
public class Worker implements Runnable {
    private Data data;
    private ExtLib extLib;

    public Worker(Data data, ExtLib extLib) {
        this.data = data;
        this.extLib = extLib;
    }

    public void run() {
        int currentIndex;
        while ((currentIndex = data.index.getAndIncrement()) < data.source.length) {
            data.result[currentIndex] = extLib.eval(data.source[currentIndex], data.exp);
        }
    }
}
