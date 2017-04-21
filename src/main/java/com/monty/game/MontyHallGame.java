package com.monty.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by keyhan on 2017-04-18.
 */
 class MontyHallGame {

    int chooseOpenBoxFromFirstPick(int[] boxArray, int firstChoice){

        validateBoxArray(boxArray);

        //Sets the first chosen to -1
        boxArray[firstChoice-1]= -1;

        int nFullBoxesInRest = getNumberOfFullBoxes(boxArray);

        //both of the rest are empty, randomize one
        List<Integer> positions = new ArrayList<>();
        if(nFullBoxesInRest == 0) {
            for(int i = 0; i < boxArray.length; i++) {
                if(boxArray[i] == 0) {
                    positions.add(i+1);
                }
            }
            return positions.get(new Random().nextInt(positions.size()));
        }



        //one is empty, return its position
        for(int i = 0; i < boxArray.length; i++) {
            if(boxArray[i] == 0) {
                return i+1;
            }
        }

        throw new RuntimeException("Should not reach here");
    }

    private int getNumberOfFullBoxes(int[] restOfBoxes) {
        int nFullBoxes = 0;
        for (int restOfBoxe : restOfBoxes) {
            if (restOfBoxe == 1) {
                nFullBoxes++;
            }
        }
        return nFullBoxes;
    }

    private void validateBoxArray(int[] boxArray) {
        if(boxArray.length != 3) {
            throw new RuntimeException("Only 3 boxes allowed");
        }

        int nFullBoxes = getNumberOfFullBoxes(boxArray);

        if(nFullBoxes != 1) {
            throw new RuntimeException("Exactly one box can be full");
        }
    }

    boolean isSecondPickWinner(int[] boxArray, int secondChoice) {
        return boxArray[secondChoice-1] == 1;
    }
}
