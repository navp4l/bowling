package com.techtest.entity;

/**
 * Bowling Game interface provides
 * public API methods roll and score
 *
 * @author palanisn
 */

public interface BowlingGame {

    /**
     * Roll the ball
     * @param pins Number of pins knocked down
     */
    void roll(int pins);

    /**
     * Calculate game score
     * @return Score
     */
    int score();

}
