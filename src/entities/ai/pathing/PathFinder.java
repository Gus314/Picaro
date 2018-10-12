package entities.ai.pathing;

import control.Coordinate;
import entities.Entity;
import enums.Direction;

import java.awt.image.DirectColorModel;
import java.util.*;

public class PathFinder
{
    private control.Map map;
    private int suggestedSearchSize;

    public PathFinder(control.Map inMap, int inSuggestedSearchSize)
    {
        map = inMap;
        suggestedSearchSize = inSuggestedSearchSize;
    }

    public PathInfo findShortestPath(Entity source, Entity target)
    {
        PathInfo attemptedPath = findPath(source, target);
        return (attemptedPath instanceof ValidPathInfo) ? attemptedPath : new InvalidPathInfo();
    }

    private PathInfo findPath(Entity source, Entity target)
    {
        Coordinate position = new Coordinate(source.getRow(), source.getColumn());
        int searchSize = suggestedSearchSize;

        if(!worthSearching(source, target, searchSize))
        {
            return new InvalidPathInfo();
        }

        // Note that since the searchSize*2 will be even, the search will start one index to the right of the centre.
        int searchRow = searchSize;
        int searchColumn = searchSize;
        Coordinate storeIndex = new Coordinate(searchRow, searchColumn);
        Node[][] nodes = new Node[searchSize*2][searchSize*2];

        Node start = new Node(0, position, storeIndex, target, null);
        nodes[searchRow][searchColumn] = start;

        int maxRow = map.getRows()-1;
        int maxColumn = map.getColumns()-1;

        Collection<Node> starts = new ArrayList<Node>();
        starts.add(start);
        continueSearch(target, nodes, starts, searchSize, maxRow, maxColumn);

        java.util.Map<Coordinate, Integer> distanceMapping = obtainDistanceMapping(nodes);

        for(Coordinate coord: distanceMapping.keySet())
        {
            if(distanceMapping.get(coord) == 1)
            {
                // Avoid moving on top of the target.
                boolean onTopOfTarget = coord.getRow() == target.getRow() && coord.getColumn() == target.getColumn();
                if(!onTopOfTarget)
                {
                    return new ValidPathInfo(coord, findFurtherPosition(position, coord, map));
                }
            }
        }

        return new InvalidPathInfo();
    }

    private static Coordinate findFurtherPosition(Coordinate source, Coordinate closer, control.Map map)
    {
        // Attempt to find a passable point further away from the target. If none exists then choose a passable
        // point at random (assuming at least one exists), otherwise return current position.
        Direction towards = source.determineDirection(closer);
        Direction away = towards.opposite();

        Coordinate awayCoord = source.move(away);
        boolean passable = Entity.passable(map.atPosition(awayCoord.getRow(), awayCoord.getColumn()));

        if(passable)
        {
            return awayCoord;
        }

        Coordinate halfAwayCoord = new Coordinate(awayCoord.getRow(), source.getColumn());
        passable = Entity.passable(map.atPosition(halfAwayCoord.getRow(), halfAwayCoord.getColumn()));
        if(passable)
        {
            return halfAwayCoord;
        }

        halfAwayCoord = new Coordinate(source.getRow(), awayCoord.getColumn());
        passable = Entity.passable(map.atPosition(halfAwayCoord.getRow(), halfAwayCoord.getColumn()));
        if(passable)
        {
            return halfAwayCoord;
        }

        // Cannot find a 'good' further position, choose a passable point at random.
        for(Direction direction: Direction.values())
        {
            Coordinate newCoord = source.move(direction);
            passable = Entity.passable(map.atPosition(newCoord.getRow(), newCoord.getColumn()));
            if(passable)
            {
                return newCoord;
            }
        }

        // Unable to find any passable position.
        return source;
    }

    private static boolean worthSearching(Entity source, Entity target, int maximumDistance)
    {
        // If the minimum distance without collisions is greater than the maximum distance then it is impossible to find a path.
        int rowChange = Math.abs(source.getRow() - target.getRow());
        int columnChange = Math.abs(source.getColumn() - target.getColumn());
        int minimumDistance = Math.max(rowChange, columnChange);// Account for diagonal moves.

        return minimumDistance <= maximumDistance;
    }

