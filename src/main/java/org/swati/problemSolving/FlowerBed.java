package org.swati.problemSolving;

import java.util.Arrays;
import java.util.List;

/**
 * Description
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class FlowerBed {
/*Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die. Given a flowerbed (represented as an array containing booleans), return if a given number of new flowers can be planted in it without violating the no-adjacent-flowers rule
Sample inputs

Input: 1,0,0,0,0,0,1,0,0

3 => true
4 => false
Input: 1,0,0,1,0,0,1,0,0

1 => true
2 => false
input: 0

1 => true
2 => false */

    public static boolean canPlaceFlowers(List<Boolean> flowerbed, int numberToPlace) {
        int currentPlaced = 0;
        for (int i = 0; i < flowerbed.size(); i++) {
            //if there is no flower, add a flower and increase currentPlaced
            if (!flowerbed.get(i) && !adjacentFlower(flowerbed, i)) {
                flowerbed.set(i, true);
                currentPlaced++;
            }

            if (currentPlaced == numberToPlace) {
                return true;
            }
        }
        return false;
    }

    private static boolean adjacentFlower(List<Boolean> flowerbed, int currPos) {
        if (currPos == 0) {
            return flowerbed.get(currPos + 1);
        } else if (currPos + 1 == flowerbed.size()) {
            return flowerbed.get(currPos - 1);
        } else {
            return flowerbed.get(currPos + 1) || flowerbed.get(currPos - 1);
        }
    }

    public static void main(String[] args) {
        List<Boolean> flowerBed = Arrays.asList(true, true, false, false, false, false, true, false, false);
        if (canPlaceFlowers(flowerBed, 3)) {
            System.out.println("Can placeFlowers is true");
        } else {
            System.out.println("Can placeFlowers is false");
        }
    }
}
