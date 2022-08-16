package com.mygdx.dsav.Screens;

import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.FactOption;
import com.mygdx.dsav.Generators.GDfsAlg;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;

public class DfsScreen extends FactOption {
    final int GRAPHSIZE = 4;
    GDfsAlg generator;
    String hintTextString;
    boolean algActive;
    int controllerSelector;
    int typingSelector;
    BenHelper.Rect titleButtonBox;
    BenHelper.Rect backButtonBox;
    BenHelper.Rect hintButtonBox;
    BenHelper.Rect algButtonBox;
    BenHelper.Rect controllerHintButtonBox;
    BenHelper.Rect[] graphButtonBoxes; 
    BenHelper.Rect[] controllerButtonBoxes;

    @Override
    public void create() {
        generator = new GDfsAlg(GRAPHSIZE);
        hintTextString = "";
        controllerSelector = -1;
        typingSelector = -1;
        titleButtonBox = new BenHelper.Rect(GW*0.3f, GH*0.85f, GW*0.4f, GH*0.15f);
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
    }

    @Override
    public void reset() {
        generator = new GDfsAlg(GRAPHSIZE);
        algActive = false;
    }

    @Override
    public String updateBefore(String factSelector) {
        hintTextString = "";
        /*
        if (titleButtonBox.checkHover()) {
            hintTextString = "Graphs are collections of nodes joined together by vertices.\n"+
                "Graphs are extremely useful when representing relationships.\n"+
                "Example: Social Media accounts are nodes that connect to others.";
        }
        else if (toggleDirectedButtonBox.checkHover()) {
            hintTextString = "Graphs can be Undirected or Directed.\n"+
                "Undirected is useful for symetric relations (e.g. friends).\n"+
                "Directed is useful for asymetric relations (e.g. followers).";
        } 
        else if (toggleWeightedButtonBox.checkHover()) {
            hintTextString = "Graphs can be Unweighted or Weighted.\n"+
                "Weighted is useful for evaluating the relation.\n"+
                "Examples: the distance between locations, or Network latency.";
        }
        else {
            boolean nodeHover = false;
            boolean controllerHover = controllerHintButtonBox.checkHover();
            boolean weightHover = false;
            for (int i = 0; i < GRAPHSIZE; i++) {
                nodeHover = nodeHover || graphButtonBoxes[i].checkHover();
            }

            if (graph.isWeighted) {
                for (int i = 0; i < 6; i++) {
                    boolean hover = weightButtonBoxes[i].checkHover();

                    int inode = 0;
                    if (i >= 3) { inode++; }
                    if (i == 5) { inode++; }

                    int jnode = 3;
                    if (i == 0) { jnode = 1; }
                    if (i == 1 || i == 3) { jnode = 2; }

                    boolean vertexExists = graph.nodes.get(inode).pointsTo(jnode) ||
                        graph.nodes.get(jnode).pointsTo(inode);

                    if (hover && vertexExists) {
                        weightHover = true;
                    }
                } 
            }

            if (nodeHover) {
                hintTextString = "Graph Nodes store one or more values\n"+
                    "and the connections it has to other nodes.\n"+
                    "Sometimes stores connections to it for back reference.";
            }
            else if (controllerHover) {
                hintTextString = "Controller to modify the vertex connections.\n"+
                    "Click one box to select the corresponding node.\n"+
                    "Click a second to connect, or unconnect to it.";
            }
            else if (weightHover) {
                hintTextString = "Modify the Weight of the vertex.";
            }
        }
        */

        // controller
        if (BenHelper.screenClicked() && !algActive) {
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
        if (BenHelper.screenClicked() && !algActive) {
            typingSelector = -1;

            for (int i = 0; i < GRAPHSIZE; i++) {
                if (graphButtonBoxes[i].checkHover()) {
                    typingSelector = i;
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
        algButtonBox.draw(shape);
       
        // draw arrows and weights
        for (int i = 0; i < GRAPHSIZE; i++) {
            for (int j = 0; j < GRAPHSIZE; j ++) {
                if (i != j && generator.graph.nodes.get(i).pointsTo(j)) {
                    graphButtonBoxes[i].arrowToCalc(shape, 
                        graphButtonBoxes[j], 
                        i, j
                    );
                }
            }
        }

        // draw text
        BenHelper.textDrawCentre(batch, font, "Depth First Search",
            titleButtonBox, 1.5f, 
            BenHelper.DEFAULT_TEXT_COLOUR
        );
        BenHelper.textDrawCentre(batch, font, "Back", 
            backButtonBox, 1.25f
        );
        BenHelper.textDrawCentre(batch, font, hintTextString, 
            hintButtonBox, 0.75f, Color.GRAY
        );
        
        String text = "[...]";
        if (algActive) {
            // text = generator.hasNext ? "Next" : "Finish";
        } else {
            text = "[Connect]";
            /*
            if (generator.isConnected()) { 
                // fix typing selector to correct range
                text = typingSelector == -1 ? "[Select]" : "Start";
            }
            */
        }
        BenHelper.textDrawCentre(batch, font, text,
            algButtonBox, 1f
        );

        //text = "";
        String[] defaults = {"[A]", "[B]", "[C]", "[D]"};
        for (int i = 0; i < GRAPHSIZE; i++) {
            text = generator.graph.nodes.get(i).value;
            if (text.equals("")) { text = defaults[i]; }
            BenHelper.textDrawCentreSelectable(batch, font, 
                text, graphButtonBoxes[i], 
                1f, (typingSelector == i)
            );
        }


        // debug: draw hitboxes
        if (BenHelper.DEBUG) {
            titleButtonBox.draw(shape, Color.RED);
            backButtonBox.draw(shape, Color.RED);
            hintButtonBox.draw(shape, Color.RED);
            controllerHintButtonBox.draw(shape, Color.RED);
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

        return factSelector;
    }
}