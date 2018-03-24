package mapgeneration;

import java.util.Random;

public class RoomGraph {
    private static final int numCells = 5;
    private static final Random generator = new Random();

    public RoomGraph() {

    }

    public int reachableCount(boolean[][] reachable) {
        int result = 0;
        for (int i = 0; i < numCells; i++) {
            for (int j = 0; j < numCells; j++) {
                if (reachable[i][j]) {
                    result++;
                }
            }
        }
        return result;
    }

    public void reachabilityRun(boolean[][] reachable, RoomCell[][] cells) {
        for (int i = 0; i < 50; i++) {
            reachabilityPass(reachable, cells);
        }
    }

    public void reachabilityPass(boolean[][] reachable, RoomCell[][] cells) {
        for (int i = 0; i < numCells; i++) {
            for (int j = 0; j < numCells; j++) {
                if (reachable[i][j]) {
                    break;
                }
                if ((i > 0) && cells[i][j].isConnected(Edge.TOP)) {
                    if (cells[i - 1][j].isConnected(Edge.BOTTOM) && reachable[i - 1][j]) {
                        reachable[i][j] = true;
                        break;
                    }
                }
                if ((i < numCells - 1) && cells[i][j].isConnected(Edge.BOTTOM)) {
                    if (cells[i + 1][j].isConnected(Edge.TOP) && reachable[i + 1][j]) {
                        reachable[i][j] = true;
                        break;
                    }
                }
                if ((j > 0) && cells[i][j].isConnected(Edge.LEFT)) {
                    if (cells[i][j - 1].isConnected(Edge.RIGHT) && reachable[i][j - 1]) {
                        reachable[i][j] = true;
                        break;
                    }
                }
                if ((j < numCells - 1) && cells[i][j].isConnected(Edge.RIGHT)) {
                    if (cells[i][j + 1].isConnected(Edge.LEFT) && reachable[i][j + 1]) {
                        reachable[i][j] = true;
                        break;
                    }
                }
            }
        }
    }

    public void reset(boolean[][] reachable, RoomCell[][] cells)
    {
        int entryRow = generator.nextInt(numCells);
        int entryColumn = generator.nextInt(numCells);

        for (int i = 0; i < numCells; i++) {
            for (int j = 0; j < numCells; j++) {
                cells[i][j].regenerate();
                reachable[i][j] = false;
            }
        }
        reachable[entryRow][entryColumn] = true;
    }

    public int[][] determineLayout()
    {
        final int cellSize = 10;
        int[][] result = new int[numCells * cellSize][numCells * cellSize];

       RoomCell[][] cells = new RoomCell[numCells][numCells];
       for(int i = 0; i < numCells; i++)
       {
           for(int j = 0; j < numCells; j++)
           {
               cells[i][j] = new RoomCell();
           }
       }


       boolean[][] reachable = new boolean[numCells][numCells];

        int minReachable = 15;
        int maxAttempts = 10000;
        int currentAttempts = 0;
        while((reachableCount(reachable) < minReachable) && (currentAttempts < maxAttempts))
        {
            reset(reachable, cells);
            reachabilityRun(reachable, cells);
            currentAttempts++;
            System.out.println("attempt = " + currentAttempts);
        }

        for(int i = 0; i < numCells; i++)
        {
            for(int j = 0; j < numCells; j++)
            {
                // Ignore data for unreachable cells.
                if(reachable[i][j])
                {
                    // Remove connections.
                    if(cells[i][j].isConnected(Edge.LEFT) && ((j == 0) || (!reachable[i][j-1])))
                    {
                        cells[i][j].removeConnection(Edge.LEFT);
                    }
                    if(cells[i][j].isConnected(Edge.RIGHT) && ((j == numCells-1) || (!reachable[i][j+1])))
                    {
                        cells[i][j].removeConnection(Edge.RIGHT);
                    }
                    if(cells[i][j].isConnected(Edge.TOP) && ((i == 0) || (!reachable[i-1][j])))
                    {
                        cells[i][j].removeConnection(Edge.TOP);
                    }
                    if(cells[i][j].isConnected(Edge.BOTTOM) && ((i == numCells-1) || (!reachable[i+1][j])))
                    {
                        cells[i][j].removeConnection(Edge.BOTTOM);
                    }
                    cells[i][j].addFloor();
                    for (int row = 0; row < cellSize; row++) {
                        for (int column = 0; column < cellSize; column++) {
                            result[(i * cellSize) + row][(j * cellSize) + column] = cells[i][j].getData()[row][column];
                        }
                    }
                }
            }
        }

        return result;
    }
}
