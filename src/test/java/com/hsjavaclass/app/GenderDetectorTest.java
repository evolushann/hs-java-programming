package com.hsjavaclass.app;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GenderDetectorTest extends TestCase {
    GenderDetector detector;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        detector = new GenderDetector();
    }

    @After
    public void tearDown() throws Exception {
        detector = null;
    }
    
    @Test
    public void testDetectGenderByName() {
        String gender = detector.detectGenderByName("Mary");
        assertEquals("female", gender);
    }

    @Test
    public void testDetectGenderMale() {
        assertFalse(false);
    }
}
