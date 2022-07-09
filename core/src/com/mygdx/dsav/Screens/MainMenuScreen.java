package com.mygdx.dsav.Screens;

import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.FactOption;

import java.awt.Desktop;
import java.net.URL;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;


public class MainMenuScreen extends FactOption {
    BenHelper.Rect titleButtonBox;
    BenHelper.Rect exitButtonBox;
    BenHelper.Rect myGHButtonBox;
    BenHelper.Rect fontLinkButtonBox;

    BenHelper.Rect arrayButtonBox;
    BenHelper.Rect llistButtonBox;
    BenHelper.Rect stackButtonBox;
    BenHelper.Rect queueButtonBox;
    BenHelper.Rect graphButtonBox;
    BenHelper.Rect treeButtonBox;
    BenHelper.Rect bubbleButtonBox;
    BenHelper.Rect binSrchButtonBox;
    BenHelper.Rect primsButtonBox;
    BenHelper.Rect dijkstrasButtonBox;
    BenHelper.Rect dfsButtonBox;
    BenHelper.Rect bfsButtonBox;

    @Override
    public void create() {
        titleButtonBox = new BenHelper.Rect(GW*0.2f, GH*0.81f, GW*0.6f, GH*0.1f);
        exitButtonBox = new BenHelper.Rect(GW*0.9f, 0, GW*0.1f, GH*0.09f);
        myGHButtonBox = new BenHelper.Rect(GW*0.001f, GH*0.06f, GW*0.175f, GH*0.08f);
        fontLinkButtonBox = new BenHelper.Rect(GW*0.001f, GH*0.001f, GW*0.38f, GH*0.05f);

        arrayButtonBox = new BenHelper.Rect(GW*0.2f, GH*0.725f, GW*0.3f, GH*0.085f);
        llistButtonBox = new BenHelper.Rect(GW*0.2f, GH*0.64f, GW*0.3f, GH*0.085f);
        stackButtonBox = new BenHelper.Rect(GW*0.2f, GH*0.555f, GW*0.3f, GH*0.085f);
        queueButtonBox = new BenHelper.Rect(GW*0.2f, GH*0.47f, GW*0.3f, GH*0.085f);
        graphButtonBox = new BenHelper.Rect(GW*0.2f, GH*0.385f, GW*0.3f, GH*0.085f);
        treeButtonBox = new BenHelper.Rect(GW*0.2f, GH*0.3f, GW*0.3f, GH*0.085f);

        bubbleButtonBox = new BenHelper.Rect(GW*0.5f, GH*0.725f, GW*0.3f, GH*0.085f);
        binSrchButtonBox = new BenHelper.Rect(GW*0.5f, GH*0.64f, GW*0.3f, GH*0.085f);
        primsButtonBox = new BenHelper.Rect(GW*0.5f, GH*0.555f, GW*0.3f, GH*0.085f);
        dijkstrasButtonBox = new BenHelper.Rect(GW*0.5f, GH*0.47f, GW*0.3f, GH*0.085f);
        dfsButtonBox = new BenHelper.Rect(GW*0.5f, GH*0.385f, GW*0.3f, GH*0.085f);
        bfsButtonBox = new BenHelper.Rect(GW*0.5f, GH*0.3f, GW*0.3f, GH*0.085f);
    }

    @Override
    public void reset() {}

    @Override
    public String updateBefore(String factSelector) {
        return factSelector;
    }

