package com.mygdx.dsav.DataStructs;

import java.util.ArrayList;
// import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import com.mygdx.dsav.DijkNodeData;

public class Graph {
    public boolean isWeighted;
    public boolean isDirected;
    public LinkedList<GraphNode> nodes;
    
    /** 
     * Creates a new Graph.
     * @param weighted Boolean: should the graph be weighted.
     * @param directed Boolean: should the graph be directed.
     */
    public Graph(boolean weighted, boolean directed) {
        isWeighted = weighted;
        isDirected = directed;
        nodes = new LinkedList<>();
    }
    /** Creates a new unweighted and undirected Graph. */
    public Graph() {
        isWeighted = false;
        isDirected = false;
        nodes = new LinkedList<>();
    }

    /**
     * Add a node to an existing Graph.
     * @param val String value that the new node should hold.
     */
    public void addNode(String val) {
        nodes.add(new GraphNode(val));
    }

    /**
     * Add a vertex to the graph by referencing two indexes.
     * @param ind1
     * @param ind2
     * @param weight * Parameter is required if Graph is weighted. 
     */
    public void addVertex(int ind1, int ind2) {
        if (!isWeighted) {
            nodes.get(ind1).connections.add(ind2);
            nodes.get(ind1).weights.add(0);
            if (!isDirected) {
                nodes.get(ind2).connections.add(ind1);
                nodes.get(ind2).weights.add(0);
            }
        }
    }
    /**
     * Add a vertex to the graph by referencing two indexes.
     * @param ind1
     * @param ind2
     * @param weight * Parameter is required if Graph is weighted.
     */
    public void addVertex(int ind1, int ind2, int weight) {
        if (isWeighted) {
            nodes.get(ind1).connections.add(ind2);
            nodes.get(ind1).weights.add(weight);
            if (!isDirected) {
                nodes.get(ind2).connections.add(ind1);
                nodes.get(ind2).weights.add(weight);
            }
        }
    }

    /**
     * Toggle vertex.
     * @param ind
     * @return
     */
    public void toggleVertex(int ind1, int ind2, int weight) {
        boolean vertexExists = nodes.get(ind1).connections.contains(ind2);
        if (vertexExists) {
            nodes.get(ind1).removeConnection(ind2);

            if (!isDirected) {
                nodes.get(ind2).removeConnection(ind1);
            }
        } else {
            nodes.get(ind1).connections.add(ind2);
            nodes.get(ind1).weights.add(weight);

            if (!isDirected) {
                nodes.get(ind2).connections.add(ind1);
                nodes.get(ind2).weights.add(weight);               
            }
        }
    }
    public void toggleVertex(int ind1, int ind2) {
        toggleVertex(ind1, ind2, 0);
    }

    /** 
     * Return the value of the node at a given index.
     * @param ind 
     * @return String value of the node.
     */
    public String valOfNodeAt(int ind) {
        return nodes.get(ind).value;
    }

    /** Return a String representation of the Graph with all nodes. */
    public String toString() {
        String s = "[";

        for (int i = 0; i < nodes.size(); i++) {
            GraphNode node = nodes.get(i);

            s += i+":"+node.toString(isWeighted, nodes);

            if (!node.equals(nodes.getLast())) {
                s += ", ";
            }
        }

        s += "]";
        return s;
    }

    public LinkedList<Integer> getConnectedNodes(int start) {
        LinkedList<Integer> countedNodes = new LinkedList<>(Arrays.asList(start));
        int ptr = 0;

        while (ptr < countedNodes.size()) {
            GraphNode n = nodes.get(countedNodes.get(ptr));

            for (int x : n.connections) {
                if (!countedNodes.contains(x)) {
                    countedNodes.add(x);
                }
            }

            ptr++;
        }

        return countedNodes;
    }

    public boolean isConnected() {
        return getConnectedNodes(0).size() == 4;
    }

    public int[] getShortestNewConnection(LinkedList<Integer> visitedNodes) {
        int[] rv = new int[]{-1, -1};
        int shortest = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = i+1; j < 4; j++) {
                boolean iIn = visitedNodes.contains(i);
                boolean jIn = visitedNodes.contains(j);

                if (iIn ^ jIn) {   // XOR; if one is- but the other is not in
                    if (nodes.get(i).connections.contains(j)) {
                        int ptr = 0;
                        while (nodes.get(i).connections.get(ptr) != j) { ptr++; }

                        int weightOfConnection = nodes.get(i).weights.get(ptr);
                        if (weightOfConnection < shortest) {
                            shortest = weightOfConnection;
                            rv[0] = i;
                            rv[1] = j;
                        }
                    }
                }
                
            }
        }

        return rv;
    }

    public int[] getShortestNewCumulativeConnection(ArrayList<Integer> visitedNodes, ArrayList<DijkNodeData> nodeData) {
        int[] rv = new int[]{-1, -1};
        int shortest = Integer.MAX_VALUE;

        boolean shortestIIn = false;

        for (int i = 0; i < 3; i++) {
            for (int j = i+1; j < 4; j++) {
                boolean iIn = visitedNodes.contains(i);
                boolean jIn = visitedNodes.contains(j);

                if (iIn ^ jIn) {   // XOR; if one is- but the other is not in
                    if (nodes.get(i).connections.contains(j)) {
                        int ptr = 0;
                        while (nodes.get(i).connections.get(ptr) != j) { ptr++; }

                        int fromWeight;
                        if (iIn) {
                            fromWeight = nodeData.get(i).minWeight;
                        } else {
                            fromWeight = nodeData.get(j).minWeight;
                        }
                        
                        int weightOfConnection = nodes.get(i).weights.get(ptr) + fromWeight;
                        
                        if (weightOfConnection < shortest) {
                            shortest = weightOfConnection;
                            rv[0] = i;
                            rv[1] = j;
                            shortestIIn = iIn;
                        }
                    }
                }
                
            }
        }

        if (shortestIIn) {
            nodeData.get(rv[1]).minWeight = shortest;
            nodeData.get(rv[1]).prevNode = nodeData.get(0).nodeName;
            visitedNodes.add(rv[1]);
        } else {
            nodeData.get(rv[0]).minWeight = shortest;
            nodeData.get(rv[0]).prevNode = nodeData.get(1).nodeName;
            visitedNodes.add(rv[0]);
        }

        return rv;
    }

    /** 
     * This doesn't seem to work exactly as intended
     * (flag when all weights are the same)
     * but it works for the most important case
     * (flag when all weights are 0)
     * (the default; it flags if the user hasn't changed anything)
     */
    public int getWeightDifference() {
        if (!isConnected()) {
            return -1;
        }

        int max = 0;
        int min = 0;

        for (GraphNode node : nodes) {
            for (int w : node.weights) {
                if (w > max) { max = w; }
                else if (w < min) { min = w; }
            }
        }

        return max - min;
    }
}
