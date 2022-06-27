package com.mygdx.dsav.DataStructs;

import java.util.LinkedList;

class GraphNode {
    public String value;
    public LinkedList<Integer> connections;
    public LinkedList<Integer> weights;

    /** Creates a Graph Node (used by the Graph class) */
    public GraphNode(String val) {
        value = val;
        connections = new LinkedList<Integer>();
        weights = new LinkedList<Integer>();
    }

    /** String representation of the node. */
    public String toString(boolean isWeighted, LinkedList<GraphNode> nodes) {
        String s = "`"+value+"`:{";

        for (int i = 0; i < connections.size(); i++) {
            int connection = connections.get(i);
            s += connection+":`"+nodes.get(connection).value+"`";

            if (isWeighted) {
                s += ":"+weights.get(i);
            }

            if (!(connection == connections.getLast())) {
                s += ", ";
            }
        }

        s += "}";
        return s;
    }
}


public class Graph {
    public boolean isWeighted;
    public boolean isDirected;
    private LinkedList<GraphNode> nodes;
    
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
            if (!isDirected) {
                nodes.get(ind2).connections.add(ind1);
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
}
