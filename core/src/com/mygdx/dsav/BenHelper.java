package com.mygdx.dsav;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.Input;

/**
 * Defines helper methods for my LibGDX use.
 */
public class BenHelper {
    /**
     * Will be set to false in production,
     * but true for testing.
     */
    public static final boolean DEBUG = true;
    public static final Color BACKGROUND_COLOUR = new Color(0.15f, 0.25f, 0.35f, 1f);
    public static final Color DEFAULT_TEXT_COLOUR = new Color(0.69f, 0.447f, 0.098f, 1);
    public static final Color HOVER_TEXT_COLOUR = new Color(1, 1, 1, 1);

    private static final float GW = Gdx.graphics.getWidth();
    //private static final float GH = Gdx.graphics.getHeight();

    /**
     * Draw an Arrow from point p1 to point p2.
     * @param p1x x-position of p1
     * @param p1y y-position of p1
     * @param p2x x-position of p2
     * @param p2y y-position of p2
     * @param ang angle (deg) between main line and arrow lines
     * @param alen length of arrow lines
     * @param thck thickness of the lines
     * @param col colour of the arrow
     */
    public static void drawArrow(ShapeRenderer shape, float p1x, float p1y, float p2x, float p2y,
                           float ang, float alen, float thck, Color col) {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(col);
        shape.rectLine(p1x, p1y, p2x, p2y, thck);

        final float degToRad = (float) Math.PI / 180;

        float p1AngP2 = (float) Math.atan2(degToRad*(p2y-p1y), degToRad*(p2x-p1x));

        float aang1, aang2;
        aang1 = p1AngP2 + (degToRad * ang);
        aang2 = p1AngP2 - (degToRad * ang);

        float ap1x, ap1y, ap2x, ap2y;
        ap1x = p2x - ((float) Math.cos(aang1) * alen);
        ap1y = p2y - ((float) Math.sin(aang1) * alen);
        ap2x = p2x - ((float) Math.cos(aang2) * alen);
        ap2y = p2y - ((float) Math.sin(aang2) * alen);

        shape.rectLine(p2x, p2y, ap1x, ap1y, thck);
        shape.rectLine(p2x, p2y, ap2x, ap2y, thck);
        shape.end();
    }

    /**
     * Return true if value is between min and max (both inclusive).
     * @param value value to test
     * @param min lower bound
     * @param max upper bound
     * @return (boolean) min <= value <= max
     */
    public static boolean between(int value, int min, int max) {
        return value >= min && value <= max;
    }

    /**
     * Return the x-position of the cursor. <br><br>
     * Abstracted from LibGdx default because y-position is not calculated correctly. <br>
     * &#032;&#032;&#032;&#032;- calculated with (0,0) as top left unlike rest of LibGdx.
     * @return int x-coordinate.
     */
    public static int mouseX() {
        return Gdx.input.getX();
    }

    /**
     * Return the y-position of the cursor. <br><br>
     * Abstracted from LibGdx default because y-position is not calculated correctly. <br>
     * &#032;&#032;&#032;&#032;- calculated with (0,0) as top left unlike rest of LibGdx.
     * @return int y-coordinate.
     */
    public static int mouseY() {
        return Gdx.graphics.getHeight() - Gdx.input.getY();
    }

    /**
     * Java doesn't have built in boolean->integer apparently...
     * @return 1 (if b == true) or 0
     */
    public static int boolToInt(boolean b) { return b ? 1 : 0; }

    public static class MathsVector {
        public float x, y;

        public MathsVector() {
            this(0, 0);
        }

