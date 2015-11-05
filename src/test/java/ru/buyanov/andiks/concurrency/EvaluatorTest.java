package ru.buyanov.andiks.concurrency;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.junit.Assert.*;

import java.math.BigInteger;

/**
 */
public class EvaluatorTest extends TestCase {

    public EvaluatorTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(EvaluatorTest.class);
    }

    public void testEvaluate() {
        BigInteger[] source = new BigInteger[] {
                new BigInteger("10"),
                new BigInteger("101"),
                new BigInteger("102"),
                new BigInteger("103"),
                new BigInteger("78"),
                new BigInteger("256"),
                new BigInteger("123"),
                new BigInteger("222"),
                new BigInteger("25")
        };
        BigInteger[] expected = new BigInteger[] {
                new BigInteger("10000000000"),
                new BigInteger("110462212541120451001"),
                new BigInteger("121899441999475713024"),
                new BigInteger("134391637934412192049"),
                new BigInteger("8335775831236199424"),
                new BigInteger("1208925819614629174706176"),
                new BigInteger("792594609605189126649"),
                new BigInteger("290756708973467203175424"),
                new BigInteger("95367431640625")
        };
        int exp = 10;

        Evaluator evaluator = new Evaluator();
        evaluator.setExtLib(new ExtLib()); // better to use Mock, but test task is not about this
        BigInteger[] result = evaluator.evaluate(source, exp);
        assertArrayEquals(expected, result);
    }
}
