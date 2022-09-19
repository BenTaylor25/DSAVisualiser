package com.mygdx.dsav;

public class DijkNodeData {
    public String nodeName;
    public int minWeight;
    public String prevNode;

    public DijkNodeData(String name, int weight, String prev) {
        nodeName = name;
        minWeight = weight;
        prevNode = prev;
    }
}
