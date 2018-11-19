package com.techtest;

import com.techtest.entity.BowlingGame;
import com.techtest.entity.OFXBowlingClub;

/**
 * Game Controller
 * @author palanisn
 */

public class GameController {

    public static void main(String[] args) {

        System.out.println("*********** Game 1 *****************");

        BowlingGame game1 = new OFXBowlingClub();

        game1.roll(10);
        game1.roll(10);
        game1.roll(10);

        game1.roll(10);
        game1.roll(10);
        game1.roll(10);

        game1.roll(10);
        game1.roll(10);
        game1.roll(10);

        game1.roll(10);
        game1.roll(10);
        game1.roll(10);

        game1.roll(10);
        game1.roll(10);
        game1.roll(10);

        game1.roll(10);
        game1.roll(10);
        game1.roll(10);

        game1.roll(10);
        game1.roll(10);
        game1.roll(10);

        game1.roll(10);
        game1.roll(10);
        game1.roll(10);

        game1.roll(10);
        game1.roll(10);
        game1.roll(10);

        game1.roll(10);
        game1.roll(10);
        game1.roll(10);

        System.out.println("Game 1 Score is " + game1.score());

        System.out.println("************************************");

        System.out.println("*********** Game 2 *****************");

        BowlingGame game2 = new OFXBowlingClub();

        game2.roll(10);
        game2.roll(10);
        game2.roll(0);

        game2.roll(4);
        game2.roll(6);
        game2.roll(7);

        game2.roll(7);
        game2.roll(2);

        game2.roll(2);
        game2.roll(8);
        game2.roll(10);

        game2.roll(10);
        game2.roll(10);
        game2.roll(10);

        game2.roll(10);
        game2.roll(10);
        game2.roll(2);

        game2.roll(10);
        game2.roll(2);
        game2.roll(3);

        game2.roll(2);
        game2.roll(3);

        game2.roll(3);
        game2.roll(7);
        game2.roll(7);

        game2.roll(8);
        game2.roll(2);
        game2.roll(3);

        System.out.println("Game 1 Score is " + game2.score());

        System.out.println("************************************");

    }

}
