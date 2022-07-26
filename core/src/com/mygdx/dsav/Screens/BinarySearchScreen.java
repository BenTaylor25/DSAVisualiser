package com.mygdx.dsav.Screens;

import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.FactOption;
import com.mygdx.dsav.Generators.GBinarySearch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;

public class BinarySearchScreen extends FactOption {
    final int ARRAYSIZE = 6;
    boolean searching;
    GBinarySearch generator;
    int typingSelector;
    String hintTextString;
    BenHelper.Rect titleButtonBox;
    BenHelper.Rect backButtonBox;
    BenHelper.Rect hintButtonBox;
    BenHelper.Rect[] arrayButtonBoxes;
    BenHelper.Rect searchButtonBox;
    int[] activeButtonInds;

    @Override
    public void create() {
        hintTextString = "";
        titleButtonBox = new BenHelper.Rect(GW*0.3f, GH*0.85f, GW*0.4f, GH*0.15f);
        backButtonBox = new BenHelper.Rect(0, 0, GW*0.1f, GH*0.1f);
        hintButtonBox = new BenHelper.Rect(GW*0.15f, 0, GW*0.7f, GH*0.1f);
        searchButtonBox = new BenHelper.Rect(GW*0.425f, GH*0.2f, GW*0.15f, GH*0.15f);

        typingSelector = -1;
        generator = new GBinarySearch(new String[ARRAYSIZE]);
        arrayButtonBoxes = new BenHelper.Rect[ARRAYSIZE];
        for (int i = 0; i < ARRAYSIZE; i++) {
            generator.arr[i] = "";
            arrayButtonBoxes[i] = new BenHelper.Rect(GW*0.05f, GH*0.4f, GW*0.15f, GW*0.15f);
            arrayButtonBoxes[i].x += i * arrayButtonBoxes[i].w;
        }
    }

    @Override
    public void reset() {
        generator = new GBinarySearch(new String[ARRAYSIZE]);
        for (int i = 0; i < ARRAYSIZE; i++) {
            generator.arr[i] = "";
        }
        typingSelector = -1;
        activeButtonInds = new int[]{-1, -1};
    }

    @Override
    public String updateBefore(String factSelector) {
        if (BenHelper.screenClicked() && !searching) {
            typingSelector = -1;
            for (int i = 0; i < ARRAYSIZE; i++) {
                if (arrayButtonBoxes[i].checkHover()) {
                    typingSelector = i;
                    break;
                }
            }
        }      

        // hint text

        return factSelector;
    }

    @Override
    public void draw() {
        // clear screen
        batch.begin();
            ScreenUtils.clear(BenHelper.BACKGROUND_COLOUR);
            font.setColor(BenHelper.DEFAULT_TEXT_COLOUR);
        batch.end();

        // draw button outlines
        for (int i = 0; i < ARRAYSIZE; i++) {
            arrayButtonBoxes[i].draw(shape);
        }
        if (typingSelector != -1) {
            arrayButtonBoxes[typingSelector].draw(shape, Color.WHITE);
        }
        if (activeButtonInds[0] >= 0 && activeButtonInds[1] >= 0) {
            for (int x : activeButtonInds) {
                arrayButtonBoxes[x].draw(shape, Color.WHITE);
            }
        }
        searchButtonBox.draw(shape);

        // draw text
        BenHelper.textDrawCentre(batch, font, "Binary Search", titleButtonBox, 1.5f);
        BenHelper.textDrawCentre(batch, font, "Back", backButtonBox, 1.25f);
        BenHelper.textDrawCentre(batch, font, hintTextString, hintButtonBox, 
            0.75f, Color.GRAY
        );
        String[] indexWords = {"[zero]", "[one]", "[two]", "[three]", "[four]", "[five]"};
        for (int i = 0; i < ARRAYSIZE; i++) {
            String drawValue = generator.arr[i];
            if (drawValue.equals("")) { drawValue = indexWords[i]; }
            BenHelper.textDrawCentreSelectable(
                batch, font, drawValue, 
                arrayButtonBoxes[i], 1f,
                (typingSelector == i)
            );
        }
        // Search Button Text
        boolean full = true;
        for (String x : generator.arr) {
            full = full && !x.equals("");
        }
        String searchButtonText = "[fill]";
        if (full) {
            searchButtonText = "Sort";
            if (generator.isSorted()) {
                searchButtonText = "Search";
                if (searching) {
                    searchButtonText = "Next";
                    if (!generator.hasNext) {
                        searchButtonText = "Finish";
                    }
                }
            }
        }
        BenHelper.textDrawCentre(
            batch, font, searchButtonText, 
            searchButtonBox, 1.25f
        );

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

        // typing
        if (typingSelector != -1 && !searching) {
            generator.arr[typingSelector] = BenHelper.typing(
                generator.arr[typingSelector],
                10
            );
        }
        
        boolean anyEmpty = false;
        for (String x : generator.arr) {
            anyEmpty = anyEmpty || x.equals("");
        }
        if (searchButtonBox.checkClick() && !anyEmpty) {
            if (!generator.isSorted()) {
                generator.sort();
            }
            else {
                searching = true;
                if (generator.hasNext) {
                    // next step of search
                }
                else {
                    // finish
                }
            }
        }


        return factSelector;
    }
}
