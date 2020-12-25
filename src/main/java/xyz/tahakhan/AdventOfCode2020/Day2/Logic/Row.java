package xyz.tahakhan.AdventOfCode2020.Day2.Logic;

public abstract class Row {
    private String password;
    private char letter;

    public abstract boolean getIsValid();

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }
}
