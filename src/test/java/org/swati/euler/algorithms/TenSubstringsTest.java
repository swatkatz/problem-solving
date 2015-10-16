package org.swati.euler.algorithms;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test suite to test TenSubstring friendliness
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class TenSubstringsTest {
    private TenSubstrings tenSubstrings;

    @Before
    public void setUp() {
        tenSubstrings = new TenSubstrings();
    }

    @Test
    public void testTenSubstringTrue() {
        assertTrue(tenSubstrings.isTenSubstringFriendly(3523014));
        assertTrue(tenSubstrings.isTenSubstringFriendly(91));
        assertTrue(tenSubstrings.isTenSubstringFriendly(910));
        assertTrue(tenSubstrings.isTenSubstringFriendly(9100));
    }

    @Test
    public void testTenSubstringFalse() {
        assertFalse(tenSubstrings.isTenSubstringFriendly(60364));
        assertFalse(tenSubstrings.isTenSubstringFriendly(23857));
    }
}
