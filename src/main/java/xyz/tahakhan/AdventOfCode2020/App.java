package xyz.tahakhan.AdventOfCode2020;

import xyz.tahakhan.AdventOfCode2020.Helpers.BaseSolution;

import java.io.FileNotFoundException;

public class App {

    private static int defaultDayCodeToRun = 5;

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, FileNotFoundException {
        Integer dayCodeToRun = getRuntimeDayCodeToRun(args);
        if (dayCodeToRun == null)
            dayCodeToRun = defaultDayCodeToRun;
        Class<?> runnerClass = Class.forName("xyz.tahakhan.AdventOfCode2020.Day" + dayCodeToRun + ".Solution");
        BaseSolution solution = (BaseSolution) runnerClass.newInstance();
        solution.process();
    }

    private static Integer getRuntimeDayCodeToRun(String[] args) {
        if (args == null || args.length < 1)
            return null;
        try {
            return Integer.parseInt(args[0]);
        } catch (NumberFormatException _) {
            return null;
        }
    }
}
