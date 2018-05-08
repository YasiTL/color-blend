package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class MyGdxGame extends ApplicationAdapter implements InputProcessor {
	Texture img;

	private Rectangle rectangle1, rectangle2, rectangle3, intersectionRectangle, currentRectangle;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Pixmap pixmap, pixmap2, pixmap3, intersectionPixmap;
	private Texture pixmaptex, pixmaptex2, pixmaptex3, intersectionPixmapTex;
	private Rectangle[] rectangles;

	float w,h;


	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();

        Gdx.input.setInputProcessor(this);

        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

        Gdx.app.log( "WIDTH", w + " ");
        Gdx.app.log( "HEIGHT", h + " ");

		camera.setToOrtho(false, 800, 800 * (h/w));
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);


        rectangle1 = new Rectangle();
//        rectangle1.x = 0;
//        rectangle1.y = 0;
        rectangle1.width = w;
        rectangle1.height = 200;

        rectangle2 = new Rectangle();
        rectangle2.x = 0;
        rectangle2.y = 0;
        rectangle2.width = w;
        rectangle2.height = 200;

        rectangle3 = new Rectangle();
        rectangle3.x = 0;
        rectangle3.y = 0;
        rectangle3.width = w;
        rectangle3.height = 200;

        rectangles = new Rectangle[]{rectangle1, rectangle2, rectangle3};

        intersectionRectangle = new Rectangle();
//        intersectionRectangle.x = 0;
//        intersectionRectangle.width = w;

        pixmap = new Pixmap( (int) rectangle1.getWidth(), (int) rectangle1.getHeight(), Pixmap.Format.RGBA8888 );
        pixmap.setColor( 0, 1, 0, 1f );
        pixmap.fillRectangle((int) rectangle1.x, (int) rectangle1.y, (int) rectangle1.getWidth(), (int) rectangle1.getHeight());
        pixmaptex = new Texture( pixmap );
        pixmap.dispose();

        pixmap2 = new Pixmap( (int) rectangle2.getWidth(), (int) rectangle2.getHeight(), Pixmap.Format.RGBA8888 );
        pixmap2.setColor( 0, 0, 1, 1f );
        pixmap2.fillRectangle((int) rectangle2.x, (int)rectangle2.y, (int) rectangle2.getWidth(), (int) rectangle2.getHeight());
        pixmaptex2 = new Texture(pixmap2);
        pixmap2.dispose();

        pixmap3 = new Pixmap( (int) rectangle3.getWidth(), (int) rectangle3.getHeight(), Pixmap.Format.RGBA8888 );
        pixmap3.setColor( 1, 0, 0, 1f );
        pixmap3.fillRectangle(0, 0, (int) rectangle3.getWidth(), (int) rectangle3.getHeight());
        pixmaptex3 = new Texture(pixmap3);
        pixmap3.dispose();

//        intersectionPixmap = new Pixmap( (int) intersectionRectangle.getWidth(), (int) intersectionRectangle.getHeight(), Pixmap.Format.RGBA8888 );
//        intersectionPixmap.setColor( 1, 0, 1, 1f );
//        intersectionPixmap.fillRectangle((int) intersectionRectangle.x, (int) intersectionRectangle.y, (int) intersectionRectangle.getWidth(), (int) intersectionRectangle.getHeight());
//        intersectionPixmapTex = new Texture( intersectionPixmap );
//        intersectionPixmap.dispose();



		camera.update();

	}

//    void main() {
//        //pass along the position
//        gl_FragColor = vec4(1.0, 0.0, 0.0, 1.0);
//    }

	@Override
	public void render () {

		Gdx.gl.glClearColor(0.976f, 0.968f, 0.886f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
        batch.draw(pixmaptex, rectangle1.x, rectangle1.y);
        batch.draw(pixmaptex2, rectangle2.x, rectangle2.y);
        batch.draw(pixmaptex3, rectangle3.x, rectangle3.y);
        if(Intersector.intersectRectangles(rectangle1, rectangle2, intersectionRectangle)){
            intersectionPixmap = new Pixmap( (int) intersectionRectangle.getWidth(), (int) intersectionRectangle.getHeight(), Pixmap.Format.RGBA8888 );
            intersectionPixmap.setColor( 1, 0, 1, 1f );
            intersectionPixmap.fillRectangle(0, 0, (int) intersectionRectangle.getWidth(), (int) intersectionRectangle.getHeight());
            intersectionPixmapTex = new Texture( intersectionPixmap );
            intersectionPixmap.dispose();
            batch.draw(intersectionPixmapTex, intersectionRectangle.x, intersectionRectangle.y);
            Gdx.app.log("INTERSECTING", "ARE INTERSECTING!" + intersectionRectangle.getHeight()
                    + "y: " + intersectionRectangle.y);
        }

        batch.end();

    }

    static public boolean intersect(Rectangle rectangle1, Rectangle rectangle2, Rectangle intersection) {
        if (rectangle1.overlaps(rectangle2)) {
            Gdx.app.log("INTERSECT METHOD", "Wow It overlaps!!" );
            intersection.x = Math.max(rectangle1.x, rectangle2.x);
            intersection.width = Math.min(rectangle1.x + rectangle1.width, rectangle2.x + rectangle2.width) - intersection.x;
            intersection.y = Math.max(rectangle1.y, rectangle2.y);
            intersection.height = Math.min(rectangle1.y + rectangle1.height, rectangle2.y + rectangle2.height) - intersection.y;
            return true;
        }
        return false;
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
        Vector3 touchPosition = new Vector3(screenX, screenY, 0);
        camera.unproject(touchPosition);
        for(Rectangle rectangle: rectangles){
            if(rectangle.contains(touchPosition.x, touchPosition.y)){
                currentRectangle = rectangle;
                Gdx.app.log("TOUCHED", "IN RECTANGLE");
            }
        }
        return false;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
	    return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector3 touchPosition = new Vector3(screenX, screenY, 0);
        camera.unproject(touchPosition);
        if(currentRectangle.contains(touchPosition.x, touchPosition.y)){
            currentRectangle.setCenter(touchPosition.x, touchPosition.y);
        }
//        if(intersect(rectangle1, rectangle2, intersectionRectangle)){
//            intersectionPixmap = new Pixmap( (int) intersectionRectangle.getWidth(), (int) intersectionRectangle.getHeight(), Pixmap.Format.RGBA8888 );
//            intersectionPixmap.setColor( 1, 0, 1, 1f );
//            intersectionPixmap.fillRectangle((int) intersectionRectangle.x, (int) intersectionRectangle.y, (int) intersectionRectangle.getWidth(), (int) intersectionRectangle.getHeight());
//            intersectionPixmapTex = new Texture( intersectionPixmap );
//            intersectionPixmap.dispose();
//            Gdx.app.log("INTERSECTING", "ARE INTERSECTING!" + intersectionRectangle.getHeight());
//            Gdx.app.log("INTERSECTING DRAG", "Width: " + intersectionRectangle.getWidth() + "height: " +
//                    intersectionRectangle.getHeight() + "center: " + intersectionRectangle.getY());
//        }
//        Gdx.app.log( "MOVED",  screenX + " " + screenY + " " + pointer);
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
