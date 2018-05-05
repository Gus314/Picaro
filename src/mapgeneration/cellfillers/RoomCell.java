package mapgeneration.cellfillers;

import control.Controller;
import enums.Edge;
import enums.FloorType;

import java.util.HashMap;
import java.util.Map;

public class RoomCell
{
    private int rows;
    private int columns;
    private Map<Edge, Boolean> connection;
    private int[][] data;
    private int properRoomChance; // Percentage chance of being a proper room.

    public RoomCell(int inRows, int inColumns, int inProperRoomChance)
    {
        connection = new HashMap<Edge, Boolean>();
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
        return connection.get(edge);
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

    private void determineEdges()
    {

        // connect edges
        boolean retry = true;

        // Determine connections, ensuring at least one.
        do {
            connection.put(Edge.BOTTOM, Controller.getGenerator().nextInt(10) > 3);
            connection.put(Edge.TOP, Controller.getGenerator().nextInt(10) > 3);
            connection.put(Edge.LEFT, Controller.getGenerator().nextInt(10) > 3);
            connection.put(Edge.RIGHT, Controller.getGenerator().nextInt(10) > 3);

            retry = ! (connection.get(Edge.BOTTOM) || connection.get(Edge.TOP) ||
                    connection.get(Edge.LEFT) || connection.get(Edge.RIGHT));
        }while(retry);

    }

    private void connectEdgesToCentre(int centreRow, int centreColumn)
    {
        // Connect edges to centre.
        if(connection.get(Edge.LEFT))
        {
            for(int i = 0; i <= centreColumn; i++)
            {
                data[centreRow][i] = FloorType.OUTSIDE.getValue();
            }
        }
        if(connection.get(Edge.RIGHT))
        {
            for(int i = columns-1; i >= centreColumn; i--)
            {
                data[centreRow][i] = FloorType.OUTSIDE.getValue();
            }
        }
        if(connection.get(Edge.TOP))
        {
            for(int i = 0; i <= centreRow; i++)
            {
                data[i][centreColumn] = FloorType.OUTSIDE.getValue();
            }
        }
        if(connection.get(Edge.BOTTOM))
        {
            for(int i = rows-1; i >= centreRow; i--)
            {
                data[i][centreColumn] = FloorType.OUTSIDE.getValue();
            }
        }
    }

}
