package com.mygdx.dsav.Screens;

import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.FactOption;

import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;

public class TreeScreen extends FactOption {
    final int TREESIZE = 7;
    String hintTextString;
    String[] nodeText;
    BenHelper.Rect titleButtonBox;
    BenHelper.Rect backButtonBox;
    BenHelper.Rect hintButtonBox;
    BenHelper.Rect[] treeNodeButtonBox;

    @Override
    public void create() {
        hintTextString = ""; 
        nodeText = new String[TREESIZE];
        titleButtonBox = new BenHelper.Rect(GW*0.4f, GH*0.85f, GW*0.2f, GH*0.15f);
        backButtonBox = new BenHelper.Rect(0, 0, GW*0.1f, GH*0.1f);
        hintButtonBox = new BenHelper.Rect(GW*0.15f, 0, GW*0.7f, GH*0.1f);

        treeNodeButtonBox = new BenHelper.Rect[TREESIZE];
        treeNodeButtonBox[0] = new BenHelper.Rect(GW*0.425f, GH*0.664f, GW*0.15f, GH*0.15f);
        treeNodeButtonBox[1] = new BenHelper.Rect(GW*0.275f, GH*0.426f, GW*0.15f, GH*0.15f);
        treeNodeButtonBox[2] = new BenHelper.Rect(GW*0.575f, GH*0.426f, GW*0.15f, GH*0.15f);
        treeNodeButtonBox[3] = new BenHelper.Rect(GW*0.125f, GH*0.188f, GW*0.15f, GH*0.15f);
        treeNodeButtonBox[4] = new BenHelper.Rect(GW*0.313f, GH*0.188f, GW*0.15f, GH*0.15f);
        treeNodeButtonBox[5] = new BenHelper.Rect(GW*0.537f, GH*0.188f, GW*0.15f, GH*0.15f);
        treeNodeButtonBox[6] = new BenHelper.Rect(GW*0.725f, GH*0.188f, GW*0.15f, GH*0.15f);
    }

    @Override
    public void reset() {
    }

    @Override
    public String updateBefore(String factSelector) {
        
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
        for (int i = 0; i < TREESIZE; i++) {
            treeNodeButtonBox[i].draw(shape);
        }

        // draw arrows
        treeNodeButtonBox[0].arrowTo(shape, treeNodeButtonBox[1], 1, 6);
        treeNodeButtonBox[0].arrowTo(shape, treeNodeButtonBox[2], 1, 6);
        treeNodeButtonBox[1].arrowTo(shape, treeNodeButtonBox[3], 1, 6);
        treeNodeButtonBox[1].arrowTo(shape, treeNodeButtonBox[4], 1, 6);
        treeNodeButtonBox[2].arrowTo(shape, treeNodeButtonBox[5], 1, 6);
        treeNodeButtonBox[2].arrowTo(shape, treeNodeButtonBox[6], 1, 6);

        // draw data items
        

        // draw button outlines
        

        // draw text
        BenHelper.textDrawCentre(batch, font, 
            "Tree", 
            titleButtonBox, 1.5f,
            BenHelper.DEFAULT_TEXT_COLOUR
        );
        BenHelper.textDrawCentre(batch, font, 
            "Back", 
            backButtonBox, 1.25f
        );
        BenHelper.textDrawCentre(batch, font, 
            hintTextString, 
            hintButtonBox, 0.75f,
            Color.GRAY
        );
        for (int i = 0; i < TREESIZE; i++) {
            BenHelper.textDrawCentre(batch, font, 
                nodeText[i], 
                treeNodeButtonBox[i], 
                1f   // wip
            );
        }


        // debug: draw hitboxes
        if (BenHelper.DEBUG) {
            titleButtonBox.draw(shape, Color.RED);
            backButtonBox.draw(shape, Color.RED);
            hintButtonBox.draw(shape, Color.RED);
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