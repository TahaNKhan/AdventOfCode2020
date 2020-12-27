package xyz.tahakhan.AdventOfCode2020.Day11;

import lombok.*;
import xyz.tahakhan.AdventOfCode2020.Day11.Logic.SeatingSystem;
import xyz.tahakhan.AdventOfCode2020.Day11.Logic.VisibilityMethod;
import xyz.tahakhan.AdventOfCode2020.Helpers.BaseSolution;

import java.util.List;

public class Solution implements BaseSolution {
    @Override
    public void process() throws Exception {
        val input = readFile();

        System.out.println("Day 11 part 1 answer: " + partOne(input));
        System.out.println("Day 11 part 2 answer: " + partTwo(input));
    }

    private int partOne(List<String> input) throws Exception {
        val system = new SeatingSystem(input, VisibilityMethod.PART_ONE);
        return getFinalSeatingState(system);
    }

    private int partTwo(List<String> input) throws Exception {
        val system = new SeatingSystem(input, VisibilityMethod.PART_TWO);
        return getFinalSeatingState(system);
    }

    private int getFinalSeatingState(SeatingSystem system) throws Exception {
        while (system.didSeatingChange())
            system.processNextState();
        return system.getNumberOfOccupiedSeats();
    }
}
