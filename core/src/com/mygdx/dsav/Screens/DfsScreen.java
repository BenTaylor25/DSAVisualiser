package com.mygdx.dsav.Screens;

import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.FactOption;

import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;

public class DfsScreen extends FactOption {
    final int TREESIZE = 7;
    boolean algActive;
    int typingSelector;
    String hintTextString;
    String[] nodeText;
    String orderText;
    BenHelper.Rect titleButtonBox;
    BenHelper.Rect backButtonBox;
    BenHelper.Rect hintButtonBox;
    BenHelper.Rect[] treeNodeButtonBox;
    BenHelper.Rect algButtonBox;
    BenHelper.Rect orderButtonBox;

    @Override
    public void create() {
        algActive = false;
        typingSelector = -1;
        hintTextString = "";
        orderText = "Order:\n------\n";
        nodeText = new String[TREESIZE];
        titleButtonBox = new BenHelper.Rect(GW*0.275f, GH*0.85f, GW*0.425f, GH*0.15f);
        backButtonBox = new BenHelper.Rect(0, 0, GW*0.1f, GH*0.1f);
        hintButtonBox = new BenHelper.Rect(GW*0.15f, 0, GW*0.7f, GH*0.1f);
        algButtonBox = new BenHelper.Rect(GW*0.1f, GH*0.7f, GW*0.15f, GH*0.15f);
        orderButtonBox = new BenHelper.Rect(GW*0.75f, GH*0.4f, GW*0.15f, GH*0.45f);

        treeNodeButtonBox = new BenHelper.Rect[TREESIZE];
        treeNodeButtonBox[0] = new BenHelper.Rect(GW*0.425f, GH*0.664f, GW*0.15f, GH*0.15f);
        treeNodeButtonBox[1] = new BenHelper.Rect(GW*0.275f, GH*0.426f, GW*0.15f, GH*0.15f);
        treeNodeButtonBox[2] = new BenHelper.Rect(GW*0.575f, GH*0.426f, GW*0.15f, GH*0.15f);
        treeNodeButtonBox[3] = new BenHelper.Rect(GW*0.125f, GH*0.188f, GW*0.15f, GH*0.15f);
        treeNodeButtonBox[4] = new BenHelper.Rect(GW*0.313f, GH*0.188f, GW*0.15f, GH*0.15f);
        treeNodeButtonBox[5] = new BenHelper.Rect(GW*0.537f, GH*0.188f, GW*0.15f, GH*0.15f);
        treeNodeButtonBox[6] = new BenHelper.Rect(GW*0.725f, GH*0.188f, GW*0.15f, GH*0.15f);

        nodeText = new String[TREESIZE];
        for (int i = 0; i < TREESIZE; i++) {
            nodeText[i] = "";
        }
    }

    @Override
    public void reset() {
        typingSelector = -1;
        
        nodeText = new String[TREESIZE];
        for (int i = 0; i < TREESIZE; i++) {
            nodeText[i] = "";
        }
    }

    @Override
    public String updateBefore(String factSelector) {
        /*
        hintTextString = "";
        if (titleButtonBox.checkHover()) {
            hintTextString = "Trees are a type of Directed Graph that maintain a hierarchical\n"+
                "structure. Examples: Computer file structure, OOP Class Structure,\n"+
                "HTML Document Object Model (Web Development).";
        }
        else {
            int ind = 0;
            while (ind < TREESIZE && !treeNodeButtonBox[ind].checkHover()) {
                ind++;
            }

            if (ind == 0) {   // hovering on a root node
                hintTextString = "The root node of a Tree has no parent Nodes.";
            }
            else if (ind < 3) {   // hovering on a middle layer node
                hintTextString = "Nodes in a Tree are stored with a parent reference and child\n"+
                    "references. In order to access one Node from another on the other\n"+
                    "side of the tree, you must go through the common ancestor.";
            }
            else if (ind < TREESIZE) {   // hovering on a leaf node
                hintTextString = "Leaf Nodes in a Tree are those that have no children.";
            }
        }
        */
        
        // typing
        if (BenHelper.screenClicked()) {
            typingSelector = -1;

            for (int i = 0; i < TREESIZE; i++) {
                if (treeNodeButtonBox[i].checkHover()) {
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
        for (int i = 0; i < TREESIZE; i++) {
            treeNodeButtonBox[i].draw(shape);
        }
        if (typingSelector >= 0) {
            treeNodeButtonBox[typingSelector].draw(shape, BenHelper.HOVER_TEXT_COLOUR);
        }
        
        // draw arrows
        treeNodeButtonBox[0].arrowTo(shape, treeNodeButtonBox[1], 1, 6);
        treeNodeButtonBox[0].arrowTo(shape, treeNodeButtonBox[2], 1, 6);
        treeNodeButtonBox[1].arrowTo(shape, treeNodeButtonBox[3], 1, 6);
        treeNodeButtonBox[1].arrowTo(shape, treeNodeButtonBox[4], 1, 6);
        treeNodeButtonBox[2].arrowTo(shape, treeNodeButtonBox[5], 1, 6);
        treeNodeButtonBox[2].arrowTo(shape, treeNodeButtonBox[6], 1, 6);

        algButtonBox.draw(shape);

        // draw data items
        String[] defaults = { "[root]", "[A]", "[B]", "[leaf]", "[leaf]", "[leaf]", "[leaf]" };
        for (int i = 0; i < TREESIZE; i++) {
            String text = nodeText[i];
            if (text.equals("")) { text = defaults[i]; }
            BenHelper.textDrawCentre(batch, font, 
                text, 
                treeNodeButtonBox[i], 
                1f
            );
        }

        // draw text
        BenHelper.textDrawCentre(batch, font, 
            "Depth First Search", 
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
        String algText = "algText";
        // if (algActive) {
        //     text = generator.hasNext ? "Next" : "Finish";
        // } else {
        //     text = "[Connect]";
        //     if (generator.graph.isConnected()) { 
        //         text = typingSelector == -1 ? "[Select]" : "Start";
        //     }
        // }
        BenHelper.textDrawCentre(batch, font, 
            algText, algButtonBox, 1f
        );
        // BenHelper.textDrawCentre(batch, font, 
        //     orderText, orderButtonBox, 1f,
        //     BenHelper.DEFAULT_TEXT_COLOUR
        // );
        if (algActive) {
            batch.begin();
                font.draw(
                    batch, orderText, 
                    orderButtonBox.x + orderButtonBox.w/5, 
                    orderButtonBox.y + orderButtonBox.h
                );
            batch.end();
        }

        // debug: draw hitboxes
        if (BenHelper.DEBUG) {
            titleButtonBox.draw(shape, Color.RED);
            backButtonBox.draw(shape, Color.RED);
            hintButtonBox.draw(shape, Color.RED);
            orderButtonBox.draw(shape, Color.RED);
        }
    }

    @Override
    public String updateAfter(String factSelector) {
        if (backButtonBox.checkClick()) {
            factSelector = "menu";
        }

        if (typingSelector >= 0) {
            nodeText[typingSelector] = BenHelper.typing(
                nodeText[typingSelector],
                10
            );
        }
        
        return factSelector;
    }
}