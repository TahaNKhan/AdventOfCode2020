package xyz.tahakhan.AdventOfCode2020.Day2.Logic;

import java.util.Arrays;

public class PartOneParsedRow extends Row {
    private int upperBound;
    private int lowerBound;

    private Boolean isValid = null;

    @Override
    public boolean getIsValid() {
        if (isValid != null)
            return isValid;
        int numberOfOccurrences = 0;
        for (char s : getPassword().toCharArray()) {
            if (s == getLetter()) {
                numberOfOccurrences++;
            }
        }

        isValid = numberOfOccurrences <= getUpperBound() && numberOfOccurrences >= getLowerBound();
        return isValid;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }
}
