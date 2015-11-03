package ru.buyanov.andiks.cents;

import junit.framework.Assert;
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

   public void testApp() {
        Cents c = new Cents();
        Assert.assertEquals(1, c.countWaysToProduceGivenAmountOfMoney(1));
        Assert.assertEquals(2, c.countWaysToProduceGivenAmountOfMoney(5));
        Assert.assertEquals(4, c.countWaysToProduceGivenAmountOfMoney(10));
        Assert.assertEquals(6, c.countWaysToProduceGivenAmountOfMoney(15));
        Assert.assertEquals(9, c.countWaysToProduceGivenAmountOfMoney(20));
        Assert.assertEquals(13, c.countWaysToProduceGivenAmountOfMoney(26));
        Assert.assertEquals(18, c.countWaysToProduceGivenAmountOfMoney(30));
        Assert.assertEquals(39, c.countWaysToProduceGivenAmountOfMoney(45));
        Assert.assertEquals(62, c.countWaysToProduceGivenAmountOfMoney(55));
    }
}
