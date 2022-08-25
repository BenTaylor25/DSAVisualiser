package com.mygdx.dsav.Screens;

import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.FactOption;
import com.mygdx.dsav.Generators.GBubbleSort;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;

public class BubbleSortScreen extends FactOption {
    final int ARRAYSIZE = 6;
    boolean sorting;
    GBubbleSort generator;
    int typingSelector;
    String hintTextString;
    BenHelper.Rect titleButtonBox;
    BenHelper.Rect backButtonBox;
    BenHelper.Rect hintButtonBox;
    BenHelper.Rect[] arrayButtonBoxes;
    BenHelper.Rect sortButtonBox;
    int[] activeButtonInds;

    @Override
    public void create() {
        sorting = false;
        titleButtonBox = new BenHelper.Rect(GW*0.35f, GH*0.85f, GW*0.3f, GH*0.15f);
        backButtonBox = new BenHelper.Rect(0, 0, GW*0.1f, GH*0.1f);
        hintButtonBox = new BenHelper.Rect(GW*0.15f, 0, GW*0.7f, GH*0.1f);
        sortButtonBox = new BenHelper.Rect(GW*0.425f, GH*0.2f, GW*0.15f, GH*0.15f);
        hintTextString = "";
        activeButtonInds = new int[]{-1, -1};

        typingSelector = -1;
        generator = new GBubbleSort(new String[ARRAYSIZE]);
        arrayButtonBoxes = new BenHelper.Rect[ARRAYSIZE];
        for (int i = 0; i < ARRAYSIZE; i++) {
            generator.arr[i] = "";
            arrayButtonBoxes[i] = new BenHelper.Rect(GW*0.05f, GH*0.4f, GW*0.15f, GW*0.15f);
            arrayButtonBoxes[i].x += i * arrayButtonBoxes[i].w;
        }
    }

    @Override
    public void reset() {
        generator = new GBubbleSort(new String[ARRAYSIZE]);
        for (int i = 0; i < ARRAYSIZE; i++) {
            generator.arr[i] = "";
        }
        typingSelector = -1;
        activeButtonInds = new int[]{-1, -1};
    }

    @Override
    public String updateBefore(String factSelector) {
        if (BenHelper.screenClicked() && !sorting) {
            typingSelector = -1;
            for (int i = 0; i < ARRAYSIZE; i++) {
                if (arrayButtonBoxes[i].checkHover()) {
                    typingSelector = i;
                    break;
                }
            }
        }

        hintTextString = "";
        boolean hoverOnAnyIndex = false;
        for (int i = 0; i < ARRAYSIZE; i++) {
            hoverOnAnyIndex = hoverOnAnyIndex ||
                arrayButtonBoxes[i].checkHover();
        }
        if (hoverOnAnyIndex) {
            hintTextString = "Arrays are quicker to sort than Linked Lists\n"+
                "because you can access them in O(1) time.";
        }
        else if (titleButtonBox.checkHover()) {
            hintTextString = "Bubble Sort is a simple, but inefficient Sorting Algorithm.\n"+
                "Check adjacent pairs along the array and stop when\n"+
                "a full cycle is completed with no swaps. O(n^2) Time, O(1) Space.";
        }
        else if (sortButtonBox.checkHover()) {
            if (!sorting) {   // "Sort" is shown
                hintTextString = "Click to start sorting.\n"+
                    "Make sure every index has a value first.";
            }
            else if (generator.hasNext) {   // "Next" is shown
                hintTextString = "The highlighted squares are the indices that have just been\n"+
                    "checked. Click to check the next indices.";
            }
            else {   // "Finish" is shown
                hintTextString = "The Algorithm is finished. The list should be in order.\n"+
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
        if (typingSelector != -1) {
            arrayButtonBoxes[typingSelector].draw(shape, BenHelper.HOVER_TEXT_COLOUR);
        }
        if (activeButtonInds[0] >= 0 && activeButtonInds[1] >= 0) {
            for (int x : activeButtonInds) {
                arrayButtonBoxes[x].draw(shape, BenHelper.HOVER_TEXT_COLOUR);
            }
        }
        sortButtonBox.draw(shape);

        // draw text 
        BenHelper.textDrawCentre(batch, font, "Bubble Sort", titleButtonBox,
            1.5f, BenHelper.DEFAULT_TEXT_COLOUR
        );
        for (int i = 0; i < ARRAYSIZE; i++) {
            String drawValue = generator.arr[i];
            String[] indexWords = {"[zero]", "[one]", "[two]", "[three]", "[four]", "[five]"};
            if (drawValue.equals("")) { drawValue = indexWords[i]; }
            BenHelper.textDrawCentreSelectable(
                batch, font, drawValue, 
                arrayButtonBoxes[i], 1f, 
                (typingSelector == i)
            );
        }

        String text = "Sort";
        if (sorting) text = "Next";
        if (!generator.hasNext) text = "Finish";
        BenHelper.textDrawCentre(batch, font, text, sortButtonBox, 1.25f);

        BenHelper.textDrawCentre(batch, font, "Back", backButtonBox, 1.25f);
        BenHelper.textDrawCentre(batch, font, hintTextString, hintButtonBox, 0.75f,
            BenHelper.HINT_TEXT_COLOUR);
        

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

        if (typingSelector != -1 && !sorting) {
            generator.arr[typingSelector] = BenHelper.typing(
                generator.arr[typingSelector],
                10
            );
        }

        boolean anyEmpty = false;
        for (String x : generator.arr) {
            anyEmpty = anyEmpty || x.equals("");
        }
        if (sortButtonBox.checkClick() && !anyEmpty) {
            if (generator.hasNext) {
                sorting = true;
                activeButtonInds = generator.getInds();
                generator.next();
            } else {
                sorting = false;
                generator.hasNext = true;
                activeButtonInds = new int[]{-1, -1};
            }
        }

        return factSelector;
    }
}
