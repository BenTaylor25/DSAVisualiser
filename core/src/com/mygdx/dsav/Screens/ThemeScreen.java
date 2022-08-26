package com.mygdx.dsav.Screens;

import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.ColorHandler;
import com.mygdx.dsav.FactOption;

import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;

public class ThemeScreen extends FactOption {
    String hintTextString;
    BenHelper.Rect titleButtonBox;
    BenHelper.Rect backButtonBox;
    BenHelper.Rect hintButtonBox;

    BenHelper.Rect classicButtonBox;
    BenHelper.Rect lightButtonBox;
    BenHelper.Rect darkButtonBox;
    BenHelper.Rect cherryButtonBox;
    BenHelper.Rect appleButtonBox;
    BenHelper.Rect midnightButtonBox;
    BenHelper.Rect protanopiaButtonBox;
    BenHelper.Rect deuteranopiaButtonBox;
    BenHelper.Rect tritanopiaButtonBox;
    BenHelper.Rect customButtonBox;

    @Override
    public void create() {
        hintTextString = "";
        titleButtonBox = new BenHelper.Rect(GW*0.4f, GH*0.85f, GW*0.2f, GH*0.15f);
        backButtonBox = new BenHelper.Rect(0, 0, GW*0.1f, GH*0.1f);
        hintButtonBox = new BenHelper.Rect(GW*0.15f, 0, GW*0.7f, GH*0.1f);

        classicButtonBox = new BenHelper.Rect(GW*0.2f, GH*0.7f, GW*0.2f, GH*0.1f);
        lightButtonBox = new BenHelper.Rect(GW*0.4f, GH*0.7f, GW*0.2f, GH*0.1f);
        darkButtonBox = new BenHelper.Rect(GW*0.6f, GH*0.7f, GW*0.2f, GH*0.1f);

        cherryButtonBox = new BenHelper.Rect(GW*0.2f, GH*0.6f, GW*0.2f, GH*0.1f);
        appleButtonBox = new BenHelper.Rect(GW*0.4f, GH*0.6f, GW*0.2f, GH*0.1f);
        midnightButtonBox = new BenHelper.Rect(GW*0.6f, GH*0.6f, GW*0.2f, GH*0.1f);

        protanopiaButtonBox = new BenHelper.Rect(GW*0.2f, GH*0.5f, GW*0.2f, GH*0.1f);
        deuteranopiaButtonBox = new BenHelper.Rect(GW*0.4f, GH*0.5f, GW*0.2f, GH*0.1f);
        tritanopiaButtonBox = new BenHelper.Rect(GW*0.6f, GH*0.5f, GW*0.2f, GH*0.1f);

        customButtonBox = new BenHelper.Rect(GW*0.4f, GH*0.4f, GW*0.2f, GH*0.1f);
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
        
        // draw static shapes
        

        // draw data items
        

        // draw button outlines
        

        // draw text
        BenHelper.textDrawCentre(batch, font, "Theme", titleButtonBox, 1.5f,
            BenHelper.DEFAULT_TEXT_COLOUR
        );
        BenHelper.textDrawCentre(batch, font, "Back", backButtonBox, 1.25f);
        
        BenHelper.textDrawCentre(batch, font, "Classic", classicButtonBox, 1);
        BenHelper.textDrawCentre(batch, font, "Light", lightButtonBox, 1);
        BenHelper.textDrawCentre(batch, font, "Dark", darkButtonBox, 1);

        BenHelper.textDrawCentre(batch, font, "Cherry", cherryButtonBox, 1);
        BenHelper.textDrawCentre(batch, font, "Apple", appleButtonBox, 1);
        BenHelper.textDrawCentre(batch, font, "Midnight", midnightButtonBox, 1);

        BenHelper.textDrawCentre(batch, font, "Protanopia", protanopiaButtonBox, 1);
        BenHelper.textDrawCentre(batch, font, "Deuteranopia", deuteranopiaButtonBox, 1);
        BenHelper.textDrawCentre(batch, font, "Tritanopia", tritanopiaButtonBox, 1);

        BenHelper.textDrawCentre(batch, font, "Custom", customButtonBox, 1);

        // debug: draw hitboxes
        if (BenHelper.DEBUG) {
            titleButtonBox.draw(shape, Color.RED);
            backButtonBox.draw(shape, Color.RED);
            hintButtonBox.draw(shape, Color.RED);

            classicButtonBox.draw(shape, Color.RED);
            lightButtonBox.draw(shape, Color.RED);
            darkButtonBox.draw(shape, Color.RED);
            cherryButtonBox.draw(shape, Color.RED);
            appleButtonBox.draw(shape, Color.RED);
            midnightButtonBox.draw(shape, Color.RED);
            protanopiaButtonBox.draw(shape, Color.RED);
            deuteranopiaButtonBox.draw(shape, Color.RED);
            tritanopiaButtonBox.draw(shape, Color.RED);
            customButtonBox.draw(shape, Color.RED);
        }
    }

    @Override
    public String updateAfter(String factSelector) {
        if (BenHelper.screenClicked()) {
            if (backButtonBox.checkHover()) {
                factSelector = "menu";
            }

            else if (classicButtonBox.checkHover()) {
                ColorHandler.setTheme("classic");
            }
            else if (lightButtonBox.checkHover()) {
                ColorHandler.setTheme("light");
            }
            else if (darkButtonBox.checkHover()) {
                ColorHandler.setTheme("dark");
            }
            else if (cherryButtonBox.checkHover()) {
                ColorHandler.setTheme("cherry");
            }
            else if (appleButtonBox.checkHover()) {
                ColorHandler.setTheme("apple");
            }
            else if (midnightButtonBox.checkHover()) {
                ColorHandler.setTheme("midnight");
            }
            else if (protanopiaButtonBox.checkHover()) {
                ColorHandler.setTheme("protanopia");
            }
            else if (deuteranopiaButtonBox.checkHover()) {
                ColorHandler.setTheme("deuteranopia");
            }
            else if (tritanopiaButtonBox.checkHover()) {
                ColorHandler.setTheme("tritanopia");
            }
        }

        return factSelector;
    }
}