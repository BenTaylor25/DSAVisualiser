package com.mygdx.dsav;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

abstract public class FactOption extends Game {
    /** Graphical Width */
    protected float GW;
    /** Graphical Height */
    protected float GH;
    protected SpriteBatch batch;
    protected ShapeRenderer shape;
    protected BitmapFont font;

    protected FactOption() {
        GW = Gdx.graphics.getWidth();
        GH = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        font = new BitmapFont(Gdx.files.internal("vcr_osd_mono_font.fnt"));
        create();
    }

    /**
     * Called when the Screen is created. <br>
     */
    public void create() {}

    @Override
    public void dispose() {
        batch.dispose();
        shape.dispose();
        font.dispose();
    }

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
    public String updateBefore(String factSelector) { return factSelector; }

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
    public String updateAfter(String factSelector) { return factSelector; }

    /**
     * Don't override! <br><br>
     * Calls updateBefore(), then draw(), then updateAfter() <br><br>
     */
    public String frame(String factSelector) {
        factSelector = updateBefore(factSelector);
        draw();
        factSelector = updateAfter(factSelector);
        return factSelector;
    }
}
