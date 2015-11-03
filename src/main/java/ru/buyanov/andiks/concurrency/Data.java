package ru.buyanov.andiks.concurrency;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class Data {
    public final BigInteger[] source;
    public final BigInteger[] result;
    public final int exp;
    public final AtomicInteger index;

    public Data(BigInteger[] source, BigInteger[] result, int exp, AtomicInteger index) {
        this.source = source;
        this.result = result;
        this.exp = exp;
        this.index = index;
    }
}
