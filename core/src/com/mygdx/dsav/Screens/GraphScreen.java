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
    BenHelper.Rect titleButtonBox;
    BenHelper.Rect backButtonBox;
    BenHelper.Rect hintButtonBox;
    BenHelper.Rect[] graphButtonBoxes;

    @Override
    public void create() {
        graph = new Graph();
        hintTextString = "";
        titleButtonBox = new BenHelper.Rect(GW*0.4f, GH*0.85f, GW*0.2f, GH*0.15f);
        backButtonBox = new BenHelper.Rect(0, 0, GW*0.1f, GH*0.1f);
        hintButtonBox = new BenHelper.Rect(GW*0.15f, 0, GW*0.7f, GH*0.1f);

        graphButtonBoxes = new BenHelper.Rect[GRAPHSIZE];
        graphButtonBoxes[0] = new BenHelper.Rect(GW*0.425f, GH*0.625f, GW*0.15f, GH*0.15f);
        graphButtonBoxes[1] = new BenHelper.Rect(GW*0.175f, GH*0.425f, GW*0.15f, GH*0.15f);
        graphButtonBoxes[2] = new BenHelper.Rect(GW*0.675f, GH*0.425f, GW*0.15f, GH*0.15f);
        graphButtonBoxes[3] = new BenHelper.Rect(GW*0.425f, GH*0.225f, GW*0.15f, GH*0.15f);

    }

    @Override
    public void reset() {
        graph = new Graph();
    }

    @Override
    public String updateBefore(String factSelector) {
        hintTextString = "";
        
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