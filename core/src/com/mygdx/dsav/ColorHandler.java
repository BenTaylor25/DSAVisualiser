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
            
            case "test":
                BenHelper.BACKGROUND_COLOUR = new Color(0.85f, 0.75f, 0.65f, 1);
                BenHelper.DEFAULT_TEXT_COLOUR = new Color(0.31f, 0.553f, 0.902f, 1);
                BenHelper.HOVER_TEXT_COLOUR = new Color(0, 0, 0, 1);
                BenHelper.HINT_TEXT_COLOUR = new Color(Color.GRAY);
                break;

            
        }
    }
}
