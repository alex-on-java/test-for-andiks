package ru.buyanov.andiks.concurrency;

import java.math.BigInteger;

/**
 * Creates an instance of ArrayEvaluation
 */
public class ArrayEvaluationFactory {
    // Expect DI here
    private ExtLib extLib;


    public ExtLib getExtLib() {
        return extLib;
    }

    public void setExtLib(ExtLib extLib) {
        this.extLib = extLib;
    }

    public ArrayEvaluation createArrayEvaluation(BigInteger[] source, int exp) {
        return new ArrayEvaluation(source, exp, extLib);
    }

    /*
    Holds arrays to evaluate and for result.
     */
    public static class ArrayEvaluation {
        private final BigInteger[] source;
        private final BigInteger[] result;
        private final int exp;
        private final ExtLib extLib;

        private ArrayEvaluation(BigInteger[] source, int exp, ExtLib extLib) {
            this.source = source;
            this.exp = exp;
            result = new BigInteger[source.length];
            this.extLib = extLib;
        }

        /*
        Evaluate by index of the source array
        Array bound safe
         */
        public boolean eval(int index) {
            if (index >= source.length || index < 0)
                return false;
            result[index] = extLib.eval(source[index], exp);
            return true;
        }

        public BigInteger[] getResult() {
            return result;
        }
    }
}
