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
    boolean showCustom;
    int typingSelector;

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

    String cTextHexCol;
    BenHelper.Rect cTextTextBox;
    BenHelper.Rect cTextInputBox;
    String cBgHexCol;
    BenHelper.Rect cBgTextBox;
    BenHelper.Rect cBgInputBox;
    String cHoverHexCol;
    BenHelper.Rect cHoverTextBox;
    BenHelper.Rect cHoverInputBox;
    String cHintHexCol;
    BenHelper.Rect cHintTextBox;
    BenHelper.Rect cHintInputBox;

    @Override
    public void create() {
        hintTextString = "";
        titleButtonBox = new BenHelper.Rect(GW*0.4f, GH*0.85f, GW*0.2f, GH*0.15f);
        backButtonBox = new BenHelper.Rect(0, 0, GW*0.1f, GH*0.1f);
        hintButtonBox = new BenHelper.Rect(GW*0.15f, 0, GW*0.7f, GH*0.1f);
        showCustom = ColorHandler.currentTheme == "custom";
        typingSelector = -1;

        classicButtonBox = new BenHelper.Rect(GW*0.2f, GH*0.7f, GW*0.2f, GH*0.1f);
        lightButtonBox = new BenHelper.Rect(GW*0.4f, GH*0.7f, GW*0.2f, GH*0.1f);
        darkButtonBox = new BenHelper.Rect(GW*0.6f, GH*0.7f, GW*0.2f, GH*0.1f);
        cherryButtonBox = new BenHelper.Rect(GW*0.4f, GH*0.6f, GW*0.2f, GH*0.1f);
        appleButtonBox = new BenHelper.Rect(GW*0.2f, GH*0.6f, GW*0.2f, GH*0.1f);
        midnightButtonBox = new BenHelper.Rect(GW*0.6f, GH*0.6f, GW*0.2f, GH*0.1f);
        protanopiaButtonBox = new BenHelper.Rect(GW*0.2f, GH*0.5f, GW*0.2f, GH*0.1f);
        deuteranopiaButtonBox = new BenHelper.Rect(GW*0.4f, GH*0.5f, GW*0.2f, GH*0.1f);
        tritanopiaButtonBox = new BenHelper.Rect(GW*0.6f, GH*0.5f, GW*0.2f, GH*0.1f);
        customButtonBox = new BenHelper.Rect(GW*0.4f, GH*0.4f, GW*0.2f, GH*0.1f);

        cTextHexCol = "";
        cTextTextBox = new BenHelper.Rect(GW*0.2f, GH*0.25f, GW*0.15f, GH*0.1f);
        cTextInputBox = new BenHelper.Rect(GW*0.35f, GH*0.25f, GW*0.125f, GH*0.1f);
        cBgHexCol = "";
        cBgTextBox = new BenHelper.Rect(GW*0.525f, GH*0.25f, GW*0.15f, GH*0.1f);
        cBgInputBox = new BenHelper.Rect(GW*0.675f, GH*0.25f, GW*0.125f, GH*0.1f);
        cHoverHexCol = "";
        cHoverTextBox = new BenHelper.Rect(GW*0.2f, GH*0.15f, GW*0.15f, GH*0.1f);
        cHoverInputBox = new BenHelper.Rect(GW*0.35f, GH*0.15f, GW*0.125f, GH*0.1f);
        cHintHexCol = "";
        cHintTextBox = new BenHelper.Rect(GW*0.525f, GH*0.15f, GW*0.15f, GH*0.1f);
        cHintInputBox = new BenHelper.Rect(GW*0.675f, GH*0.15f, GW*0.125f, GH*0.1f);
    }

    @Override
    public void reset() {
        showCustom = ColorHandler.currentTheme == "custom";
        typingSelector = -1;
    }

    @Override
    public String updateBefore(String factSelector) {
        
        hintTextString = "";
        if (titleButtonBox.checkHover()) {
            hintTextString = "Modify the look of the DSAVisualiser.";
        }
        else if (classicButtonBox.checkHover()) {
            hintTextString = "This is the default look of the DSAVisualiser.";
        }
        else if (lightButtonBox.checkHover()) {
            hintTextString = "Standard light mode.";
        }
        else if (darkButtonBox.checkHover()) {
            hintTextString = "Standard dark mode.";
        }
        else if (appleButtonBox.checkHover()) {
            hintTextString = "Alternative light mode with an Apple theme.";
        }
        else if (cherryButtonBox.checkHover()) {
            hintTextString = "Alternative light mode with a Cherry Blossom theme.";
        }
        else if (midnightButtonBox.checkHover()) {
            hintTextString = "Alternative dark mode with a Midnight theme.";
        }
        else if (protanopiaButtonBox.checkHover()) {
            hintTextString = "Theme created with Protanopia colour blindness in mind.";
        }
        else if (deuteranopiaButtonBox.checkHover()) {
            hintTextString = "Theme created with Deuteranopia colour blindness in mind.";
        }
        else if (tritanopiaButtonBox.checkHover()) {
            hintTextString = "Theme created with Tritanopia colour blindness in mind.";
        }
        else if (customButtonBox.checkHover()) {
            hintTextString = "Create your own theme using hex colour codes.";
        }
        else if (showCustom) {
            boolean hoverTextBox =
                cTextTextBox.checkHover() ||
                cBgTextBox.checkHover() ||
                cHoverTextBox.checkHover() ||
                cHintTextBox.checkHover();
            
            boolean hoverHexBox = 
                cTextInputBox.checkHover() ||
                cBgInputBox.checkHover() ||
                cHoverInputBox.checkHover() ||
                cHintInputBox.checkHover();

            if (hoverTextBox) {
                hintTextString = "The palette of this app is made of 4 colours.\n" +
                    "Text colour, Background colour, Hover / Selected\n" +
                    "colour and Hint Text / Border colour.";
            }
            else if (hoverHexBox) {
                hintTextString = "Enter a hex value to enable a colour (#RRGGBB).";
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

        BenHelper.textDrawCentre(batch, font, hintTextString, 
            hintButtonBox, 0.75f, BenHelper.HINT_TEXT_COLOUR
        );

        switch (ColorHandler.currentTheme) {
            case "classic":
                BenHelper.textDrawCentre(
                    batch, font, "Classic", classicButtonBox, 
                    1, BenHelper.HOVER_TEXT_COLOUR
                );
                break;
            case "light":
                BenHelper.textDrawCentre(
                    batch, font, "Light", lightButtonBox, 
                    1, BenHelper.HOVER_TEXT_COLOUR
                );
                break;
            case "dark":
                BenHelper.textDrawCentre(
                    batch, font, "Dark", darkButtonBox, 
                    1, BenHelper.HOVER_TEXT_COLOUR
                );
                break;
            case "apple":
                BenHelper.textDrawCentre(
                    batch, font, "Apple", appleButtonBox, 
                    1, BenHelper.HOVER_TEXT_COLOUR
                );
                break;
            case "cherry":
                BenHelper.textDrawCentre(
                    batch, font, "Cherry", cherryButtonBox, 
                    1, BenHelper.HOVER_TEXT_COLOUR
                );
                break;
            case "midnight":
                BenHelper.textDrawCentre(
                    batch, font, "Midnight", midnightButtonBox, 
                    1, BenHelper.HOVER_TEXT_COLOUR
                );
                break;
            case "protanopia":
                BenHelper.textDrawCentre(
                    batch, font, "Protanopia", protanopiaButtonBox, 
                    1, BenHelper.HOVER_TEXT_COLOUR
                );
                break;
            case "deuteranopia":
                BenHelper.textDrawCentre(
                    batch, font, "Deuteranopia", deuteranopiaButtonBox, 
                    1, BenHelper.HOVER_TEXT_COLOUR
                );
                break;
            case "tritanopia":
                BenHelper.textDrawCentre(
                    batch, font, "Tritanopia", tritanopiaButtonBox, 
                    1, BenHelper.HOVER_TEXT_COLOUR
                );
                break;
            case "custom":
                BenHelper.textDrawCentre(
                    batch, font, "Custom", customButtonBox, 
                    1, BenHelper.HOVER_TEXT_COLOUR
                );
                break;
        }

        if (showCustom) {
            BenHelper.textDrawCentre(batch, font, "Text:", cTextTextBox, 1);
            BenHelper.textDrawCentre(batch, font, "#"+cTextHexCol, cTextInputBox, 1);
            BenHelper.textDrawCentre(batch, font, "Background:", cBgTextBox, 1);
            BenHelper.textDrawCentre(batch, font, "#"+cBgHexCol, cBgInputBox, 1);
            BenHelper.textDrawCentre(batch, font, "Hover:", cHoverTextBox, 1);
            BenHelper.textDrawCentre(batch, font, "#"+cHoverHexCol, cHoverInputBox, 1);
            BenHelper.textDrawCentre(batch, font, "Hint:", cHintTextBox, 1);
            BenHelper.textDrawCentre(batch, font, "#"+cHintHexCol, cHintInputBox, 1);

            if (typingSelector == 0) BenHelper.textDrawCentre(batch, font, "#"+cTextHexCol, cTextInputBox, 1, BenHelper.HOVER_TEXT_COLOUR);
            if (typingSelector == 1) BenHelper.textDrawCentre(batch, font, "#"+cBgHexCol, cBgInputBox, 1, BenHelper.HOVER_TEXT_COLOUR);
            if (typingSelector == 2) BenHelper.textDrawCentre(batch, font, "#"+cHoverHexCol, cHoverInputBox, 1, BenHelper.HOVER_TEXT_COLOUR);
            if (typingSelector == 3) BenHelper.textDrawCentre(batch, font, "#"+cHintHexCol, cHintInputBox, 1, BenHelper.HOVER_TEXT_COLOUR);
        }


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

            cTextTextBox.draw(shape, Color.RED);
            cTextInputBox.draw(shape, Color.RED);
            cBgTextBox.draw(shape, Color.RED);
            cBgInputBox.draw(shape, Color.RED);
            cHoverTextBox.draw(shape, Color.RED);
            cHoverInputBox.draw(shape, Color.RED);
            cHintTextBox.draw(shape, Color.RED);
            cHintInputBox.draw(shape, Color.RED);
        }
    }

    @Override
    public String updateAfter(String factSelector) {
        if (BenHelper.screenClicked()) {
            typingSelector = -1;
            
            if (backButtonBox.checkHover()) {
                factSelector = "menu";
            }

            else if (classicButtonBox.checkHover()) {
                showCustom = false;
                ColorHandler.setTheme("classic");
                ColorHandler.currentTheme = "classic";
            }
            else if (lightButtonBox.checkHover()) {
                showCustom = false;
                ColorHandler.setTheme("light");
                ColorHandler.currentTheme = "light";
            }
            else if (darkButtonBox.checkHover()) {
                showCustom = false;
                ColorHandler.setTheme("dark");
                ColorHandler.currentTheme = "dark";
            }
            else if (cherryButtonBox.checkHover()) {
                showCustom = false;
                ColorHandler.setTheme("cherry");
                ColorHandler.currentTheme = "cherry";
            }
            else if (appleButtonBox.checkHover()) {
                showCustom = false;
                ColorHandler.setTheme("apple");
                ColorHandler.currentTheme = "apple";
            }
            else if (midnightButtonBox.checkHover()) {
                showCustom = false;
                ColorHandler.setTheme("midnight");
                ColorHandler.currentTheme = "midnight";
            }
            else if (protanopiaButtonBox.checkHover()) {
                showCustom = false;
                ColorHandler.setTheme("protanopia");
                ColorHandler.currentTheme = "protanopia";
            }
            else if (deuteranopiaButtonBox.checkHover()) {
                showCustom = false;
                ColorHandler.setTheme("deuteranopia");
                ColorHandler.currentTheme = "deuteranopia";
            }
            else if (tritanopiaButtonBox.checkHover()) {
                showCustom = false;
                ColorHandler.setTheme("tritanopia");
                ColorHandler.currentTheme = "tritanopia";
            }
            else {
                // custom colour theme
                showCustom = true;
                ColorHandler.setTheme("classic");
                ColorHandler.currentTheme = "custom";
                ColorHandler.tryCustomTheme(cTextHexCol, cBgHexCol, cHoverHexCol, cHintHexCol);
 
                if (showCustom) {
                    // typing selectors
                    if (cTextInputBox.checkHover()) {
                        typingSelector = 0;
                    }
                    else if (cBgInputBox.checkHover()) {
                        typingSelector = 1;
                    }
                    else if (cHoverInputBox.checkHover()) {
                        typingSelector = 2;
                    }
                    else if (cHintInputBox.checkHover()) {
                        typingSelector = 3;
                    }
                }
            }
           
        }

        // typing
        switch (typingSelector) {
            case -1:
                break;
            
            case 0:
                cTextHexCol = BenHelper.typing(cTextHexCol, 6);
                break;

            case 1:
                cBgHexCol = BenHelper.typing(cBgHexCol, 6);
                break;
            
            case 2:
                cHoverHexCol = BenHelper.typing(cHoverHexCol, 6);
                break;

            case 3:
                cHintHexCol = BenHelper.typing(cHintHexCol, 6);
                break;
        }

        return factSelector;
    }
}