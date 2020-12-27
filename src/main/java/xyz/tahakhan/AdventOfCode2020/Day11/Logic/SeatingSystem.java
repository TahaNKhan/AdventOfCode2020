package xyz.tahakhan.AdventOfCode2020.Day11.Logic;

import java.util.*;
import lombok.*;
public class SeatingSystem {
    private SeatState[][] previousState;
    private SeatState[][] currentState;
    public SeatingSystem(List<String> input) {
        previousState = null;
        currentState = new SeatState[input.size()][];
        for (int i = 0; i < input.size(); i++)
            currentState[i] = parseSeatLine(input.get(i).toCharArray());
    }

    public void processNextState() throws Exception {
        val nextState = new SeatState[currentState.length][];
        for(var i = 0; i < currentState.length; i++) {
            nextState[i] = new SeatState[currentState[i].length];
            for(var j = 0; j < currentState[i].length; j++) {
                nextState[i][j] = processNextStateForIndex(currentState, i, j);
            }
        }
        previousState = currentState;
        currentState = nextState;
    }

    public boolean didSeatingChange() {
        if (previousState == null)
            return true;
        for(var i = 0; i < currentState.length; i++)
            for (var j = 0; j < currentState[i].length; j++)
                if (currentState[i][j] != previousState[i][j])
                    return true;
        return false;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public int getNumberOfOccupiedSeats() {
        return Arrays.stream(currentState)
                .flatMap(i -> Arrays.stream(i.clone()))
                .map(i -> i == SeatState.OCCUPIED ? 1 : 0)
                .reduce(Integer::sum)
                .get();
    }

    private static SeatState processNextStateForIndex(SeatState[][] currentState, int i, int j) throws Exception {
        switch (currentState[i][j]) {
            case EMPTY:
                if (numberOfAdjacentOccupiedSeats(currentState, i, j) == 0)
                    return SeatState.OCCUPIED;
                else
                    return SeatState.EMPTY;
            case OCCUPIED:
                if (numberOfAdjacentOccupiedSeats(currentState, i, j) > 3)
                    return SeatState.EMPTY;
                else
                    return SeatState.OCCUPIED;
            case FLOOR:
                return SeatState.FLOOR;
            default:
                throw new Exception("Invalid state");
        }
    }

    private static int numberOfAdjacentOccupiedSeats(SeatState[][] currentState, int i, int j) {
        var totalOccupied = 0;

        // check top
        if (i > 0 && currentState[i-1][j] == SeatState.OCCUPIED) totalOccupied++;
        // check bottom
        if (i < currentState.length - 1 && currentState[i+1][j] == SeatState.OCCUPIED) totalOccupied++;

        // check right
        if (j < currentState[i].length - 1 && currentState[i][j+1] == SeatState.OCCUPIED) totalOccupied++;

        // check left
        if (j > 0 && currentState[i][j-1] == SeatState.OCCUPIED) totalOccupied++;

        // check corners
        /// top left
        if (i > 0 && j > 0 && currentState[i-1][j-1] == SeatState.OCCUPIED) totalOccupied++;

        /// top right
        if (i > 0 && j < currentState[i].length - 1 && currentState[i-1][j+1] == SeatState.OCCUPIED) totalOccupied++;

        /// bottom left
        if (i < currentState.length - 1 && j > 0 && currentState[i+1][j-1] == SeatState.OCCUPIED) totalOccupied++;

        /// bottom right
        if (i < currentState.length - 1 && j < currentState[i].length - 1 && currentState[i+1][j+1] == SeatState.OCCUPIED) totalOccupied++;

        return totalOccupied;
    }

    private static SeatState[] parseSeatLine(char[] line) {
        val retVal = new SeatState[line.length];
        for (int i = 0; i < line.length; i++) {
            char j = line[i];
            switch (j) {
                case '.':
                    retVal[i] = SeatState.FLOOR;
                    break;
                case 'L':
                    retVal[i] = SeatState.EMPTY;
                    break;
                case '#':
                    retVal[i] = SeatState.OCCUPIED;
                    break;
            }
        }
        return retVal;
    }
}
