package com.monty;

import com.monty.game.GameManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by keyhan on 2017-04-19.
 */
public class Application {

    private final static List<Integer> BOX_NUMBERS = Arrays.asList(1, 2, 3);

    private final static Scanner SCANNER = new Scanner(System.in);

    private static GameManager gameManager;

    private static void playBatch() {
        while (true) {
            System.out.print("1) Switch box in second run 2) Keep box in second run: ");
            int switchOrKeep = SCANNER.nextInt();
            if (1 > switchOrKeep || switchOrKeep > 2) {
                continue;
            }
            System.out.print("Batch Size: ");
            int batchSize = SCANNER.nextInt();

            int[] results = gameManager.batchPlay(batchSize,switchOrKeep==1);

            System.out.println("Wins: " + results[0]);
            System.out.println("Losses: " + results[1]);
        }
    }

    private static void playInteractive() {
        int wins = 0;
        int losses = 0;
        while (true) {

            List<Integer> remainingBoxes = new ArrayList<>(BOX_NUMBERS);

            int firstChoice = 0;
            boolean inputException = false;

            try {
                firstChoice = getFirstChoice(SCANNER);
            } catch (InputMismatchException e) {
                SCANNER.next();
                inputException = true;
            }

            while (inputException || !BOX_NUMBERS.contains(firstChoice)) {
                inputException = false;
                try {
                    firstChoice = getFirstChoice(SCANNER);
                } catch (InputMismatchException e) {
                    SCANNER.next();
                    inputException = true;
                }
            }

            Integer openedBox = gameManager.getOpenedBox(firstChoice);

            remainingBoxes.remove(openedBox);

            int secondChoice = 0;
            try {
                secondChoice = getSecondChoice(SCANNER, remainingBoxes, openedBox);
            } catch (InputMismatchException e) {
                SCANNER.next();
                inputException = true;
            }

            while (inputException || !remainingBoxes.contains(secondChoice)) {
                inputException = false;
                try {
                    secondChoice = getSecondChoice(SCANNER, remainingBoxes, openedBox);
                } catch (InputMismatchException e) {
                    SCANNER.next();
                    inputException = true;
                }
            }

            if (gameManager.isSecondChoiceWinner(secondChoice)) {
                System.out.println("You have won!");
                wins++;
            } else {
                System.out.println("You have lost!");
                losses++;
            }
            System.out.println("Wins: " + wins);
            System.out.println("Losses: " + losses);
        }
    }

    public static void main(String ... args) {
        System.out.println("######## Monty Hall Simulator #########");
        gameManager = new GameManager();
        System.out.print("1) Interactive , 2) Batch Run: ");

        int runChoice = SCANNER.nextInt();

        if(runChoice < 1 || runChoice > 2) {
            System.out.println("Choice not known");
            return;
        }

        if(runChoice == 2) {
            playBatch();
        }else {
            playInteractive();
        }



    }

    private static int getSecondChoice(Scanner scanner, List<Integer> remainingBoxes, Integer openedBox) {
        System.out.print(String.format("Box %s is opened and is empty" +
                ", choose again from remaining BOX_NUMBERS %s: ", openedBox, remainingBoxes));
        return scanner.nextInt();
    }

    private static int getFirstChoice(Scanner scanner) {
        System.out.print("Choose from one of the Boxes " + BOX_NUMBERS + ": ");
        return scanner.nextInt();
    }
}