    private static java.util.Map<Coordinate, Integer> obtainDistanceMapping(Node[][] nodes)
    {
        java.util.Map<Coordinate, Integer> result = new HashMap<Coordinate, Integer>();
        for(int i = 0; i < nodes.length; i++)
        {
            for(int j = 0; j < nodes[0].length; j++)
            {
                Node current = nodes[i][j];
                if(current != null && current.onGoodPath)
                {

                    result.put(current.position, current.distance);
                }
            }
        }

        return result;
    }


    private static boolean isSuitable(Entity target, Coordinate position)
    {
        return (target.getRow() == position.getRow()) && (target.getColumn() == position.getColumn());
    }

    private boolean checkThisPosition(Node current)
    {
        return current.suitable;
    }

    public boolean continueSearch(Entity target, Node[][] nodes, Collection<Node> currents, int maxDistance, int maxRow, int maxColumn)
    {
        for(Node current: currents)
        {
            if(current.distance > maxDistance)
            {
                return false;
            }

            if(checkThisPosition(current))
            {
                current.onGoodPath = true;
                Node prev = current.previous;
                while(prev != null)
                {
                    prev.onGoodPath = true;
                    prev = prev.previous;
                }
                return true;
            }
        }

        Collection<Node> collatedNexts = new HashSet<Node>();
        for(Node current: currents)
        {
            Collection<Coordinate> nexts = newAdjacentPositions(current.position, current.storeIndex, nodes, maxRow, maxColumn);
            for(Coordinate next: nexts)
            {
                int rowDifference = next.getRow() - current.position.getRow();
                int columnDifference = next.getColumn() - current.position.getColumn();
                int nextRow = current.storeIndex.getRow() + rowDifference ;
                int nextColumn = current.storeIndex.getColumn() + columnDifference;

                Coordinate storeIndex = new Coordinate(nextRow, nextColumn);
                boolean impassable = !Entity.passable(map.atPosition(current.position.getRow(), current.position.getColumn()));
                if(impassable && current.distance != 0) // Initial search node will be on source entity.
                {
                    continue;
                }

                if(nodes[storeIndex.getRow()][storeIndex.getColumn()] == null || nodes[storeIndex.getRow()][storeIndex.getColumn()].distance > current.distance+1)
                {
                    Node added =  new Node(current.distance+1, next, storeIndex, target, current);
                    nodes[storeIndex.getRow()][storeIndex.getColumn()] = added;
                    collatedNexts.add(added);
                }
            }
        }


            if((collatedNexts.size() > 0) && continueSearch(target, nodes, collatedNexts, maxDistance, maxRow, maxColumn))
            {
                return true;
            }

        return false;
    }

    private static Collection<Coordinate> newAdjacentPositions(Coordinate source, Coordinate index, Node[][] nodes, int maxRow, int maxColumn)
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
            int rowChange = adjacent.getRow()-sourceRow;
            int columnChange = adjacent.getColumn() - sourceColumn;
            int adjacentIndexRow = index.getRow() + rowChange;
            int adjacentIndexColumn = index.getColumn() + columnChange;

            if(adjacent.getRow() > 0 && adjacent.getRow() < maxRow &&
                    adjacent.getColumn() > 0 && adjacent.getColumn() < maxColumn &&
                    adjacentIndexRow > 0 && adjacentIndexRow < nodes.length &&
                    adjacentIndexColumn > 0 && adjacentIndexColumn < nodes[0].length)
            {
                boolean notPresent = (nodes[adjacentIndexRow][adjacentIndexColumn] == null);
                boolean closer = (!notPresent) && (nodes[adjacentIndexRow][adjacentIndexColumn].distance > (nodes[index.getRow()][index.getColumn()].distance));
                if(notPresent || closer)
                {
                    results.add(adjacent);
                }
            }
        }

        return results;
    }

    public static class Node
    {
        private int distance;
        private Coordinate position;
        private Coordinate storeIndex;
        private Node previous;
        boolean suitable;
        boolean onGoodPath;

        public Node(int inDistance, Coordinate inPosition, Coordinate inStoreIndex, Entity target, Node inPrevious)
        {
            distance = inDistance;
            position = inPosition;
            storeIndex = inStoreIndex;
            previous = inPrevious;
            suitable = isSuitable(target, inPosition);
            onGoodPath = false;
        }
    }
}
