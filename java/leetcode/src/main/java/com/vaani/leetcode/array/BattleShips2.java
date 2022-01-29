package com.vaani.leetcode.array;
import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;
//
//class Result {
//
//    // ignoring [0][0] to align with input
//    static char[][] player1Matrix = new char[7][7];
//    static char[][] guessMatrix = new char[7][7];
//    static int inputShipCount = 0;
//    static int expectedHitCount = 0;
//    static int guessesLeft = 10;
//    static int hitCount = 0;
//    static Map<String, Integer> letterMapping = new HashMap<>();
//
//    /*
//     * Complete the 'play' function below.
//     *
//     * The function accepts following parameters:
//     *  1. STRING playerOneShips
//     *  2. STRING_ARRAY playerTwoGuesses
//     */
//
//    /**
//     * Main driver function of the game
//     * @param playerOneShips
//     * @param playerTwoGuesses
//     */
//    public static void play(String playerOneShips, List<String> playerTwoGuesses) {
//        // Write your code here
//        // setup letter mapping
//        initLettermapping();
//        // init Guess Matrix with "."
//        initGuessMatrix();
//
//        // Split the space delimited player one ship indexes
//        String[] startAndEnd = playerOneShips.split(" ");
//
//        // Input indexes should be in pairs
//        if(startAndEnd.length%2 != 0) {
//            System.out.println("invalid");
//            System.exit(0);
//        }
//
//        int i = 0;
//        while(i < startAndEnd.length - 1) {
//            System.out.println(" " + startAndEnd[i]);
//            // Send to Sanity check i and i+1
//            // if sanity check passes, insert into the player one's matrix
//            boolean retVal = sanityCheckAndInsert(startAndEnd[i], startAndEnd[i + 1]);
//
//            // count the number of input ships
//            if (retVal) inputShipCount++;
//            else {
//                System.out.println("invalid");
//                System.exit(0);
//            }
//
//            i += 2;
//        }
//        // update expected ship count based on # ships inputted
//        expectedHitCount = 3 * inputShipCount;
//
//        System.out.println("Player One entered " + inputShipCount + " ships.");
//        System.out.println();
//        player2Guess(playerTwoGuesses);
//        if(hitCount == expectedHitCount) {
//            System.out.println("You win!");
//        } else {
//            System.out.println("You lose!");
//        }
//    }
//
//    /**
//     * Method to drive the player 2's game
//     * Exits in case of any discrepancies in the player 2's data
//     * @param playerTwoGuesses
//     */
//    public static void player2Guess(List<String> playerTwoGuesses) {
//        int counter  = 0;
//        while(counter<playerTwoGuesses.size() && guessesLeft > 0) {
//            System.out.println();
//            System.out.println("Player Two, you have "+guessesLeft+" guesses left. Board Status:");
//            printBoardStatus();
//
//            System.out.println("Player Two, please enter your guess: " + playerTwoGuesses.get(counter));
//
//            String retVal = hitOrMiss(playerTwoGuesses.get(counter));
//            System.out.println("That was a "+retVal+"!");
//            guessesLeft--;
//            counter++;
//        }
//    }
//
//    /**
//     * Finds if player2's input is a hit or a miss
//     */
//    static String hitOrMiss(String index) {
//        String[] split = index.split("");
//        int row = letterMapping.get(split[0]);
//        int column = Integer.parseInt(split[1]);
//
//        // Check the sanity of Player 2's inputs
//        // Also helps with avoiding Out of bounds exception
//        if(!(row > 0 && row < 7) || !(column > 0 && column < 7)) {
//            System.out.println("invalid");
//            System.exit(0);
//        }
//
//        if (player1Matrix[row][column] == 'X') {
//            hitCount++;
//            // Update the guess Matrix with 'X'
//            guessMatrix[row][column] = 'X';
//            return "hit";
//        } else {
//            // Update the guess Matrix with '0'
//            guessMatrix[row][column] = 'O';
//            return "miss";
//        }
//    }
//
//    /**
//     * Method to check the sanity of the given Player1's ship locations
//     * print "invalid" and exit if discrepancies found
//     * else, go ahead and insert into the Player 1's matrix
//     */
//    public static boolean sanityCheckAndInsert(String start, String end) {
//        String[] splitStart = start.split("");
//        String[] splitEnd = end.split("");
//        int startRow = letterMapping.get(splitStart[0]);
//        int endRow = letterMapping.get(splitEnd[0]);
//        int startColumn = Integer.parseInt(splitStart[1]);
//        int endColumn = Integer.parseInt(splitEnd[1]);
//
//        // Case 1: Start and end starts with same character (row major):
//        if(startRow == endRow) {
//            int startIndex = Math.min(Integer.parseInt(splitStart[1]), Integer.parseInt(splitEnd[1]));
//            int endIndex = Math.max(Integer.parseInt(splitStart[1]), Integer.parseInt(splitEnd[1]));
//            // Make sure the length of the ship is 3
//            if (endIndex - startIndex != 2) return false;
//            while (startIndex <= endIndex) {
//                char lit = player1Matrix[startRow][startIndex];
//                if (lit == 'X') {
//                    // Already marked by another ship input
//                    return false;
//                } else {
//                    player1Matrix[startRow][startIndex] = 'X';
//                }
//                startIndex++;
//            }
//        }
//        // Case 2: Start and end starts with different character (column major)
//        else if(startColumn == endColumn) {
//            int startIndex = Math.min(startRow, endRow);
//            int endIndex = Math.max(startRow, endRow);
//
//            if(endIndex - startIndex != 2) return false;
//
//            while(startIndex <= endIndex) {
//                char lit = player1Matrix[startIndex][startColumn];
//                if (lit == 'X') {
//
//                    // Already marked by another ship input - intersection
//                    return false;
//                } else {
//                    player1Matrix[startIndex][startColumn] = 'X';
//                }
//                endIndex++;
//            }
//        } else {
//            return false;
//        }
//        return true;
//    }
//
//    // UTIL METHODS
//
//    /**
//     * A quick map to translate Letters into index in the matrix
//     */
//    public static void initLettermapping() {
//        letterMapping.put("A", 1);
//        letterMapping.put("B", 2);
//        letterMapping.put("C", 3);
//        letterMapping.put("D", 4);
//        letterMapping.put("E", 5);
//        letterMapping.put("F", 6);
//    }
//
//    /**
//     * Prints the status of Player 2's guesses so far on each turn
//     */
//    public static void printBoardStatus() {
//        for(int i=1;i<guessMatrix.length;i++) {
//            for(int j=1;j<guessMatrix.length;j++){
//                System.out.print(guessMatrix[i][j]);
//            }
//            System.out.println();
//        }
//    }
//
//    /**
//     * Initialize the GuessMatrix
//     * Helps when we are printing the status on each Guess
//     */
//    public static void initGuessMatrix() {
//        for(int i=1;i<guessMatrix.length;i++) {
//            for(int j=1;j<guessMatrix.length;j++) {
//                guessMatrix[i][j] = '.';
//            }
//        }
//    }
//}

//public class BattleShips2 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//
//        String playerOneShips = bufferedReader.readLine();
//
//        int playerTwoGuessesCount = Integer.parseInt(bufferedReader.readLine().trim());
//
//        List<String> playerTwoGuesses = IntStream.range(0, playerTwoGuessesCount).mapToObj(i -> {
//                    try {
//                        return bufferedReader.readLine();
//                    } catch (IOException ex) {
//                        throw new RuntimeException(ex);
//                    }
//                })
//                .collect(toList());
//
//        Result.play(playerOneShips, playerTwoGuesses);
//
//        bufferedReader.close();
//    }
//}

