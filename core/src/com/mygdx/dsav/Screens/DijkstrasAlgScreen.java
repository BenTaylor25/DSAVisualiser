package com.mygdx.dsav.Screens;

import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.DijkNodeData;
import com.mygdx.dsav.FactOption;
import com.mygdx.dsav.DataStructs.Graph;
import com.mygdx.dsav.Generators.GDijkstrasAlg;
import com.badlogic.gdx.utils.ScreenUtils;

import com.badlogic.gdx.graphics.Color;

public class DijkstrasAlgScreen extends FactOption {
    final int GRAPHSIZE = 4;
    GDijkstrasAlg generator;
    String hintTextString;
    int controllerSelector;
    int typingSelector;
    int weightSelector;
    boolean algActive;
    BenHelper.Rect titleButtonBox;
    BenHelper.Rect backButtonBox;
    BenHelper.Rect hintButtonBox;
    BenHelper.Rect algButtonBox;
    BenHelper.Rect controllerHintButtonBox;
    BenHelper.Rect[] graphButtonBoxes; 
    BenHelper.Rect[] controllerButtonBoxes;
    BenHelper.Rect[] weightButtonBoxes;
    BenHelper.Rect orderButtonBox;

    @Override
    public void create() {
        generator = new GDijkstrasAlg(GRAPHSIZE);
        generator.graph = new Graph(true, false);
        for (int i = 0; i < GRAPHSIZE; i++) {
            generator.graph.addNode("");
        }
        hintTextString = "";
        controllerSelector = -1;
        typingSelector = -1;
        weightSelector = -1;
        algActive = false;
        titleButtonBox = new BenHelper.Rect(GW*0.25f, GH*0.85f, GW*0.5f, GH*0.15f);
        backButtonBox = new BenHelper.Rect(0, 0, GW*0.1f, GH*0.1f);
        hintButtonBox = new BenHelper.Rect(GW*0.15f, 0, GW*0.7f, GH*0.1f);
        algButtonBox = new BenHelper.Rect(GW*0.1f, GH*0.7f, GW*0.15f, GH*0.15f);

        controllerHintButtonBox = new BenHelper.Rect(GW*0.86f, GW*0.03f, GW*0.11f, GW*0.11f);

        graphButtonBoxes = new BenHelper.Rect[GRAPHSIZE];
        graphButtonBoxes[0] = new BenHelper.Rect(GW*0.425f, GH*0.625f, GW*0.15f, GH*0.15f);
        graphButtonBoxes[1] = new BenHelper.Rect(GW*0.175f, GH*0.425f, GW*0.15f, GH*0.15f);
        graphButtonBoxes[2] = new BenHelper.Rect(GW*0.675f, GH*0.425f, GW*0.15f, GH*0.15f);
        graphButtonBoxes[3] = new BenHelper.Rect(GW*0.425f, GH*0.225f, GW*0.15f, GH*0.15f);

        controllerButtonBoxes = new BenHelper.Rect[GRAPHSIZE];
        controllerButtonBoxes[0] = new BenHelper.Rect(GW*0.9f,  GW*0.11f, GW*0.03f, GW*0.03f);
        controllerButtonBoxes[1] = new BenHelper.Rect(GW*0.86f, GW*0.07f, GW*0.03f, GW*0.03f);
        controllerButtonBoxes[2] = new BenHelper.Rect(GW*0.94f, GW*0.07f, GW*0.03f, GW*0.03f);
        controllerButtonBoxes[3] = new BenHelper.Rect(GW*0.9f,  GW*0.03f, GW*0.03f, GW*0.03f);

        weightButtonBoxes = new BenHelper.Rect[6];
        weightButtonBoxes[0] = new BenHelper.Rect(GW*0.35f, GH*0.55f, GW*0.05f, GW*0.05f);
        weightButtonBoxes[1] = new BenHelper.Rect(GW*0.6f, GH*0.55f, GW*0.05f, GW*0.05f);
        weightButtonBoxes[2] = new BenHelper.Rect(GW*0.485f, GH*0.5f, GW*0.05f, GW*0.05f);
        weightButtonBoxes[3] = new BenHelper.Rect(GW*0.435f, GH*0.5f-GW*0.025f, GW*0.05f, GW*0.05f);
        weightButtonBoxes[4] = new BenHelper.Rect(GW*0.35f, GH*0.35f, GW*0.05f, GW*0.05f);
        weightButtonBoxes[5] = new BenHelper.Rect(GW*0.6f, GH*0.35f, GW*0.05f, GW*0.05f);

        orderButtonBox = new BenHelper.Rect(GW*0.825f, GH*0.35f, GW*0.175f, GH*0.625f);
    }

