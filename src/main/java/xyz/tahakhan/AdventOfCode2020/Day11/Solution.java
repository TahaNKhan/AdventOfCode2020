package xyz.tahakhan.AdventOfCode2020.Day11;

import lombok.*;
import xyz.tahakhan.AdventOfCode2020.Day11.Logic.SeatingSystem;
import xyz.tahakhan.AdventOfCode2020.Helpers.BaseSolution;

public class Solution implements BaseSolution {
    @Override
    public void process() throws Exception {
        val input = readFile();
        val seating = new SeatingSystem(input);

        System.out.println("Day 11 part 1 answer: " + partOne(seating));
    }

    public int partOne(SeatingSystem system) throws Exception {
        while (system.didSeatingChange())
            system.processNextState();
        return system.getNumberOfOccupiedSeats();
    }
}
