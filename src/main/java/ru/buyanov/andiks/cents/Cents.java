package ru.buyanov.andiks.cents;

/*
         Please implement this method to
         return the number of different combinations of US coins
         (penny: 1c, nickel: 5c, dime: 10c, quarter: 25c, half-dollar: 50c)
         which may be used to produce a given amount of money.

         For example, 11 cents can be produced with
         one 10-cent coin and one 1-cent coin,
         two 5-cent coins and one 1-cent coin,
         one 5-cent coin and six 1-cent coins,
         or eleven 1-cent coins.
         So there are four unique ways to produce 11 cents.
         Assume that the cents parameter is always positive.
*/
public class Cents {
    private int [] denominations = new int[]{1,5,10,25,50};

    public int countWaysToProduceGivenAmountOfMoney(int cents) {
        if (cents < 1)
            return 0;
        int denominationIndex = 0;
        for (int i = denominations.length - 1; i >= 0; i--) {
            if (cents >= denominations[i]) {
                denominationIndex = i;
                break;
            }
        }
        return getCombinations(cents, denominationIndex);
    }

    private int getCombinations(int cents, int denominationIndex) {
        if (denominationIndex == 0 || cents < 5)
            return 1;
        int result = 0;
        int denomination = denominations[denominationIndex];
        int max = cents / denomination;
        for (int i = max; i >= 0; i--) {
            int leftover = cents - i * denomination;
            int combinations = getCombinations(leftover, denominationIndex - 1);
            result += combinations;
        }
        return result;
    }
}