    @Override
    public void reset() {
        generator = new GDijkstrasAlg(GRAPHSIZE);
        algActive = false;
        typingSelector = -1;
    }

    @Override
    public String updateBefore(String factSelector) {
        hintTextString = "";
        if (titleButtonBox.checkHover()) {
            hintTextString = "Dijkstra's Algorithm takes a Weighted Graph and a\n"+
                "starting node, and finds the shortest path to every\n"+
                "other node. O(N^2) Time, O(N+E) Space.";
        }
        else if (orderButtonBox.checkHover()) {
            hintTextString = "This section shows the weight of the minimum path from\n"+
                "the starting node to every other node. This also shows the\n"+
                "previous node in the shortest path to it.";
        }
        else if (algButtonBox.checkHover()) {
            if (algActive) {
                if (generator.hasNext) {
                    // "Next" is shown
                    hintTextString = "Add the next closest Node.";
                } else {
                    // "Finish" is shown
                    hintTextString = "The shortest path to all other nodes has been obtained.\n"+
                        "Also see the A* Algorithm, which is a varient of Dijkstra's.";
                }
            } else {
                if (generator.graph.isConnected()) {
                    if (typingSelector == -1) {
                        // "[Select]" is shown
                        hintTextString = "Dijkstra's Algorithm requires a starting Node.\n"+
                            "Select a Node to set the starting point.";
                    } else {
                        // "Start" is shown
                        hintTextString = "Start Dijkstra's Algorithm.";

                        // If all weights are zero
                        if (generator.graph.getWeightDifference() <= 0) {
                            hintTextString += "\nYou may wish to vary the Edge\n"+
                                "Weights before starting.";
                        }
                    }
                } else {
                    // "[Connect]" is shown
                    hintTextString = "The Graph must be Connected (all Nodes must be reachable\n"+
                        "from all others via Edges) in order to start the Algorithm.\n"+
                        "This is not neccesarily the case in practice.";
                }
            }
        }
        else {
            boolean nodeHover = false;
            boolean controllerHover = controllerHintButtonBox.checkHover();
            boolean weightHover = false;
            for (int i = 0; i < GRAPHSIZE; i++) {
                nodeHover = nodeHover || graphButtonBoxes[i].checkHover();
            }

            for (int i = 0; i < 6; i++) {
                boolean hover = weightButtonBoxes[i].checkHover();

                int inode = 0;
                if (i >= 3) { inode++; }
                if (i == 5) { inode++; }

                int jnode = 3;
                if (i == 0) { jnode = 1; }
                if (i == 1 || i == 3) { jnode = 2; }

                // My Dijkstra's Alg is undirected, so if B->A, A->B (we only need to check one)
                boolean vertexExists = generator.graph.nodes.get(inode).pointsTo(jnode);

                if (hover && vertexExists) {
                    weightHover = true;
                }
            } 

            if (nodeHover) {
                hintTextString = "The values of the Nodes do not impact the Algorithm,\n"+
                    "but it may be helpful to present using an example.";
            }
            else if (controllerHover) {
                hintTextString = "Controller to modify the Edge connections.\n"+
                    "Click one box to select the corresponding Node.\n"+
                    "Click a second to connect to- or disconnect from it.";
            }
            else if (weightHover) {
                hintTextString = "Modify the Weight of the Edge.\n"+
                    "This impacts the Algorithm.";
            }
        }

        // controller
        if (BenHelper.screenClicked()) {
            boolean controllerSelected = false;
            for (int i = 0; i < GRAPHSIZE; i++) {
                if (controllerButtonBoxes[i].checkHover()) {
                    controllerSelected = true;
                    if (controllerSelector != -1) {
                        if (controllerSelector != i) {
                            generator.graph.toggleVertex(controllerSelector, i);
                        }
                        controllerSelector = -1;
                    } else {
                        controllerSelector = i;
                    }
                }
            }
            if (!controllerSelected) {
                controllerSelector = -1;
            }
        }

        // typing
        if (BenHelper.screenClicked()) {
            if (!algButtonBox.checkHover()) {
                typingSelector = -1;
            }

            for (int i = 0; i < GRAPHSIZE; i++) {
                if (graphButtonBoxes[i].checkHover()) {
                    typingSelector = i;
                    break;
                }
            }
        }

        // weights
        if (BenHelper.screenClicked()) {
            weightSelector = -1;

            for (int i = 0; i < 6; i++) {
                if (weightButtonBoxes[i].checkHover()) {
                    weightSelector = i;
                    break;
                }
            }
        }

        return factSelector;
    }

