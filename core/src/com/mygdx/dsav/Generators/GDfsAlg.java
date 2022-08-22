package com.mygdx.dsav.Generators;

public class GDfsAlg {
    public String[] nodeValues;
    public boolean hasNext;
    
    public GDfsAlg(int size) {
        nodeValues = new String[size];
        for (int i = 0; i < size; i++) {
            nodeValues[i] = "";
        }
        hasNext = true;
    }

    public boolean allNamed() {
        boolean rv = true;

        for (String node : nodeValues) {
            rv = rv && !node.equals("");
        }

        return rv;
    }
}
