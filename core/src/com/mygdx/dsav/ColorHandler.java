package com.mygdx.dsav;

import com.badlogic.gdx.graphics.Color;

public class ColorHandler {
    public static void setTheme(String mode) {
        switch (mode) {
            case "classic":
                BenHelper.BACKGROUND_COLOUR = new Color(0.15f, 0.25f, 0.35f, 1);
                BenHelper.DEFAULT_TEXT_COLOUR = new Color(0.69f, 0.447f, 0.098f, 1);
                BenHelper.HOVER_TEXT_COLOUR = new Color(1, 1, 1, 1);
                BenHelper.HINT_TEXT_COLOUR = new Color(Color.GRAY);
                break;
            
            case "light":
                BenHelper.BACKGROUND_COLOUR = new Color(0.95f, 0.95f, 0.95f, 1);
                BenHelper.DEFAULT_TEXT_COLOUR = new Color(Color.BLACK);
                BenHelper.HOVER_TEXT_COLOUR = new Color(Color.TEAL);
                BenHelper.HINT_TEXT_COLOUR = new Color(Color.GRAY);
                break;

            case "dark":
                BenHelper.BACKGROUND_COLOUR = new Color(0.15f, 0.15f, 0.15f, 1);
                BenHelper.DEFAULT_TEXT_COLOUR = new Color(1, 0.9f, 0.75f, 1);
                BenHelper.HOVER_TEXT_COLOUR = new Color(0.48f, 0.286f, 0.533f, 1);
                BenHelper.HINT_TEXT_COLOUR = new Color(0.6f, 0.6f, 0.6f, 1);
                break;

            case "cherry":
                BenHelper.BACKGROUND_COLOUR = new Color(1, 0.8f, 0.855f, 1);
                BenHelper.DEFAULT_TEXT_COLOUR = new Color(0.729f, 0.263f, 0.396f, 1);
                BenHelper.HOVER_TEXT_COLOUR = new Color(0.48f, 0.286f, 0.533f, 1);
                BenHelper.HINT_TEXT_COLOUR = new Color(0.82f, 0.4f, 0.443f, 1);
                break;
            
            case "apple":
                BenHelper.BACKGROUND_COLOUR = new Color(0.867f, 0.929f, 0.659f, 1);
                BenHelper.DEFAULT_TEXT_COLOUR = new Color(0.522f, 0.627f, 0.173f, 1);
                BenHelper.HOVER_TEXT_COLOUR = new Color(0.651f, 0.784f, 0.216f, 1);
                BenHelper.HINT_TEXT_COLOUR = new Color(0.565f, 0.612f, 0.42f, 1);
                break;


        }
    }
}
