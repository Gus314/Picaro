package mapgeneration;

import control.Controller;
import enums.Edge;
import mapgeneration.cellfillers.RoomCell;
import mapgeneration.cellfillers.RoomCellFactory;

public class RoomGraph
{
    private int rows;
    private int columns;
    private int getNumRCells(){return rows / cellRows;}
    private int getNumCCells(){return columns / cellColumns;}
    private int cellRows;
    private int cellColumns;
    private RoomCellFactory roomCellFactory;

    public RoomGraph(int inRows, int inColumns, int inCellRows, int inCellColumns, int inProperRoomChance)
    {
        rows = inRows;
        columns = inColumns;
        cellRows = inCellRows;
        cellColumns = inCellColumns;
        roomCellFactory = new RoomCellFactory(inProperRoomChance);
    }

    public int getRows(){return rows;}

    public int getColumns(){return columns;}

    public int reachableCount(boolean[][] reachable) {
        int result = 0;
        for (int i = 0; i < getNumRCells(); i++) {
            for (int j = 0; j < getNumCCells(); j++) {
                if (reachable[i][j]) {
                    result++;
                }
            }
        }
        return result;
    }

    public void reachabilityRun(boolean[][] reachable, RoomCell[][] cells) {
        for (int i = 0; i < getNumRCells()+getNumCCells(); i++) {
            reachabilityPass(reachable, cells);
        }
    }

    public void reachabilityPass(boolean[][] reachable, RoomCell[][] cells) {
        for (int i = 0; i < getNumRCells(); i++) {
            for (int j = 0; j < getNumCCells(); j++) {
                if (reachable[i][j]) {
                    break;
                }
                if ((i > 0) && cells[i][j].isConnected(Edge.TOP)) {
                    if (cells[i - 1][j].isConnected(Edge.BOTTOM) && reachable[i - 1][j]) {
                        reachable[i][j] = true;
                        break;
                    }
                }
                if ((i < getNumRCells() - 1) && cells[i][j].isConnected(Edge.BOTTOM)) {
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
                if ((j < getNumCCells() - 1) && cells[i][j].isConnected(Edge.RIGHT)) {
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
        int entryRow = Controller.getGenerator().nextInt(getNumRCells());
        int entryColumn = Controller.getGenerator().nextInt(getNumCCells());

        for (int i = 0; i < getNumRCells(); i++) {
            for (int j = 0; j < getNumCCells(); j++) {
                cells[i][j].regenerate();
                reachable[i][j] = false;
            }
        }
        reachable[entryRow][entryColumn] = true;
    }

    public Integer[][] determineLayout()
    {
        Integer[][] result = new Integer[getNumRCells() * cellRows][getNumCCells() * cellColumns];
        for(int i = 0; i < getNumRCells() * cellRows; i++)
        {
            for(int j = 0; j < getNumCCells() * cellColumns; j++)
            {
                result[i][j] = new Integer(0);
            }
        }


       RoomCell[][] cells = new RoomCell[getNumRCells()][getNumCCells()];
       for(int i = 0; i < getNumRCells(); i++)
       {
           for(int j = 0; j < getNumCCells(); j++)
           {
               cells[i][j] = roomCellFactory.construct(cellRows, cellColumns);
           }
       }


       boolean[][] reachable = new boolean[getNumRCells()][getNumCCells()];

        int minReachable = (getNumCCells() * getNumRCells())/3;
        int maxAttempts = (getNumCCells() * getNumRCells()) * 4;
        int currentAttempts = 0;
        while((reachableCount(reachable) < minReachable) && (currentAttempts < maxAttempts))
        {
            reset(reachable, cells);
            reachabilityRun(reachable, cells);
            currentAttempts++;
            System.out.println("attempt = " + currentAttempts);
        }

        for(int i = 0; i < getNumRCells(); i++)
        {
            for(int j = 0; j < getNumCCells(); j++)
            {
                // Ignore mapgeneration.data for unreachable cells.
                if(reachable[i][j])
                {
                    // Remove connections.
                    if(cells[i][j].isConnected(Edge.LEFT) && ((j == 0) || (!reachable[i][j-1])))
                    {
                        cells[i][j].removeConnection(Edge.LEFT);
                    }
                    if(cells[i][j].isConnected(Edge.RIGHT) && ((j == getNumCCells()-1) || (!reachable[i][j+1])))
                    {
                        cells[i][j].removeConnection(Edge.RIGHT);
                    }
                    if(cells[i][j].isConnected(Edge.TOP) && ((i == 0) || (!reachable[i-1][j])))
                    {
                        cells[i][j].removeConnection(Edge.TOP);
                    }
                    if(cells[i][j].isConnected(Edge.BOTTOM) && ((i == getNumRCells()-1) || (!reachable[i+1][j])))
                    {
                        cells[i][j].removeConnection(Edge.BOTTOM);
                    }
                    cells[i][j].addFloor();
                    for (int row = 0; row < cellRows; row++) {
                        for (int column = 0; column < cellColumns; column++) {
                            result[(i * cellRows) + row][(j * cellColumns) + column] = cells[i][j].getData()[row][column];
                        }
                    }
                }
            }
        }

        return result;
    }
}
