package com.monty;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by keyhan on 2017-04-18.
 */
public class MontyHallTest {

    @Test
    public void runTestWithChange() {
        int wins = 0;
        int losses = 0;

        for (int i  = 0; i < 50000;  i++) {
            MontyHallGame game = new MontyHallGame();
            Integer chosenBox = new Random().nextInt(3) + 1;
            Integer openedBox = game.chooseOpenBoxFromFirstPick(chosenBox);
            if(game.secondPick(getLastBoxNumber(game,chosenBox, openedBox))) {
                wins++;
            } else {
                losses++;
            }
        }

        System.out.println("wins = " + wins);
        System.out.println("losses = " + losses);
    }

    private Integer getLastBoxNumber(MontyHallGame game, Integer chosenBox, Integer openedBox) {
        List<Integer> boxNumbers = new ArrayList<>(Arrays.asList(1,2,3));
        boxNumbers.remove(chosenBox);
        boxNumbers.remove(openedBox);
        return boxNumbers.get(0);
    }

    @Test
    public void testChosenBoxFromFirstPick() {
        MontyHallGame game = new MontyHallGame();
        int openBox = game.chooseOpenBoxFromFirstPick(1);
        System.out.println("openBox = " + openBox);
    }
}
