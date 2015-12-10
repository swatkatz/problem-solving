package org.swati.dynamicProgramming;

/**
 * If only one egg is available and we wish to be sure of obtaining the right result,
 * the experiment can be carried out in only one way. Drop the egg from the first-floor window;
 * if it survives, drop it from the second floor window. Continue upward until it breaks.
 * In the worst case, this method may require 36 droppings. Suppose 2 eggs are available.
 * What is the least number of egg-droppings that is guaranteed to work in all cases?
 * The problem is not actually to find the critical floor,
 * but merely to decide floors from which eggs should be dropped so that total number of trials are minimized.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class EggDropProblem {
    //using recursion, super slow
    public int eggDrop(int numEggs, int kFloor) {
        if (kFloor == 0 || kFloor == 1) {
            return kFloor;
        }

        //When only one egg, drop from each floor.
        if (numEggs == 1) {
            return kFloor;
        }

        int min = Integer.MAX_VALUE;
        int res;
        //To check for all floors upto the given floor
        for (int x = 1; x <= kFloor; x++) {
            //this is the worse case for a floor upto the given floors
            //i.e. either it's the solution when the egg breaks on that floor same as the solution for all floors beneath it or if it doesn't break
            //then it's the solution for the floors above it
            res = Math.max(eggDrop(numEggs - 1, x - 1), eggDrop(numEggs, kFloor - x));
            if (res < min) {
                min = res;
            }
        }
        return min + 1;
    }

    //using memoization and recursion
    public int eggDrop(int numEggs, int kFloor, int[][] eggDrop) {
        if (kFloor == 0 || kFloor == 1) {
            eggDrop[numEggs][kFloor] = kFloor;
            return kFloor;
        }

        //When only one egg, drop from each floor.
        if (numEggs == 1) {
            eggDrop[1][kFloor] = kFloor;
            return kFloor;
        }

        if (eggDrop[numEggs][kFloor] < Integer.MAX_VALUE) {
            return eggDrop[numEggs][kFloor];
        }

        int min = Integer.MAX_VALUE;
        int res;
        //To check for all cases
        for (int x = 1; x <= kFloor; x++) {
            res = Math.max(eggDrop(numEggs - 1, x - 1, eggDrop), eggDrop(numEggs, kFloor - x, eggDrop));
            if (res < min) {
                min = res;
            }
        }
        eggDrop[numEggs][kFloor] = min + 1;
        return min + 1;
    }

    //using dynamic programming
    public int eggDropDy(int numEggs, int numFloors) {
        int[][] eggDrop = new int[numEggs + 1][numFloors + 1];
        //initialize the matrix
        for (int i = 0; i <= numEggs; i++) {
            for (int j = 0; j <= numFloors; j++) {
                if (j == 0 || j == 1 || i == 1) {
                    eggDrop[i][j] = j;
                } else {
                    eggDrop[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int res;
        for (int i = 2; i <= numEggs; i++) {
            for (int j = 2; j <= numFloors; j++) {
                for (int x = 1; x <= j; x++) {
                    res = 1 + Math.max(eggDrop[i - 1][x - 1], eggDrop[i][j - x]);
                    if (eggDrop[i][j] > res) {
                        eggDrop[i][j] = res;
                    }
                }
            }
        }
        return eggDrop[numEggs][numFloors];
    }

    public static void main(String[] args) {
        EggDropProblem eggDropProblem = new EggDropProblem();
        int numOfFloors = 10;
        int numOfEggs = 2;
        int[][] eggDrop = new int[numOfEggs + 1][numOfFloors + 1];
        for (int i = 0; i <= numOfEggs; i++) {
            for (int j = 0; j <= numOfFloors; j++) {
                eggDrop[i][j] = Integer.MAX_VALUE;
            }
        }
        System.out.println("The minimum # of trials in worst case solution is with recursion " + eggDropProblem.eggDrop(numOfEggs, numOfFloors));
        System.out.println("The minimum # of trials in worst case solution is with memoization " + eggDropProblem.eggDrop(numOfEggs, numOfFloors, eggDrop));
        System.out.println("The minimum # of trials in worst case solution is with dy prog " + eggDropProblem.eggDropDy(numOfEggs, numOfFloors));
    }

}
