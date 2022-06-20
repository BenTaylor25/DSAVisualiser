package com.mygdx.dsav.Screens;

import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.FactOption;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

public class LinkedListScreen extends FactOption {
    final int LISTSIZE = 4;
    boolean includeInserted;
    String[] listTextString;
    String insertedTextString;
    int typingSelector;
    String hintTextString;
    BenHelper.Rect titleButtonBox;
    BenHelper.Rect backButtonBox;
    BenHelper.Rect hintButtonBox;
    BenHelper.Rect[] listButtonBoxes;
    BenHelper.Rect insertedButtonBox;

    @Override
    public void create() {
        hintTextString = "";
        titleButtonBox = new BenHelper.Rect(GW*0.4f, GH*0.85f, GW*0.2f, GH*0.15f);
        backButtonBox = new BenHelper.Rect(0, 0, GW*0.1f, GH*0.1f);
        hintButtonBox = new BenHelper.Rect(GW*0.15f, 0, GW*0.7f, GH*0.1f);

        typingSelector = -1;
        listTextString = new String[LISTSIZE];
        insertedTextString = "";
        listButtonBoxes = new BenHelper.Rect[LISTSIZE];
        insertedButtonBox = new BenHelper.Rect(GW*0.425f, GH*0.425f, GW*0.15f, GH*0.15f);

        for (int i = 0; i < LISTSIZE; i++) {
            listTextString[i] = "";
            listButtonBoxes[i] = new BenHelper.Rect(GW*0.1f, GH*0.575f, GW*0.15f, GH*0.15f);
            if (i == 1 || i == 2) {
                listButtonBoxes[i].y = GH*0.275f;
            }
            if (i > 1) {
                listButtonBoxes[i].x = GW*0.75f;
            }
        }
        
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
        
        // draw button outlines
        for (int i = 0; i < LISTSIZE; i++) {
            listButtonBoxes[i].draw(shape);
        }
        // wip insertable button
        if (typingSelector >= 0 && typingSelector < LISTSIZE) {
            listButtonBoxes[typingSelector].draw(shape, Color.WHITE);
        }

        // draw text
        BenHelper.textDrawCentre(batch, font, "Linked List", titleButtonBox, 
            1.5f, BenHelper.DEFAULT_TEXT_COLOUR
        );
        BenHelper.textDrawCentre(batch, font, "Back", backButtonBox, 1.25f);
        BenHelper.textDrawCentre(batch, font, hintTextString, hintButtonBox, 
            0.75f, Color.GRAY
        );

        // debug: draw hitboxes

    }

    @Override
    public String updateAfter(String factSelector) {
        
        if (backButtonBox.checkClick()) {
            factSelector = "menu";
        }

        return factSelector;
    }
}