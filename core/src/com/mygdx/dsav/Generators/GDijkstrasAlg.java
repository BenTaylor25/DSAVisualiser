package com.mygdx.dsav.Generators;

import java.util.ArrayList;

import com.mygdx.dsav.DijkNodeData;
import com.mygdx.dsav.DataStructs.Graph;

public class GDijkstrasAlg {
    public Graph graph;
    // public ArrayList<Integer> visitedNodes;
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
        visitedGraph = new Graph();
        nodeData = new ArrayList<>();
        for (int i = 0; i < _size; i++) {
            graph.addNode("");
            visitedGraph.addNode("");
            nodeData.add(new DijkNodeData("test", 99999, "None"));
        }
    }

    public void reset() {
        hasNext = true;
        hasStarted = false;
        graph = new Graph(true, false);
        for (int i = 0; i < _size; i++) {
            graph.addNode("");
        }
    }

    public void next(int typingSelector) {
        assert hasNext;

        // if just pressed start
        if (!hasStarted) {
            assert typingSelector != -1;
            // visitedNodes.add(typingSelector);
            hasStarted = true;
        }


    }
}