        public MathsVector(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Return true if left mouse button has just been released.
     */
    public static boolean screenClicked() {
        return Gdx.input.isButtonJustPressed(Input.Buttons.LEFT);
    }

    /**
     * Rectangle class that has methods for handling hitboxing.
     */
    public static class Rect {
        public float x, y, w, h;

        public Rect() {
            this(0, 0, 0, 0);
        }

        /**
         * @param x
         * @param y
         * @param w
         * @param h
         */
        public Rect(float x, float y, float w, float h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

        /**
         * Return true if the cursor is in the rectangle.
         */
        public boolean checkHover() {
            float mx = mouseX();
            float my = mouseY();

            return mx >= x && mx <= x+w &&
                   my >= y && my <= y+h;
        }

        /**
         * Return true if the cursor is in the rectangle and 
         * the left mouse button has been clicked.
         */
        public boolean checkClick() {
            return screenClicked() && checkHover();
        }

        /**
         * @param shape (ShapeRenderer)
         * @param col (default: black)
         */
        public void draw(ShapeRenderer shape, Color col) {
            shape.setColor(col);
            shape.begin(ShapeType.Line);
                shape.rect( x, y, w, h );
            shape.end();
        }
        public void draw(ShapeRenderer shape) {
            draw(shape, Color.BLACK);
        }

        public void drawDotted(ShapeRenderer shape, Color col, boolean isDotted) {
            if (!isDotted) {
                draw(shape, col);
                return;
            } 
            
            shape.setColor(col);
            shape.begin(ShapeType.Line);
                for (float i = x; i < x + w; i += 10) {
                    shape.circle(i, y, 1);
                    shape.circle(i, y+h, 1);
                }
                for (float j = y; j < y + h; j += 10) {
                    shape.circle(x, j, 1);
                    shape.circle(x+w, j, 1);
                }
            shape.end();
        }
        public void drawDotted(ShapeRenderer shape, boolean isDotted) {
            drawDotted(shape, Color.BLACK, isDotted);
        }

        /**
         * Draw an arrow from this Rect to another Rect. <br><br>
         * Points: 
         * <ul>
         *     <li>0: bottom left</li>
         *     <li>1: bottom middle</li>
         *     <li>2: bottom right</li>
         *     <li>3: middle left</li>
         *     <li>4: middle right</li>
         *     <li>5: top left</li>
         *     <li>6: top middle</li>
         *     <li>7: top right</li>
         * </ul>
         * ~
         * @param shape
         * @param otherBox
         * @param thisPoint
         * @param otherPoint
         */
        public void arrowTo(ShapeRenderer shape, Rect otherBox, int thisPoint, int otherPoint) {
            Integer[] horHalf = new Integer[]{1, 6};
            Integer[] horFull = new Integer[]{2, 4, 7};
            Integer[] verHalf = new Integer[]{3, 4};
            Integer[] verFull = new Integer[]{5, 6, 7};

            float startX = getCoords(thisPoint, x, w, horHalf, horFull);
            float startY = getCoords(thisPoint, y, h, verHalf, verFull);
            float endX = getCoords(otherPoint, otherBox.x, otherBox.w, horHalf, horFull);
            float endY = getCoords(otherPoint, otherBox.y, otherBox.h, verHalf, verFull);

            drawArrow(shape, startX, startY, endX, endY, 30, GW/75, GW/500, Color.BLACK);
        }
        /**
         * Calculate points from index.
         * 
         * @param shape
         * @param otherBox
         * @param ind1
         *Color col = BenHelper.DEFAULT_TEXT_COLOUR; @param ind2
         */
        public void arrowToCalc(ShapeRenderer shape, Rect otherBox, int ind1, int ind2) {
            assert ind1 != ind2: "BenHelper.Rect.arrowToCalc() - indices are the same";
            boolean bothInRange = 
                ind1 >= 0 && ind1 <= 3 &&
                ind2 >= 0 && ind2 <= 3;
            assert bothInRange: "BenHelper.Rect.arrowToCalc() - index out of range";

            int tp, op;
            boolean shouldSwap;

            if (ind1 == 0 || ind2 == 0) {
                if (ind1 == 1 || ind2 == 1) {
                    // set(ind1, ind2) = set(0, 1)
                    tp = 3;
                    op = 6;
                }
                else if (ind1 == 2 || ind2 == 2) {
                    // set(ind1, ind2) = set(0, 2)
                    tp = 4;
                    op = 6;
                }
                else {
                    // set(ind1, ind2) = set(0, 3)
                    tp = 1;
                    op = 6;
                }
                shouldSwap = (ind2 == 0);
            }
            else if (ind1 == 1 || ind2 == 1) {
                if (ind1 == 2 || ind2 == 2) {
                    // set(ind1, ind2) = set(1, 2)
                    tp = 4;
                    op = 3;
                }
                else {
                    // set(ind1, ind2) = set(1, 3)
                    tp = 1;
                    op = 3;
                }
                shouldSwap = (ind2 == 1);
            }
            else {
                // set(ind1, ind2) = set(2, 3)
                tp = 1;
                op = 4;
                shouldSwap = (ind2 == 2);
            }

            if (shouldSwap) {
                tp = tp ^ op ^ (op = tp);
            }

            this.arrowTo(shape, otherBox, tp, op);
        }
    }

    private static float getCoords(int point, float start, float dist, Integer[] half, Integer[] full) {
        float pos = start;

        boolean inHalf = false;
        boolean inFull = false;
        for (int x : half) {
            if (point == x) {
                inHalf = true;
            }
        }
        if (!inHalf) {
            for (int x : full) {
                if (point == x) {
                    inFull = true;
                }
            }
        }

        if (inHalf) {
            pos += dist * 0.5f;
        } else if (inFull) {
            pos += dist;
        }

        return pos;
    }

    /**
     * Enter a string (what is currently displayed). <br><br>
     * Check the keyboard and update string. <br><br>
     * Return the result.
     * @param text 
     * @param maxLen (default: 100)
     * @return text + keyboardChanges
     */
    public static String typing(String text, int maxLen) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE) && text.length() > 0) {
            text = text.substring(0, text.length()-1);
        }

