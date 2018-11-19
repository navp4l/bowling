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
                scoreboard.add(currentFrame);
            } else {
                // Retrieve frame details
                currentFrame = lastFrame;
            }
        } else {
            // This is the first throw of the first frame
            currentFrame = new Frame();
            scoreboard.add(currentFrame);
        }

        // Retrieve list of tries
        List<Try> tries = currentFrame.getTries();

        // Initialize a new try
        Try roll = new Try();
        roll.setKnockedPins(pins);
        tries.add(roll);

        // determine and set frame type
        currentFrame.determineType();

        currentFrame.checkCompletion();

        // calculate frame score
        currentFrame.calcScore();
    }

    @Override
    public int score() {
        int score = 0;
        for (Frame f : this.scoreboard) {
            score += f.getScore();
        }
        return score;
    }

    public List<Frame> getScoreboard() {
        return scoreboard;
    }

}
