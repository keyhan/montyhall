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
        int wins = 0;
        int losses = 0;
        Scanner scanner = new Scanner(System.in);
        List<Integer> boxes = Arrays.asList(1, 2, 3);

        System.out.println("######## Monty Hall Simulator #########");

        System.out.print("1) Interactive , 2) Batch Run: ");

        int runChoice = scanner.nextInt();

        if(runChoice != 1 && runChoice != 2) {
            System.out.println("Choice not known");
            return;
        }

        if(runChoice == 2) {
            System.out.print("1)Switch in second run 2)Stay on second run: ");
            int stayOrSwitch = scanner.nextInt();
            if(1 > stayOrSwitch || stayOrSwitch > 2) {

            }
        }

        while (true) {

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
                wins++;
            } else {
            System.out.println("You have lost!");
            losses++;
            }
            System.out.println("Wins: " + wins);
            System.out.println("Losses: " + losses);
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
