package com.monty.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by keyhan on 2017-04-20.
 */
public class GameManager {

    private static int[] boxArray = new int[3];

    private final static int SWITCH = 1;

    private final static List<Integer> BOX_NUMBERS = Arrays.asList(1, 2, 3);

    private boolean firstChoiceMade = false;



    private void init() {
        boxArray[0] = 0;
        boxArray[1] = 0;
        boxArray[2] = 0;

        Random generator = new Random();
        int randomKey = generator.nextInt(3);
        boxArray[randomKey] = 1;

    }

    private MontyHallGame game = new MontyHallGame();

    public int[] batchPlay(int batchSize, int switchOrKeep){
        int wins = 0;
        int losses = 0;
        for (int i = 0; i < batchSize; i++) {
            init();
            Integer firstChoice = new Random().nextInt(3) + 1;
            Integer openedBox = game.chooseOpenBoxFromFirstPick(boxArray, firstChoice);
            int secondChoice;
            if(switchOrKeep == SWITCH) {
                List<Integer> boxNumbers = new ArrayList<>(BOX_NUMBERS);
                boxNumbers.remove(firstChoice);
                boxNumbers.remove(openedBox);
                secondChoice = boxNumbers.get(0);
            } else {
                secondChoice = firstChoice;
            }
            if (game.isSecondPickWinner(boxArray, secondChoice)) {
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
        return game.chooseOpenBoxFromFirstPick(boxArray,firstChoice);
    }

    public boolean isSecondChoiceWinner(int secondChoice) {
        if(!firstChoiceMade) {
            throw new RuntimeException("No cheating, make first choice");
        }
        return game.isSecondPickWinner(boxArray,secondChoice);
    }
}
