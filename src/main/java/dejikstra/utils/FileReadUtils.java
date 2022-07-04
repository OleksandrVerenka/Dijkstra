package main.java.dejikstra.utils;

import main.java.dejikstra.exceptions.GridIsEmptyException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileReadUtils {

    private FileReadUtils() {
    }

    public static char[][] readFileToGrid(String path) throws IOException, GridIsEmptyException {
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        char[][] grid = getGrid(bufferedReader);
        bufferedReader.close();
        fileReader.close();

        fileReader = new FileReader(file);
        bufferedReader = new BufferedReader(fileReader);
        grid = fillGrid(bufferedReader, grid);
        bufferedReader.close();
        fileReader.close();

        return grid;
    }

    private static char[][] fillGrid(BufferedReader reader, char[][] grid) throws IOException {

        if (grid == null) return null;
        String line = reader.readLine();
        int row = 0;
        while (line != null) {
            char[] chars = line.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                grid[row][i] = chars[i];
            }
            line = reader.readLine();
            row++;
        }

        return grid;
    }

    private static char[][] getGrid(BufferedReader reader) throws GridIsEmptyException {
        int rowCount = reader.lines().findFirst().orElse("").length();
        int columnCount = (int) reader.lines().count() + 1;

        if (rowCount == 0) {
            throw new GridIsEmptyException("Grid is empty");
        }

        return new char[columnCount][rowCount];
    }
}