        if (text.length() >= maxLen) {
            return text;
        }

        for (int i = 29; i < 54; i++) {
            if (Gdx.input.isKeyJustPressed(i)) {
                if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ||
                    Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT))
                {
                    text += Input.Keys.toString(i);
                } else {
                    text += Input.Keys.toString(i).toLowerCase();
                }
            }
        }

        for (int i = 7; i <= 16; i ++) {
            if (Gdx.input.isKeyJustPressed(i)) {
                text += Input.Keys.toString(i);
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) { text += ' '; }
        if (Gdx.input.isKeyJustPressed(Input.Keys.COMMA)) { text += ','; }
        if (Gdx.input.isKeyJustPressed(Input.Keys.PERIOD)) { text += '.'; }

        return text;
    }
    public static String typing(String text) {
        return typing(text, 100);
    }

    public static int typingNumbers(int num, int maxLen) {
        String sNum = Integer.toString(num);
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE) && sNum.length() > 0) {
            sNum = sNum.substring(0, sNum.length()-1);
        }

        if (sNum.length() == 0) {
            sNum += "0";
        }

        if (sNum.length() >= maxLen) {
            return Integer.parseInt(sNum);
        }

        for (int i = 7; i <= 16; i ++) {
            if (Gdx.input.isKeyJustPressed(i)) {
                sNum += Input.Keys.toString(i);
            }
        }

        return Integer.parseInt(sNum);
    }
    public static int typingNumbers(int num) {
        return typingNumbers(num, 4);
    }

    /**
     * Draw text with centre midX, midY.
     * @param batch
     * @param font
     * @param text
     * @param midX
     * @param midY
     * @param scale
     * @param col
     */
    public static void textDrawCentre(SpriteBatch batch, BitmapFont font, 
        String text, float midX, float midY, float scale, Color col) 
    {
        batch.begin();
            font.getData().setScale(scale);
            font.setColor(col);
            GlyphLayout glyph = new GlyphLayout(font, text);
            
            float drwX = midX - (glyph.width / 2);
            float drwY = midY + (glyph.height / 2);

            font.draw(batch, glyph, drwX, drwY);
        batch.end();
    }
    /**
     * Draw text with centre mid(Rect hitbox).
     * @param batch
     * @param font
     * @param text
     * @param hitbox
     * @param scale
     * @param col
     */
    public static void textDrawCentre(SpriteBatch batch, BitmapFont font,
        String text, Rect hitbox, float scale, Color col)
    {
        float midX = hitbox.x + (hitbox.w / 2);
        float midY = hitbox.y + (hitbox.h / 2);

        textDrawCentre(batch, font, text, midX, midY, scale, col);
    }
    /**
     * Draw text with centre mid(Rect hitbox). <br>
     * Colour will be supplied by the function; variable on hover.
     * @param batch
     * @param font
     * @param text
     * @param hitbox
     * @param scale
     */
    public static void textDrawCentre(SpriteBatch batch, BitmapFont font,
        String text, Rect hitbox, float scale) 
    {
        Color col = DEFAULT_TEXT_COLOUR;
        if (hitbox.checkHover()) {
            col = HOVER_TEXT_COLOUR;
        }

        textDrawCentre(batch, font, text, hitbox, scale, col);
    }

    /**
     * Draw text for a selectable hitbox. <br><br>
     * When unselected, it will have a hover colour when hovered over. <br><br>
     * When selected, it will just have a static colour.
     * @param batch
     * @param font
     * @param text
     * @param hitbox
     * @param scale
     * @param selected
     */
    public static void textDrawCentreSelectable(SpriteBatch batch, BitmapFont font, 
        String text, Rect hitbox, float scale, boolean selected) 
    {
        if (selected) {
            textDrawCentre( batch, font, text, hitbox, scale,
                DEFAULT_TEXT_COLOUR
            );
        }
        else {
            textDrawCentre(batch, font, text, hitbox, scale);
        }
    }
}
