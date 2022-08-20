package com.mygdx.dsav.Generators;

import java.util.LinkedList;

import com.mygdx.dsav.DataStructs.Graph;
// import com.mygdx.dsav.DataStructs.GraphNode;

public class GPrimsAlg {
    public Graph graph;
    public Graph visitedGraph;
    public boolean hasNext;

    public GPrimsAlg(int size) {
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
    }
    
    public void next(int start) {
        assert hasNext;

        LinkedList<Integer> visitedNodes = visitedGraph.getConnectedNodes(start);
        
    }
}
