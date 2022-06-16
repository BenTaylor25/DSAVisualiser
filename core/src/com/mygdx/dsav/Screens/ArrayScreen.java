package com.mygdx.dsav.Screens;

import java.util.Arrays;

import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.FactOption;

import com.badlogic.gdx.graphics.Color;

public class ArrayScreen extends FactOption {
    int ARRAYSIZE;
    String[] array;
    int typingSelection;
    String hintTextString;
    BenHelper.Rect titleButtonBox;
    BenHelper.Rect backButtonBox;
    BenHelper.Rect[] arrayButtonBoxes;
    BenHelper.Rect hintButtonBox;

    @Override
    public void create() {
        ARRAYSIZE = 6;
        array = new String[ARRAYSIZE];
        Arrays.fill(array, "test");
        typingSelection = -1;

        titleButtonBox = new BenHelper.Rect(GW*0.4f, GH*0.85f, GW*0.2f, GH*0.15f);
        backButtonBox = new BenHelper.Rect(0, 0, GW*0.1f, GH*0.1f);

        arrayButtonBoxes = new BenHelper.Rect[ARRAYSIZE];
        Arrays.fill(arrayButtonBoxes, new BenHelper.Rect(GW*0.1f, GH*0.5f, GW*0.15f, GW*0.15f));
        for (int i = 1; i < ARRAYSIZE; i++) {
            arrayButtonBoxes[i].x += i * arrayButtonBoxes[i].w;
        }
    }

    @Override
    public void reset() {
        array = new String[6];
        Arrays.fill(array, "");
        typingSelection = -1;
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

        // draw button outlines
        for (BenHelper.Rect indBB : arrayButtonBoxes) {
            indBB.draw(shape);
        }

        // draw text 
        BenHelper.textDrawCentre(batch, font, "Array", titleButtonBox, 1.5f, 
            BenHelper.DEFAULT_TEXT_COLOUR);
        for (int i = 0; i < ARRAYSIZE; i++) {   // wip: this isn't displaying
            BenHelper.textDrawCentre(batch, font, array[i], 
                arrayButtonBoxes[i], 1.25f);
        }
        BenHelper.textDrawCentre(batch, font, "Back", backButtonBox, 1.25f);

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