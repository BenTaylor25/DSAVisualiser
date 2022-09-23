package com.mygdx.dsav.Screens;

import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.FactOption;
import com.mygdx.dsav.Generators.GDfsAlg;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;

public class DfsScreen extends FactOption {
    final int TREESIZE = 7;
    GDfsAlg generator;
    boolean algActive;
    int typingSelector;
    String hintTextString;
    ArrayList<String> orderText;
    BenHelper.Rect titleButtonBox;
    BenHelper.Rect backButtonBox;
    BenHelper.Rect hintButtonBox;
    BenHelper.Rect[] treeNodeButtonBox;
    BenHelper.Rect algButtonBox;
    BenHelper.Rect orderButtonBox;

    @Override
    public void create() {
        generator = new GDfsAlg(TREESIZE);
        algActive = false;
        typingSelector = -1;
        hintTextString = "";
        orderText = new ArrayList<>();
        orderText.add("Order:");
        orderText.add("------");
        titleButtonBox = new BenHelper.Rect(GW*0.275f, GH*0.85f, GW*0.45f, GH*0.15f);
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
    }

    @Override
    public void reset() {
        typingSelector = -1;

        orderText = new ArrayList<>();
        orderText.add("Order:");
        orderText.add("------");

        algActive = false;
        
        generator = new GDfsAlg(TREESIZE);
    }

    @Override
    public String updateBefore(String factSelector) {
        
        hintTextString = "";
        if (titleButtonBox.checkHover()) {
            hintTextString = "Depth First Search is a Graph Searching Algorithm. For every\n"+
                "neighbour you visit, visit all of their neighbours before returning\n"+
                "back. DFS is implemented using a Stack. O(N) Time, O(H) Space.";
        }
        else if (algButtonBox.checkHover()) {
            if (algActive) {
                if (generator.hasNext) {
                    // "Next" is shown
                    hintTextString = "Click to visit the next node.";
                } else {
                    // "Finish" is shown
                    hintTextString = "The Algorithm is finished.\n"+
                        "Click to return to editing mode.";
                }
            } else {
                if (generator.allNamed()) {
                    // "Start" is shown
                    hintTextString = "Click to start the Algorithm.\n"+
                        "The Graph isn't required to be a Tree for the Algorithm\n"+
                        "to work but I think it makes it easier to understand.";
                } else {
                    // "[Name]" is shown
                    hintTextString = "Please give each node a value.";
                }
            }
        }
        else if (orderButtonBox.checkHover() && algActive) {
            hintTextString = "This list keeps track of the order that the nodes were \n"+
                "visited. This is going to be different for DFS and BFS.";
        }
        else {
            for (int ind = 0; ind < TREESIZE; ind++) {
                if (treeNodeButtonBox[ind].checkHover()) {
                    // hover over one of the nodes
                    hintTextString = "Click to edit node values.";
                    break;
                }
            }
        }
        
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
            String text = generator.nodeValues[i];
            if (text.equals("")) { text = defaults[i]; }
            BenHelper.textDrawCentre(batch, font, 
                text, 
                treeNodeButtonBox[i], 
                1f
            );
        }

        // alg traversal
        for (int i = 0; i < TREESIZE-1; i++) {
            for (int j = i+1; j < TREESIZE; j++) {
                if (generator.visitedTree.nodes.get(i).connections.contains(j)) {
                    treeNodeButtonBox[i].arrowTo(shape, 
                        treeNodeButtonBox[j], 
                        1, 6,
                        BenHelper.HOVER_TEXT_COLOUR
                    );
                }
            }
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
            BenHelper.HINT_TEXT_COLOUR
        );
        String algText = "algText";
        if (algActive) {
            algText = generator.hasNext ? "Next" : "Finish";
        } else {
            algText = "[Name]";
            if (generator.allNamed()) { 
                algText = "Start";
            }
        }
        BenHelper.textDrawCentre(batch, font, 
            algText, algButtonBox, 1f
        );
        if (algActive) {
            for (int i = 0; i < orderText.size(); i++) {
                BenHelper.textDrawCentre(batch, font, 
                    orderText.get(i), 
                    orderButtonBox.x + orderButtonBox.w / 2, 
                    orderButtonBox.y + orderButtonBox.h - (i+1.3f)*30, 
                    1f, BenHelper.DEFAULT_TEXT_COLOUR
                );
            }
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
            generator.nodeValues[typingSelector] = BenHelper.typing(
                generator.nodeValues[typingSelector],
                10
            );
        }
        
        if (algButtonBox.checkClick()) {
            if (algActive) {
                if (generator.hasNext) {
                    int i = generator.next();
                    orderText.add(generator.nodeValues[i]);
                } else {
                    algActive = false;
                    generator.reset();
                    orderText = new ArrayList<>();
                    orderText.add("Order:");
                    orderText.add("------");
                }
            } else {
                if (generator.allNamed()) {
                    algActive = true;
                    orderText.add(generator.nodeValues[0]);
                }
            }
        }

        return factSelector;
    }
}
