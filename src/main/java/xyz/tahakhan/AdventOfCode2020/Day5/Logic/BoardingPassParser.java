package xyz.tahakhan.AdventOfCode2020.Day5.Logic;

import lombok.val;
import lombok.var;

import javax.naming.OperationNotSupportedException;

public class BoardingPassParser {
    public static BoardingSeat parse(String input) {
        val parsedSeat = new BoardingSeat();
        val col = parseColumn(input.substring(7));
        val row = parseRow(input.substring(0, 7));
        parsedSeat.setColumn(col);
        parsedSeat.setRow(row);
        return parsedSeat;
    }

    private static int parseRow(String input) {
        return solveBinarySpacePartition(input, 127, 'F', 'B');
    }

    private static int parseColumn(String input) {
        return solveBinarySpacePartition(input, 7, 'L', 'R');
    }

    private static int solveBinarySpacePartition(String input, int highStart, char upperHalfIdentifier, char lowerHalfIdentifier) {
        var low = 0;
        var high = highStart;

        for (var i = 0; i < input.length() - 1; i++) {
            val mid = (high + low) / 2;
            if (input.charAt(i) == upperHalfIdentifier) {
                high = mid;
            } else if (input.charAt(i) == lowerHalfIdentifier) {
                low = mid + 1;
            }
        }
        if (input.charAt(input.length() - 1) == upperHalfIdentifier) {
            return Math.min(low, high);
        } else if (input.charAt(input.length() - 1) == lowerHalfIdentifier) {
            return Math.max(low, high);
        }
        // Shouldn't reach this state
        return -1;
    }
}
