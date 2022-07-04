package main.java.dejikstra.utils;

import main.java.dejikstra.algorithm.Node;

import java.util.LinkedList;

public class PrintUtils {
    private PrintUtils() {
    }

    public static void printGrid(char[][] grid, Node shortestPathNode) {
        int rowLength = grid.length;
        int columnLength = grid[0].length;
        LinkedList<Node> shortestPath = shortestPathNode.getShortestPath();
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                int finalI = i;
                int finalJ = j;
                if (shortestPath.stream().anyMatch(node -> node.getRow() == finalI && node.getCol() == finalJ) && grid[i][j] != 'S') {
                    System.out.print('+');
                } else {
                    System.out.print(grid[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println("\nPath weight is : " + shortestPathNode.getDist());
    }
}
