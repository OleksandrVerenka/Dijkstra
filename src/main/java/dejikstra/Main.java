package main.java.dejikstra;

import main.java.dejikstra.algorithm.Dijkstra;
import main.java.dejikstra.utils.FileReadUtils;

public class Main {

    public static void main(String[] args) throws Exception {
        String defaultPath = "{YOUR_PATH}" + "/src/main/resources/test.txt";
        String filePath = args.length > 1 ? args[0] : defaultPath;
        char[][] grid = FileReadUtils.readFileToGrid(filePath);
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.findAndPrintShortestPath(grid);
    }
}

