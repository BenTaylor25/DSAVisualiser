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
    String resultTextString;
    String targetTextString;
    BenHelper.Rect titleButtonBox;
    BenHelper.Rect backButtonBox;
    BenHelper.Rect hintButtonBox;
    BenHelper.Rect[] arrayButtonBoxes;
    BenHelper.Rect searchButtonBox;
    BenHelper.Rect targetButtonBox;
    BenHelper.Rect resultButtonBox;
    int[] activeButtonInds;

    @Override
    public void create() {
        hintTextString = "";
        resultTextString = "";
        titleButtonBox = new BenHelper.Rect(GW*0.3f, GH*0.85f, GW*0.4f, GH*0.15f);
        backButtonBox = new BenHelper.Rect(0, 0, GW*0.1f, GH*0.1f);
        hintButtonBox = new BenHelper.Rect(GW*0.15f, 0, GW*0.7f, GH*0.1f);
        searchButtonBox = new BenHelper.Rect(GW*0.425f, GH*0.3f, GW*0.15f, GH*0.15f);
        targetButtonBox = new BenHelper.Rect(GW*0.12f, GH*0.15f, GW*0.15f, GW*0.15f);
        resultButtonBox = new BenHelper.Rect(GW*0.4f, GH*0.1f, GW*0.2f, GH*0.1f);
        targetTextString = "";

        typingSelector = -1;
        generator = new GBinarySearch(new String[ARRAYSIZE]);
        arrayButtonBoxes = new BenHelper.Rect[ARRAYSIZE];
        for (int i = 0; i < ARRAYSIZE; i++) {
            generator.arr[i] = "";
            arrayButtonBoxes[i] = new BenHelper.Rect(GW*0.05f, GH*0.5f, GW*0.15f, GW*0.15f);
            arrayButtonBoxes[i].x += i * arrayButtonBoxes[i].w;
        }
    }

    @Override
    public void reset() {
        generator = new GBinarySearch(new String[ARRAYSIZE]);
        for (int i = 0; i < ARRAYSIZE; i++) {
            generator.arr[i] = "";
        }
        targetTextString = "";
        typingSelector = -1;
        activeButtonInds = new int[]{-1, -1};
        searching = false;
    }

    @Override
    public String updateBefore(String factSelector) {
        if (BenHelper.screenClicked() && !searching) {
            typingSelector = -1;
            if (targetButtonBox.checkHover()) {
                typingSelector = ARRAYSIZE;
            }
            else {
                for (int i = 0; i < ARRAYSIZE; i++) {
                    if (arrayButtonBoxes[i].checkHover()) {
                        typingSelector = i;
                        break;
                    }
                }
            }
        }      

        // hint text
        hintTextString = "";

        boolean arrayHover = false;
        for (BenHelper.Rect x : arrayButtonBoxes) {
            if (x.checkHover()) {
                arrayHover = true;
            }
        }

        if (titleButtonBox.checkHover()) {
            hintTextString = "Binary Search is where you compare the item in middle of the\n" +
                "array first so that you can rule out half of the items at once.\n" +
                "The array must be sorted. O(log n) Time, O(1) Space.";
        }
        else if (arrayHover && !searching) {
            hintTextString = "Enter data to search through.";
        }
        else if (targetButtonBox.checkHover() && !searching) {
            hintTextString = "Enter a value to search the array for.";
        }
        else if (searchButtonBox.checkHover()) {
            boolean showFill = !generator.isFull();
            boolean showSort = !generator.isSorted();
            boolean showSearch = !searching;
            boolean showNext = searching && generator.hasNext;
            boolean showFinish = searching && !generator.hasNext;

            if (showFill) {
                hintTextString = "The array items and the target must be set to perform the \n" +
                    "Algorithm.";
            }
            else if (showSort) {
                hintTextString = "The array must be sorted for the Binary Search to work.\n" +
                    "This is because we rule out half of the array every iteration;\n" +
                    "If leftItems < current and current < target, then leftItems < target.";
            }
            else if (showSearch) {
                hintTextString = "Begin the Binary Search.\n" +
                    "We start from the middle of the Array \n" +
                    "(left middle on even length array)";
            }
            else if (showNext) {
                hintTextString = "The current item is not equal to the target so we need to keep\n" +
                    "looking. If the current is too small, so will all items to the\n" +
                    "left, so we can just look at the right. (and vice versa).";
            }
            else if (showFinish) {
                hintTextString = "When either the target has been found, or there are no\n" +
                    "more spaces to check, the Algorithm has been completed.\n" +
                    "Click to return to editing mode.";
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

        // draw button outlines
        for (int i = 0; i < ARRAYSIZE; i++) {
            arrayButtonBoxes[i].draw(shape);
        }
        targetButtonBox.draw(shape);
        if (typingSelector != -1) {
            if (typingSelector == ARRAYSIZE) {
                targetButtonBox.draw(shape, Color.WHITE);
            }
            else {
                arrayButtonBoxes[typingSelector].draw(shape, Color.WHITE);
            }
        }
        if (activeButtonInds[0] >= 0 && activeButtonInds[1] >= 0) {
            for (int x : activeButtonInds) {
                arrayButtonBoxes[x].draw(shape, Color.WHITE);
            }
        }
        searchButtonBox.draw(shape);
        if (generator.selected >= 0) {
            arrayButtonBoxes[generator.selected].draw(shape, Color.WHITE);
        } 
        else if (generator.selected == ARRAYSIZE) {
            targetButtonBox.draw(shape, Color.WHITE);
        }

        // draw text
        BenHelper.textDrawCentre(batch, font, "Binary Search", titleButtonBox, 1.5f);
        BenHelper.textDrawCentre(batch, font, "Back", backButtonBox, 1.25f);
        BenHelper.textDrawCentre(batch, font, hintTextString, hintButtonBox, 
            0.75f, Color.GRAY
        );
        BenHelper.textDrawCentre(batch, font, resultTextString, resultButtonBox,
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
        boolean full = !targetTextString.equals("");
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
        String targetDrawText = targetTextString;
        if (targetDrawText.length() == 0) { targetDrawText = "[target]"; }
        BenHelper.textDrawCentre(
            batch, font, targetDrawText, 
            targetButtonBox, 1f
        );

        // debug: draw hitboxes
        if (BenHelper.DEBUG) {
            titleButtonBox.draw(shape, Color.RED);
            backButtonBox.draw(shape, Color.RED);
            hintButtonBox.draw(shape, Color.RED);
            resultButtonBox.draw(shape, Color.RED);
        }
    }

    @Override
    public String updateAfter(String factSelector) {
        if (backButtonBox.checkClick()) {
            factSelector = "menu";
        }

        // typing
        if (typingSelector != -1 && !searching) {
            if (typingSelector == ARRAYSIZE) {
                targetTextString = BenHelper.typing(
                    targetTextString,
                    10
                );
            }
            else {
                generator.arr[typingSelector] = BenHelper.typing(
                    generator.arr[typingSelector],
                    10
                );
            }
        }
        
        boolean anyEmpty = targetTextString.equals("");
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
                    generator.next(targetTextString);

                    if (!generator.hasNext) {
                        resultTextString = generator.found ? "found" : "not found";
                    }
                }
                else {
                    // finish
                    generator.reset();
                    searching = false;
                    resultTextString = "";
                }
            }
        }

        return factSelector;
    }
}
