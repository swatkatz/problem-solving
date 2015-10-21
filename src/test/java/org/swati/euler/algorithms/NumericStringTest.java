package org.swati.euler.algorithms;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Description
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class NumericStringTest {
    NumericString ns = new NumericString();

    @Test
    public void testNumericFloat() {
        assertTrue(ns.isNumber("959440.94f"));
        assertTrue(ns.isNumber("0.1"));
    }
}