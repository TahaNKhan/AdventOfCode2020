package xyz.tahakhan.AdventOfCode2020.Day5;

import lombok.val;
import lombok.var;
import xyz.tahakhan.AdventOfCode2020.Day5.Logic.BoardingPassParser;
import xyz.tahakhan.AdventOfCode2020.Day5.Logic.BoardingSeat;
import xyz.tahakhan.AdventOfCode2020.Helpers.BaseSolution;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Solution implements BaseSolution {
    @Override
    public void process() throws Exception {
        val input = readFile("src/main/java/xyz/tahakhan/AdventOfCode2020/Day5/input.txt");
        val boardingPasses = input
                .stream()
                .map(BoardingPassParser::parse)
                .collect(Collectors.toList());
        val maxId = partOne(boardingPasses);
        System.out.println("Day 5 part one answer: " + maxId);

        System.out.println("Day 5 part two answer: " + partTwo(boardingPasses, maxId));
    }

    /**
     * Finds the missing ID from the list of boarding passes in the middle.
     */
    int partTwo(List<BoardingSeat> boardingPasses, int maxId) {
        val boardingPassIds = boardingPasses.stream()
                .map(BoardingSeat::getID)
                .collect(Collectors.toSet());
        var hasStarted = false;
        for(var i = 0; i < maxId; i++) {
            if (boardingPassIds.contains(i) && !hasStarted)
                hasStarted = true;
            if (!boardingPassIds.contains(i) && hasStarted)
                return i;
        }
        return -1;
    }

    /**
     * Finds the max id of the boarding passes
     */
    int partOne(List<BoardingSeat> boardingPasses) throws Exception {
        val optionalBoardingSeat = boardingPasses
                .stream()
                .max(Comparator.comparingInt(BoardingSeat::getID));
        if (!optionalBoardingSeat.isPresent()) {
            throw new Exception("Could not find max id.");
        }
        return optionalBoardingSeat.get().getID();
    }
}
