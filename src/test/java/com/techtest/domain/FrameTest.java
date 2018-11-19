package com.techtest.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FrameTest {

    private Frame classUnderTest;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new Frame();
    }

    @After
    public void tearDown() throws Exception {
        classUnderTest = null;
    }

    public List<Try> setupSpareFrame() {
        List<Try> tries = new ArrayList<>();
        Try firstThrow = new Try();
        firstThrow.setKnockedPins(7);
        tries.add(firstThrow);

        Try secondThrow = new Try();
        secondThrow.setKnockedPins(3);
        tries.add(secondThrow);

        Try thirdThrow = new Try();
        thirdThrow.setKnockedPins(8);
        tries.add(thirdThrow);

        return tries;
    }

    public List<Try> setupOpenFrame() {
        List<Try> tries = new ArrayList<>();
        Try firstThrow = new Try();
        firstThrow.setKnockedPins(7);
        tries.add(firstThrow);

        Try secondThrow = new Try();
        secondThrow.setKnockedPins(1);
        tries.add(secondThrow);

        return tries;
    }

    public List<Try> setupStrikeFrame() {
        List<Try> tries = new ArrayList<>();
        Try firstThrow = new Try();
        firstThrow.setKnockedPins(10);
        tries.add(firstThrow);

        Try secondThrow = new Try();
        secondThrow.setKnockedPins(4);
        tries.add(secondThrow);

        Try thirdThrow = new Try();
        thirdThrow.setKnockedPins(3);
        tries.add(thirdThrow);

        return tries;
    }

    public List<Try> setupIncompleteFrame() {
        List<Try> tries = new ArrayList<>();
        Try firstThrow = new Try();
        firstThrow.setKnockedPins(7);
        tries.add(firstThrow);

        return tries;
    }

    @Test
    public void testSetTypeForOpenFrame() {
        classUnderTest.setTries(setupOpenFrame());
        classUnderTest.determineType();
        assertEquals(Frame.Type.OPEN_FRAME, classUnderTest.getType());
    }

    @Test
    public void testSetTypeForSpareFrame() {
        classUnderTest.setTries(setupSpareFrame());
        classUnderTest.determineType();
        assertEquals(Frame.Type.SPARE, classUnderTest.getType());
    }

    @Test
    public void testSetTypeForStrikeFrame() {
        classUnderTest.setTries(setupStrikeFrame());
        classUnderTest.determineType();
        assertEquals(Frame.Type.STRIKE, classUnderTest.getType());

    }

    @Test
    public void testSetTypeForIncompleteFrame() {
        classUnderTest.setTries(setupIncompleteFrame());
        classUnderTest.determineType();
        assertNull(classUnderTest.getType());
    }

    @Test
    public void testCalcScoreForCompletedOpenFrame() {
        // Test completed frame
        classUnderTest.setTries(setupOpenFrame());
        classUnderTest.setComplete(true);
        classUnderTest.setType(Frame.Type.OPEN_FRAME);
        classUnderTest.calcScore();
        assertEquals(8, classUnderTest.getScore());
    }

    @Test
    public void testCalcScoreForCompletedSpareFrame() {
        // Test completed frame
        classUnderTest.setTries(setupSpareFrame());
        classUnderTest.setComplete(true);
        classUnderTest.setType(Frame.Type.SPARE);
        classUnderTest.calcScore();
        assertEquals(18, classUnderTest.getScore());
    }

    @Test
    public void testCalcScoreForCompletedStrikeFrame() {
        // Test completed frame
        classUnderTest.setTries(setupStrikeFrame());
        classUnderTest.setComplete(true);
        classUnderTest.setType(Frame.Type.STRIKE);
        classUnderTest.calcScore();
        assertEquals(17, classUnderTest.getScore());
    }

    @Test
    public void testCalcScoreForInCompleteOpenFrame() {
        // Test completed frame
        classUnderTest.setTries(setupIncompleteFrame());
        classUnderTest.calcScore();
        assertEquals(7, classUnderTest.getScore());
    }

    @Test
    public void testCheckCompletionForInCompleteOpenFrame() {
        // Test completed frame
        classUnderTest.setTries(setupIncompleteFrame());
        classUnderTest.checkCompletion();
        assertFalse(classUnderTest.isComplete());
    }

    @Test
    public void testCheckCompletionForCompletedOpenFrame() {
        // Test completed frame
        classUnderTest.setTries(setupOpenFrame());
        classUnderTest.setType(Frame.Type.OPEN_FRAME);
        classUnderTest.checkCompletion();
        assertTrue(classUnderTest.isComplete());
    }

    @Test
    public void testCheckCompletionForCompletedStrikeFrame() {
        // Test completed frame
        classUnderTest.setTries(setupStrikeFrame());
        classUnderTest.setType(Frame.Type.STRIKE);
        classUnderTest.checkCompletion();
        assertTrue(classUnderTest.isComplete());
    }

    @Test
    public void testCheckCompletionForCompletedSpareFrame() {
        // Test completed frame
        classUnderTest.setTries(setupSpareFrame());
        classUnderTest.setType(Frame.Type.SPARE);
        classUnderTest.checkCompletion();
        assertTrue(classUnderTest.isComplete());
    }
}