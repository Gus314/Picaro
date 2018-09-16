package entities.ai.pathing;

import control.Coordinate;
import entities.Entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

public class PathFinder
{
    public static PathInfo findPath(Entity source, Entity target, control.Map map, int suggestedSearchSize)
    {
        Coordinate position = new Coordinate(source.getRow(), source.getColumn());
        int searchSize = (suggestedSearchSize % 2 == 0) ? suggestedSearchSize : suggestedSearchSize + 1;

        if(!worthSearching(source, target, searchSize))
        {
            return new InvalidPathInfo();
        }


        int searchRow = searchSize / 2;
        int searchColumn = searchSize / 2;
        Coordinate storeIndex = new Coordinate(searchRow, searchColumn);
        Node[][] nodes = new Node[searchRow][searchColumn];

        Node start = new Node(0, position, storeIndex, target);

        continueSearch(target, map, nodes, start, searchSize);

        java.util.Map<Coordinate, Integer> distanceMapping = obtainDistanceMapping(nodes);
        Coordinate targetPosition = new Coordinate(target.getRow(), target.getColumn());

        for(Coordinate coord: distanceMapping.keySet())
        {
            if(distanceMapping.get(coord) == 1)
            {
                return new ValidPathInfo(coord);
            }
        }

        return new InvalidPathInfo();
    }

    private static boolean worthSearching(Entity source, Entity target, int maximumDistance)
    {
        // If the minimum distance without collisions is greater than the maximum distance then it is impossible to find a path.
        int rowChange = Math.abs(source.getRow() - target.getRow());
        int columnChange = Math.abs(source.getColumn() - target.getColumn());
        int minimumDistance = rowChange+columnChange; // Note: about diagonal moves?

        return minimumDistance <= maximumDistance;
    }

    private static java.util.Map<Coordinate, Integer> obtainDistanceMapping(Node[][] nodes)
    {
        java.util.Map<Coordinate, Integer> result = new HashMap<Coordinate, Integer>();
        for(int i = 0; i < nodes.length; i++)
        {
            for(int j = 0; j < nodes[0].length; j++)
            {
                if(nodes[i][j] != null && nodes[i][j].onGoodPath)
                {
                    result.put(new Coordinate(i, j), nodes[i][j].distance);
                }
            }
        }

        return result;
    }


    private static boolean isSuitable(Entity target, Coordinate position)
    {
        return (target.getRow() == position.getRow()) && (target.getColumn() == position.getColumn());
    }

    public static boolean continueSearch(Entity target, control.Map map, Node[][] nodes, Node current, int maxDistance)
    {
        if(current.suitable)
        {
            current.onGoodPath = true;
            return true;
        }

        // Note that return order is important incase target is at exactly maxDistance;
        if(current.distance == maxDistance)
        {
            return false;
        }

        if(!Entity.passable(map.atPosition(current.position.getRow(), current.position.getColumn())))
        {
            return false;
        }

        Collection<Coordinate> nexts = newAdjacentPositions(current.position, nodes);

        for(Coordinate next: nexts)
        {
            int rowDifference = next.getRow() - current.position.getRow();
            int columnDifference = next.getColumn() - current.position.getColumn();
            Coordinate storeIndex = new Coordinate(current.storeIndex.getRow() + rowDifference, current.storeIndex.getColumn() + columnDifference);

            nodes[storeIndex.getRow()][storeIndex.getColumn()] = new Node(current.distance+1, next, storeIndex, target);
        }

        Collection<Integer> distances = new Vector<Integer>();
        // Wait for all nodes to update before continuing the search.
        for(Coordinate next: nexts)
        {
            int rowDifference = next.getRow() - current.position.getRow();
            int columnDifference = next.getColumn() - current.position.getColumn();
            Coordinate storeIndex = new Coordinate(current.storeIndex.getRow() + rowDifference, current.storeIndex.getColumn() + columnDifference);

            if(continueSearch(target, map, nodes, nodes[storeIndex.getRow()][storeIndex.getColumn()], maxDistance))
            {
                current.onGoodPath = true;
                return true;
            }
        }

        return false;
    }

    private static Collection<Coordinate> newAdjacentPositions(Coordinate source, Node[][] nodes)
    {
        Collection<Coordinate> results = new Vector<Coordinate>();
        int sourceRow = source.getRow();
        int sourceColumn = source.getColumn();

        Collection<Coordinate> adjacents = new Vector<Coordinate>();
        adjacents.add(new Coordinate(sourceRow, sourceColumn-1));
        adjacents.add(new Coordinate(sourceRow, sourceColumn+1));
        adjacents.add(new Coordinate(sourceRow-1, sourceColumn));
        adjacents.add(new Coordinate(sourceRow+1, sourceColumn));
        adjacents.add(new Coordinate(sourceRow-1, sourceColumn-1));
        adjacents.add(new Coordinate(sourceRow-1, sourceColumn+1));
        adjacents.add(new Coordinate(sourceRow+1, sourceColumn-1));
        adjacents.add(new Coordinate(sourceRow+1, sourceColumn+1));

        for(Coordinate adjacent: adjacents)
        {
            if(adjacent.getRow() > 0 && adjacent.getRow() < nodes.length &&
                    adjacent.getColumn() > 0 && adjacent.getColumn() < nodes[0].length &&
                    nodes[adjacent.getRow()][adjacent.getColumn()] == null)
            {
                results.add(adjacent);
            }
        }

        return results;
    }

    public static class Node
    {
        private int distance;
        private Coordinate position;
        private Coordinate storeIndex;
        boolean suitable;
        boolean onGoodPath;

        public Node(int inDistance, Coordinate inPosition, Coordinate inStoreIndex, Entity target)
        {
            distance = inDistance;
            position = inPosition;
            storeIndex = inStoreIndex;
            suitable = isSuitable(target, inPosition);
            onGoodPath = false;
        }
    }
}