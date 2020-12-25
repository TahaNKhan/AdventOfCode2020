package xyz.tahakhan.AdventOfCode2020.Day8;

import xyz.tahakhan.AdventOfCode2020.Day8.Logic.ConsoleEmulator;
import xyz.tahakhan.AdventOfCode2020.Helpers.BaseSolution;
import lombok.*;

import java.util.List;

public class Solution implements BaseSolution {
    @Override
    public void process() throws Exception {
        val input = readFile();
        val emulator = new ConsoleEmulator(input);

        System.out.println("Day 8 part 1 answer: " + partOne(emulator));
    }

    private int partOne(ConsoleEmulator emulator) {
        return emulator.findInfiniteLoop().getAccumulator();
    }
}
