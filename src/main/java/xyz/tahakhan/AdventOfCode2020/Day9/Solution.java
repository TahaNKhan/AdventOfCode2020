package xyz.tahakhan.AdventOfCode2020.Day9;

import xyz.tahakhan.AdventOfCode2020.Helpers.BaseSolution;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Solution implements BaseSolution {

    private final int PREAMBLE_SIZE = 25;

    @Override
    public void process() throws Exception {
        val input = readFile()
                .stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());

        val partOneAnswer = partOne(input, PREAMBLE_SIZE);
        System.out.println("Day 9 Part 1 answer: " + partOneAnswer);

        val partTwoAnswer = partTwo(input, partOneAnswer);
        System.out.println("Day 9 Part 2 answer: " + partTwoAnswer);

    }

    private long partOne(List<Long> input, int preambleSize) throws Exception {
        for(var i = preambleSize; i < input.size(); i++) {
            if (!isValidXMAS(input, i, preambleSize))
                return input.get(i);
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

    private long partTwo(List<Long> input, long partOneAnswer) {
        var startIndex = 0;
        var endIndex = 1;

        while(true) {
            var total = getSum(input, startIndex, endIndex);
            if (getSum(input, startIndex, endIndex) != partOneAnswer) {
                if (total > partOneAnswer) {
                    startIndex++;
                    endIndex = startIndex + 1;
                } else {
                    endIndex++;
                }
            } else {
                return getMinMaxSum(input, startIndex, endIndex);
            }
        }
    }

    /**
     * Calculates the sum of contiguous elements starting from startIndex to endIndex (inclusive).
     */
    private long getSum(List<Long> input, int startIndex, int endIndex) {
        var total = 0;

        for(var i = startIndex; i <= endIndex; i++)
            total += input.get(i);

        return total;
    }

    /**
     * Finds the minimum and maximum values in the contiguous elements given the range (inclusive)
     * and returns the total.
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private long getMinMaxSum(List<Long> input, int startIndex, int endIndex) {
        val set = new HashSet<Long>();
        for(var i = startIndex; i <= endIndex; i++)
            set.add(input.get(i));
        val min = set
                .stream()
                .min(Long::compareTo)
                .get();
        val max = set
                .stream()
                .max(Long::compareTo)
                .get();
        return min + max;
    }
}
