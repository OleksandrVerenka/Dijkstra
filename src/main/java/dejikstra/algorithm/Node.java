package main.java.dejikstra.algorithm;

import java.util.LinkedList;
import java.util.Objects;

public class Node {
    private int row;
    private int col;
    private double dist;
    private LinkedList<Node> shortestPath = new LinkedList<>();

    public Node(int row, int col, double dist) {
        this.row = row;
        this.col = col;
        this.dist = dist;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public LinkedList<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedList<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return row == node.getRow() && col == node.getCol();
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
