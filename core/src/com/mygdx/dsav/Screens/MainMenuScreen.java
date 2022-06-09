package com.mygdx.dsav.Screens;

import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.FactOption;

import java.awt.Desktop;
import java.net.URL;

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
    BenHelper.Rect myGHButtonBox;
    BenHelper.Rect fontLinkButtonBox;

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
        myGHButtonBox = new BenHelper.Rect(GW*0.001f, GH*0.06f, GW*0.175f, GH*0.08f);
        fontLinkButtonBox = new BenHelper.Rect(GW*0.001f, GH*0.001f, GW*0.38f, GH*0.05f);
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
            Color baseCol = new Color(0.69f, 0.447f, 0.098f, 1);
            Color hoverCol = new Color(1, 1, 1, 1);
            boolean hoverHere;
            font.setColor(baseCol);

            font.getData().setScale(2);
            font.draw(batch, "DSAVisualiser", GW*0.2f, GH*0.9f);

            hoverHere = stackButtonBox.checkHover();
            if (hoverHere) { font.setColor(hoverCol); }
            font.getData().setScale(1.125f);
            font.draw(batch, "Stack", GW*0.2f, GH*0.775f);
            if (hoverHere) { font.setColor(baseCol); }

            hoverHere = myGHButtonBox.checkHover();
            if (hoverHere) { font.setColor(hoverCol); }
            font.getData().setScale(1);
            font.draw(batch, "Ben Taylor", GW*0.01f, GH*0.1f);
            if (hoverHere) { font.setColor(baseCol); }

            hoverHere = fontLinkButtonBox.checkHover();
            if (hoverHere) { font.setColor(hoverCol); }
            font.getData().setScale(0.5f);
            font.draw(batch, "Thanks to Riciery Leal for the VCR OSD Mono font",
                GW*0.01f, GH*0.03f);
            if (hoverHere) { font.setColor(baseCol); }

            hoverHere = exitButtonBox.checkHover();
            if (hoverHere) { font.setColor(hoverCol); }
            font.getData().setScale(1);
            font.draw(batch, "Exit", GW*0.9f, GH*0.05f);
            if (hoverHere) { font.setColor(baseCol); }
        batch.end();

        if (BenHelper.DEBUG) {
            shape.setColor(Color.RED);
            exitButtonBox.draw(shape);
            stackButtonBox.draw(shape);
            myGHButtonBox.draw(shape);
            fontLinkButtonBox.draw(shape);
        }
    }

    @Override
    public String updateAfter(String factSelector) {
        if (exitButtonBox.checkClick()) {
            factSelector = "quit";
        }
        else if (stackButtonBox.checkClick()) {
            factSelector = "stack";
        }
        else if (myGHButtonBox.checkClick()) {
            try {
                Desktop.getDesktop().browse(new URL("https://github.com/BenTaylor25").toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (fontLinkButtonBox.checkClick()) {
            try {
                Desktop.getDesktop().browse(new URL("https://www.dafont.com/vcr-osd-mono.font").toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return factSelector;
    }
}
