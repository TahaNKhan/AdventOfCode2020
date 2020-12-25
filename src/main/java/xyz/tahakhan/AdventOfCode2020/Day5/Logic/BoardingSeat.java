package xyz.tahakhan.AdventOfCode2020.Day5.Logic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardingSeat {
    private int row;
    private int column;

    public int getID() {
        return (row * 8) + column;
    }
}
