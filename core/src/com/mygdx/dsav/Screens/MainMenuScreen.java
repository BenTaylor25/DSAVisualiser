package com.mygdx.dsav.Screens;

import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.FactOption;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;


public class MainMenuScreen extends FactOption {
    int GW, GH;   // graphical width and height
    BitmapFont font;
    SpriteBatch batch;
    ShapeRenderer shape;

    BenHelper.Rect exitButtonBox;
    BenHelper.Rect stackButtonBox;

    public MainMenuScreen() {
        GW = Gdx.graphics.getWidth();
        GH = Gdx.graphics.getHeight();
        create();
    }

    @Override
    public void create() {
        font = new BitmapFont(Gdx.files.internal("vcr_osd_mono_font.fnt"));
        batch = new SpriteBatch();
        shape = new ShapeRenderer();

        exitButtonBox = new BenHelper.Rect(GW*0.88f, 0, GW*0.1f, GH*0.09f);
        stackButtonBox = new BenHelper.Rect(GW*0.19f, GH*0.725f, GW*0.25f, GH*0.085f);
    }

    @Override
    public void dispose() {
        font.dispose();
        batch.dispose();
        shape.dispose();
    }

    @Override
    public void reset() {}

    @Override
    public String updateBefore(String factSelector) {
        return factSelector;
    }

    @Override
    public void draw() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
            ScreenUtils.clear(0.15f, 0.25f, 0.35f, 1f);

            font.setColor(0.69f, 0.447f, 0.098f, 1);
            font.getData().setScale(2);
            font.draw(batch, "DSAVisualiser", GW*0.2f, GH*0.9f);

            font.getData().setScale(1.125f);
            font.draw(batch, "Stack", GW*0.2f, GH*0.775f);

            font.getData().setScale(1);
            font.draw(batch, "Ben Taylor", GW*0.01f, GH*0.1f);

            font.getData().setScale(0.5f);
            font.draw(batch, "Thanks to Riciery Leal for the VCR OSD Mono font",
                GW*0.01f, GH*0.03f);

            font.getData().setScale(1);
            font.draw(batch, "Exit", GW*0.9f, GH*0.05f);
        batch.end();

        if (BenHelper.DEBUG) {
            shape.setColor(Color.RED);
            exitButtonBox.draw(shape);
            stackButtonBox.draw(shape);
        }
    }

    @Override
    public String updateAfter(String factSelector) {
        if (exitButtonBox.checkClick()) {
            factSelector = "quit";
        }
        if (stackButtonBox.checkClick()) {
            factSelector = "stack";
        }

        return factSelector;
    }
}
