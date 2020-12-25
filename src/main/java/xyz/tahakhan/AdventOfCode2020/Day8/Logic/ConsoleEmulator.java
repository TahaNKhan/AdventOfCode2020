package xyz.tahakhan.AdventOfCode2020.Day8.Logic;

import java.util.*;
import lombok.*;

public class ConsoleEmulator {
    private int accumulator;
    private int programCounter;
    private List<String> instructions;

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
        }
        return new ConsoleState(this.programCounter, this.accumulator);
    }

    public void reset() {
        accumulator = 0;
        programCounter = 0;
    }

    private void processNextInstruction() {
        val instructionLine = instructions.get(programCounter);

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

    @Getter
    public class ConsoleState {
        private final int programCounter;
        private final int accumulator;

        public ConsoleState(int programCounter, int accumulator) {
            this.accumulator = accumulator;
            this.programCounter = programCounter;
        }

    }

}
