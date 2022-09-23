package com.mygdx.dsav.Generators;

import java.util.ArrayList;

import com.mygdx.dsav.DijkNodeData;
import com.mygdx.dsav.DataStructs.Graph;

public class GDijkstrasAlg {
    public Graph graph;
    public ArrayList<Integer> visitedNodes;
    public Graph visitedGraph;
    public ArrayList<DijkNodeData> nodeData;
    
    public boolean hasNext;
    private boolean hasStarted;
    private int _size;

    public GDijkstrasAlg(int size) {
        _size = size;
        hasNext = true;
        hasStarted = false;
        graph = new Graph(true, false);
        visitedNodes = new ArrayList<>();
        visitedGraph = new Graph();
        nodeData = new ArrayList<>();
        for (int i = 0; i < _size; i++) {
            graph.addNode("");
            visitedGraph.addNode("");
            nodeData.add(new DijkNodeData("", 99999, "None"));
        }
    }

    public void reset() {
        hasNext = true;
        hasStarted = false;
        visitedNodes = new ArrayList<>();
        visitedGraph = new Graph();
        nodeData = new ArrayList<>();
        for (int i = 0; i < _size; i++) {
            graph.addNode("");
            visitedGraph.addNode("");
            nodeData.add(new DijkNodeData("", 99999, "None"));
        }
    }

    public void next(int typingSelector) {
        assert hasNext;

        // if just pressed start
        if (!hasStarted) {
            assert typingSelector != -1;

            visitedNodes.add(typingSelector);
            nodeData.get(typingSelector).minWeight = 0;

            hasStarted = true;
        } else {
            // LinkedList<Integer> visitedNodes = visitedGraph.getConnectedNodes(start);
            int[] newNodes = graph.getShortestNewCumulativeConnection(visitedNodes, nodeData);
            visitedGraph.addVertex(newNodes[0], newNodes[1]);

            hasNext = !visitedGraph.isConnected();
        }
    }
}
