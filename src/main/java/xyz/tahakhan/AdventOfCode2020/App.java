package xyz.tahakhan.AdventOfCode2020;

import xyz.tahakhan.AdventOfCode2020.Helpers.BaseSolution;

public class App {

    private static final int defaultDayCodeToRun = 6;

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {
        Integer dayCodeToRun = getDayCodeToRun(args);
        Class<?> runnerClass = Class.forName("xyz.tahakhan.AdventOfCode2020.Day" + dayCodeToRun + ".Solution");
        BaseSolution solution = (BaseSolution) runnerClass.newInstance();
        solution.process();
    }

    private static Integer getDayCodeToRun(String[] args) {
        if (args == null || args.length < 1)
            return defaultDayCodeToRun;
        try {
            return Integer.parseInt(args[0]);
        } catch (NumberFormatException ex) {
            return defaultDayCodeToRun;
        }
    }
}
