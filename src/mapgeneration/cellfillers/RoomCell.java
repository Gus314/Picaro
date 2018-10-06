package mapgeneration.cellfillers;

import control.Controller;
import entities.Floor;
import enums.Edge;
import enums.FloorType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RoomCell implements Serializable
{
    private int rows;
    private int columns;
    private Map<Edge, CellConnection> connection;
    private int[][] data;
    private int properRoomChance; // Percentage chance of being a proper room.

    public RoomCell(int inRows, int inColumns, int inProperRoomChance)
    {
        connection = new HashMap<Edge, CellConnection>();
        rows = inRows;
        columns = inColumns;
        data = new int[rows][columns];
        properRoomChance = inProperRoomChance;
    }

    public int[][] getData()
    {
        return data;
    }

    public void addFloor()
    {
        // Change floor from 1 to 2 to indicate reachability.
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                if(data[i][j] == FloorType.OUTSIDE.getValue())
                {
                    data[i][j] = FloorType.FLOOR.getValue();
                }
            }
        }
    }

    public void removeConnection(Edge edge)
    {
        // Used to remove unused connections. TODO: Also remove from connection actions?
        switch(edge)
        {
            case TOP:
            {
                for(int j = 0; j < columns; j++)
                {
                    data[0][j] = FloorType.EMPTY.getValue();
                }
                break;
            }
            case BOTTOM:
            {
                for(int j = 0; j < columns; j++)
                {
                    data[rows-1][j] = FloorType.EMPTY.getValue();
                }
                break;
            }
            case LEFT:
            {
                for(int i = 0; i < rows; i++)
                {
                    data[i][0] = FloorType.EMPTY.getValue();
                }
                break;
            }
            case RIGHT:
            {
                for(int i = 0; i < rows; i++)
                {
                    data[i][columns-1] = FloorType.EMPTY.getValue();
                }
                break;
            }
            default:
            {
                System.out.println("RoomCell::removeConnection - unexpected edge.");
            }
        }
    }

    public boolean isConnected(Edge edge)
    {
        return connection.get(edge) instanceof  ValidCellConnection;
    }

    private void clearCell()
    {
        // clear
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                data[i][j] = FloorType.EMPTY.getValue();
            }
        }
    }

    private int determineSize(int cellSize)
    {
        int size = Controller.getGenerator().nextInt(cellSize/2) + (cellSize/2);
        // Change probability of being a proper room.

        if(size % 2 == 1)
        {
            size = size - 1;
        }

        return size;
    }

    public void regenerate()
    {
        clearCell();

        int rowSize = determineSize(rows);
        int columnSize = determineSize(columns);

        if(Controller.getGenerator().nextInt(100)+1 >= properRoomChance)
        {
            rowSize = 2;
            columnSize = 2;
        }

        int centreRow = rows / 2;
        int centreColumn = columns / 2;

        fillCell(rowSize, columnSize, centreRow, centreColumn);

        determineEdges();
        connectEdgesToCentre(centreRow, centreColumn);
    }

    private void fillCell(int rows, int columns, int centreRow, int centreColumn)
    {
        // fill centre
        int rowRadius = (rows+1) / 2;
        int columnRadius = (columns+1) / 2;
        for(int i = 0; i < rowRadius; i++)
        {
            for(int j = 0; j < columnRadius; j++)
            {
                for(int a = centreRow - i; a < centreRow + i; a++)
                {
                    for(int b = centreColumn - j; b < centreColumn + j; b++)
                    {
                        data[a][b] = FloorType.OUTSIDE.getValue();
                    }
                }
            }
        }
    }

    public void addRandomFloors(int randomFloorPercentage)
    {
        double randomFloorPercentageD = (double) randomFloorPercentage;
        double numTiles = rows * columns;
        int newTiles = (int) (numTiles * (randomFloorPercentageD / 100.0));

        int tilesAdded = 0;
        int failedAttempts = 0;

        while((tilesAdded < newTiles) && (failedAttempts < newTiles * 100))
        {
            int row = Controller.getGenerator().nextInt(rows-2) + 1;
            int column = Controller.getGenerator().nextInt(columns-2) + 1;

            int here = data[row][column];
            boolean upOutside = up(row, column) == FloorType.OUTSIDE.getValue();
            boolean downOutside = down(row, column) == FloorType.OUTSIDE.getValue();
            boolean leftOutside = left(row, column) == FloorType.OUTSIDE.getValue();
            boolean rightOutside = right(row, column) == FloorType.OUTSIDE.getValue();
            boolean oneOutside = (upOutside || downOutside || leftOutside || rightOutside);

            // If the tile is not floor but reachable by one that is.
            if((here != FloorType.OUTSIDE.getValue()) && oneOutside)
            {
               data[row][column] = FloorType.OUTSIDE.getValue();
               tilesAdded++;
            }
            else
            {
                failedAttempts++;
            }
        }
    }

    private int up(int row, int column)
    {
        return data[row-1][column];
    }

    private int left(int row, int column)
    {
        return data[row][column-1];
    }

    private int down(int row, int column)
    {
        return data[row+1][column];
    }

    private int right(int row, int column)
    {
        return data[row][column+1];
    }

    private boolean makeConnection()
    {
        return Controller.getGenerator().nextInt(10) > 3;
    }

    private CellConnection obtainCellConnection(int maxOffset)
    {
        return CellConnectionFactory.construct(makeConnection(), maxOffset);
    }

    private boolean atLeastOneConnection()
    {
        for(CellConnection connection: connection.values())
        {
           if(connection instanceof ValidCellConnection)
           {
               return true;
           }
        }

        // No valid connection found, therefore no connections.
        return false;
    }

    private void determineEdges()
    {

        // connect edges
        boolean retry = true;

        // Determine connections, ensuring at least one.
        do {
            int maxRowOffset = (rows/2)-1;
            int maxColumnOffset = (columns/2)-1;

            connection.put(Edge.BOTTOM, obtainCellConnection(maxColumnOffset));
            connection.put(Edge.TOP, obtainCellConnection(maxColumnOffset));
            connection.put(Edge.LEFT, obtainCellConnection(maxRowOffset));
            connection.put(Edge.RIGHT, obtainCellConnection(maxRowOffset));

            retry = !atLeastOneConnection();
        }while(retry);

    }

    private int obtainOffset(Edge edge)
    {
        // Helper method, assumes edge is associated to a valid cell connection.
        return ((ValidCellConnection) connection.get(edge)).getOffset();
    }

    private void connectEdgesToCentre(int centreRow, int centreColumn)
    {
        // Connect edges to centre, with an offset.
        if(connection.get(Edge.LEFT) instanceof  ValidCellConnection)
        {
            int offset = obtainOffset(Edge.LEFT);
            for(int i = 0; i <= centreColumn; i++)
            {
                data[centreRow+offset][i] = FloorType.OUTSIDE.getValue();
            }
        }
        if(connection.get(Edge.RIGHT)  instanceof  ValidCellConnection)
        {
            for(int i = columns-1; i >= centreColumn; i--)
            {
                int offset = obtainOffset(Edge.RIGHT);
                data[centreRow+offset][i] = FloorType.OUTSIDE.getValue();
            }
        }
        if(connection.get(Edge.TOP)  instanceof  ValidCellConnection)
        {
            int offset = obtainOffset(Edge.TOP);
            for(int i = 0; i <= centreRow; i++)
            {
                data[i][centreColumn+offset] = FloorType.OUTSIDE.getValue();
            }
        }
        if(connection.get(Edge.BOTTOM)  instanceof  ValidCellConnection)
        {
            int offset = obtainOffset(Edge.BOTTOM);
            for(int i = rows-1; i >= centreRow; i--)
            {
                data[i][centreColumn+offset] = FloorType.OUTSIDE.getValue();
            }
        }

        fillOuterRing();
    }


    private void fillOuterRing()
    {
        // Offsets mean that outer ring must be floor.
            for(int i = 0; i < columns; i++)
            {
                if(connection.get(Edge.TOP) instanceof ValidCellConnection)
                {
                    data[0][i] = FloorType.OUTSIDE.getValue();
                }
                if(connection.get(Edge.BOTTOM) instanceof  ValidCellConnection)
                {
                    data[rows-1][i] = FloorType.OUTSIDE.getValue();
                }
            }

        for(int j = 0; j < rows; j++)
        {
            if(connection.get(Edge.LEFT) instanceof  ValidCellConnection)
            {
                data[j][0] = FloorType.OUTSIDE.getValue();
            }
            if(connection.get(Edge.RIGHT) instanceof ValidCellConnection)
            {
                data[j][columns-1] = FloorType.OUTSIDE.getValue();
            }
        }
    }
}
