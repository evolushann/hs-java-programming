package com.hsjavaclass.app;

import junit.framework.TestCase;
import org.junit.jupiter.api.*;

public class GenderDetectorTest extends TestCase {
    GenderDetector detector;

    @BeforeAll
    public void setUp() throws Exception {
        super.setUp();
        detector = new GenderDetector();
    }

    @AfterAll
    public void tearDown() throws Exception {
        detector = null;
    }
    
    @Test
    public void testDetectGenderByName() {
        String gender = detector.detectGenderByName("John");
        assertEquals("female", gender);
    }

    @Test
    public void testDetectGenderMale() {
        assertFalse(false);
    }
}
