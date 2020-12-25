package xyz.tahakhan.AdventOfCode2020.Day3;

import xyz.tahakhan.AdventOfCode2020.Helpers.BaseSolution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution implements BaseSolution {
    @Override
    public void process() throws FileNotFoundException {
        List<String> testData = importTestData();
        int partOneAnswer = findNumberOfTreesPart1(testData);
        System.out.println("Answer to part 1: " + partOneAnswer);
        long partTwoAnswer = findNumberOfTreesPart2(testData, partOneAnswer);
        System.out.println("Answer to part 2: " + partTwoAnswer);
    }

    private Integer findNumberOfTreesPart1(List<String> input) {
        return findNumberOfTreesBySlope(input, 1, 3);
    }

    private Long findNumberOfTreesPart2(List<String> input, int answerFromPart1) {
        /*
        Right 1, down 1.
        Right 3, down 1. (This is the slope you already checked.)
        Right 5, down 1.
        Right 7, down 1.
        Right 1, down 2.
        */
        return (long) findNumberOfTreesBySlope(input, 1, 1)
                * answerFromPart1
                * findNumberOfTreesBySlope(input, 1, 5)
                * findNumberOfTreesBySlope(input, 1, 7)
                * findNumberOfTreesBySlope(input, 2, 1);
    }

    private Integer findNumberOfTreesBySlope(List<String> input, int down, int right) {
        // Count number of #s
        int numberOfTrees = 0;
        int j = 0;
        for(int i = down; i < input.size(); i = i + down)
        {
            j += right;
            String line = input.get(i);
            j %= line.length();
            if (line.charAt(j) == '#')
                numberOfTrees++;
        }
        return numberOfTrees;
    }

    private List<String> importTestData() throws FileNotFoundException {
        return readFile("src/main/java/xyz/tahakhan/AdventOfCode2020/Day3/input.txt");
    }
}
