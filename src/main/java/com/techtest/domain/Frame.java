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

    private int score = -1;

    private boolean complete;

    private int number;

    private Type type;

    public enum Type {
        OPEN_FRAME,
        SPARE,
        STRIKE,
        LAST_THROW_SPARE,
        LAST_THROW_STRIKE,
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void determineType() {
        // Normal throws - user throws twice - may / may not manage to knock all 10 pins == 2 throws
        // If user knocks all pins in 2 throws - SPARE == 2 throws
        // If user knocks all pins in 1st throw - STRIKE == 1 throw

        if (this.tries.size() == 1) {
            if (this.tries.get(0).getKnockedPins() == 10) {
                if (this.getNumber() == 10) { // check if this is the last bowl
                    this.type = Type.LAST_THROW_STRIKE;
                } else {
                    this.type = Frame.Type.STRIKE;
                }
            }
        } else if (this.tries.size() == 2) {
            // if there have only been 2 tries so far then
            Try firstTry = this.tries.get(0);
            int firstTryKnockedPins = firstTry.getKnockedPins();

            Try secondTry = this.tries.get(1);
            int secondTryKnockedPins = secondTry.getKnockedPins();

            if ((firstTryKnockedPins + secondTryKnockedPins) == 10) {
                if (this.getNumber() == 10) { // check if this is the last bowl
                    this.type = Type.LAST_THROW_SPARE;
                } else {
                    this.type = Frame.Type.SPARE;
                }
            } else {    // There will not be any further tries in this this if all pins don't fall in 2 tries
                this.type = Frame.Type.OPEN_FRAME;
            }
        }
    }

    public void checkFrameCompletion() {
        if (this.tries.size() == 1) {
            if (this.type == Type.STRIKE) this.complete = true;
        } else if (this.tries.size() == 2) {
            // there can only be a maximum of 2 tries in a frame
            if (this.type == Type.SPARE || this.type == Type.OPEN_FRAME ) this.setComplete(true);
        } else if (this.tries.size() == 3) {
            if (this.type == Type.LAST_THROW_SPARE || this.type == Type.LAST_THROW_STRIKE ) this.setComplete(true);
        }
    }

}
