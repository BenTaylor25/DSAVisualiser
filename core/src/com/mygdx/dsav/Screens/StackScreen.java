package com.mygdx.dsav.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.FactOption;

public class StackScreen extends FactOption {
    float GW, GH;
    SpriteBatch batch;
    ShapeRenderer shape;
    BitmapFont font;

    BenHelper.Rect backButtonBox;

    public StackScreen() {
        GW = Gdx.graphics.getWidth();
        GH = Gdx.graphics.getHeight();
        create();
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        font = new BitmapFont(Gdx.files.internal("vcr_osd_mono_font.fnt"));

        backButtonBox =  new BenHelper.Rect(0, 0, GW*0.1f, GH*0.1f);
    }

    @Override
    public void dispose() {
        batch.dispose();
        shape.dispose();
        font.dispose();
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
            ScreenUtils.clear(0, 0, 1, 1);
            
            font.setColor(0.69f, 0.447f, 0.098f, 1);
            font.getData().setScale(1.25f);
            font.draw(batch, "Back", GW*0.01f, GH*0.065f);

        batch.end();

        if (BenHelper.DEBUG) {
            shape.setColor(Color.RED);
            backButtonBox.draw(shape);
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
