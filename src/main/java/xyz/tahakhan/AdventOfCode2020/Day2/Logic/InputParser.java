package xyz.tahakhan.AdventOfCode2020.Day2.Logic;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;

public class InputParser {

    public static Row parseRow(String line, ParserType type) throws OperationNotSupportedException {
        switch (type)
        {
            case PART_ONE:
                return parsePartOne(line);
            case PART_TWO:
                return parsePartTwo(line);
            default:
                throw new OperationNotSupportedException();
        }
    }

    private static Row parsePartTwo(String line) {
        PartTwoParsedRow row = new PartTwoParsedRow();
        // splits into the password attributes and the password
        String[] firstSplit = line.split(":");
        String passwordAttributes = firstSplit[0];

        // Last char is the validator letter
        row.setLetter(passwordAttributes.charAt(passwordAttributes.length() - 1));

        // Remove the validator letter
        passwordAttributes = passwordAttributes.substring(0, passwordAttributes.length() - 2);
        int[] indexes = Arrays.stream(passwordAttributes.split("-")).mapToInt(Integer::parseInt).toArray();
        row.setFirstIndex(indexes[0] - 1);
        row.setSecondIndex(indexes[1] - 1);

        row.setPassword(firstSplit[1].trim());

        return row;
    }

    private static Row parsePartOne(String line) {
        PartOneParsedRow row = new PartOneParsedRow();
        // splits into the password attributes and the password
        String[] firstSplit = line.split(":");
        String passwordAttributes = firstSplit[0];

        // Last char is the validator letter
        row.setLetter(passwordAttributes.charAt(passwordAttributes.length() - 1));

        // Remove the validator letter
        passwordAttributes = passwordAttributes.substring(0, passwordAttributes.length() - 2);
        int[] bounds = Arrays.stream(passwordAttributes.split("-")).mapToInt(Integer::parseInt).toArray();
        row.setLowerBound(bounds[0]);
        row.setUpperBound(bounds[1]);

        // First char is a space, just remove it.
        row.setPassword(firstSplit[1].substring(1));

        return row;
    }
}

