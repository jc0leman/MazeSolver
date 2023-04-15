import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     *
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // Initialize an empty ArrayList to store the solution
        ArrayList<MazeCell> solution = new ArrayList<>();

        // Start at the end cell and follow the parent links back to the start cell
        MazeCell cur = maze.getEndCell();
        while (cur != null) {
            solution.add(0, cur); // add the current cell to the front of the list
            cur = cur.getParent(); // move to the parent cell
        }

        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     *
     * @return An ArrayList of MazeCells in order from the start to end cell
     */

    //solves the maze using Depth First Search
    public ArrayList<MazeCell> solveMazeDFS() {
        Stack<MazeCell> visitOrder = new Stack<>();
        MazeCell cur = maze.getStartCell();
        //cals the method to check the DFS
        solveDFS(visitOrder, cur);

        // Convert the Stack to an ArrayList and return it
        ArrayList<MazeCell> solution = new ArrayList<>(visitOrder);
        return solution;
    }

    private void solveDFS(Stack<MazeCell> visitOrder, MazeCell cur) {
        // Base case: if the current cell is the end cell, return
        if (cur == maze.getEndCell()) {
            return;
        }

        //temp variables designed to represent index of the location
        int row = cur.getRow();
        int col = cur.getCol();
        cur.setExplored(true);

        // Explore the neighboring cells in the order: NORTH, EAST, SOUTH, WEST
        if (maze.isValidCell(row - 1, col)) {
            MazeCell north = maze.getCell(row - 1, col);
            if (!north.isExplored()) {
                visitOrder.push(north);
                north.setParent(cur);
                solveDFS(visitOrder, north);
            }
        }
        if (maze.isValidCell(row, col + 1)) {
            MazeCell east = maze.getCell(row, col + 1);
            if (!east.isExplored()) {
                visitOrder.push(east);
                east.setParent(cur);
                solveDFS(visitOrder, east);
            }
        }
        if (maze.isValidCell(row + 1, col)) {
            MazeCell south = maze.getCell(row + 1, col);
            if (!south.isExplored()) {
                visitOrder.push(south);
                south.setParent(cur);
                solveDFS(visitOrder, south);
            }
        }
        if (maze.isValidCell(row, col - 1)) {
            MazeCell west = maze.getCell(row, col - 1);
            if (!west.isExplored()) {
                visitOrder.push(west);
                west.setParent(cur);
                solveDFS(visitOrder, west);
            }
        }
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     *
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        //declares the queue instead of the stack to solve BFS
        Queue<MazeCell> visitOrder = new LinkedList<>();
        MazeCell cur = maze.getStartCell();
        visitOrder.add(cur);
        //repeats while not empty
        while (!visitOrder.isEmpty()) {
            MazeCell current = visitOrder.poll();
            if (current.equals(maze.getEndCell())) {
                return getSolution();
            }
            int row = current.getRow();
            int col = current.getCol();
            current.setExplored(true);

            // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
            if (maze.isValidCell(row - 1, col)) {  // NORTH
                MazeCell neighbor = maze.getCell(row - 1, col);
                if (!neighbor.isExplored() && !neighbor.isWall()) {
                    visitOrder.add(neighbor);
                    neighbor.setParent(current);
                }
            }
            if (maze.isValidCell(row, col + 1)) {  // EAST
                MazeCell neighbor = maze.getCell(row, col + 1);
                if (!neighbor.isExplored() && !neighbor.isWall()) {
                    visitOrder.add(neighbor);
                    neighbor.setParent(current);
                }
            }
            if (maze.isValidCell(row + 1, col)) {  // SOUTH
                MazeCell neighbor = maze.getCell(row + 1, col);
                if (!neighbor.isExplored() && !neighbor.isWall()) {
                    visitOrder.add(neighbor);
                    neighbor.setParent(current);
                }
            }
            if (maze.isValidCell(row, col - 1)) {  // WEST
                MazeCell neighbor = maze.getCell(row, col - 1);
                if (!neighbor.isExplored() && !neighbor.isWall()) {
                    visitOrder.add(neighbor);
                    neighbor.setParent(current);
                }
            }
        }
        return null;
    }
}


