package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

/**
 * Created by mostafay on 4/30/18.
 */

public class RectangleActor extends Actor{
    private TextureRegion region;
    private Rectangle rectangle;
    private Texture pixmaptex;
    private Pixmap pixmap;
    public boolean dragging = false;



        public RectangleActor () {
            setTouchable(Touchable.enabled);

            rectangle = new Rectangle();
            rectangle.x = 0;
            rectangle.y = 0;
            rectangle.width = Gdx.graphics.getWidth();
            rectangle.height = 200;

            pixmap = new Pixmap( (int)rectangle.getWidth(), (int)rectangle.getHeight(), Pixmap.Format.RGBA8888 );
            pixmap.setColor( 0, 1, 0, 1f );
            pixmap.fillRectangle(0, 0, (int)rectangle.getWidth(), (int)rectangle.getHeight());
            pixmaptex = new Texture( pixmap );
            pixmap.dispose();
            region = new TextureRegion(pixmaptex);
            setBounds(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
            addListener(new DragListener() {
                public void touchDragged(InputEvent event, float x, float y, int pointer) {
                    Gdx.app.log("DRAG", "DRAGGING");
                    ((RectangleActor)event.getTarget()).dragging = true;
                    moveBy(0, y - getHeight() / 2);
                }
            });



        }

        @Override
        public void draw (Batch batch, float parentAlpha) {
            setBounds(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
            Color color = getColor();
            batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
            batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
            batch.draw(pixmaptex,rectangle.x,rectangle.y);
        }

    @Override
    public void act(float delta) {
            if(dragging){
                moveBy(0,  getHeight() / 2);
            }
        super.act(delta);
    }
}

