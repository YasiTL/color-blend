package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

import colormixer.KMColorUtils;


public class MyGdxGame extends ApplicationAdapter implements InputProcessor {
	Texture img;

	private Rectangle rectangle1, rectangle2, intersection;
	private ShapeRenderer shapeRenderer;
	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	private Pixmap pixmap, pixmap2, pixmap3, intersectionPixmap;
	private Texture pixmaptex, pixmaptex2, pixmaptex3, intersectiontext;
	private Rectangle[] rectangles;

	private float xpos, ypos;
	private boolean inRectangle;
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
//        int blendColor = blendARGB();

        Gdx.app.log("COLORSPACE", " " + KMColorUtils.mix(Color.RED, Color.BLUE));


        int rectWidth = 800;
        int rectHeight = 200;

        shapeRenderer = new ShapeRenderer();
        rectangle1 = new Rectangle();
        rectangle1.x = 0;
        rectangle1.y = 0;
        rectangle1.width = w;
        rectangle1.height = 200;

        rectangle2 = new Rectangle();
        rectangle2.x = 0;
        rectangle2.y = 0;
        rectangle2.width = w;
        rectangle2.height = h/5;

        rectangles = new Rectangle[]{rectangle1, rectangle2};





        pixmap = new Pixmap( (int) rectangle1.getWidth(), (int) rectangle1.getHeight(), Pixmap.Format.RGBA8888 );
        pixmap.setColor( 0, 1, 0, 1f );
        pixmap.fillRectangle((int) rectangle1.x, (int) rectangle1.y, (int) rectangle1.getWidth(), (int) rectangle1.getHeight());
        pixmaptex = new Texture( pixmap );
        pixmap.dispose();

        pixmap2 = new Pixmap( (int) rectangle1.getWidth(), (int) rectangle1.getHeight(), Pixmap.Format.RGBA8888 );
        pixmap2.setColor( 0, 0, 1, 1f );
        pixmap2.fillRectangle(0, 0, (int) rectangle1.getWidth(), (int) rectangle1.getHeight());
        pixmaptex2 = new Texture(pixmap2);
        pixmap2.dispose();


//
//        pixmap3 = new Pixmap( rectWidth, rectHeight, Pixmap.Format.RGBA8888 );
//        pixmap3.setColor( 1, 0, 0, 1f );
//        pixmap3.fillRectangle(0, 0, rectWidth, rectHeight);
//        pixmaptex3 = new Texture(pixmap3);
//        pixmap3.dispose();

        //spritePosition.set(0, 200, 0);

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
        batch.draw(pixmaptex, 0, rectangle1.y);
        batch.draw(pixmaptex2, 0, rectangle2.y);
        batch.end();

//        if (inRectangle){
//            rectangle1.y = ypos;
//        }


//        if (Gdx.input.isTouched()) {
//            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
//            touch = camera.unproject(touch);
//            camera.unproject(spritePosition.set(0, Gdx.input.getY(), 0));
//            //if(rectangle2.contains(touch.x, touch.y)){
//                Gdx.app.log("TOUCHED", "CONTAINS RECTANGLE");
//
//                // the unproject method takes a Vector3 in window coordinates (origin in
//                // upper left corner, y-axis pointing down) and transforms it to world
//                // coordinates.
//            }
//
//        }




        //batch.setBlendFunction(GL20.GL_CONSTANT_COLOR, GL20.GL_ONE_MINUS_SRC_COLOR); //CMYK Blending

        //batch.draw(pixmaptex, rectangle1.getX(), rectangle1.getY());
//        batch.draw(pixmaptex, spritePosition.x, spritePosition.y);
//        batch.draw(pixmaptex2, spritePosition2.x, spritePosition2.y);

//        batch.draw(pixmaptex3, spritePosition3.x, spritePosition3.y);
//        if(Gdx.input.isTouched() && rectangle1.contains(Gdx.input.getX(), Gdx.graphics.getHeight() -Gdx.input.getY())) {
//            Vector3 touchPos = new Vector3();
//            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//            camera.unproject(touchPos);
////            rectangle1.x = touchPos.y - 64 / 2;
//                rectangle1.setPosition(0, touchPos.y);
//                Gdx.app.log("IN RECT", "IN RECTANGLE");
//            }
//            else {
//                rectangle1.set(rectangle1.x, rectangle1.y, rectangle1.getWidth(), rectangle1.getHeight());
//                Gdx.app.log("IN RECT", "NOT IN RECTANGLE");
//            }

        //}

//        if(Gdx.input.isTouched() && rectangle1.contains(Gdx.input.getX(),camera.unproject(Gdx.input.getY()))) {
//            //rectangle1.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 100, 100);
//            Gdx.app.log("IN RECT", "IN RECTANGLE");
//        }

        //camera.unproject(spritePosition.set(0, Gdx.input.getY(), 0));
        //Gdx.app.log( "Y POSITION", spritePosition + " ");
        //camera.unproject(spritePosition2.set(0, Gdx.input.getY(), 0));

    }

//    intersectionPixmap = new Pixmap( (int)intersection.getWidth(), (int)intersection.getHeight(), Pixmap.Format.RGBA8888 );
//            intersectionPixmap.setColor( 0, 0, 1, 1f );
//            intersectionPixmap.fillRectangle(0, 0, (int)intersection.getWidth(), (int)intersection.getHeight());
//    intersectiontext = new Texture(intersectionPixmap);
//            intersectionPixmap.dispose();
//            batch.draw(intersectiontext, (int)intersection.x, (int)intersection.y);


    private Rectangle currentRectangle;

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
        Vector3 touchPosition = new Vector3(screenX, screenY, 0);
        camera.unproject(touchPosition);
        for(Rectangle rectangle: rectangles){
            if(rectangle.contains(0, touchPosition.y)){
                currentRectangle = rectangle;
                Gdx.app.log("TOUCHED", "IN RECTANGLE");
                //rectangle.setCenter(0, touchPosition.y);
            }
        }
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
        Vector3 touchPosition = new Vector3(screenX, screenY, 0);
        camera.unproject(touchPosition);
        currentRectangle.setCenter(0, touchPosition.y);
        for(Rectangle rectangle: rectangles){
            if(rectangle.contains(0, touchPosition.y)){
                Gdx.app.log("RECTANGLE", "IN RECTANGLE");
                rectangle.setCenter(0, touchPosition.y);
            }
        }

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
