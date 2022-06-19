package com.mygdx.dsav.Screens;

import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.FactOption;

import com.badlogic.gdx.utils.ScreenUtils;

public class LinkedListScreen extends FactOption {
    

    @Override
    public void create() {
        
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
        
    }

    @Override
    public String updateAfter(String factSelector) {
        
        return factSelector;
    }
}