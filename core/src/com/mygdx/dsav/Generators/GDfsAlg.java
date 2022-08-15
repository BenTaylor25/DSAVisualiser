package com.mygdx.dsav.Generators;

import com.mygdx.dsav.DataStructs.Graph;
//import com.mygdx.dsav.DataStructs.GraphNode;

public class GDfsAlg {
    public Graph graph;
    
    public GDfsAlg(int size) {
        graph = new Graph();
        for (int i = 0; i < size; i++) {
            graph.addNode("");
        }
    }
}
