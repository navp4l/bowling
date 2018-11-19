package com.techtest.entity;

import com.techtest.domain.Frame;
import com.techtest.domain.Try;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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

    private List<Frame> setupCompleteFrames(){
        List<Frame> frames = new ArrayList<>();

        Frame first = new Frame();
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

        first.setTries(tries);
        frames.add(first);

        Frame second = new Frame();
        List<Try> triesTwo = new ArrayList<>();
        Try one = new Try();
        one.setKnockedPins(7);
        triesTwo.add(one);

        Try two = new Try();
        two.setKnockedPins(3);
        triesTwo.add(two);

        Try three = new Try();
        three.setKnockedPins(8);
        triesTwo.add(three);

        second.setTries(triesTwo);
        frames.add(second);

        return frames;
    }

    @Test
    public void testFrameCreation() {
        assertEquals(0, classUnderTest.getScoreboard().size());

        classUnderTest.roll(10);
        assertEquals(1, classUnderTest.getScoreboard().size());

        classUnderTest.roll(4);
        classUnderTest.roll(3);
        assertEquals(1, classUnderTest.getScoreboard().size());

        classUnderTest.roll(5);
        classUnderTest.roll(1);
        assertEquals(2, classUnderTest.getScoreboard().size());

        classUnderTest.roll(2);
        assertEquals(3, classUnderTest.getScoreboard().size());
    }

    @Test
    public void testScoreCalc() {
        assertEquals(0, classUnderTest.score());

        classUnderTest.roll(10);
        assertEquals(10, classUnderTest.score());

        classUnderTest.roll(10);
        classUnderTest.roll(10);

        assertEquals(30, classUnderTest.score());

        classUnderTest.roll(10);

        assertEquals(40, classUnderTest.score());

        classUnderTest.roll(10);
        classUnderTest.roll(10);

        assertEquals(60, classUnderTest.score());

    }
}