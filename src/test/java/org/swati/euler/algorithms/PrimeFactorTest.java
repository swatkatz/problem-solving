package org.swati.euler.algorithms;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Larges prime factor test
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class PrimeFactorTest {
    private PrimeFactor primeFactor;

    @Before
    public void setUp() {
        primeFactor = new PrimeFactor();
    }

    @Test
    public void testLargestPrimeFactor() {
        assertEquals(primeFactor.getLargestPrimeFactor(15L), Long.valueOf(5));
        assertEquals(primeFactor.getLargestPrimeFactor(1787866L), Long.valueOf(893933));
        assertEquals(primeFactor.getLargestPrimeFactor(7L), Long.valueOf(7));
        assertEquals(primeFactor.getLargestPrimeFactor(113L), Long.valueOf(113));
    }
}
