package com.vaani.leetcode.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;


import static java.util.stream.Collectors.toList;

class Result {
    private static final String BOARD_START_LINE = "......";
    private static final String DELIM = " ";
    private static final Map<String, Integer> LETTER_MAPPING = Map.of("A", 1, "B", 2, "C", 3, "D", 4, "E", 5, "F", 6);
    private static final String SHIP = "S";
    private static final String EMPTY = ".";
    private static final String HIT = "X";
    private static final String MISSED = "O";

    public static void play(String playerOneShips, List<String> playerTwoGuesses) {
        List<String> playerOneBoard = new ArrayList<>(6);
        initBoard(playerOneBoard);
        if (!addPlayerOneShips(playerOneShips, playerOneBoard)) {
            System.out.println("Invalid");
        }
        System.out.println("Player One entered " + getNumber(playerOneShips) + " ships");
        for (int i = 0; i < 10; i++) {
            int remainGuess = 10;
            System.out.println("Player Two, you have " + remainGuess + " guesses left, Board Statuses:");
            displayBoard(playerOneBoard);
            processPlayer2Guess(playerOneBoard, playerTwoGuesses.get(i));
            if (isFinished(playerOneBoard)) {
                System.out.println("You win!");
            }
        }
    }

    private static boolean isFinished(List<String> playerOneBoard) {
        for (String row : playerOneBoard) {
            if (row.contains(SHIP)) {
                return false;
            }
        }
        return true;
    }

    private static void processPlayer2Guess(List<String> playerOneBoard, String playerTwoGuess) {
        int rowIndex = LETTER_MAPPING.get(getRowLetter(playerTwoGuess)) - 1;
        int column = getColumnNumber(playerTwoGuess) - 1;
        String row = playerOneBoard.get(rowIndex);
        String substring = row.substring(column, column + 1);

        String newRow = row.substring(0, column);
        if (substring.equals(SHIP)) {
            newRow += HIT;
        } else {
            newRow += MISSED;
        }
        if (column < 4) {
            newRow += row.substring(column + 2);
        }
        playerOneBoard.set(rowIndex, newRow);
    }

    private static void displayBoard(List<String> playerOneBoardVisible) {
        for (String row : playerOneBoardVisible) {
            System.out.println(row);
        }
    }

    private static int getNumber(String playerOneShips) {
        String[] positions = playerOneShips.split(DELIM);
        return positions.length;
    }

    private static void initBoard(List<String> playerOneBoard) {
        for (int i = 0; i < 6; i++) {
            playerOneBoard.add(BOARD_START_LINE);
        }
    }


    private static boolean addPlayerOneShips(String playerOneShips, List<String> playerOneBoard) {
        String[] positions = playerOneShips.split(DELIM);
        int length = positions.length;
        if (length % 2 != 0) {
            return false;
        }
        for (int i = 0; i < length; i += 2) {
            String pos1 = positions[i];
            String pos2 = positions[i + 1];
            if (isHorizontalShip(pos1, pos2)) {
                if (!addHorizontalShip(playerOneBoard, pos1, pos2)) {
                    return false;
                }
            } else if (isVerticalShip(pos1, pos2)) {
                int shipLength = getVerticalShipLength(pos1, pos2);
                if (shipLength != 3) {
                    return true;
                }
                if (!addVerticalShip(playerOneBoard, pos1, pos2)) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean addVerticalShip(List<String> playerOneBoard, String pos1, String pos2) {
        int shipLength = getVerticalShipLength(pos1, pos2);
        if (shipLength != 3) {
            return false;
        }
        Integer index1 = LETTER_MAPPING.get(getRowLetter(pos1)) - 1;
        Integer index2 = LETTER_MAPPING.get(getRowLetter(pos2)) - 1;
        Integer letterIndex = getColumnNumber(pos1);
        if (index1 == null || index2 == null || letterIndex > 6) {
            return false;
        }
        if (index1 < index2) {
            for (int i = index1; i <= index2; i++) {
                String row = playerOneBoard.get(i);
                String pos = row.substring(letterIndex, letterIndex + 1);
                if (pos.equals(SHIP)) {
                    return false;
                }
                String newRow = row.substring(0, letterIndex) + SHIP;
                if (letterIndex < 4) {
                    newRow += row.substring(letterIndex + 2);
                }

            }
        }
        return true;
    }

    private static boolean addHorizontalShip(List<String> playerOneBoard, String pos1, String pos2) {
        int shipLength = getHorizontalShipLength(pos1, pos2);
        Integer rowCount = LETTER_MAPPING.get(getRowLetter(pos1));
        if (shipLength != 3 || rowCount > 6) {
            return false;
        }
        Integer index1 = getColumnNumber(pos1);
        Integer index2 = getColumnNumber(pos2);
        if (index1 > 6 || index2 > 6) {
            return false;
        }

        int rowIndex = rowCount - 1;
        String row = playerOneBoard.get(rowIndex);
        for (int j = 0; j < 6; j++) {
            String newRow = "";
            String oldPos = row.substring(j, j + 1);
            if (oldPos.equals(SHIP)) {
                return false;
            }
            if (index1 < index2) {
                if (j >= index1 && j <= index2) {
                    newRow += SHIP;
                } else {
                    newRow += EMPTY;
                }
            } else {
                if (j >= index2 && j <= index1) {
                    newRow += SHIP;
                } else {
                    newRow += EMPTY;
                }
            }
            playerOneBoard.set(rowIndex, newRow);
        }
        return true;
    }

    private static int getVerticalShipLength(String pos1, String pos2) {
        Integer index1 = LETTER_MAPPING.get(getRowLetter(pos1));
        Integer index2 = LETTER_MAPPING.get(getRowLetter(pos2));
        int length = index1 > index2 ? index1 - index2 : index2 - index1;
        return length + 1;
    }

    private static int getHorizontalShipLength(String pos1, String pos2) {
        Integer index1 = getColumnNumber(pos1);
        Integer index2 = getColumnNumber(pos2);
        int length = index1 > index2 ? index1 - index2 : index2 - index1;
        return length + 1;
    }

    private static boolean isVerticalShip(String pos1, String pos2) {
        return getColumnNumber(pos1).equals(getColumnNumber(pos2));
    }

    private static Integer getColumnNumber(String pos) {
        return Integer.parseInt(pos.substring(1, 2));
    }

    private static boolean isHorizontalShip(String pos1, String pos2) {
        return getRowLetter(pos1).equals(getRowLetter(pos2));
    }

    private static String getRowLetter(String pos) {
        return pos.substring(0, 1);
    }

}

public class BattleShips {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String playerOneShips = bufferedReader.readLine();

        int playerTwoGuessesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> playerTwoGuesses = IntStream.range(0, playerTwoGuessesCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        Result.play(playerOneShips, playerTwoGuesses);

        bufferedReader.close();
    }

}
