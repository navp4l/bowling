package com.techtest.domain;

/**
 * The Try class is the domain class
 * which deals with all properties & operations
 * related to a Try in a Frame of a bowling game.
 *
 * 3 Tries = 1 Frame
 *
 * @author palanisn
 */

public class Try {

    private int knockedPins;

    public int getKnockedPins() {
        return knockedPins;
    }

    public void setKnockedPins(int knockedPins) {
        this.knockedPins = knockedPins;
    }

}