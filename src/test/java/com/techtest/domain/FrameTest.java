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
    public void testSetTypeForLastSpareFrame() {
        classUnderTest.setTries(setupSpareFrame());
        classUnderTest.setNumber(10);
        classUnderTest.determineType();
        assertEquals(Frame.Type.LAST_THROW_SPARE, classUnderTest.getType());
    }

    @Test
    public void testSetTypeForLastStrikeFrame() {
        classUnderTest.setTries(setupStrikeFrame());
        classUnderTest.setNumber(10);
        classUnderTest.determineType();
        assertEquals(Frame.Type.LAST_THROW_STRIKE, classUnderTest.getType());

    }

    @Test
    public void testCheckCompletionForInCompleteOpenFrame() {
        // Test completed frame
        classUnderTest.setTries(setupIncompleteFrame());
        classUnderTest.checkFrameCompletion();
        assertFalse(classUnderTest.isComplete());
    }

    @Test
    public void testCheckCompletionForCompletedOpenFrame() {
        // Test completed frame
        classUnderTest.setTries(setupOpenFrame());
        classUnderTest.setType(Frame.Type.OPEN_FRAME);
        classUnderTest.checkFrameCompletion();
        assertTrue(classUnderTest.isComplete());
    }

    @Test
    public void testCheckCompletionForCompletedStrikeFrame() {
        // Test completed frame
        classUnderTest.setTries(setupStrikeFrame());
        classUnderTest.setType(Frame.Type.STRIKE);
        classUnderTest.checkFrameCompletion();
        assertTrue(classUnderTest.isComplete());
    }

    @Test
    public void testCheckCompletionForCompletedSpareFrame() {
        // Test completed frame
        classUnderTest.setTries(setupSpareFrame());
        classUnderTest.setType(Frame.Type.SPARE);
        classUnderTest.checkFrameCompletion();
        assertTrue(classUnderTest.isComplete());
    }

    @Test
    public void testCheckCompletionForCompletedLastStrikeFrame() {
        // Test completed frame
        List<Try> tries = setupStrikeFrame();
        Try second = new Try();
        second.setKnockedPins(3);
        tries.add(second);

        Try third = new Try();
        third.setKnockedPins(3);
        tries.add(third);

        classUnderTest.setTries(tries);
        classUnderTest.setType(Frame.Type.LAST_THROW_STRIKE);
        classUnderTest.setNumber(10);
        classUnderTest.checkFrameCompletion();
        assertTrue(classUnderTest.isComplete());
    }

    @Test
    public void testCheckCompletionForCompletedLastSpareFrame() {
        // Test completed frame
        List<Try> tries = setupStrikeFrame();
        Try second = new Try();
        second.setKnockedPins(3);
        tries.add(second);

        Try third = new Try();
        third.setKnockedPins(3);
        tries.add(third);

        classUnderTest.setTries(tries);
        classUnderTest.setType(Frame.Type.LAST_THROW_SPARE);
        classUnderTest.setNumber(10);
        classUnderTest.checkFrameCompletion();
        assertTrue(classUnderTest.isComplete());
    }
}