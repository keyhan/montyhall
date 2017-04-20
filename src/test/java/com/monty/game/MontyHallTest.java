package com.monty.game;

import com.monty.game.MontyHallGame;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.fail;

/**
 * Created by keyhan on 2017-04-18.
 */
public class MontyHallTest {

    private Integer getLastBoxNumber(MontyHallGame game, Integer chosenBox, Integer openedBox) {
        List<Integer> boxNumbers = new ArrayList<>(Arrays.asList(1,2,3));
        boxNumbers.remove(chosenBox);
        boxNumbers.remove(openedBox);
        return boxNumbers.get(0);
    }

    @Test
    public void testOpenBoxFromOneNonEmpty() {
        Map<Integer, String> boxMap= new HashMap<>();
        boxMap.put(1,null);
        boxMap.put(2,null);
        boxMap.put(3,"Hello");
        MontyHallGame game = new MontyHallGame();
        int openBox = game.chooseOpenBoxFromFirstPick(boxMap,1);
        Assert.assertEquals(2, openBox);
    }

    @Test
    public void testOpenBoxFromTwoEmpty() {
        Map<Integer, String> boxMap= new HashMap<>();
        boxMap.put(1,null);
        boxMap.put(2,"Hello");
        boxMap.put(3,"Hello");
        MontyHallGame game = new MontyHallGame();
        try {
            game.chooseOpenBoxFromFirstPick(boxMap, 3);
            fail();
        } catch (RuntimeException e) {
            //PASS
        }
    }

    @Test
    public void testOpenBoxFromAllEmpty() {
        Map<Integer, String> boxMap= new HashMap<>();
        boxMap.put(1,null);
        boxMap.put(2,null);
        boxMap.put(3,null);
        MontyHallGame game = new MontyHallGame();
        try {
            game.chooseOpenBoxFromFirstPick(boxMap, 3);
            fail();
        } catch (RuntimeException e) {
            //PASS
        }
    }

    @Test
    public void testOpenBoxFromTooBigMap() {
        Map<Integer, String> boxMap= new HashMap<>();
        boxMap.put(1,null);
        boxMap.put(2,null);
        boxMap.put(3,null);
        boxMap.put(4,null);
        MontyHallGame game = new MontyHallGame();
        try {
            game.chooseOpenBoxFromFirstPick(boxMap, 3);
            fail();
        } catch (RuntimeException e) {
            //PASS
        }
    }



}
