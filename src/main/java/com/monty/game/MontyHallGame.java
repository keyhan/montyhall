package com.monty.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by keyhan on 2017-04-18.
 */
 class MontyHallGame {

    final static Random GENERATOR = new Random();

    static int chooseOpenBoxFromFirstPick(final int[] boxArray, int firstChoice){

        validateBoxArray(boxArray);
        int openedBox;

        //Sets the first chosen to -1
        int [] copyArray = Arrays.copyOf(boxArray, boxArray.length);
        copyArray[firstChoice-1]= -1;

        int nFullBoxesInRest = getNumberOfFullBoxes(copyArray);

        //both of the rest are empty, randomize one
        if(nFullBoxesInRest == 0) {
            openedBox = chooseOpenedBoxFromTwoEmptyRemaining(copyArray);
        }
        //one is empty, return its position
        else {
            openedBox = chooseOpenedBoxFromOneEmptyRemaining(copyArray);
        } if (openedBox == -1) {
            throw new RuntimeException("Should not be reached");
        }
        return openedBox;
    }

    private static int chooseOpenedBoxFromOneEmptyRemaining(int[] boxArray) {
        for(int i = 0; i < boxArray.length; i++) {
            if(boxArray[i] == 0) {
                return i+1;
            }
        }
        return -1;
    }

    private static int chooseOpenedBoxFromTwoEmptyRemaining(int[] array) {
        List<Integer> positions = new ArrayList<>();
        for(int i = 0; i < array.length; i++) {
            if(array[i] == 0) {
                positions.add(i+1);
            }
        }
        return positions.get(GENERATOR.nextInt(positions.size()));
    }

    static private int getNumberOfFullBoxes(int[] array) {
        return Arrays.stream(array).filter(item -> item == 1).sum();
    }

    static private void validateBoxArray(int[] boxArray) {
        if(boxArray == null || boxArray.length != 3) {
            throw new RuntimeException("Only 3 boxes allowed");
        }

        int nFullBoxes = getNumberOfFullBoxes(boxArray);

        if(nFullBoxes != 1) {
            throw new RuntimeException("Exactly one box can be full");
        }
    }

    static boolean isSecondPickWinner(int[] boxArray, int secondChoice) {
        return boxArray[secondChoice-1] == 1;
    }
}
