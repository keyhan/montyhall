package com.monty.game;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.fail;

/**
 * Created by keyhan on 2017-04-18.
 */
public class MontyHallTest {

    @Test
    public void testOpenBoxFromOneNonEmpty() {
        int[] boxArray= new int[]{0,0,1};
        int openBox = MontyHallGame.chooseOpenBoxFromFirstPick(boxArray,1);
        Assert.assertEquals(2, openBox);
    }

    @Test
    public void testOpenBoxFromTwoEmpty() {
        int[] boxArray= new int[]{0,1,1};
        try {
            MontyHallGame.chooseOpenBoxFromFirstPick(boxArray, 3);
            fail();
        } catch (RuntimeException e) {
            //PASS
        }
    }

    @Test
    public void testOpenBoxFromAllEmpty() {
        int[] boxArray= new int[]{0,0,0};
        try {
            MontyHallGame.chooseOpenBoxFromFirstPick(boxArray, 3);
            fail();
        } catch (RuntimeException e) {
            //PASS
        }
    }

    @Test
    public void testOpenBoxFromTooBigMap() {
        int[] boxArray= new int[]{0,0,0,1};
        try {
            MontyHallGame.chooseOpenBoxFromFirstPick(boxArray, 3);
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
        int[] ints = gameManager.batchPlay(100, true);
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
