package com.mygdx.dsav;

import com.badlogic.gdx.Game;

public class FactOption extends Game {
    /**
     * Called when the Screen is created. <br>
     */
    public void create() {}

    /**
     * Called when the Screen is switched to. <br>
     */
    public void reset() {}

    /**
     * Called every frame, before the draw() call. <br><br>
     *
     * Must take screen identifier (factSelector) char as a parameter, <br>
     * and return it back out (may be modified inside). <br>
     *
     * @param factSelector
     * @return factSelector
     */
    public char updateBefore(char factSelector) { return factSelector; }

    /**
     * Called every frame; should be used to draw to the screen. <br><br>
     */
    public void draw() {}

    /**
     * Called every frame after the draw() call. <br><br>
     *
     * Must take screen identifier (factSelector) char as a parameter, <br>
     * and return it back out (may be modified inside). <br>
     *
     * @param factSelector
     * @return factSelector
     */
    public char updateAfter(char factSelector) { return factSelector; }

    /**
     * Don't override! <br><br>
     * Calls updateBefore(), then draw(), then updateAfter() <br><br>
     */
    public char frame(char factSelector) {
        factSelector = updateBefore(factSelector);
        draw();
        factSelector = updateAfter(factSelector);
        return factSelector;
    }
}
