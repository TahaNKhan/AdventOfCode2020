package xyz.tahakhan.AdventOfCode2020.Day2.Logic;

public class PartTwoParsedRow extends Row {

    private int firstIndex;
    private int secondIndex;

    private Boolean isValid = null;

    @Override
    public boolean getIsValid() {
        if (isValid != null)
            return isValid;
        String password = getPassword();
        isValid = password.charAt(getFirstIndex()) == getLetter() ^ password.charAt(getSecondIndex()) == getLetter();
        return isValid;
    }

    public int getSecondIndex() {
        return secondIndex;
    }

    public void setSecondIndex(int secondIndex) {
        this.secondIndex = secondIndex;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }
}
