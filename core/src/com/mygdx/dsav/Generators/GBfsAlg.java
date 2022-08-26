package com.mygdx.dsav.Generators;

import com.mygdx.dsav.DataStructs.Graph;

public class GBfsAlg {
    public String[] nodeValues;
    public boolean hasNext;
    public Graph visitedTree;
    private int[][] order;
    private int orderPtr;
    
    public GBfsAlg(int size) {
        nodeValues = new String[size];
        for (int i = 0; i < size; i++) {
            nodeValues[i] = "";
        }

        order = new int[][]{ 
            {0, 1}, {0, 2},
            {1, 3}, {1, 4},
            {2, 5}, {2, 6}
        };

        reset();
    }

    public void reset() {
        visitedTree = new Graph();
        for (int i = 0; i < 7; i++) {
            visitedTree.addNode("");
        }

        hasNext = true;
        orderPtr = 0;
    }

    public boolean allNamed() {
        boolean rv = true;

        for (String node : nodeValues) {
            rv = rv && !node.equals("");
        }

        return rv;
    }

    public int next() {
        assert hasNext;

        int[] joinNodes = order[orderPtr];
        visitedTree.addVertex(joinNodes[0], joinNodes[1]);
        orderPtr++;

        hasNext = orderPtr < order.length;
        
        return order[orderPtr-1][1];
    }
}