package xyz.tahakhan.AdventOfCode2020.Day4;

import xyz.tahakhan.AdventOfCode2020.Helpers.BaseSolution;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Solution implements BaseSolution {
    @Override
    public void process() throws FileNotFoundException {
        List<String> inputData = readFile("src/main/java/xyz/tahakhan/AdventOfCode2020/Day4/input.txt");
        List<PassportData> parsedPassports = parseData(inputData);
        PassportDataValidator validator = new PassportDataValidator();
        long partOneAnswer = parsedPassports
                .stream()
                .filter(validator::validatePartOne)
                .count();
        System.out.println("Part 1 answer: " + partOneAnswer);

        long partTwoAnswer = parsedPassports
                .stream()
                .filter(validator::validatePartTwo)
                .count();

        System.out.println("Part 2 answer: " + partTwoAnswer);
    }

    private List<PassportData> parseData(List<String> input) {
        ArrayList<PassportData> passports = new ArrayList<>();
        ArrayList<String> buffer = new ArrayList<>();
        for (String line : input) {
            if (line.isEmpty()) {
                PassportData parsed = PassportDataParser.parse(buffer);
                passports.add(parsed);

                // Clear the buffer after the passport is parsed
                buffer = new ArrayList<>();
                continue;
            }
            buffer.add(line);
        }
        // parse the last one
        if (buffer.size() > 0) {
            PassportData parsed = PassportDataParser.parse(buffer);
            passports.add(parsed);
        }

        return passports;
    }
}
