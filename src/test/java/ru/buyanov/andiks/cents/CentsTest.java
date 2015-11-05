package ru.buyanov.andiks.cents;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class CentsTest extends TestCase {

    public CentsTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(CentsTest.class);
    }

   public void testCountWaysToProduceGivenAmountOfMoney() {
        Cents c = new Cents();
        assertEquals(1, c.countWaysToProduceGivenAmountOfMoney(1));
        assertEquals(2, c.countWaysToProduceGivenAmountOfMoney(5));
        assertEquals(4, c.countWaysToProduceGivenAmountOfMoney(10));
        assertEquals(6, c.countWaysToProduceGivenAmountOfMoney(15));
        assertEquals(9, c.countWaysToProduceGivenAmountOfMoney(20));
        assertEquals(13, c.countWaysToProduceGivenAmountOfMoney(26));
        assertEquals(18, c.countWaysToProduceGivenAmountOfMoney(30));
        assertEquals(39, c.countWaysToProduceGivenAmountOfMoney(45));
        assertEquals(62, c.countWaysToProduceGivenAmountOfMoney(55));
    }
}
