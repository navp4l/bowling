package com.techtest.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The Frame class is the domain class
 * which deals with all properties & operations
 * related to a Frame in a bowling game.
 *
 * 1 Frame = 3 Tries
 *
 * @author palanisn
 */

public class Frame {

    private List<Try> tries = new ArrayList<>();

    private int score;

    private boolean complete;

    private Type type;

    public enum Type {
        OPEN_FRAME,
        SPARE,
        STRIKE,
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Try> getTries() {
        return tries;
    }

    public void setTries(List<Try> tries) {
        this.tries = tries;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public void determineType() {
        // Normal throws - user throws twice - may / may not maange to knock all 10 pins > 2 throws
        // If user knocks all pins in 2 throws - SPARE, gets a bonus throw > 3 throws
        // If user knocks all pins in 1st throw - STRIKE, gets 2 bonus > 3 throws

        if (this.tries.size() >= 2 && this.type == null) {
            // if there have only been 2 tries so far then
            Try firstTry = this.tries.get(0);
            int firstTryKnockedPins = firstTry.getKnockedPins();

            Try secondTry = this.tries.get(1);
            int secondTryKnockedPins = secondTry.getKnockedPins();

            if (firstTryKnockedPins == 10) {
                this.type = Frame.Type.STRIKE;
            } else if ((firstTryKnockedPins + secondTryKnockedPins) == 10) {
                this.type = Frame.Type.SPARE;
            } else {    // There will not be any further tries in this this if all pins don't fall in 2 tries
                this.type = Frame.Type.OPEN_FRAME;
            }
        }

    }

    public void checkCompletion() {
        if (this.tries.size() == 2) {
            if (this.type == Frame.Type.OPEN_FRAME) this.complete = true;
        } else if (this.tries.size() == 3) {
            // there can only be a maximum of 3 tries
            this.setComplete(true);
        }
    }

    public void calcScore() {
        int frameScore = 0;
        if (this.complete) {
            switch (this.type) {

                case OPEN_FRAME: {
                    frameScore = (this.tries.get(0).getKnockedPins()
                            + this.tries.get(1).getKnockedPins());
                    System.out.println("Frame score is " + frameScore);
                    break;
                }

                case SPARE: {
                    frameScore = 10 + this.tries.get(2).getKnockedPins();
                    System.out.println("Frame score is " + frameScore);
                    break;
                }

                case STRIKE: {
                    frameScore = 10 + (this.tries.get(1).getKnockedPins()
                            + this.tries.get(2).getKnockedPins());
                    System.out.println("Frame score is " + frameScore);
                    break;
                }
            }
        } else {
            frameScore = this.tries.get(0).getKnockedPins();
        }
        this.score = frameScore;
    }

}
