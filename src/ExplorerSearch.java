import java.util.ArrayList;
import java.util.List;

public class ExplorerSearch {

    /**
     * Returns how much land area an explorer can reach on a rectangular island.
     * 
     * The island is represented by a rectangular int[][] that contains
     * ONLY the following nunbers:
     * 
     * '0': represents the starting location of the explorer
     * '1': represents a field the explorer can walk through
     * '2': represents a body of water the explorer cannot cross
     * '3': represents a mountain the explorer cannot cross
     * 
     * The explorer can move one square at a time: up, down, left, or right.
     * They CANNOT move diagonally.
     * They CANNOT move off the edge of the island.
     * They CANNOT move onto a a body of water or mountain.
     * 
     * This method should return the total number of spaces the explorer is able
     * to reach from their starting location. It should include the starting
     * location of the explorer.
     * 
     * For example
     * 
     * @param island the locations on the island
     * @return the number of spaces the explorer can reach
     */
    public static int reachableArea(int[][] island) {
        int[] start = explorerLocation(island);
        boolean[][] visited = new boolean[island.length][island[0].length];
        return reachableArea(island, visited, start);
    }

    public static int reachableArea(int[][] island, boolean[][] visited, int[] current) {
        int curRow = current[0];
        int curColumn = current[1];

        int landCount = 1;

        if (visited[curRow][curColumn]) return landCount;
        visited[curRow][curColumn] = true;

        List<int[]> neighbors = possibleMoves(island, current);
        for (int[] neighbor : neighbors) {
            if (!visited[neighbor[0]][neighbor[1]]) {
                landCount += reachableArea(island, visited, neighbor);
            }
        }

        return landCount;
    }

    public static List<int[]> possibleMoves(int[][] island, int[] current) {
        int curRow = current[0];
        int curColumn = current[1];

        int newRow, newColumn;
        List<int[]> possible = new ArrayList<>();

        // up

        newRow = curRow -1;
        newColumn = curColumn;

        if (newRow >= 0 && island[newRow][newColumn] != 2 && island[newRow][newColumn] != 3) {
            possible.add(new int[]{newRow, newColumn});
        }

        // down

        newRow = curRow + 1;
        newColumn = curColumn;

        if (newRow < island.length && island[newRow][newColumn] != 2 && island[newRow][newColumn] != 3) {
            possible.add(new int[]{newRow, newColumn});
        }

        // left

        newRow = curRow;
        newColumn = curColumn - 1;

        if (newColumn >= 0 && island[newRow][newColumn] != 2 && island[newRow][newColumn] != 3) {
            possible.add(new int[]{newRow, newColumn});
        }

        // right

        newRow = curRow;
        newColumn = curColumn + 1;

        if (newColumn < island[newRow].length && island[newRow][newColumn] != 2 && island[newRow][newColumn] != 3) {
            possible.add(new int[]{newRow, newColumn});
        }

        return possible;
    }

    public static int[] explorerLocation(int[][] island) {
        for (int row = 0; row < island.length; row++) {
            for (int column = 0; column < island[row].length; column++) {
                if (island[row][column] == 0) {
                    return new int[]{row, column};
                }
            }
        }

        throw new IllegalArgumentException("Your island does not have an explorer!");
    }
}
