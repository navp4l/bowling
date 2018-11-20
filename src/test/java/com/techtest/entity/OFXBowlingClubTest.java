package com.techtest.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OFXBowlingClubTest {

    private OFXBowlingClub classUnderTest;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new OFXBowlingClub();
    }

    @After
    public void tearDown() throws Exception {
        classUnderTest = null;
    }

    @Test
    public void testFrameCreation() {
        assertEquals(0, classUnderTest.getScoreboard().size());

        classUnderTest.roll(10);
        assertEquals(1, classUnderTest.getScoreboard().size());

        classUnderTest.roll(4);
        classUnderTest.roll(3);
        assertEquals(2, classUnderTest.getScoreboard().size());

        classUnderTest.roll(5);
        classUnderTest.roll(1);
        assertEquals(3, classUnderTest.getScoreboard().size());

        classUnderTest.roll(2);
        assertEquals(4, classUnderTest.getScoreboard().size());
    }

    @Test
    public void testScoreCalc1() {
        assertEquals(0, classUnderTest.score());

        classUnderTest.roll(10);
        assertEquals(10, classUnderTest.score());

        classUnderTest.roll(10);
        classUnderTest.roll(10);

        assertEquals(60, classUnderTest.score());

        classUnderTest.roll(10);

        assertEquals(90, classUnderTest.score());

        classUnderTest.roll(10);
        classUnderTest.roll(10);

        assertEquals(150, classUnderTest.score());

        classUnderTest.roll(10);
        classUnderTest.roll(10);
        classUnderTest.roll(10);
        classUnderTest.roll(10);
        classUnderTest.roll(10);
        classUnderTest.roll(10);

        assertEquals(300, classUnderTest.score());
    }

    @Test
    public void testScoreCalc2() {
        classUnderTest.roll(10);

        classUnderTest.roll(7);
        classUnderTest.roll(3);

        assertEquals(30, classUnderTest.score());

        classUnderTest.roll(7);
        classUnderTest.roll(2);

        classUnderTest.roll(9);
        classUnderTest.roll(1);

        classUnderTest.roll(10);

        classUnderTest.roll(10);

        assertEquals(96, classUnderTest.score());

        classUnderTest.roll(10);

        classUnderTest.roll(2);
        classUnderTest.roll(3);

        assertEquals(138, classUnderTest.score());

        classUnderTest.roll(6);
        classUnderTest.roll(4);

        classUnderTest.roll(7);
        classUnderTest.roll(3);

        assertEquals(162, classUnderTest.score());

        // Bonus throw
        classUnderTest.roll(3);

        assertEquals(168, classUnderTest.score());
    }
}