package com.mygdx.dsav.Screens;

import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.FactOption;
import com.badlogic.gdx.graphics.Color;

public class ArrayScreen extends FactOption {
    final int ARRAYSIZE = 6;
    String[] arrayTextString;
    int typingSelector;
    String hintTextString;
    BenHelper.Rect titleButtonBox;
    BenHelper.Rect backButtonBox;
    BenHelper.Rect[] arrayButtonBoxes;
    BenHelper.Rect hintButtonBox;

    @Override
    public void create() {
        titleButtonBox = new BenHelper.Rect(GW*0.4f, GH*0.85f, GW*0.2f, GH*0.15f);
        backButtonBox = new BenHelper.Rect(0, 0, GW*0.1f, GH*0.1f);
        hintButtonBox = new BenHelper.Rect(GW*0.15f, 0, GW*0.7f, GH*0.1f);
        hintTextString = "";

        typingSelector = -1;
        arrayTextString = new String[ARRAYSIZE];
        arrayButtonBoxes = new BenHelper.Rect[ARRAYSIZE];
        for (int i = 0; i < ARRAYSIZE; i++) {
            arrayTextString[i] = "";
            arrayButtonBoxes[i] = new BenHelper.Rect(GW*0.05f, GH*0.4f, GW*0.15f, GW*0.15f);
            arrayButtonBoxes[i].x += i * arrayButtonBoxes[i].w;
        }
    }

    @Override
    public void reset() {
        arrayTextString = new String[6];
        for (int i = 0; i < ARRAYSIZE; i++) {
            arrayTextString[i] = "";
        }
        typingSelector = -1;
    }

    @Override
    public String updateBefore(String factSelector) {
        if (BenHelper.screenClicked()) {
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
            hintTextString = "You can access and modify any item in \nthe Array individually.";
        }
        else if (titleButtonBox.checkHover()) {
            hintTextString = "An Array is a collection of items that \nare stored in adjacent memory spaces.";
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

        // draw text 
        BenHelper.textDrawCentre(batch, font, "Array", titleButtonBox,
            1.5f, BenHelper.DEFAULT_TEXT_COLOUR
        );
        for (int i = 0; i < ARRAYSIZE; i++) {
            String drawValue = arrayTextString[i];
            String[] indexWords = {"[zero]", "[one]", "[two]", "[three]", "[four]", "[five]"};
            if (drawValue.equals("")) { drawValue = indexWords[i]; }
            BenHelper.textDrawCentreSelectable(
                batch, font, drawValue, 
                arrayButtonBoxes[i], 1f, 
                (typingSelector == i)
            );

            BenHelper.textDrawCentre(
                batch, font, ""+i, 
                arrayButtonBoxes[i].x + (arrayButtonBoxes[i].w * 0.5f), 
                arrayButtonBoxes[i].y - (arrayButtonBoxes[i].h * 0.5f), 
                1.5f, BenHelper.DEFAULT_TEXT_COLOUR
            );
        }
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

        if (typingSelector != -1) {
            arrayTextString[typingSelector] = BenHelper.typing(
                arrayTextString[typingSelector],
                10
            );
        }

        return factSelector;
    }
}