# Advent of Code 2020 Solutions

Practice for relearning java.

## How it works

App.java is the entry point for the application.

It uses reflection to get the correct day's solution class, instantiate it and run the process function.

### How to add more solutions

1. Add new folder inside xyz.tahakhan.AdventOfCode2020 folder and call it "DayX" where X is a number.
2. Create a java file and call it "Solution", implement the "xyz.tahakhan.AdventOfCode2020.Helpers.BaseSolution" interface.
   1. Make sure the package naming scheme is correct.
3. Either:
    1. Update App.java, set defaultDayCodeToRun variable to X from above.
    2. Add a command line argument of X. (This takes precedence over the defaultDayCodeToRun variable in App.java).
4. Compile + run.