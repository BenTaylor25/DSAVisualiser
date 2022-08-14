package com.mygdx.dsav.Generators;

import com.mygdx.dsav.DataStructs.Graph;
// import com.mygdx.dsav.DataStructs.GraphNode;

public class GPrimsAlg {
    public Graph graph;

    public GPrimsAlg(int size) {
        graph = new Graph(true, false);
        for (int i = 0; i < size; i++) {
            graph.addNode("");
        }
    }

    public boolean isConnected() {
        return true;
    }
}
