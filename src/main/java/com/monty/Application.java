package com.monty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by keyhan on 2017-04-19.
 */
public class Application {
    public static void main(String ... args) {
        while (true) {
            System.out.println("######## Monty Hall Simulator #########");
            Scanner scanner = new Scanner(System.in);

            List<Integer> boxes = Arrays.asList(1, 2, 3);

            List<Integer> remainingBoxes = new ArrayList<>(boxes);

            int firstChoice = 0;
            boolean inputException = false;

            try {
                firstChoice = getFirstChoice(scanner, boxes);
            } catch (InputMismatchException e) {
                scanner.next();
                inputException = true;
            }

            while (inputException || !boxes.contains(firstChoice)) {
                inputException = false;
                try {
                    firstChoice = getFirstChoice(scanner, boxes);
                } catch (InputMismatchException e) {
                    scanner.next();
                    inputException = true;
                }
            }

            MontyHallGame game = new MontyHallGame();

            Integer openedBox = game.firstPick(firstChoice);

            remainingBoxes.remove(openedBox);

            int secondChoice = 0;
            try {
                secondChoice = getSecondChoice(scanner, remainingBoxes, openedBox);
            } catch (InputMismatchException e) {
                scanner.next();
                inputException = true;
            }

            while (inputException || !remainingBoxes.contains(secondChoice)) {
                inputException = false;
                try {
                    secondChoice = getSecondChoice(scanner, remainingBoxes, openedBox);
                } catch (InputMismatchException e) {
                    scanner.next();
                    inputException = true;
                }
            }

            if (game.secondPick(secondChoice)) {
                System.out.println("You have won!");
            } else {
            System.out.println("You have lost!");
            }
        }
    }

    private static int getSecondChoice(Scanner scanner, List<Integer> remainingBoxes, Integer openedBox) {
        System.out.print(String.format("Box %s is opened and is empty" +
                ", choose again from remaining boxes %s: ", openedBox, remainingBoxes));
        return scanner.nextInt();
    }

    private static int getFirstChoice(Scanner scanner, List<Integer> boxes) {
        System.out.print("Choose from one of the Boxes " + boxes + ": ");
        return scanner.nextInt();
    }
}
