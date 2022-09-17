package com.mygdx.dsav.Generators;

import java.util.ArrayList;

import com.mygdx.dsav.DataStructs.Graph;

public class GDijkstrasAlg {
    public Graph graph;
    public ArrayList<Integer> visitedNodes;
    
    public boolean hasNext;
    private int _size;

    public GDijkstrasAlg(int size) {
        _size = size;
        hasNext = true;
        graph = new Graph(true, false);
        for (int i = 0; i < _size; i++) {
            graph.addNode("");
        }
    }

    public void reset() {
        hasNext = true;
        graph = new Graph(true, false);
        for (int i = 0; i < _size; i++) {
            graph.addNode("");
        }
    }

    public void next() {
        assert hasNext;

        //
    }
}
