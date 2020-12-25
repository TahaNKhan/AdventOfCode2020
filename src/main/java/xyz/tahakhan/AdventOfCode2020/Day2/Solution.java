package xyz.tahakhan.AdventOfCode2020.Day2;

import xyz.tahakhan.AdventOfCode2020.Day2.Logic.InputParser;
import xyz.tahakhan.AdventOfCode2020.Day2.Logic.ParserType;
import xyz.tahakhan.AdventOfCode2020.Day2.Logic.Row;
import xyz.tahakhan.AdventOfCode2020.Helpers.BaseSolution;

import javax.naming.OperationNotSupportedException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution implements BaseSolution {
    @Override
    public void process() {
        try {
            ParseResult input = readInputFromFile();

            long partOneValidPasswords = input.partOneResult.stream().filter(Row::getIsValid).count();
            long partTwoValidPasswords = input.partTwoResult.stream().filter(Row::getIsValid).count();

            System.out.println("Total valid passwords for Part 1: " + partOneValidPasswords);
            System.out.println("Total valid passwords for Part 2: " + partTwoValidPasswords);
        } catch (IOException | OperationNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private static ParseResult readInputFromFile() throws IOException, OperationNotSupportedException {
        ArrayList<Row> partOneResults = new ArrayList<>();
        ArrayList<Row> partTwoResults = new ArrayList<>();
        FileInputStream stream = new FileInputStream("src/main/java/xyz/tahakhan/AdventOfCode2020/Day2/input.txt");
        Scanner scanner = new Scanner(stream);
        while(scanner.hasNextLine())
        {
            String readLine = scanner.nextLine();
            partOneResults.add(InputParser.parseRow(readLine, ParserType.PART_ONE));
            partTwoResults.add(InputParser.parseRow(readLine, ParserType.PART_TWO));
        }
        return new ParseResult(partOneResults, partTwoResults);
    }

    private static class ParseResult
    {
        public ArrayList<Row> partOneResult;
        public ArrayList<Row> partTwoResult;
        public ParseResult(ArrayList<Row> partOneResult, ArrayList<Row> partTwoResult)
        {
            this.partOneResult = partOneResult;
            this.partTwoResult = partTwoResult;
        }
    }
}