    @Override
    public void draw() {
        // clear screen
        batch.begin();
            ScreenUtils.clear(BenHelper.BACKGROUND_COLOUR);
            font.setColor(BenHelper.DEFAULT_TEXT_COLOUR);
        batch.end();
        
        // draw static shapes
        for (int i = 0; i < GRAPHSIZE; i++) {
            graphButtonBoxes[i].draw(shape);
            controllerButtonBoxes[i].draw(shape);
        }
        if (controllerSelector != -1) {
            controllerButtonBoxes[controllerSelector].draw(
                shape, BenHelper.HOVER_TEXT_COLOUR
            );
        }
        if (typingSelector >= 0) {
            graphButtonBoxes[typingSelector].draw(shape, BenHelper.HOVER_TEXT_COLOUR);
        }
        algButtonBox.draw(shape, BenHelper.HINT_TEXT_COLOUR);

        // draw arrows and weights
        for (int i = 0; i < GRAPHSIZE; i++) {
            for (int j = 0; j < GRAPHSIZE; j ++) {
                if (i != j && generator.graph.nodes.get(i).pointsTo(j)) {
                    graphButtonBoxes[i].arrowToCalc(shape, 
                        graphButtonBoxes[j], 
                        i, j
                    );

                    if (generator.visitedGraph.nodes.get(i).pointsTo(j)) {
                        graphButtonBoxes[i].arrowToCalc(shape, 
                            graphButtonBoxes[j], 
                            i, j,
                            BenHelper.HOVER_TEXT_COLOUR
                        );
                    }
                    
                    int x = generator.graph.nodes.get(i).connections.indexOf(j);
                    
                    // weight box index
                    int wInd;
                    if (i == 0 || j == 0) {
                        wInd = i + j - 1;
                    } else if (i == 1 || j == 1) {
                        wInd = i + j;
                    } else {
                        wInd = 5;
                    }
                    
                    // not perfect, but should be good enough for now
                    String text = Integer.toString(
                        generator.graph.nodes.get(i).weights.get(x)
                    );

                    if (wInd == weightSelector) {
                        BenHelper.textDrawCentre(
                            batch, font, text, 
                            weightButtonBoxes[wInd], 1f,
                            BenHelper.HOVER_TEXT_COLOUR
                        );
                    } else {
                        BenHelper.textDrawCentre(
                            batch, font, text, 
                            weightButtonBoxes[wInd], 1f
                        );
                    }
                    
                }
            }
        }

        algButtonBox = new BenHelper.Rect(GW*0.1f, GH*0.7f, GW*0.15f, GH*0.15f);
        
        // draw text
        BenHelper.textDrawCentre(batch, font, "Dijkstra's Algorithm",
            titleButtonBox, 1.5f, 
            BenHelper.DEFAULT_TEXT_COLOUR
        );
        BenHelper.textDrawCentre(batch, font, "Back", 
            backButtonBox, 1.25f
        );
        BenHelper.textDrawCentre(batch, font, hintTextString, 
            hintButtonBox, 0.75f, BenHelper.HINT_TEXT_COLOUR
        );
        
        String algText = "[...]";
        if (algActive) {
            algText = generator.hasNext ? "Next" : "Finish";
        } else {
            algText = "[Connect]";
            if (generator.graph.isConnected()) {
                algText = typingSelector == -1 ? "[Select]" : "Start";
            }
        }
        BenHelper.textDrawCentre(batch, font, 
            algText, algButtonBox, 1f
        );

        String text;
        String[] defaults = {"[A]", "[B]", "[C]", "[D]"};
        for (int i = 0; i < GRAPHSIZE; i++) {
            text = generator.graph.nodes.get(i).value;
            if (text.equals("")) { text = defaults[i]; }
            BenHelper.textDrawCentreSelectable(batch, font, 
                text, graphButtonBoxes[i], 
                1f, (typingSelector == i)
            );
        }

        float nodeDataX = orderButtonBox.x + orderButtonBox.w / 2;
        float nodeDataY = orderButtonBox.y + orderButtonBox.h;
        BenHelper.textDrawCentre(batch, font, "Node Value", nodeDataX, nodeDataY - 15, 0.75f, BenHelper.DEFAULT_TEXT_COLOUR);
        BenHelper.textDrawCentre(batch, font, "Weight:Previous", nodeDataX, nodeDataY - 45, 0.75f, BenHelper.DEFAULT_TEXT_COLOUR);
        BenHelper.textDrawCentre(batch, font, "---", nodeDataX, nodeDataY - 75, 0.75f, BenHelper.DEFAULT_TEXT_COLOUR);
        for (int i = 0; i < GRAPHSIZE; i++) {
            DijkNodeData n = generator.nodeData.get(i);
            BenHelper.textDrawCentre(batch, font, n.nodeName, nodeDataX, nodeDataY - (105 + i*90), 0.75f, BenHelper.DEFAULT_TEXT_COLOUR);
            BenHelper.textDrawCentre(batch, font, n.minWeight+":"+n.prevNode, nodeDataX, nodeDataY - (135 + i*90), 0.75f, BenHelper.DEFAULT_TEXT_COLOUR);
        }

        // debug: draw hitboxes
        if (BenHelper.DEBUG) {
            titleButtonBox.draw(shape, Color.RED);
            backButtonBox.draw(shape, Color.RED);
            hintButtonBox.draw(shape, Color.RED);
            controllerHintButtonBox.draw(shape, Color.RED);
            orderButtonBox.draw(shape, Color.RED);

            for (int i = 0; i < 6; i++) {
                weightButtonBoxes[i].draw(shape, Color.RED);
            }
        }
    }

