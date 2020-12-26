package xyz.tahakhan.AdventOfCode2020.Day9;

import xyz.tahakhan.AdventOfCode2020.Helpers.BaseSolution;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

public class Solution implements BaseSolution {

    @Override
    public void process() throws Exception {
        val input = readFile()
                .stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());
        val preambleSize = 25;

        System.out.println("Day 9 Part 1 answer: " + input.get(partOne(input, preambleSize)));

    }

    private int partOne(List<Long> input, int preambleSize) throws Exception {
        for(var i = preambleSize; i < input.size(); i++) {
            if (!isValidXMAS(input, i, preambleSize))
                return i;
        }
        return -1;
    }

    private boolean isValidXMAS(List<Long> input, int indexToValidate, int preambleSize) throws Exception {
        if (indexToValidate < preambleSize)
            throw new Exception("Invalid input");

        val number = input.get(indexToValidate);
        val start = indexToValidate - preambleSize;
        for (var i = start; i < indexToValidate; i++)
            for (var j = i + 1; j < indexToValidate; j++)
                if (input.get(i) + input.get(j) == number)
                    return true;
        return false;
    }
}
