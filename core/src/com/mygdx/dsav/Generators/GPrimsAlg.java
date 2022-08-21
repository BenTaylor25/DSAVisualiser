package com.mygdx.dsav.Generators;

import java.util.LinkedList;

import com.mygdx.dsav.DataStructs.Graph;

public class GPrimsAlg {
    public Graph graph;
    public Graph visitedGraph;
    public boolean hasNext;
    private int _size;

    public GPrimsAlg(int size) {
        _size = size;
        graph = new Graph(true, false);
        visitedGraph = new Graph();
        for (int i = 0; i < size; i++) {
            graph.addNode("");
            visitedGraph.addNode("");
        }
        hasNext = true;
    }

    public void reset() {
        hasNext = true;
        visitedGraph = new Graph();
        for (int i = 0; i < _size; i++) {
            visitedGraph.addNode("");
        }
    }
    
    public void next(int start) {
        assert hasNext;

        LinkedList<Integer> visitedNodes = visitedGraph.getConnectedNodes(start);
        int[] newNodes = graph.getShortestNewConnection(visitedNodes);
        visitedGraph.addVertex(newNodes[0], newNodes[1]);

        hasNext = !visitedGraph.isConnected();
    }
}
