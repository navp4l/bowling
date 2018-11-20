package com.techtest.entity;

import com.techtest.domain.Frame;
import com.techtest.domain.Try;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the main bowling game entity.
 * Contains all the entity members and methods
 * for game process.
 */

public class OFXBowlingClub implements BowlingGame {

    private List<Frame> scoreboard = new ArrayList<>();

    @Override
    public void roll(int pins) {
        Frame currentFrame, lastFrame;

        if (scoreboard.size() > 0) {
            lastFrame = (scoreboard.get(scoreboard.size() - 1));
            if (lastFrame.isComplete()) {
                // Create new frame
                currentFrame = new Frame();
                currentFrame.setNumber(scoreboard.size() + 1);
                scoreboard.add(currentFrame);
            } else {
                // Retrieve frame details
                currentFrame = lastFrame;
            }
        } else {
            // This is the first throw of the first frame
            currentFrame = new Frame();
            currentFrame.setNumber(scoreboard.size() + 1);
            scoreboard.add(currentFrame);
        }

        // Retrieve list of tries
        List<Try> tries = currentFrame.getTries();

        // Initialize a new try
        Try roll = new Try();
        roll.setKnockedPins(pins);
        tries.add(roll);

        // determine and set frame type if not already set
        if (currentFrame.getType() == null) {
            currentFrame.determineType();
        }

        currentFrame.checkFrameCompletion();

    }

    @Override
    public int score() {
        int score = 0;
        for (int i = 0; i < this.scoreboard.size(); i++) {
            int frameScore = 0;
            Frame frame = this.scoreboard.get(i);
            Frame.Type type = frame.getType();
            if (frame.isComplete()) {
                switch (type) {

                    case OPEN_FRAME: { // scored by adding the pins knocked in 2 tries
                        frameScore = (frame.getTries().get(0).getKnockedPins()
                                + frame.getTries().get(1).getKnockedPins());
                        break;
                    }

                    case SPARE: { // scored by adding 10 + (pins knocked in the next throw)
                        frameScore = 10;
                        if (this.getScoreboard().size() >= (i + 2)) { // check if next frames are available
                            Frame nextFrame = this.getScoreboard().get(i + 1);
                            List<Try> nextFrameTries = nextFrame.getTries();
                            frameScore += nextFrameTries.get(0).getKnockedPins();
                        }
                        break;
                    }

                    case STRIKE: { // scored by adding 10 + (pins knocked in the next 2 throws)
                        frameScore = 10;
                        if (this.getScoreboard().size() >= (i + 2)) { // check if next frames are available
                            Frame nextFrame = this.getScoreboard().get(i + 1);
                            List<Try> nextFrameTries = nextFrame.getTries();

                            // If this is the penultimate throw and the final throw
                            // happens to be a strike followed by bonus throws,
                            // we need to restrict score to consider - current throw + next 2 throws
                            if (nextFrameTries.size() >= 1) {
                                int count = 0;
                                for (Try bowl : nextFrameTries) {
                                    if (count <= 1) {
                                        frameScore += bowl.getKnockedPins();
                                    }
                                    count++;
                                }
                            }

                            // If this is the penultimate throw and the final throw
                            // happens to be a strike followed by another strike followed by bonus throws,
                            // we need to restrict score to consider - current throw + next 2 throws
                            if (nextFrame.getType() == Frame.Type.STRIKE &&
                                    this.getScoreboard().size() >= (i + 3)) {
                                Frame thirdFrame = this.getScoreboard().get(i + 2);
                                List<Try> thirdFrameTries = thirdFrame.getTries();
                                frameScore += thirdFrameTries.get(0).getKnockedPins();
                            }
                        }
                        break;
                    }

                    case LAST_THROW_STRIKE:

                    case LAST_THROW_SPARE: {

                        List<Try> tries = frame.getTries();
                        for (Try roll : tries) {
                            frameScore += roll.getKnockedPins();
                        }
                        break;
                    }
                }
                System.out.println("Frame score for frame number " + (i + 1)
                        + " with type " + frame.getType().toString() + " is " + frameScore);

                frame.setScore(frameScore);
            } else {
                frameScore = frame.getTries().get(0).getKnockedPins();
            }
            score += frameScore;
        }
        return score;
    }

    public List<Frame> getScoreboard() {
        return scoreboard;
    }

}
