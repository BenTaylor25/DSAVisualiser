package com.mygdx.dsav;

import com.badlogic.gdx.graphics.Color;

public class ColorHandler {
    public static String currentTheme;
    private static final String HEXCHARS = "0123456789ABCDEF";

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

            case "midnight":
                BenHelper.BACKGROUND_COLOUR = new Color(0.11f, 0.063f, 0.31f, 1);
                BenHelper.DEFAULT_TEXT_COLOUR = new Color(0.682f, 0.608f, 0.98f, 1);
                BenHelper.HOVER_TEXT_COLOUR = new Color(0.447f, 0.318f, 0.965f, 1);
                BenHelper.HINT_TEXT_COLOUR = new Color(0.525f, 0.416f, 0.706f, 1);
                break;
            
            case "protanopia":
                BenHelper.BACKGROUND_COLOUR = new Color(0.612f, 0.953f, 0.851f, 1);
                BenHelper.DEFAULT_TEXT_COLOUR = new Color(0.243f, 0.384f, 0.655f, 1);
                BenHelper.HOVER_TEXT_COLOUR = new Color(0.341f, 0.486f, 0.757f, 1);
                BenHelper.HINT_TEXT_COLOUR = new Color(0.482f, 0.482f, 0.482f, 1);
                break;

            case "deuteranopia":
                BenHelper.BACKGROUND_COLOUR = new Color(0.949f, 0.953f, 0.612f, 1);
                BenHelper.DEFAULT_TEXT_COLOUR = new Color(0.345f, 0.486f, 0.757f, 1);
                BenHelper.HOVER_TEXT_COLOUR = new Color(0.49f, 0.6f, 0.812f, 1);
                BenHelper.HINT_TEXT_COLOUR = new Color(0.482f, 0.482f, 0.482f, 1);
                break;
            
            case "tritanopia":
                BenHelper.BACKGROUND_COLOUR = new Color(0.855f, 0.816f, 0.494f, 1);
                BenHelper.DEFAULT_TEXT_COLOUR = new Color(0.655f, 0.243f, 0.388f, 1);
                BenHelper.HOVER_TEXT_COLOUR = new Color(0.757f, 0.345f, 0.49f, 1);
                BenHelper.HINT_TEXT_COLOUR = new Color(0.482f, 0.482f, 0.482f, 1);
                break;

            
        }
    }

    private static boolean isValidHex(String str) {
        if (str.length() != 6) { return false; }

        for (int i = 0; i < str.length(); i++) {
            char thisChar = Character.toUpperCase(str.charAt(i));
            if (!HEXCHARS.contains("" + thisChar)) {
                return false;
            }
        }
        return true;
    }

    private static float[] hexToUnitInterval(String str) {
        assert isValidHex(str);

        float[] unitInterval = new float[3];

        for (int i = 0; i < 3; i++) {
            char cSxtns = Character.toUpperCase(str.charAt(i*2));
            char cOnes = Character.toUpperCase(str.charAt(i*2+1));

            int iSxtns = HEXCHARS.indexOf(cSxtns);
            int iOnes = HEXCHARS.indexOf(cOnes);

            unitInterval[i] = (16f * iSxtns + iOnes) / 255;
        }

        return unitInterval;
    }

    public static void tryCustomTheme(String cText, String cBg, String cHover, String cHint) {
        if (isValidHex(cText)) {
            float[] rgb = hexToUnitInterval(cText);
            BenHelper.DEFAULT_TEXT_COLOUR = new Color(rgb[0], rgb[1], rgb[2], 1);
        }
        
        if (isValidHex(cBg)) {
            float[] rgb = hexToUnitInterval(cBg);
            BenHelper.BACKGROUND_COLOUR = new Color(rgb[0], rgb[1], rgb[2], 1);
        }

        if (isValidHex(cHover)) {
            float[] rgb = hexToUnitInterval(cHover);
            BenHelper.HOVER_TEXT_COLOUR = new Color(rgb[0], rgb[1], rgb[2], 1);
        }

        if (isValidHex(cHint)) {
            float[] rgb = hexToUnitInterval(cHint);
            BenHelper.HINT_TEXT_COLOUR = new Color(rgb[0], rgb[1], rgb[2], 1);
        }
    }
}
