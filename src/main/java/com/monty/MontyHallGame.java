package com.monty;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by keyhan on 2017-04-18.
 */
public class MontyHallGame {

    private Map<Integer, Box> boxMap = new HashMap<>();

    public MontyHallGame() {
        boxMap.put(1,new Box());
        boxMap.put(2,new Box());
        boxMap.put(3,new Box());
        getRandomBoxFromArray(boxMap).setEmpty(false);
    }

    private Box getRandomBoxFromArray(Map<Integer, Box> boxes) {
        Random generator = new Random();
        int randomKey = generator.nextInt(boxes.values().size()) + 1;
        return boxes.get(randomKey);
    }

    public Integer firstPick(Integer chosenBox) throws RuntimeException{

        //List<Box> restOfBoxes = new ArrayList<>(boxMap.values());
        Map<Integer, Box> restOfBoxes = new HashMap<>();
        restOfBoxes.putAll(boxMap);
        restOfBoxes.remove(chosenBox);

        int nonEmpty = -1;

        for(Map.Entry<Integer, Box> entry: restOfBoxes.entrySet()) {
            if(!entry.getValue().isEmpty()) {
                nonEmpty = entry.getKey();
                break;
            }
        }

        //both are empty, randomize one
        if(nonEmpty!= -1) {
            return (Integer) restOfBoxes.keySet().toArray()[new Random().nextInt(restOfBoxes.keySet().size())];

        }

        //one is empty, return its key
        for(Integer key : restOfBoxes.keySet()) {
            if(key != nonEmpty) {
                return key;
            }
        }

        // This is not reachable, should not happen.
        throw new RuntimeException("Only one box can be full");
    }

    public boolean secondPick(Integer chosenBox) {
        return boxMap.get(chosenBox).isEmpty();
    }
}