    @Override
    public void draw() {
        batch.begin();
            ScreenUtils.clear(BenHelper.BACKGROUND_COLOUR);
            font.setColor(BenHelper.DEFAULT_TEXT_COLOUR);
        batch.end();

        BenHelper.textDrawCentre(batch, font, "DSAVisualiser", titleButtonBox, 2,
            BenHelper.DEFAULT_TEXT_COLOUR);
        BenHelper.textDrawCentre(batch, font, "Ben Taylor", myGHButtonBox, 1);
        BenHelper.textDrawCentre(batch, font, "Thanks to Riciery Leal for the VCR OSD Mono font", 
            fontLinkButtonBox, 0.5f);
        BenHelper.textDrawCentre(batch, font, "Exit", exitButtonBox, 1f);

        BenHelper.textDrawCentre(batch, font, "Array", arrayButtonBox, 1.125f);
        BenHelper.textDrawCentre(batch, font, "Linked List", llistButtonBox, 1.125f);
        BenHelper.textDrawCentre(batch, font, "Stack", stackButtonBox, 1.125f);
        BenHelper.textDrawCentre(batch, font, "Queue", queueButtonBox, 1.125f);
        BenHelper.textDrawCentre(batch, font, "Graph", graphButtonBox, 1.125f);
        BenHelper.textDrawCentre(batch, font, "Tree", treeButtonBox, 1.125f);

        BenHelper.textDrawCentre(batch, font, "Bubble Sort", bubbleButtonBox, 1.125f);
        BenHelper.textDrawCentre(batch, font, "Binary Search", binSrchButtonBox, 1.125f);
        BenHelper.textDrawCentre(batch, font, "Prim's", primsButtonBox, 1.125f);
        BenHelper.textDrawCentre(batch, font, "Dijkstra's", dijkstrasButtonBox, 1.125f);
        BenHelper.textDrawCentre(batch, font, "DFS", dfsButtonBox, 1.125f);
        BenHelper.textDrawCentre(batch, font, "BFS", bfsButtonBox, 1.125f);

        if (BenHelper.DEBUG) {
            titleButtonBox.draw(shape, Color.RED);
            exitButtonBox.draw(shape, Color.RED);
            myGHButtonBox.draw(shape, Color.RED);
            fontLinkButtonBox.draw(shape, Color.RED);
            
            arrayButtonBox.draw(shape, Color.RED);
            llistButtonBox.draw(shape, Color.RED);
            stackButtonBox.draw(shape, Color.RED);
            queueButtonBox.draw(shape, Color.RED);
            graphButtonBox.draw(shape, Color.RED);
            treeButtonBox.draw(shape, Color.RED);

            bubbleButtonBox.draw(shape, Color.RED);
            binSrchButtonBox.draw(shape, Color.RED);
            primsButtonBox.draw(shape, Color.RED);
            dijkstrasButtonBox.draw(shape, Color.RED);
            dfsButtonBox.draw(shape, Color.RED);
            bfsButtonBox.draw(shape, Color.RED);
        }
    }

    @Override
    public String updateAfter(String factSelector) {
        if (BenHelper.screenClicked()) {
            if (arrayButtonBox.checkHover()) {
                factSelector = "array";
            }
            else if (llistButtonBox.checkHover()) {
                factSelector = "llist";
            }
            else if (stackButtonBox.checkHover()) {
                factSelector = "stack";
            }
            else if (queueButtonBox.checkHover()) {
                factSelector = "queue";
            }
            else if (graphButtonBox.checkHover()) {
                factSelector = "graph";
            }
            else if (treeButtonBox.checkHover()) {
                factSelector = "tree";
            }
            else if (bubbleButtonBox.checkHover()) {
                factSelector = "bubble";
            }
            else if (binSrchButtonBox.checkHover()) {
                //factSelector = "binsrch";
            }
            else if (primsButtonBox.checkHover()) {
                //factSelector = "prim";
            }
            else if (dijkstrasButtonBox.checkHover()) {
                //factSelector = "dijkstras";
            }
            else if (dfsButtonBox.checkHover()) {
                //factSelector = "dfs";
            }
            else if (bfsButtonBox.checkHover()) {
                //factSelector = "bfs";
            }
            else if (myGHButtonBox.checkHover()) {
                try {
                    Desktop.getDesktop().browse(new URL("https://github.com/BenTaylor25").toURI());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (fontLinkButtonBox.checkHover()) {
                try {
                    Desktop.getDesktop().browse(new URL("https://www.dafont.com/vcr-osd-mono.font").toURI());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (exitButtonBox.checkHover()) {
                factSelector = "quit";
            }
        }

        return factSelector;
    }
}
