package com.mygdx.dsav.Screens;

import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.FactOption;
import com.mygdx.dsav.DataStructs.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.ScreenUtils;

public class StackScreen extends FactOption {
    float GW, GH;
    SpriteBatch batch;
    ShapeRenderer shape;
    BitmapFont font;

    Stack stack;

    boolean inputTypingEnabled;
    String inputTextString;
    String outputTextString;
    BenHelper.Rect backButtonBox;
    BenHelper.Rect inputTextButtonBox;
    BenHelper.Rect pushButtonBox;
    BenHelper.Rect outputTextButtonBox;
    BenHelper.Rect peekButtonBox;
    BenHelper.Rect popButtonBox;

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

        stack = new Stack();

        inputTypingEnabled = false;
        inputTextString = new String("");
        outputTextString = new String("");
        backButtonBox =  new BenHelper.Rect(0, 0, GW*0.1f, GH*0.1f);
        inputTextButtonBox = new BenHelper.Rect(GW*0.025f, GH*0.525f, GW*0.3f, GH*0.1f);
        pushButtonBox = new BenHelper.Rect(GW*0.105f, GH*0.3f, GW*0.14f, GH*0.14f);
        outputTextButtonBox = new BenHelper.Rect(GW*0.675f, GH*0.525f, GW*0.3f, GH*0.1f);
        peekButtonBox = new BenHelper.Rect(GW*0.675f, GH*0.3f, GW*0.14f, GH*0.14f);
        popButtonBox = new BenHelper.Rect(GW*0.835f, GH*0.3f, GW*0.14f, GH*0.14f);
    }

    @Override
    public void dispose() {
        batch.dispose();
        shape.dispose();
        font.dispose();
    }

    @Override
    public void reset() {
        stack = new Stack();
    }

    @Override
    public String updateBefore(String factSelector) {
        if (inputTypingEnabled) {
            inputTextString = BenHelper.typing(inputTextString);
        }

        return factSelector;
    }

    @Override
    public void draw() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
            ScreenUtils.clear(BenHelper.BACKGROUND_COLOUR);
            boolean hoverHere;
            font.setColor(BenHelper.DEFAULT_TEXT_COLOUR);
            
            hoverHere = backButtonBox.checkHover();
            if (hoverHere) { font.setColor(BenHelper.HOVER_TEXT_COLOUR);}
            font.getData().setScale(1.25f);
            font.draw(batch, "Back", GW*0.01f, GH*0.065f);
            if (hoverHere) { font.setColor(BenHelper.DEFAULT_TEXT_COLOUR); }
  
        batch.end();

        shape.begin(ShapeType.Filled);
            shape.setColor(Color.BLACK);

            // Stack Drawing
            shape.rectLine(GW*0.35f, GH*0.2f, GW*0.35f, GH*0.8f, 2);
            shape.rectLine(GW*0.65f, GH*0.2f, GW*0.65f, GH*0.8f, 2);
            shape.rectLine(GW*0.35f, GH*0.2f, GW*0.65f, GH*0.2f, 2);
        shape.end();

        for (int i = 0; i < stack.size(); i++) {
            String value = stack.viewStack().get(i);
            BenHelper.Rect valueRect = new BenHelper.Rect(GW*0.35f, GH*0.2f, GW*0.3f, GH*0.1f);
            valueRect.y += i * GH*0.1;
            valueRect.draw(shape);
            BenHelper.textDrawCentre(batch, font, value, valueRect, 1f);
        }

        if (inputTypingEnabled) {
            inputTextButtonBox.draw(shape, Color.WHITE);
        } else {
            inputTextButtonBox.draw(shape, Color.BLACK);
        }
        pushButtonBox.draw(shape);
        outputTextButtonBox.draw(shape);
        peekButtonBox.draw(shape);
        popButtonBox.draw(shape);

        if (inputTextString.equals("")) {
            BenHelper.textDrawCentre(batch, font, "[click to type]", inputTextButtonBox, 1f);
        } else {
            BenHelper.textDrawCentre(batch, font, inputTextString, inputTextButtonBox, 1f);
        }
        BenHelper.textDrawCentre(batch, font, outputTextString, outputTextButtonBox, 1f);
        BenHelper.textDrawCentre(batch, font, "Push", pushButtonBox, 1.25f);
        BenHelper.textDrawCentre(batch, font, "Peek", peekButtonBox, 1.25f);
        BenHelper.textDrawCentre(batch, font, "Pop", popButtonBox, 1.25f);


        if (BenHelper.DEBUG) {
            shape.setColor(Color.RED);
            backButtonBox.draw(shape);
        }
    }

    @Override
    public String updateAfter(String factSelector) {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (inputTextButtonBox.checkClick()) {
                inputTypingEnabled = true;
            } else {
                inputTypingEnabled = false;
            }
        }
        
        if (backButtonBox.checkClick()) {
            factSelector = "menu";
        }
        else if (pushButtonBox.checkClick()) {
            if (!inputTextString.equals("")) {
                stack.push(inputTextString);
                inputTextString = "";
            }
        }

        return factSelector;
    }
}
