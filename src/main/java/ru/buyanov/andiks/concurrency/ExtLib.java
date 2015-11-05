package ru.buyanov.andiks.concurrency;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Provided lib
 */
public class ExtLib {

    
    public BigInteger eval(BigInteger a, int p) {
        try {
            // Do some tough work
            Thread.sleep(20 * a.longValue());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return a.pow(p);
    }
}
