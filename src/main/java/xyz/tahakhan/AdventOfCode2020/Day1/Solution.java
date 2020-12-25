package xyz.tahakhan.AdventOfCode2020.Day1;

import xyz.tahakhan.AdventOfCode2020.Helpers.BaseSolution;

import java.io.FileNotFoundException;

public class Solution implements BaseSolution {
    public void process() {
        try {
            int[] input = readFile("src/main/java/xyz/tahakhan/AdventOfCode2020/Day1/input.txt")
                    .stream()
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int partOneAnswer = partOne(input);
            System.out.println("Part one answer is: " + partOneAnswer);
            int partTwoAnswer = partTwo(input);
            System.out.println("Part two answer is: " + partTwoAnswer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int partTwo(int[] input)
    {
        for(int i = 0; i < input.length; i++)
        {
            for(int j = i+1; j < input.length; j++)
            {
                for(int k = j+1; k < input.length; k++)
                    if ((input[i] + input[j] + input[k]) == 2020)
                        return input[i] * input[j] * input[k];
            }
        }
        return 0;
    }

    private static int partOne(int[] input)
    {
        for(int i = 0; i < input.length; i++)
        {
            for(int j = i+1; j < input.length; j++)
            {
                if ((input[i] + input[j]) == 2020)
                    return input[i] * input[j];
            }
        }
        return 0;
    }
}
