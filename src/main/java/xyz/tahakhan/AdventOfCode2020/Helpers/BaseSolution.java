package xyz.tahakhan.AdventOfCode2020.Helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * All solutions to each day need to inherit this interface to work.
 */
public interface BaseSolution {
    void process() throws Exception;

    /**
     * Reads the input.txt file in the DayX folder, expects the file to be in "src/main/java/xyz/tahakhan/AdventOfCode2020/DayX" folder.
     * @return The lines of the read file.
     */
    default List<String> readFile() throws FileNotFoundException {
        String fileName = "src/main/java/" + getClass().getPackage().getName().replace(".", "/") +  "/input.txt";
        return readFile(fileName);
    }

    /**
     * Read a file given a file name.
     * @param fileName The location of the file.
     * @return The lines of a the read file.
     */
    default List<String> readFile(String fileName) throws FileNotFoundException {
        ArrayList<String> result = new ArrayList<>();
        Scanner reader = new Scanner(new File(fileName));

        while(reader.hasNextLine())
            result.add(reader.nextLine());
        return result;
    }
}
