package org.swati.euler.algorithms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test RandomBinarySearch
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class RandomBinarySearchTest {
    private RandomBinarySearch rb;

    @Test
    public void testBoundaryConditions() {
        rb = new RandomBinarySearch(20, 19, 1);
        assertEquals(rb.randomBinarySearch(rb.getMinLimit(), rb.getMaxLimit(), 1), Integer.valueOf(1));

        rb = new RandomBinarySearch(20, 1, 20);
        assertEquals(rb.randomBinarySearch(rb.getMinLimit(), rb.getMaxLimit(), 1), Integer.valueOf(1));

        rb = new RandomBinarySearch(1, 1, 20);
        assertEquals(rb.randomBinarySearch(rb.getMinLimit(), rb.getMaxLimit(), 1), Integer.valueOf(1));
    }

}
