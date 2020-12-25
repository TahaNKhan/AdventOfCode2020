package xyz.tahakhan.AdventOfCode2020.Day5;

import lombok.val;
import xyz.tahakhan.AdventOfCode2020.Day5.Logic.BoardingPassParser;
import xyz.tahakhan.AdventOfCode2020.Day5.Logic.BoardingSeat;
import xyz.tahakhan.AdventOfCode2020.Helpers.BaseSolution;

import java.io.FileNotFoundException;
import java.util.Comparator;

public class Solution implements BaseSolution {
    @Override
    public void process() throws FileNotFoundException {
        val input = readFile("src/main/java/xyz/tahakhan/AdventOfCode2020/Day5/input.txt");
        val boardingPasses = input
                .stream()
                .map(BoardingPassParser::parse);

        val optionalMax = boardingPasses
                .max(Comparator.comparingInt(BoardingSeat::getID));
        if (!optionalMax.isPresent()) {
            System.out.println("Could not find max id.");
            return;
        }
        val maxBoardingPassId = optionalMax.get().getID();
        System.out.println("Max boarding pass id: " + maxBoardingPassId);

    }
}
