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
    public static final boolean DEBUG = false;
    public static final Color BACKGROUND_COLOUR = new Color(0.15f, 0.25f, 0.35f, 1f);
    public static final Color DEFAULT_TEXT_COLOUR = new Color(0.69f, 0.447f, 0.098f, 1);
    public static final Color HOVER_TEXT_COLOUR = new Color(1, 1, 1, 1);

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
     * @param b (boolean)
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

    public static class Rect {
        public float x, y, w, h;

        public Rect() {
            this(0, 0, 0, 0);
        }

        public Rect(float x, float y, float w, float h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

        public boolean checkHover() {
            float mx = mouseX();
            float my = mouseY();

            return mx >= x && mx <= x+w &&
                   my >= y && my <= y+h;
        }

        public boolean checkClick() {
            return Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                   checkHover();
        }

        public void draw(ShapeRenderer shape, Color col) {
            shape.setColor(col);
            shape.begin(ShapeType.Line);
                shape.rect( x, y, w, h );
            shape.end();
        }
        public void draw(ShapeRenderer shape) {
            draw(shape, Color.BLACK);
        }
    }

    public static String typing(String text) {
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

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) { text += ' '; }
        if (Gdx.input.isKeyJustPressed(Input.Keys.COMMA)) { text += ','; }
        if (Gdx.input.isKeyJustPressed(Input.Keys.PERIOD)) { text += '.'; }

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE) && text.length() > 0) {
            text = text.substring(0, text.length()-1);
        }


        /*
        if (Gdx.input.isKeyJustPressed(29)) {
            text += Input.Keys.toString(29);
            //text += 'a';
        }
         */

        return text;
    }

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
    public static void textDrawCentre(SpriteBatch batch, BitmapFont font,
        String text, Rect hitbox, float scale, Color col)
    {
        float midX = hitbox.x + (hitbox.w / 2);
        float midY = hitbox.y + (hitbox.h / 2);

        textDrawCentre(batch, font, text, midX, midY, scale, col);
    }
    public static void textDrawCentre(SpriteBatch batch, BitmapFont font,
        String text, Rect hitbox, float scale) 
    {
        Color col = DEFAULT_TEXT_COLOUR;
        if (hitbox.checkHover()) {
            col = HOVER_TEXT_COLOUR;
        }

        textDrawCentre(batch, font, text, hitbox, scale, col);
    }
}
