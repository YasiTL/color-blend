package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor {
	Texture img;

	private Rectangle rectangle, rectangle2, intersection;
	private ShapeRenderer shapeRenderer;
	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	private Pixmap pixmap, pixmap2, pixmap3, intersectionPixmap;
	private Texture pixmaptex, pixmaptex2, pixmaptex3, intersectiontext;

	private int xpos, ypos;
    Vector3 spritePosition = new Vector3();
    Vector3 spritePosition2 = new Vector3();
    Vector3 spritePosition3 = new Vector3();


	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();

        Gdx.input.setInputProcessor(this);

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

        Gdx.app.log( "WIDTH", w + " ");
        Gdx.app.log( "HEIGHT", h + " ");


//		pixmap.setColor(1.0f,0f, 0f, 1.0f);
//		pixmap.drawRectangle(100, 100, (int)w, (int)h/5);

//        pixmap = new Pixmap(800, 16, Pixmap.Format.RGBA8888);
//        for (int x = 0; x < pixmap.getWidth(); x++) {
//            int rgb = HSBtoRGBA8888(x / (float)pixmap.getWidth(), 1f, 1f);
//
//            // change the current color
//            pixmap.setColor(rgb);
//
//            // perform a fill operation with current color
//            pixmap.drawRectangle(x, 0, 1, pixmap.getHeight());
//            pixmaptex = new Texture(pixmap);
//        }

		camera.setToOrtho(false, 800, 800 * (h/w));
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0); //Makes it so that the bottom


        int rectWidth = 800;
        int rectHeight = 200;

        shapeRenderer = new ShapeRenderer();
        rectangle = new Rectangle();
        rectangle.x = 0;
        rectangle.y = 0;
        rectangle.width = w;
        rectangle.height = 200;

//        rectangle2 = new Rectangle();
//        rectangle2.x = 100;
//        rectangle2.y = 100;
//        rectangle2.width = w;
//        rectangle2.height = h/5;
//
//        intersection = new Rectangle();




        pixmap = new Pixmap( (int)rectangle.getWidth(), (int)rectangle.getHeight(), Pixmap.Format.RGBA8888 );
        pixmap.setColor( 0, 1, 0, 1f );
        pixmap.fillRectangle(0, 0, (int)rectangle.getWidth(), (int)rectangle.getHeight());
        pixmaptex = new Texture( pixmap );
        pixmap.dispose();
//
//        pixmap2 = new Pixmap( (int)rectangle.getWidth(), (int)rectangle.getHeight(), Pixmap.Format.RGBA8888 );
//        pixmap2.setColor( 0, 0, 1, 1f );
//        pixmap2.fillRectangle(0, 0, (int)rectangle.getWidth(), (int)rectangle.getHeight());
//        pixmaptex2 = new Texture(pixmap2);
//        pixmap2.dispose();


//
//        pixmap3 = new Pixmap( rectWidth, rectHeight, Pixmap.Format.RGBA8888 );
//        pixmap3.setColor( 1, 0, 0, 1f );
//        pixmap3.fillRectangle(0, 0, rectWidth, rectHeight);
//        pixmaptex3 = new Texture(pixmap3);
//        pixmap3.dispose();

        //spritePosition3.set(0, 200, 0);

		camera.update();

	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(0.976f, 0.968f, 0.886f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
//        if(Gdx.input.isTouched()){
//            xpos = Gdx.input.getX();
//            ypos = Gdx.graphics.getHeight() - Gdx.input.getY();
//        }
        batch.draw(pixmaptex, rectangle.x, rectangle.y);




        //batch.setBlendFunction(GL20.GL_CONSTANT_COLOR, GL20.GL_ONE_MINUS_SRC_COLOR); //CMYK Blending

        //batch.draw(pixmaptex, rectangle.getX(), rectangle.getY());
//        batch.draw(pixmaptex, spritePosition.x, spritePosition.y);
//        batch.draw(pixmaptex2, spritePosition2.x, spritePosition2.y);

//        batch.draw(pixmaptex3, spritePosition3.x, spritePosition3.y);
        batch.end();
        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
//            rectangle.x = touchPos.y - 64 / 2;
            if(rectangle.contains(touchPos.x, touchPos.y)){
                rectangle.set(0, touchPos.y, rectangle.getWidth(), rectangle.getHeight());
                Gdx.app.log("IN RECT", "IN RECTANGLE");
            }
            else {
                rectangle.set(0, touchPos.y, rectangle.getWidth(), rectangle.getHeight());
                Gdx.app.log("IN RECT", "NOT IN RECTANGLE");
            }
        }

//        if(Gdx.input.isTouched() && rectangle.contains(Gdx.input.getX(),camera.unproject(Gdx.input.getY()))) {
//            //rectangle.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 100, 100);
//            Gdx.app.log("IN RECT", "IN RECTANGLE");
//        }

        camera.unproject(spritePosition.set(0, Gdx.input.getY(), 0));
        //Gdx.app.log( "Y POSITION", spritePosition + " ");
        //camera.unproject(spritePosition2.set(0, Gdx.input.getY(), 0));

    }

//    intersectionPixmap = new Pixmap( (int)intersection.getWidth(), (int)intersection.getHeight(), Pixmap.Format.RGBA8888 );
//            intersectionPixmap.setColor( 0, 0, 1, 1f );
//            intersectionPixmap.fillRectangle(0, 0, (int)intersection.getWidth(), (int)intersection.getHeight());
//    intersectiontext = new Texture(intersectionPixmap);
//            intersectionPixmap.dispose();
//            batch.draw(intersectiontext, (int)intersection.x, (int)intersection.y);

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        xpos = Gdx.input.getX();
        ypos = Gdx.input.getY();
        Gdx.app.log( "TOUCHED",  pointer + " ");
        return false;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

	    return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        xpos = Gdx.input.getX();
        ypos = Gdx.input.getY();
        Gdx.app.log( "MOVED",  screenX + " " + screenY + " " + pointer);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
