package com.mygdx.dsav.Screens;

import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.FactOption;
import com.mygdx.dsav.DataStructs.Queue;

import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class QueueScreen extends FactOption {
    Queue queue;
    boolean inputTypingEnabled;
    String inputTextString;
    String outputTextString;
    String hintTextString;
    BenHelper.Rect titleButtonBox;
    BenHelper.Rect backButtonBox;
    BenHelper.Rect inputTextButtonBox;
    BenHelper.Rect enqueueButtonBox;
    BenHelper.Rect outputTextButtonBox;
    BenHelper.Rect peekButtonBox;
    BenHelper.Rect dequeueButtonBox;
    BenHelper.Rect hintButtonBox;

    @Override
    public void create() {
        queue = new Queue();
        inputTypingEnabled = false;
        inputTextString = "";
        outputTextString = "";
        hintTextString = "";
        titleButtonBox = new BenHelper.Rect(GW*0.4f, GH*0.85f, GW*0.2f, GH*0.15f);
        backButtonBox =  new BenHelper.Rect(0, 0, GW*0.1f, GH*0.1f);
        inputTextButtonBox = new BenHelper.Rect(GW*0.025f, GH*0.525f, GW*0.3f, GH*0.1f);
        enqueueButtonBox = new BenHelper.Rect(GW*0.105f, GH*0.3f, GW*0.14f, GH*0.14f);
        outputTextButtonBox = new BenHelper.Rect(GW*0.675f, GH*0.525f, GW*0.3f, GH*0.1f);
        peekButtonBox = new BenHelper.Rect(GW*0.675f, GH*0.3f, GW*0.14f, GH*0.14f);
        dequeueButtonBox = new BenHelper.Rect(GW*0.835f, GH*0.3f, GW*0.14f, GH*0.14f);
        hintButtonBox =  new BenHelper.Rect(GW*0.15f, 0, GW*0.7f, GH*0.1f);
    }

    @Override
    public void reset() {
        queue = new Queue();
        inputTextString = "";
        outputTextString = "";
    }

    @Override
    public String updateBefore(String factSelector) {
        hintTextString = "";
        if (titleButtonBox.checkHover()) {
            hintTextString = "A Queue is a collection of values where you can \nonly add to the back and take from the front.";
        }
        else if (inputTextButtonBox.checkHover()) {
            hintTextString = "Type a value to add to the Queue.";
        }
        else if (enqueueButtonBox.checkHover()) {
            hintTextString = "You can 'Enqueue' (add) values to the back of a \nQueue.";
        }
        else if (outputTextButtonBox.checkHover()) {
            hintTextString = "This is the where previous value obtained from \nthe Queue is displayed.";
        }
        else if (peekButtonBox.checkHover()) {
            hintTextString = "You can 'Peek' at the top value without \nremoving it from the Queue.";
        }
        else if (dequeueButtonBox.checkHover()) {
            hintTextString = "You can 'Dequeue' (remove) the value from the \nfront of the Queue.";
        }

        if (inputTypingEnabled) {
            inputTextString = BenHelper.typing(inputTextString, 20);
        }

        return factSelector;
    }

    @Override
    public void draw() {
        // clear screen
        batch.begin();
            ScreenUtils.clear(BenHelper.BACKGROUND_COLOUR);
            font.setColor(BenHelper.DEFAULT_TEXT_COLOUR);
        batch.end();

        // draw Queue outline
        shape.begin(ShapeType.Filled);
            shape.setColor(Color.BLACK);
            shape.rectLine(GW*0.35f, GH*0.2f, GW*0.35f, GH*0.8f, 2);
            shape.rectLine(GW*0.65f, GH*0.2f, GW*0.65f, GH*0.8f, 2);
        shape.end();

        // draw Queue items
        for (int i = 0; i < queue.size(); i++) {
            String value = queue.viewQueue().get(i);
            BenHelper.Rect valueRect = new BenHelper.Rect(GW*0.35f, GH*0.2f, GW*0.3f, GH*0.1f);
            valueRect.y += i * GH*0.1;
            valueRect.draw(shape);
            BenHelper.textDrawCentre(batch, font, value, 
                valueRect, 1f, 
                BenHelper.DEFAULT_TEXT_COLOUR
            );
        }

        // draw button outlines
        if (inputTypingEnabled) {
            inputTextButtonBox.draw(shape, Color.WHITE);
        } else {
            inputTextButtonBox.draw(shape, Color.BLACK);
        }
        enqueueButtonBox.draw(shape);
        outputTextButtonBox.draw(shape);
        peekButtonBox.draw(shape);
        dequeueButtonBox.draw(shape);

        // draw text
        BenHelper.textDrawCentre(batch, font, "Queue", 
            titleButtonBox, 1.5f, BenHelper.DEFAULT_TEXT_COLOUR
        );

        String drawValue = inputTextString;
        if (drawValue.equals("")) { drawValue = "[click to type]"; }
        BenHelper.textDrawCentreSelectable(batch, font, 
            drawValue, inputTextButtonBox, 1f, 
            inputTypingEnabled
        );

        BenHelper.textDrawCentre(batch, font, outputTextString, 
            outputTextButtonBox, 1f, 
            BenHelper.DEFAULT_TEXT_COLOUR
        );

        BenHelper.textDrawCentre(batch, font, "Enqueue", enqueueButtonBox, 1.25f);
        BenHelper.textDrawCentre(batch, font, "Peek", peekButtonBox, 1.25f);
        BenHelper.textDrawCentre(batch, font, "Dequeue", dequeueButtonBox, 1.25f);
        BenHelper.textDrawCentre(batch, font, hintTextString, 
            hintButtonBox, 0.75f, Color.GRAY
        );

        BenHelper.textDrawCentre(batch, font, "Back", backButtonBox, 1.25f);

        // debug: draw hitboxes
        if (BenHelper.DEBUG) {
            titleButtonBox.draw(shape, Color.RED);
            backButtonBox.draw(shape, Color.RED);
            hintButtonBox.draw(shape, Color.RED);
        }
    }

    @Override
    public String updateAfter(String factSelector) {
        if (BenHelper.screenClicked()) {
            if (inputTextButtonBox.checkHover()) {
                inputTypingEnabled = true;
            } else {
                inputTypingEnabled = false;
            }
        }

        if (backButtonBox.checkClick()) {
            factSelector = "menu";
        }
        else if (enqueueButtonBox.checkClick()) {
            if (!inputTextString.equals("")) {
                queue.enqueue(inputTextString);
                inputTextString = "";
            }
        }
        else if (peekButtonBox.checkClick()) {
            outputTextString = queue.peek();
        }
        else if (dequeueButtonBox.checkClick()) {
            outputTextString = queue.dequeue();
        }

        return factSelector;
    }
}