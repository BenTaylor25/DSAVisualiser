package com.mygdx.dsav.DataStructs;

import java.util.LinkedList;

public class GraphNode {
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

    public boolean pointsTo(int other) {
        for (int x : connections) {
            if (x == other) {
                return true;
            }
        }
        return false;
    }

    public void removeConnection(int ind) {
        for (int i = 0; i < connections.size(); i++) {
            if (connections.get(i) == ind) {
                connections.remove(i);
                weights.remove(i);
            }
        }
    }
}

