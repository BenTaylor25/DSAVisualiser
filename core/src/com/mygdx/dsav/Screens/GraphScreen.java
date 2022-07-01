package com.mygdx.dsav.Screens;

import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.FactOption;
import com.mygdx.dsav.DataStructs.Graph;

import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;

public class GraphScreen extends FactOption {
    final int GRAPHSIZE = 4;
    Graph graph;
    String hintTextString;
    int controllerSelector;
    BenHelper.Rect titleButtonBox;
    BenHelper.Rect backButtonBox;
    BenHelper.Rect hintButtonBox;
    BenHelper.Rect toggleDirectedButtonBox;
    BenHelper.Rect toggleWeightedButtonBox;
    BenHelper.Rect[] graphButtonBoxes;
    BenHelper.Rect[] controllerButtonBoxes;

    @Override
    public void create() {
        graph = new Graph();
        for (int i = 0; i < GRAPHSIZE; i++) {
            graph.addNode("");
        }
        hintTextString = "";
        controllerSelector = -1;
        titleButtonBox = new BenHelper.Rect(GW*0.4f, GH*0.85f, GW*0.2f, GH*0.15f);
        backButtonBox = new BenHelper.Rect(0, 0, GW*0.1f, GH*0.1f);
        hintButtonBox = new BenHelper.Rect(GW*0.15f, 0, GW*0.7f, GH*0.1f);

        toggleDirectedButtonBox = new BenHelper.Rect(GW*0.175f, GH*0.85f, GW*0.15f, GH*0.075f);
        toggleWeightedButtonBox = new BenHelper.Rect(GW*0.675f, GH*0.85f, GW*0.15f, GH*0.075f);

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
        graph = new Graph(false, false);
        for (int i = 0; i < GRAPHSIZE; i++) {
            graph. addNode("");
        }
    }

    @Override
    public String updateBefore(String factSelector) {
        hintTextString = "";

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

        // toggles
        if (toggleDirectedButtonBox.checkClick()) {
            graph.isDirected = !graph.isDirected;
        }
        else if (toggleWeightedButtonBox.checkClick()) {
            graph.isWeighted = !graph.isWeighted;
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

        toggleDirectedButtonBox.draw(shape);
        toggleWeightedButtonBox.draw(shape);


        // draw arrows
        for (int i = 0; i < GRAPHSIZE; i++) {
            for (int j = 0; j < GRAPHSIZE; j ++) {
                if (i != j && graph.nodes.get(i).pointsTo(j)) {
                    graphButtonBoxes[i].arrowToCalc(shape, 
                        graphButtonBoxes[j], 
                        i, j
                    );
                }
            }
        }

        // draw data items


        // draw button outlines


        // draw text
        BenHelper.textDrawCentre(batch, font, "Graph",
            titleButtonBox, 1.5f
        );
        BenHelper.textDrawCentre(batch, font, "Back", 
            backButtonBox, 1.25f
        );

        String text = "to directed";
        if (graph.isDirected) text = "to undirected";
        BenHelper.textDrawCentre(batch, font, text, 
            toggleDirectedButtonBox, 0.75f
        );
        text = "to weighted";
        if (graph.isWeighted) text = "to unweighted";
        BenHelper.textDrawCentre(batch, font, text, 
            toggleWeightedButtonBox, 0.75f
        );


        // debug: draw hitboxes
        if (BenHelper.DEBUG) {
            titleButtonBox.draw(shape, Color.RED);
            backButtonBox.draw(shape, Color.RED);
        }
    }

    @Override
    public String updateAfter(String factSelector) {
        if (backButtonBox.checkClick()) {
            factSelector = "menu";
        }


        return factSelector;
    }
}