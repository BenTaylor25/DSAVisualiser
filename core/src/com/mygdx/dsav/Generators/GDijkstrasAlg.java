package com.mygdx.dsav.Generators;

import com.mygdx.dsav.DataStructs.Graph;

public class GDijkstrasAlg {
    public Graph graph;
    
    public boolean hasNext;
    private int _size;

    public GDijkstrasAlg(int size) {
        _size = size;
        graph = new Graph(true, false);
        for (int i = 0; i < _size; i++) {
            graph.addNode("");
        }
        hasNext = true;
    }
}
