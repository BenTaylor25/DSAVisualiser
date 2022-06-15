package com.mygdx.dsav.Screens;

import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.FactOption;

import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;

public class QueueScreen extends FactOption {
    BenHelper.Rect backButtonBox;

    @Override
    public void create() {
        backButtonBox = new BenHelper.Rect(0, 0, GW*0.1f, GH*0.1f);
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
        batch.begin();
            ScreenUtils.clear(BenHelper.BACKGROUND_COLOUR);
            font.setColor(BenHelper.DEFAULT_TEXT_COLOUR);
        batch.end();

        BenHelper.textDrawCentre(batch, font, "Back", backButtonBox, 1.25f);

        if (BenHelper.DEBUG) {
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