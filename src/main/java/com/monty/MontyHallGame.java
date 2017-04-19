package com.monty;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by keyhan on 2017-04-18.
 */
public class MontyHallGame {

    private Map<Integer, String> boxMap = new HashMap<>();

    public MontyHallGame() {
        boxMap.put(1,null);
        boxMap.put(2,null);
        boxMap.put(3,null);
        Random generator = new Random();
        int randomKey = generator.nextInt(3) + 1;
        boxMap.put(randomKey, "HELLO");
    }

    public int chooseOpenBoxFromFirstPick(int firstChoice) throws RuntimeException{
        Map<Integer, String> restOfBoxes = new HashMap<>();
        restOfBoxes.putAll(boxMap);
        restOfBoxes.remove(firstChoice);

        int nonEmpty = -1;

        for(Map.Entry<Integer, String> entry: restOfBoxes.entrySet()) {
            if(entry.getValue() != null) {
                nonEmpty = entry.getKey();
                break;
            }
        }

        //both are empty, randomize one
        if(nonEmpty == -1) {
            return (int) restOfBoxes.keySet().toArray()[new Random().nextInt(restOfBoxes.keySet().size())];

        }

        //one is empty, return its key
        for(int key : restOfBoxes.keySet()) {
            if(key != nonEmpty) {
                return key;
            }
        }

        // This is not reachable, should not happen.
        throw new RuntimeException("Only one box can be full");
    }

    public boolean secondPick(int chosenBox) {
        return boxMap.get(chosenBox) != null;
    }
}
