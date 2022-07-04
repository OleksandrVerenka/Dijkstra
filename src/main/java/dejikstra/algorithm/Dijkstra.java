package main.java.dejikstra.algorithm;

import main.java.dejikstra.exceptions.PointNotFoundException;
import main.java.dejikstra.utils.PrintUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Dijkstra {

    public void findAndPrintShortestPath(char[][] grid) throws PointNotFoundException {
        Node start = findPoint(grid, 'S');
        Node end = findPoint(grid, 'E');

        if (Objects.isNull(start)) {
            throw new PointNotFoundException("Start point not found");
        }
        if (Objects.isNull(end)) {
            throw new PointNotFoundException("End point not found");
        }

        List<Node> unvisitedItems = new LinkedList<>();
        List<Node> visitedItems = new LinkedList<>();
        unvisitedItems.add(new Node(start.getRow(), start.getCol(), 0));

        while (!unvisitedItems.isEmpty()) {
            Node node = findClosestNode(unvisitedItems, end);
            unvisitedItems.remove(node);

            if (grid[node.getRow()][node.getCol()] == 'E') {
                PrintUtils.printGrid(grid, node);
                return;
            }

            moveUp(node, node.getRow(), node.getCol(), node.getDist(), grid, visitedItems, unvisitedItems);
            moveDown(node, node.getRow(), node.getCol(), node.getDist(), grid, visitedItems, unvisitedItems);
            moveLeft(node, node.getRow(), node.getCol(), node.getDist(), grid, visitedItems, unvisitedItems);
            moveRight(node, node.getRow(), node.getCol(), node.getDist(), grid, visitedItems, unvisitedItems);
            moveUpLeft(node, node.getRow(), node.getCol(), node.getDist(), grid, visitedItems, unvisitedItems);
            moveUpRight(node, node.getRow(), node.getCol(), node.getDist(), grid, visitedItems, unvisitedItems);
            moveDownLeft(node, node.getRow(), node.getCol(), node.getDist(), grid, visitedItems, unvisitedItems);
            moveDownRight(node, node.getRow(), node.getCol(), node.getDist(), grid, visitedItems, unvisitedItems);

            visitedItems.add(node);
        }

        System.out.println("No path found from S to E");
    }

    private Node findPoint(char[][] grid, char c) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == c) {
                    return new Node(i, j, 0);
                }
            }
        }
        return null;
    }

    private void move(Node fromNode, int row, int col, double dist, char[][] grid, List<Node> visitedItems, List<Node> unvisitedItems) {
        if (!isMoveValid(row, col, grid) || containsItem(visitedItems, row, col)) {
            return;
        }

        Optional<Node> optItem = findItem(unvisitedItems, row, col);
        Node toNode;

        if (optItem.isPresent()) {
            toNode = optItem.get();
        } else {
            toNode = new Node(row, col, dist);
            unvisitedItems.add(toNode);
        }

        if (toNode.getDist() >= dist) {
            toNode.setDist(dist);
            LinkedList<Node> shortestPath = new LinkedList<>(fromNode.getShortestPath());
            shortestPath.add(fromNode);
            toNode.setShortestPath(shortestPath);
        }
    }

    private void moveUp(Node fromNode, int row, int col, double dist, char[][] grid, List<Node> visitedItems, List<Node> unvisitedItems) {
        move(fromNode, row - 1, col, dist + 1, grid, visitedItems, unvisitedItems);
    }

    private void moveDown(Node fromNode, int row, int col, double dist, char[][] grid, List<Node> visitedItems, List<Node> unvisitedItems) {
        move(fromNode, row + 1, col, dist + 1, grid, visitedItems, unvisitedItems);
    }

    private void moveLeft(Node fromNode, int row, int col, double dist, char[][] grid, List<Node> visitedItems, List<Node> unvisitedItems) {
        move(fromNode, row, col - 1, dist + 1, grid, visitedItems, unvisitedItems);
    }

    private void moveRight(Node fromNode, int row, int col, double dist, char[][] grid, List<Node> visitedItems, List<Node> unvisitedItems) {
        move(fromNode, row, col + 1, dist + 1, grid, visitedItems, unvisitedItems);
    }

    private void moveUpLeft(Node fromNode, int row, int col, double dist, char[][] grid, List<Node> visitedItems, List<Node> unvisitedItems) {
        move(fromNode, row - 1, col - 1, dist + 1.414, grid, visitedItems, unvisitedItems);
    }

    private void moveUpRight(Node fromNode, int row, int col, double dist, char[][] grid, List<Node> visitedItems, List<Node> unvisitedItems) {
        move(fromNode, row - 1, col + 1, dist + 1.414, grid, visitedItems, unvisitedItems);
    }

    private void moveDownLeft(Node fromNode, int row, int col, double dist, char[][] grid, List<Node> visitedItems, List<Node> unvisitedItems) {
        move(fromNode, row + 1, col - 1, dist + 1.414, grid, visitedItems, unvisitedItems);
    }

    private void moveDownRight(Node fromNode, int row, int col, double dist, char[][] grid, List<Node> visitedItems, List<Node> unvisitedItems) {
        move(fromNode, row + 1, col + 1, dist + 1.414, grid, visitedItems, unvisitedItems);
    }

    private boolean containsItem(List<Node> nodes, int row, int col) {
        return nodes.stream().anyMatch(node -> node.getRow() == row && node.getCol() == col);
    }

    private Optional<Node> findItem(List<Node> nodes, int row, int col) {
        return nodes.stream().filter(node -> node.getRow() == row && node.getCol() == col).findFirst();
    }

    private boolean isMoveValid(int x, int y, char[][] grid) {
        return x >= 0 && y >= 0 && x < grid.length
                && y < grid[0].length && grid[x][y] != '#';
    }

    private double findDirectDistance(int i1, int j1, int i2, int j2) {
        int height = Math.abs(i1 - i2);
        int width = Math.abs(j1 - j2);
        return Math.sqrt(Math.pow(height, 2) + Math.pow(width, 2));
    }

    private Node findClosestNode(List<Node> unvisitedItems, Node endNode) {
        double shortestDirectDistance = Double.MAX_VALUE;
        Node shortestDistanceNode = null;
        for (Node n : unvisitedItems) {
            double directDistance = findDirectDistance(n.getRow(), n.getCol(), endNode.getRow(), endNode.getCol()) + n.getDist();
            if (shortestDirectDistance > directDistance) {
                shortestDirectDistance = directDistance;
                shortestDistanceNode = n;
            }
        }
        return shortestDistanceNode;
    }
}
