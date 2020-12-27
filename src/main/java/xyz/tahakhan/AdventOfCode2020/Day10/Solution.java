package xyz.tahakhan.AdventOfCode2020.Day10;

import xyz.tahakhan.AdventOfCode2020.Helpers.BaseSolution;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

public class Solution implements BaseSolution {
    @Override
    public void process() throws Exception {
        val input = readFile()
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        val partOneAnswer = partOne(input);

        System.out.println("Day 10 Part 1 answer: " + partOneAnswer);
    }

    private int partOne(List<Integer> input) {
        val sorted = input
                .stream()
                .sorted()
                .collect(Collectors.toList());

        var oneJoltDifferenceTotal = 1;
        var threeJoltDifferentTotal = 1;

        for(var i = 1; i < sorted.size(); i++) {
            val difference = sorted.get(i) - sorted.get(i - 1);
            switch (difference) {
                case 1:
                    oneJoltDifferenceTotal++;
                    break;
                case 3:
                    threeJoltDifferentTotal++;
                    break;
                default:
                    System.out.println("Last: " + sorted.get(i - 1) + " Current: " + sorted.get(i));
            }
        }
        return oneJoltDifferenceTotal * threeJoltDifferentTotal;
    }
}
