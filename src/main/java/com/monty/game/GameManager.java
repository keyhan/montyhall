package com.monty.game;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by keyhan on 2017-04-20.
 */
public class GameManager {

    private static int[] boxArray = new int[3];

    private final static int[] BOX_NUMBERS = new int[]{1, 2, 3};

    private boolean firstChoiceMade = false;

    private void init() {
        boxArray[0] = 0;
        boxArray[1] = 0;
        boxArray[2] = 0;
        boxArray[MontyHallGame.GENERATOR.nextInt(3)] = 1;
    }

    public int[] batchPlay(int batchSize, boolean doSwitch){
        int wins = 0;
        int losses = 0;
        for (int i = 0; i < batchSize; i++) {
            init();
            int firstChoice = new Random().nextInt(3) + 1;
            int openedBox = MontyHallGame.chooseOpenBoxFromFirstPick(boxArray, firstChoice);
            int secondChoice;
            if(doSwitch) {
                secondChoice = Arrays.stream(BOX_NUMBERS)
                        .filter(number -> number != firstChoice && number != openedBox).toArray()[0];
            } else { //KEEP
                secondChoice = firstChoice;
            }
            if (MontyHallGame.isSecondPickWinner(boxArray, secondChoice)) {
                wins++;
            } else {
                losses++;
            }
        }
        return new int[]{wins,losses};
    }

    public int getOpenedBox(int firstChoice) {
        init();
        firstChoiceMade = true;
        return MontyHallGame.chooseOpenBoxFromFirstPick(boxArray,firstChoice);
    }

    public boolean isSecondChoiceWinner(int secondChoice) {
        if(!firstChoiceMade) {
            throw new RuntimeException("No cheating, make first choice");
        }
        return MontyHallGame.isSecondPickWinner(boxArray,secondChoice);
    }
}
