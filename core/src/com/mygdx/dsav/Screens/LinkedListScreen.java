package com.mygdx.dsav.Screens;

import com.mygdx.dsav.BenHelper;
import com.mygdx.dsav.FactOption;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

public class LinkedListScreen extends FactOption {
    final int LISTSIZE = 4;
    boolean includeInserted;
    String[] listTextString;
    String insertedTextString;
    int typingSelector;
    String hintTextString;
    BenHelper.Rect titleButtonBox;
    BenHelper.Rect backButtonBox;
    BenHelper.Rect hintButtonBox;
    BenHelper.Rect[] listButtonBoxes;
    BenHelper.Rect insertedButtonBox;
    BenHelper.Rect toggleInsertButtonBox;

    @Override
    public void create() {
        hintTextString = "";
        titleButtonBox = new BenHelper.Rect(GW*0.35f, GH*0.85f, GW*0.3f, GH*0.15f);
        backButtonBox = new BenHelper.Rect(0, 0, GW*0.1f, GH*0.1f);
        hintButtonBox = new BenHelper.Rect(GW*0.15f, 0, GW*0.7f, GH*0.1f);

        includeInserted = false;
        typingSelector = -1;
        listTextString = new String[LISTSIZE];
        insertedTextString = "";
        listButtonBoxes = new BenHelper.Rect[LISTSIZE];
        insertedButtonBox = new BenHelper.Rect(GW*0.425f, GH*0.425f, GW*0.15f, GH*0.15f);
        toggleInsertButtonBox = new BenHelper.Rect(GW*0.425f, GH*0.613f, GW*0.15f, GH*0.075f);

        for (int i = 0; i < LISTSIZE; i++) {
            listTextString[i] = "";
            listButtonBoxes[i] = new BenHelper.Rect(GW*0.1f, GH*0.575f, GW*0.15f, GH*0.15f);
            if (i == 1 || i == 2) {
                listButtonBoxes[i].y = GH*0.275f;
            }
            if (i > 1) {
                listButtonBoxes[i].x = GW*0.75f;
            }
        }
        
    }

    @Override
    public void reset() {
        
    }

    @Override
    public String updateBefore(String factSelector) {
        if (BenHelper.screenClicked()) {
            typingSelector = -1;

            if (insertedButtonBox.checkHover()) {
                typingSelector = LISTSIZE;
            } else {
                for (int i = 0; i < LISTSIZE; i++) {
                    if (listButtonBoxes[i].checkHover()) {
                        typingSelector = i;
                        break;
                    }
                }
            }

            if (toggleInsertButtonBox.checkClick()) {
                includeInserted = !includeInserted;
            }
        }

        hintTextString = "";
        boolean hoverOnAnyIndex = false;
        for (int i = 0; i < LISTSIZE; i++) {
            hoverOnAnyIndex = hoverOnAnyIndex ||
                listButtonBoxes[i].checkHover();
        }
        if (hoverOnAnyIndex) {
            hintTextString = "Linked List items store a value and a reference to the next item.\n"+
                "This means that items can be fragmented in memory.";
        } else if (insertedButtonBox.checkHover()) {
            hintTextString = "To insert a value into a Linked List\n"+
                "just copy the 'next' of the item-before to the new item,\n"+
                "and change the 'next' of the item-before to the new item.";
        } else if (titleButtonBox.checkHover()) {
            hintTextString = "A Linked List is a collection of items similar to an Array.\n"+
                "Linked Lists are generally slower to access, but quicker to modify.\n"+
                "'Doubly' (AOT 'Singly') Linked Lists also reference previous items.";
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
        
        // draw button outlines
        for (int i = 0; i < LISTSIZE; i++) {
            listButtonBoxes[i].draw(shape);
        }
        insertedButtonBox.drawDotted(shape, !includeInserted);
        if (typingSelector >= 0 && typingSelector < LISTSIZE) {
            listButtonBoxes[typingSelector].draw(shape, Color.WHITE);
        }
        if (typingSelector == LISTSIZE) {   // insertable
            insertedButtonBox.drawDotted(shape, BenHelper.HOVER_TEXT_COLOUR, !includeInserted);
        }
        toggleInsertButtonBox.draw(shape);

        // draw arrows
        listButtonBoxes[0].arrowTo(shape, listButtonBoxes[1], 1, 6);
        listButtonBoxes[2].arrowTo(shape, listButtonBoxes[3], 6, 1);
        if (includeInserted) {
            listButtonBoxes[1].arrowTo(shape, insertedButtonBox, 4, 3);
            insertedButtonBox.arrowTo(shape, listButtonBoxes[2], 4, 3);
        } else {
            listButtonBoxes[1].arrowTo(shape, listButtonBoxes[2], 4, 3);
        }

        // draw text
        BenHelper.textDrawCentre(batch, font, "Linked List", titleButtonBox, 
            1.5f, BenHelper.DEFAULT_TEXT_COLOUR
        );
        BenHelper.textDrawCentre(batch, font, "Back", backButtonBox, 1.25f);
        BenHelper.textDrawCentre(batch, font, hintTextString, hintButtonBox, 
            0.75f, Color.GRAY
        );

        String[] indexDefaults = {"[zero]", "[one]", "[two]", "[three]"};
        if (includeInserted) {
            indexDefaults[2] = "[three]";
            indexDefaults[3] = "[four]";
        }
        for (int i = 0; i < LISTSIZE; i++) {
            String drawValue = listTextString[i];
            if (drawValue.equals("")) { drawValue = indexDefaults[i]; }
            BenHelper.textDrawCentreSelectable(batch, font,
                drawValue, listButtonBoxes[i], 
                1f, (typingSelector == i)
            );
        }

        String drawValue = insertedTextString;
        if (drawValue.equals("")) {
            if (includeInserted) {
                drawValue = "[two]";
            } else {
                drawValue = "[insert]"; 
            }
        }
        BenHelper.textDrawCentreSelectable(batch, font, 
            drawValue, insertedButtonBox, 
            1f, (typingSelector == LISTSIZE)
        );

        drawValue = "insert";
        if (includeInserted) {
            drawValue = "remove";
        }
        BenHelper.textDrawCentre(batch, font, drawValue, toggleInsertButtonBox, 1f);

        // debug: draw hitboxes
        if (BenHelper.DEBUG) {
            titleButtonBox.draw(shape, Color.RED);
            backButtonBox.draw(shape, Color.RED);
            hintButtonBox.draw(shape, Color.RED);
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