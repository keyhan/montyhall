package com.monty.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by keyhan on 2017-04-18.
 */
 class MontyHallGame {

    public int chooseOpenBoxFromFirstPick(Map<Integer, String> boxMap, int firstChoice){

        validateBoxMap(boxMap);

        Map<Integer, String> restOfBoxes = new HashMap<>();
        restOfBoxes.putAll(boxMap);
        restOfBoxes.remove(firstChoice);

        List<Integer> nonEmptyBoxes = restOfBoxes.entrySet().stream().filter(entry -> entry.getValue() != null)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());

        //both are empty, randomize one
        if(nonEmptyBoxes.size() == 0) {
            return (int) restOfBoxes.keySet().toArray()[new Random().nextInt(restOfBoxes.keySet().size())];

        }

        //one is empty, return its key
        List<Integer> emptyBoxes = restOfBoxes.keySet().stream().filter(key ->
                key != nonEmptyBoxes.get(0)).collect(Collectors.toList());

        if(emptyBoxes == null && emptyBoxes.size() != 1) {
            throw new RuntimeException("There should be exactly 2 empty boxes in this game");
        }
        return emptyBoxes.get(0);
    }

    private void validateBoxMap(Map<Integer, String> boxMap) {
        if(boxMap.size() != 3) {
            throw new RuntimeException("Only 3 boxes allowed");
        }

        List<Integer> nonEmptyInputBoxes = boxMap.entrySet().stream().filter(entry -> entry.getValue() != null)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());

        if(nonEmptyInputBoxes.size() != 1) {
            throw new RuntimeException("Exactly one box can be full");
        }
    }

    public boolean isSecondPickWinner(Map<Integer, String> boxMap, int secondChoice) {
        return boxMap.get(secondChoice) != null;
    }
}
