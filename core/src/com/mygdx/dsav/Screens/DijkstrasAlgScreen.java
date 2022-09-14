package com.mygdx.dsav.Screens;

import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.FactOption;
import com.mygdx.dsav.DataStructs.Graph;
import com.mygdx.dsav.Generators.GDijkstrasAlg;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;

public class DijkstrasAlgScreen extends FactOption {
    final int GRAPHSIZE = 4;
    GDijkstrasAlg generator;
    Graph graph;
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
    ArrayList<String> orderText;

    @Override
    public void create() {
        generator = new GDijkstrasAlg(GRAPHSIZE);
        graph = new Graph(true, false);
        for (int i = 0; i < GRAPHSIZE; i++) {
            graph.addNode("");
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
        orderText = new ArrayList<>();
        orderText.add("Node Value");
        orderText.add("Order : Weight");
        orderText.add("---");

        // orderText.add("WWWWWWWWWW");
        // orderText.add("0 : 5555");
        // orderText.add("");
        // orderText.add("WWWWWWWWWW");
        // orderText.add("1 : 5555");
        // orderText.add("");
        // orderText.add("WWWWWWWWWW");
        // orderText.add("2 : 5555");
        // orderText.add("");
        // orderText.add("WWWWWWWWWW");
        // orderText.add("3 : 5555");
    }

    @Override
    public void reset() {
        // selectors?
        graph = new Graph(true, false);
        for (int i = 0; i < GRAPHSIZE; i++) {
            graph. addNode("");
        }
    }

    @Override
    public String updateBefore(String factSelector) {
        // hintTextString = "";
        // if (titleButtonBox.checkHover()) {
        //     hintTextString = "Graphs are collections of 'Nodes' joined together by 'Edges'.\n"+
        //         "Graphs are extremely useful when representing relationships.\n"+
        //         "Example: Social Media accounts are nodes that connect to others.";
        // }
        // else if (toggleDirectedButtonBox.checkHover()) {
        //     hintTextString = "Graphs can be Undirected or Directed.\n"+
        //         "Undirected is useful for symetric relations (e.g. friends).\n"+
        //         "Directed is useful for asymetric relations (e.g. followers).";
        // } 
        // else if (toggleWeightedButtonBox.checkHover()) {
        //     hintTextString = "Graphs can be Unweighted or Weighted.\n"+
        //         "Weighted is useful for evaluating the relation.\n"+
        //         "Examples: the distance between locations, or Network latency.";
        // }
        // else {
        //     boolean nodeHover = false;
        //     boolean controllerHover = controllerHintButtonBox.checkHover();
        //     boolean weightHover = false;
        //     for (int i = 0; i < GRAPHSIZE; i++) {
        //         nodeHover = nodeHover || graphButtonBoxes[i].checkHover();
        //     }

        //     if (graph.isWeighted) {
        //         for (int i = 0; i < 6; i++) {
        //             boolean hover = weightButtonBoxes[i].checkHover();

        //             int inode = 0;
        //             if (i >= 3) { inode++; }
        //             if (i == 5) { inode++; }

        //             int jnode = 3;
        //             if (i == 0) { jnode = 1; }
        //             if (i == 1 || i == 3) { jnode = 2; }

        //             boolean vertexExists = graph.nodes.get(inode).pointsTo(jnode) ||
        //                 graph.nodes.get(jnode).pointsTo(inode);

        //             if (hover && vertexExists) {
        //                 weightHover = true;
        //             }
        //         } 
        //     }

        //     if (nodeHover) {
        //         hintTextString = "Graph Nodes store one or more values, and the connections\n"+
        //             "it has to other nodes. Nodes are also known as Vertices.";
        //     }
        //     else if (controllerHover) {
        //         hintTextString = "Controller to modify the Edge connections. Click one box\n"+
        //             "to select the corresponding Node. Click a second to connect\n"+
        //             "to- or disconnect from it. Edges are also known as Arcs.";
        //     }
        //     else if (weightHover) {
        //         hintTextString = "Modify the Weight of the Edge.";
        //     }
        // }

        // controller
        if (BenHelper.screenClicked()) {
            boolean controllerSelected = false;
            for (int i = 0; i < GRAPHSIZE; i++) {
                if (controllerButtonBoxes[i].checkHover()) {
                    controllerSelected = true;
                    if (controllerSelector != -1) {
                        if (controllerSelector != i) {
                            graph.toggleVertex(controllerSelector, i);
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
            typingSelector = -1;

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
                if (i != j && graph.nodes.get(i).pointsTo(j)) {
                    graphButtonBoxes[i].arrowToCalc(shape, 
                        graphButtonBoxes[j], 
                        i, j
                    );

                    if (graph.isWeighted) {
                        int x = graph.nodes.get(i).connections.indexOf(j);
                        
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
                            graph.nodes.get(i).weights.get(x)
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
        String algText = "[test]";
        BenHelper.textDrawCentre(batch, font, 
            algText, algButtonBox, 1f
        );

        String text;
        String[] defaults = {"[A]", "[B]", "[C]", "[D]"};
        for (int i = 0; i < GRAPHSIZE; i++) {
            text = graph.nodes.get(i).value;
            if (text.equals("")) { text = defaults[i]; }
            BenHelper.textDrawCentreSelectable(batch, font, 
                text, graphButtonBoxes[i], 
                1f, (typingSelector == i)
            );
        }

        for (int i = 0; i < orderText.size(); i++) {
            BenHelper.textDrawCentre(batch, font, 
                orderText.get(i), 
                orderButtonBox.x + orderButtonBox.w / 2, 
                orderButtonBox.y + orderButtonBox.h - (i+1.3f)*30, 
                0.75f, BenHelper.DEFAULT_TEXT_COLOUR
            );
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
            graph.nodes.get(typingSelector).value = BenHelper.typing(
                graph.nodes.get(typingSelector).value,
                10
            );
        }

        if (weightSelector >= 0) {
            int i = 0;
            if (weightSelector >= 3) { i++; }
            if (weightSelector == 5) { i++; }

            int j = 3;
            if (weightSelector == 0) { j = 1; }
            if (weightSelector == 1 ||
                weightSelector == 3) { j = 2; }

            if (graph.nodes.get(i).pointsTo(j)) {
                int x = graph.nodes.get(i).connections.indexOf(j);
                graph.nodes.get(i).weights.set(x, BenHelper.typingNumbers(
                    graph.nodes.get(i).weights.get(x)
                ));
            }

            if (graph.nodes.get(j).pointsTo(i)) {
                int x = graph.nodes.get(j).connections.indexOf(i);
                graph.nodes.get(j).weights.set(x, BenHelper.typingNumbers(
                    graph.nodes.get(j).weights.get(x)
                ));
            }
        }
        
        // if (algButtonBox.checkClick()) {
        //     if (algActive) {
        //         if (generator.hasNext) {
        //             int i = generator.next();
        //             orderText.add(generator.nodeValues[i]);
        //         } else {
        //             algActive = false;
        //             generator.reset();
        //             orderText = new ArrayList<>();
        //             orderText.add("Order:");
        //             orderText.add("------");
        //         }
        //     } else {
        //         if (generator.allNamed()) {
        //             algActive = true;
        //             orderText.add(generator.nodeValues[0]);
        //         }
        //     }
        // }

        return factSelector;
    }
}