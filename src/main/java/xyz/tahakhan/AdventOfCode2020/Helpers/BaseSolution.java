package xyz.tahakhan.AdventOfCode2020.Helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public interface BaseSolution {
    void process() throws Exception;

    default List<String> readFile(String fileName) throws FileNotFoundException {
        ArrayList<String> result = new ArrayList<>();
        Scanner reader = new Scanner(new File(fileName));

        while(reader.hasNextLine())
            result.add(reader.nextLine());
        return result;
    }
}
