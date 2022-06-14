package com.mygdx.dsav.Screens;

import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.FactOption;
import com.mygdx.dsav.DataStructs.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.ScreenUtils;

public class StackScreen extends FactOption {
    Stack stack;
    boolean inputTypingEnabled;
    String inputTextString;
    String outputTextString;
    String hintTextString;
    BenHelper.Rect titleButtonBox;
    BenHelper.Rect backButtonBox;
    BenHelper.Rect inputTextButtonBox;
    BenHelper.Rect pushButtonBox;
    BenHelper.Rect outputTextButtonBox;
    BenHelper.Rect peekButtonBox;
    BenHelper.Rect popButtonBox;
    BenHelper.Rect hintButtonBox;

    @Override
    public void create() {
        stack = new Stack();
        inputTypingEnabled = false;
        inputTextString = new String("");
        outputTextString = new String("");
        hintTextString = new String("");
        titleButtonBox = new BenHelper.Rect(GW*0.4f, GH*0.85f, GW*0.2f, GH*0.15f);
        backButtonBox =  new BenHelper.Rect(0, 0, GW*0.1f, GH*0.1f);
        inputTextButtonBox = new BenHelper.Rect(GW*0.025f, GH*0.525f, GW*0.3f, GH*0.1f);
        pushButtonBox = new BenHelper.Rect(GW*0.105f, GH*0.3f, GW*0.14f, GH*0.14f);
        outputTextButtonBox = new BenHelper.Rect(GW*0.675f, GH*0.525f, GW*0.3f, GH*0.1f);
        peekButtonBox = new BenHelper.Rect(GW*0.675f, GH*0.3f, GW*0.14f, GH*0.14f);
        popButtonBox = new BenHelper.Rect(GW*0.835f, GH*0.3f, GW*0.14f, GH*0.14f);
        hintButtonBox =  new BenHelper.Rect(GW*0.15f, 0, GW*0.7f, GH*0.1f);
    }

    @Override
    public void reset() {
        stack = new Stack();
        inputTextString = "";
        outputTextString = "";
    }

    @Override
    public String updateBefore(String factSelector) {
        hintTextString = "";
        if (titleButtonBox.checkHover()) {
            hintTextString = "A Stack is a collection of values where \nyou only have access to the top item.";
        }
        else if (inputTextButtonBox.checkHover()) {
            hintTextString = "Type a value to push to the Stack.";
        }
        else if (pushButtonBox.checkHover()) {
            hintTextString = "You can 'Push' values to the top of a \nStack.";
        }
        else if (outputTextButtonBox.checkHover()) {
            hintTextString = "This is the where previous value obtained \nfrom the Stack is displayed.";
        }
        else if (peekButtonBox.checkHover()) {
            hintTextString = "You can 'Peek' at the top value without \nremoving it from the Stack.";
        }
        else if (popButtonBox.checkHover()) {
            hintTextString = "You can 'Pop' the top value from the \nStack.";
        }

        if (inputTypingEnabled) {
            inputTextString = BenHelper.typing(inputTextString, 20);
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
            BenHelper.textDrawCentre(batch, font, value, valueRect, 1f, 
                BenHelper.DEFAULT_TEXT_COLOUR);
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

        BenHelper.textDrawCentre(batch, font, "Stack", titleButtonBox, 1.5f, 
            BenHelper.DEFAULT_TEXT_COLOUR);

        if (inputTextString.equals("")) {
            BenHelper.textDrawCentre(batch, font, "[click to type]", inputTextButtonBox, 1f,
                BenHelper.DEFAULT_TEXT_COLOUR);
        } else {
            BenHelper.textDrawCentre(batch, font, inputTextString, inputTextButtonBox, 1f, 
                BenHelper.DEFAULT_TEXT_COLOUR);
        }
        BenHelper.textDrawCentre(batch, font, outputTextString, outputTextButtonBox, 1f, 
            BenHelper.DEFAULT_TEXT_COLOUR);

        BenHelper.textDrawCentre(batch, font, "Push", pushButtonBox, 1.25f);
        BenHelper.textDrawCentre(batch, font, "Peek", peekButtonBox, 1.25f);
        BenHelper.textDrawCentre(batch, font, "Pop", popButtonBox, 1.25f);
        BenHelper.textDrawCentre(batch, font, hintTextString, hintButtonBox, 0.75f,
            Color.GRAY);

        if (BenHelper.DEBUG) {
            titleButtonBox.draw(shape, Color.RED);
            backButtonBox.draw(shape, Color.RED);
        }
    }

    @Override
    public String updateAfter(String factSelector) {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (inputTextButtonBox.checkHover()) {
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
        else if (peekButtonBox.checkClick()) {
            outputTextString = stack.peek();
        }
        else if (popButtonBox.checkClick()) {
            outputTextString = stack.pop();
        }

        return factSelector;
    }
}
