package xyz.tahakhan.AdventOfCode2020.Day11.Logic;

import lombok.val;
import lombok.var;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class SeatingSystem {
    private SeatState[][] previousState;
    private SeatState[][] currentState;
    private VisibilityMethod visibilityMethod;

    public SeatingSystem(List<String> input, VisibilityMethod visibilityMethod) {
        previousState = null;
        currentState = new SeatState[input.size()][];
        for (int i = 0; i < input.size(); i++)
            currentState[i] = parseSeatLine(input.get(i).toCharArray());

        this.visibilityMethod = visibilityMethod;
    }
    public void processNextState() throws Exception {
        val nextState = new SeatState[currentState.length][];
        for(var i = 0; i < currentState.length; i++) {
            nextState[i] = new SeatState[currentState[i].length];
            for(var j = 0; j < currentState[i].length; j++) {
                nextState[i][j] = processNextStateForIndex(currentState, i, j, visibilityMethod);
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

    private static SeatState processNextStateForIndex(SeatState[][] currentState, int i, int j, VisibilityMethod visibilityMethod) throws Exception {
        Callable<Integer> occupiedSeatResolver;
        int maxNumberOfVisibleOccupiedSeats;
        switch (visibilityMethod) {
            case PART_ONE:
                occupiedSeatResolver = () -> numberOfAdjacentOccupiedSeatsPartOne(currentState, i, j);
                maxNumberOfVisibleOccupiedSeats = 4;
                break;
            case PART_TWO:
                occupiedSeatResolver = () -> numberOfAdjacentOccupiedSeatsPartTwo(currentState, i, j);
                maxNumberOfVisibleOccupiedSeats = 5;
                break;
            default:
                throw new Exception("Invalid state");
        }
        switch (currentState[i][j]) {
            case EMPTY:
                if (occupiedSeatResolver.call() == 0)
                    return SeatState.OCCUPIED;
                else
                    return SeatState.EMPTY;
            case OCCUPIED:
                if (occupiedSeatResolver.call() > maxNumberOfVisibleOccupiedSeats - 1)
                    return SeatState.EMPTY;
                else
                    return SeatState.OCCUPIED;
            case FLOOR:
                return SeatState.FLOOR;
            default:
                throw new Exception("Invalid state");
        }
    }


    private static int numberOfAdjacentOccupiedSeatsPartOne(SeatState[][] currentState, int i, int j) {
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

    private static int numberOfAdjacentOccupiedSeatsPartTwo(SeatState[][] currentState, int i, int j) {
        var totalOccupied = 0;

        // check top
        var topI = i;
        while (topI > 0) {
            if (currentState[topI-1][j] == SeatState.OCCUPIED) {
                totalOccupied++;
                break;
            }
            topI--;
        }

        // check bottom
        var bottomI = i;
        while (bottomI < currentState.length - 1) {
            if (currentState[bottomI + 1][j] == SeatState.OCCUPIED) {
                totalOccupied++;
                break;
            }
            bottomI++;
        }

        // check right
        var rightJ = j;
        while (rightJ < currentState[i].length - 1) {
            if (currentState[i][rightJ + 1] == SeatState.OCCUPIED) {
                totalOccupied++;
                break;
            }
            rightJ++;
        }

        // check left
        var leftJ = j;
        while (leftJ > 0) {
            if (currentState[i][leftJ - 1] == SeatState.OCCUPIED) {
                totalOccupied++;
                break;
            }
            leftJ--;
        }

        // check corners
        /// top left
        var topLeftI = i;
        var topLeftJ = j;
        while (topLeftI > 0 && topLeftJ > 0) {
            if (currentState[topLeftI - 1][topLeftJ - 1] == SeatState.OCCUPIED) {
                totalOccupied++;
                break;
            }
            topLeftI--;
            topLeftJ--;
        }

        /// top right
        var topRightI = i;
        var topRightJ = j;
        while (topRightI > 0 && topRightJ < currentState[i].length - 1) {
            if (currentState[topRightI - 1][topRightJ + 1] == SeatState.OCCUPIED) {
                totalOccupied++;
                break;
            }
            topRightI--;
            topRightJ++;
        }

        /// bottom left
        var bottomLeftI = i;
        var bottomLeftJ = j;
        while (bottomLeftI < currentState.length - 1 && bottomLeftJ > 0) {
            if (currentState[bottomLeftI + 1][bottomLeftJ - 1] == SeatState.OCCUPIED) {
                totalOccupied++;
                break;
            }
            bottomLeftI++;
            bottomLeftJ--;
        }

        /// bottom right
        var bottomRightI = i;
        var bottomRightJ = j;
        while (bottomRightI < currentState.length - 1 && bottomRightJ < currentState[i].length - 1) {
            if (currentState[bottomRightI + 1][bottomRightJ + 1] == SeatState.OCCUPIED) {
                totalOccupied++;
                break;
            }
            bottomRightI++;
            bottomRightJ++;
        }

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

