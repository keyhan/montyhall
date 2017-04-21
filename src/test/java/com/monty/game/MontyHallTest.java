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
        int[] boxArray= new int[]{0,0,1};
        MontyHallGame game = new MontyHallGame();
        int openBox = game.chooseOpenBoxFromFirstPick(boxArray,1);
        Assert.assertEquals(2, openBox);
    }

    @Test
    public void testOpenBoxFromTwoEmpty() {
        int[] boxArray= new int[]{0,1,1};
        MontyHallGame game = new MontyHallGame();
        try {
            game.chooseOpenBoxFromFirstPick(boxArray, 3);
            fail();
        } catch (RuntimeException e) {
            //PASS
        }
    }

    @Test
    public void testOpenBoxFromAllEmpty() {
        int[] boxArray= new int[]{0,0,0};
        MontyHallGame game = new MontyHallGame();
        try {
            game.chooseOpenBoxFromFirstPick(boxArray, 3);
            fail();
        } catch (RuntimeException e) {
            //PASS
        }
    }

    @Test
    public void testOpenBoxFromTooBigMap() {
        int[] boxArray= new int[]{0,0,0,1};
        MontyHallGame game = new MontyHallGame();
        try {
            game.chooseOpenBoxFromFirstPick(boxArray, 3);
            fail();
        } catch (RuntimeException e) {
            //PASS
        }
    }

    @Test
    public void testGoToSecondStepDirectlyNotAllowed() {
        GameManager gameManager = new GameManager();
        try {
            gameManager.isSecondChoiceWinner(1);
            fail();
        } catch (RuntimeException e) {
            //PASS
        }
    }

    @Test
    public void testBatchPlay() {
        GameManager gameManager = new GameManager();
        int[] ints = gameManager.batchPlay(100, 1);
        System.out.println("ints = " + Arrays.toString(ints));
        Assert.assertEquals(100, Arrays.stream(ints).sum());
    }

    @Test
    public void testGetOpenedBox() {
        GameManager gameManager = new GameManager();
        int b = gameManager.getOpenedBox(1);
        Assert.assertNotEquals(1,b);
    }





}