    @Override
    public String updateAfter(String factSelector) {
        if (backButtonBox.checkClick()) {
            factSelector = "menu";
        }

        if (typingSelector >= 0) {
            generator.graph.nodes.get(typingSelector).value = BenHelper.typing(
                generator.graph.nodes.get(typingSelector).value,
                10
            );
        }
        // sync node data values to graph nodes
        String[] defaults = {"[A]", "[B]", "[C]", "[D]"};
        for (int i = 0; i < GRAPHSIZE; i++) {
            if (generator.graph.nodes.get(i).value.equals("")) {
                generator.nodeData.get(i).nodeName = defaults[i];
            } else {
                generator.nodeData.get(i).nodeName = generator.graph.nodes.get(i).value;
            }
        }           
        

        if (weightSelector >= 0) {
            int i = 0;
            if (weightSelector >= 3) { i++; }
            if (weightSelector == 5) { i++; }

            int j = 3;
            if (weightSelector == 0) { j = 1; }
            if (weightSelector == 1 ||
                weightSelector == 3) { j = 2; }

            if (generator.graph.nodes.get(i).pointsTo(j)) {
                int x = generator.graph.nodes.get(i).connections.indexOf(j);
                generator.graph.nodes.get(i).weights.set(x, BenHelper.typingNumbers(
                    generator.graph.nodes.get(i).weights.get(x)
                ));
            }

            if (generator.graph.nodes.get(j).pointsTo(i)) {
                int x = generator.graph.nodes.get(j).connections.indexOf(i);
                generator.graph.nodes.get(j).weights.set(x, BenHelper.typingNumbers(
                    generator.graph.nodes.get(j).weights.get(x)
                ));
            }
        }
        
        if (algButtonBox.checkClick()) {
            if (algActive) {
                if (generator.hasNext) {
                    generator.next(typingSelector);
                } else {
                    algActive = false;
                    generator.reset();
                }
            } else {
                if (generator.graph.isConnected() && typingSelector != -1) {
                    algActive = true;
                }
            }
        }

        return factSelector;
    }
}