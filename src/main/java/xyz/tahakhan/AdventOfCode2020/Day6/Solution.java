package xyz.tahakhan.AdventOfCode2020.Day6;

import lombok.val;
import xyz.tahakhan.AdventOfCode2020.Day6.Logic.CustomsAnswersParser;
import xyz.tahakhan.AdventOfCode2020.Helpers.BaseSolution;

import java.util.List;


@SuppressWarnings("OptionalGetWithoutIsPresent")
public class Solution implements BaseSolution {
    @Override
    public void process() throws Exception {
        val input = readFile("src/main/java/xyz/tahakhan/AdventOfCode2020/Day6/input.txt");
        val parsedAnswers = CustomsAnswersParser.parseGroups(input);

        System.out.println("Day 6 part 1 answer: " + partOne(parsedAnswers));
        System.out.println("Day 6 part 2 answer: " + partTwo(parsedAnswers));
    }

    private int partOne(List<List<String>> parsedAnswers) {
        return parsedAnswers
                .stream()
                .map(CustomsAnswersParser::numberOfUniqueAnswers)
                .reduce(Integer::sum)
                .get();
    }

    private int partTwo(List<List<String>> parsedAnswers) {
        return parsedAnswers
                .stream()
                .map(CustomsAnswersParser::numberOfPopularAnswers)
                .reduce(Integer::sum)
                .get();
    }
}
