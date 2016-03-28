package org.swati.interviewcake;

import java.util.Arrays;

/**
 * Coin change combinations
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class CoinChangeCombo {
    public int changePossibilitiesTopDown(int amountLeft, int[] denominationsLeft) {

        // base cases:
        // we hit the amount spot on. yes!
        if (amountLeft == 0) return 1;
        // we overshot the amount left (used too many coins)
        if (amountLeft < 0) return 0;
        // we're out of denominations
        if (denominationsLeft.length == 0) return 0;

        System.out.println("checking ways to make " + amountLeft + " with " + Arrays.toString(denominationsLeft));

        // choose a current coin
        int current_coin = denominationsLeft[0];
        int[] restOfCoins = Arrays.copyOfRange(denominationsLeft, 1, denominationsLeft.length);

        // see how many possibilities we can get
        // for each number of times to use current_coin
        int numPossibilities = 0;
        while (amountLeft >= 0) {
            //number of possibilities without using the current_coin
            numPossibilities += changePossibilitiesTopDown(amountLeft, restOfCoins);
            amountLeft -= current_coin;
        }

        return numPossibilities;
    }

    public int changePossibilitiesBottomUp(int amount, int[] denominations) {
        /**
         * waysOfMaking1Cent for all of the amount is
         * With 1
         * am   makeup   ways
         * 1    1         1
         * 2    1,1       1
         * 3    1,1,1     1
         * 4    1,1,1,1   1
         * 5    1,1,1,1,1 1
         *
         * With 1&2
         * am  makeup    ways
         * 1   1           1
         * 2   1,1 & 2     2
         * 3   1,1,1&1,2   2
         *
         * So, basically for creating 3 from 1 & 2 it's whatever we had for 1 + whatever we have with 2.
         * Now, to find the number of ways with 2,
         * we can assume that those will have at least one 2, so then we have to figure out #of ways of 3-2 with 1&2
         * i.e. 1 with 1&2 which is 1 for 1 and 1 for 2. Thus, the final is 2.
         *
         * Taking another example:
         * waysWith1&2for5 -> waysWith1For5 + waysWith1&2For3
         *
         * Since waysWith1&2For3 => 2 and waysWith1For5 is 1, waysWith1&2For5 = 3
         */
        int[] waysOfMakingNCents = new int[amount + 1];
        waysOfMakingNCents[0] = 1;

        for (int coin : denominations) {
            for (int i = coin; i < waysOfMakingNCents.length; i++) {
                waysOfMakingNCents[i] = waysOfMakingNCents[i] + waysOfMakingNCents[i - coin];
            }
        }
        return waysOfMakingNCents[amount];
    }

    public static void main(String[] args) {
        CoinChangeCombo coinChangeCombo = new CoinChangeCombo();
        System.out.println("ans with recursion " + coinChangeCombo.changePossibilitiesTopDown(4, new int[] {1, 2, 3}));
        System.out.println("ans with dy prg " + coinChangeCombo.changePossibilitiesBottomUp(4, new int[] {1, 2, 3}));
    }
}
