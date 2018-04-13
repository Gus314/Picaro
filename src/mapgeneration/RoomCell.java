package mapgeneration;

import control.Controller;
import enums.Edge;
import enums.FloorType;

import java.util.HashMap;
import java.util.Map;

public class RoomCell
{
    private static final int cellSize = 10;
    private Map<Edge, Boolean> connection;
    private int[][] data;

    public RoomCell()
    {
        connection = new HashMap<Edge, Boolean>();
        data = new int[cellSize][cellSize];
    }

    public int[][] getData()
    {
        return data;
    }

    public void addFloor()
    {
        // Change floor from 1 to 2 to indicate reachability.
        for(int i = 0; i < cellSize; i++)
        {
            for(int j = 0; j < cellSize; j++)
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
                for(int j = 0; j < cellSize; j++)
                {
                    data[0][j] = FloorType.EMPTY.getValue();
                }
                break;
            }
            case BOTTOM:
            {
                for(int j = 0; j < cellSize; j++)
                {
                    data[cellSize-1][j] = FloorType.EMPTY.getValue();
                }
                break;
            }
            case LEFT:
            {
                for(int i = 0; i < cellSize; i++)
                {
                    data[i][0] = FloorType.EMPTY.getValue();
                }
                break;
            }
            case RIGHT:
            {
                for(int i = 0; i < cellSize; i++)
                {
                    data[i][cellSize-1] = FloorType.EMPTY.getValue();
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

    public void regenerate()
    {
        // clear
        for(int i = 0; i < cellSize; i++)
        {
            for(int j = 0; j < cellSize; j++)
            {
                data[i][j] = FloorType.EMPTY.getValue();
            }
        }

        int size = Controller.getGenerator().nextInt(cellSize-6) + 6;
        // Change probability of being a proper room.
        if(Controller.getGenerator().nextInt(4) != 3)
        {
            size = 2;
        }

        if(size % 2 == 1)
        {
            size = size - 1;
        }

        // fill centre
        int centre = cellSize / 2;
        int radius = size / 2;
        for(int i = 0; i < radius; i++)
        {
            for(int a = centre - i; a < centre + i; a++)
            {
                for(int b = centre - i; b < centre + i; b++)
                {
                    data[a][b] = FloorType.OUTSIDE.getValue();
                }
            }
        }

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

        // Connect edges to centre.
        if(connection.get(Edge.LEFT))
        {
            for(int i = 0; i <= centre; i++)
            {
                data[centre][i] = FloorType.OUTSIDE.getValue();
            }
        }
        if(connection.get(Edge.RIGHT))
        {
            for(int i = cellSize-1; i >= centre; i--)
            {
                data[centre][i] = FloorType.OUTSIDE.getValue();
            }
        }
        if(connection.get(Edge.TOP))
        {
            for(int i = 0; i <= centre; i++)
            {
                data[i][centre] = FloorType.OUTSIDE.getValue();
            }
        }
        if(connection.get(Edge.BOTTOM))
        {
            for(int i = cellSize-1; i >= centre; i--)
            {
                data[i][centre] = FloorType.OUTSIDE.getValue();
            }
        }
    }

}
