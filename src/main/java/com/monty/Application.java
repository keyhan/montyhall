package com.monty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by keyhan on 2017-04-19.
 */
public class Application {

    private final static List<Integer> BOX_NUMBERS = Arrays.asList(1, 2, 3);

    private final static Scanner SCANNER = new Scanner(System.in);

    private final static int SWITCH = 1;

    private static void playBatch() {
        while (true) {
            int wins = 0;
            int losses = 0;
            System.out.print("1) Switch box in second run 2) Keep box in second run: ");
            int switchOrKeep = SCANNER.nextInt();
            if (1 > switchOrKeep || switchOrKeep > 2) {
                continue;
            }
            System.out.print("Batch Size: ");
            int batchSize = SCANNER.nextInt();

            for (int i = 0; i < batchSize; i++) {
                MontyHallGame game = new MontyHallGame();
                Integer firstChoice = new Random().nextInt(3) + 1;
                Integer openedBox = game.chooseOpenBoxFromFirstPick(firstChoice);
                int secondChoice;
                if(switchOrKeep == SWITCH) {
                    List<Integer> boxNumbers = new ArrayList<>(BOX_NUMBERS);
                    boxNumbers.remove(firstChoice);
                    boxNumbers.remove(openedBox);
                    secondChoice = boxNumbers.get(0);
                } else {
                    secondChoice = firstChoice;
                }
                if (game.secondPick(secondChoice)) {
                    wins++;
                } else {
                    losses++;
                }
            }
            System.out.println("Wins: " + wins);
            System.out.println("Losses: " + losses);
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

            MontyHallGame game = new MontyHallGame();

            Integer openedBox = game.chooseOpenBoxFromFirstPick(firstChoice);

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

            if (game.secondPick(secondChoice)) {
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
