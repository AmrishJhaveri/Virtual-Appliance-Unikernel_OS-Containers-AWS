package com.uic.chess.cs441.chessrestapi.utils;

import pl.art.lach.mateusz.javaopenchess.core.Square;

public final class ChessUtility {

    public static int[] getIndexesFromString(String square) {

        //in valid square checks
        if (square == null || square.isEmpty() || square.length() > 2) {
            return null;
        }
        char[] squareIndex = square.toCharArray();

        // check the square letters are within the valid range.
        if (squareIndex[0] < 'a' || squareIndex[0] > 'h' || squareIndex[1] < '1' || squareIndex[1] > '8') {
            return null;
        }
        return new int[]{mapLetterToDigit(squareIndex[0]), squareIndex[1] - 49};
    }

    private static int mapLetterToDigit(char in) {
        switch (in) {
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 'e':
                return 4;
            case 'f':
                return 5;
            case 'g':
                return 6;
            case 'h':
                return 7;
            default:
                return -1;
        }
    }

    private static char mapDigitToLetter(int in) {
        switch (in) {
            case 0:
                return 'a';
            case 1:
                return 'b';
            case 2:
                return 'c';
            case 3:
                return 'd';
            case 4:
                return 'e';
            case 5:
                return 'f';
            case 6:
                return 'g';
            case 7:
                return 'h';
            default:
                return '0';
        }
    }

    public static String getStringFromIndex(Square sq) {
        return String.valueOf(mapDigitToLetter(sq.getPozX()) + "" + (sq.getPozY() + 1));
    }
}
