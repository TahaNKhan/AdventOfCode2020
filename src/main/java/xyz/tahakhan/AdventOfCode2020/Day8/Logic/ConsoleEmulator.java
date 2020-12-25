package xyz.tahakhan.AdventOfCode2020.Day8.Logic;

import java.util.*;
import lombok.*;
import xyz.tahakhan.AdventOfCode2020.Helpers.Utilities;

public class ConsoleEmulator {

    //region State and getters
    private int accumulator;
    private int programCounter;
    private final List<String> instructions;
    private List<String> instructionsOverride;

    private List<String> getInstructions() {
        if (instructionsOverride != null)
            return instructionsOverride;
        return instructions;
    }

    @Getter
    public static class ConsoleState {
        private final int programCounter;
        private final int accumulator;
        private final boolean executedSuccessfully;

        public ConsoleState(int programCounter, int accumulator, boolean executedSuccessfully) {
            this.accumulator = accumulator;
            this.programCounter = programCounter;
            this.executedSuccessfully = executedSuccessfully;
        }

    }
    //endregion

    public ConsoleEmulator(List<String> instructions) {
        this.instructions = instructions;
        accumulator = 0;
        programCounter = 0;
    }

    public ConsoleState findInfiniteLoop() {
        val instructionsAlreadyRan = new HashSet<Integer>();
        while (!instructionsAlreadyRan.contains(programCounter)) {
            instructionsAlreadyRan.add(programCounter);
            processNextInstruction();
            if (this.programCounter >= getInstructions().size())
                return new ConsoleState(this.programCounter, this.accumulator, true);
        }
        return new ConsoleState(this.programCounter, this.accumulator, false);
    }

    public ConsoleState fixInfiniteLoop() throws Exception {
        var currentFlippedLine = 0;
        do {
            currentFlippedLine = flipNextInstructionAndReset(currentFlippedLine);
        } while(!findInfiniteLoop().isExecutedSuccessfully());

        return new ConsoleState(this.programCounter, this.accumulator, true);
    }

    private int flipNextInstructionAndReset(int currentFlippedLine) throws Exception {
        instructionsOverride = (List<String>) Utilities.DeepClone(instructions);
        val nextFlippedLine = findNextJumpOrNop(currentFlippedLine);
        instructionsOverride.set(currentFlippedLine, flipInstruction(getInstructions().get(currentFlippedLine)));
        reset();
        return nextFlippedLine;
    }

    public void reset() {
        accumulator = 0;
        programCounter = 0;
    }

    private void processNextInstruction() {
        val instructionLine = getInstructions().get(programCounter);

        val instructionSplit = instructionLine.split(" ");
        val instruction = instructionSplit[0];
        val instructionValue = Integer.parseInt(instructionSplit[1]);

        switch(instruction) {
            case "nop":
                break;
            case "acc":
                accumulator += instructionValue;
                break;
            case "jmp":
                programCounter += instructionValue;
                return;
        }

        programCounter++;
    }

    private String flipInstruction(String instruction) {
        val instructionSplit = instruction.split(" ");
        if ("jmp".equals(instructionSplit[0]))
            return "nop " + instructionSplit[1];
        else if ("nop".equals(instructionSplit[0]))
            return "jmp " + instructionSplit[1];
        return instruction;
    }

    private int findNextJumpOrNop(int startIndex) throws Exception {
        var index = startIndex + 1;
        var parsedInstruction = parseInstruction(getInstructions().get(index));
        while (
                !"jmp".equals(parsedInstruction)
                &&
                !"nop".equals(parsedInstruction)
        ) {
            index++;
            if (index>=getInstructions().size())
                throw new Exception();
            parsedInstruction = parseInstruction(getInstructions().get(index));
        }
        return index;
    }

    private String parseInstruction(String line) {
        return line.split(" ")[0];
    }
}
