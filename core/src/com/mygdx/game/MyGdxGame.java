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
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MyGdxGame extends ApplicationAdapter implements InputProcessor {

    private OrthographicCamera camera;
	private SpriteBatch batch;
    private float w,h;

    private MyRectangle rectangle1, rectangle2, rectangle3, rectangle4,
            intersectionRectangle, currentRectangle, intersectionRectangle2;
    private Pixmap pixmap, pixmap2, pixmap3, pixmap4,
            intersectionPixmap, doubleintersectionPixmap;
	private Texture pixmaptex, pixmaptex2, pixmaptex3, pixmaptex4, intersectionPixmapTex;
	private MyRectangle[] rectangles;
	List<Texture> intersections, doubleIntersections;
	List<MyRectangle> intersectionRectangles, doubleIntersectionRectangles;
    public int randomint;
    private boolean hasDoubleIntersection, touchup;
    private Texture doubleintersectionPixmapTex;

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

        Random r = new Random();
        randomint = r.nextInt(3);
        switch (randomint) {
            case 0:
                rectangle1 = new MyRectangle(Color.FIREBRICK, 0, 0, w, 220);
                rectangle2 = new MyRectangle(Color.ORANGE, 0, 200, w, 220);
                rectangle3 = new MyRectangle(Color.YELLOW, 0, 400, w, 220);
                rectangle4 = new MyRectangle(Color.GREEN, 0, 600, w, 220);
                break;
            case 1:
                rectangle1 = new MyRectangle(Color.CHARTREUSE, 0, 0, w, 220);
                rectangle2 = new MyRectangle(Color.LIME, 0, 200, w, 220);
                rectangle3 = new MyRectangle(Color.FOREST, 0, 400, w, 220);
                rectangle4 = new MyRectangle(Color.OLIVE, 0, 600, w, 220);
                break;
            case 2:
                rectangle1 = new MyRectangle(Color.RED, 0, 0, w, 220);
                rectangle2 = new MyRectangle(Color.ORANGE, 0, 200, w, 220);
                rectangle3 = new MyRectangle(Color.GREEN, 0, 400, w, 220);
                rectangle4 = new MyRectangle(Color.BLUE, 0, 600, w, 220);
            case 3:
                rectangle1 = new MyRectangle(Color.RED, 0, 0, w, 220);
                rectangle2 = new MyRectangle(Color.BLUE, 0, 200, w, 220);
                rectangle3 = new MyRectangle(Color.TEAL, 0, 400, w, 220);
                rectangle4 = new MyRectangle(Color.ORANGE, 0, 600, w, 220);
                break;
        }




        rectangles = new MyRectangle[]{rectangle1, rectangle2, rectangle3, rectangle4};
        intersectionRectangle = new MyRectangle();
        intersectionRectangle2 = new MyRectangle();

        intersections = new ArrayList<Texture>();
        doubleIntersections = new ArrayList<Texture>();
        intersectionRectangles = new ArrayList<MyRectangle>();
        doubleIntersectionRectangles = new ArrayList<MyRectangle>();

        pixmap = new Pixmap( (int) rectangle1.getWidth(), (int) rectangle1.getHeight(), Pixmap.Format.RGBA8888 );
        pixmap.setColor( rectangle1.getColor() );
        pixmap.fillRectangle(0, 0, (int) rectangle1.getWidth(), (int) rectangle1.getHeight());
        pixmaptex = new Texture( pixmap );
        pixmap.dispose();

        pixmap2 = new Pixmap( (int) rectangle2.getWidth(), (int) rectangle2.getHeight(), Pixmap.Format.RGBA8888 );
        pixmap2.setColor( rectangle2.getColor() );
        pixmap2.fillRectangle(0, 0, (int) rectangle2.getWidth(), (int) rectangle2.getHeight());
        pixmaptex2 = new Texture(pixmap2);
        pixmap2.dispose();

        pixmap3 = new Pixmap( (int) rectangle3.getWidth(), (int) rectangle3.getHeight(), Pixmap.Format.RGBA8888 );
        pixmap3.setColor( rectangle3.getColor() );
        pixmap3.fillRectangle(0, 0, (int) rectangle3.getWidth(), (int) rectangle3.getHeight());
        pixmaptex3 = new Texture(pixmap3);
        pixmap3.dispose();

        pixmap4 = new Pixmap( (int) rectangle4.getWidth(), (int) rectangle4.getHeight(), Pixmap.Format.RGBA8888 );
        pixmap4.setColor( rectangle4.getColor() );
        pixmap4.fillRectangle(0, 0, (int) rectangle4.getWidth(), (int) rectangle4.getHeight());
        pixmaptex4 = new Texture(pixmap4);
        pixmap4.dispose();

        intersectionPixmap = new Pixmap( (int) intersectionRectangle.getWidth(), (int) intersectionRectangle.getHeight(), Pixmap.Format.RGBA8888 );
        intersectionPixmapTex = new Texture( intersectionPixmap );
        intersectionPixmap.dispose();

        doubleintersectionPixmap = new Pixmap( (int) intersectionRectangle2.getWidth(), (int) intersectionRectangle2.getHeight(), Pixmap.Format.RGBA8888 );
        doubleintersectionPixmapTex = new Texture( doubleintersectionPixmap );
        doubleintersectionPixmap.dispose();

        camera.update();

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.976f, 0.968f, 0.886f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);


		batch.begin();

        batch.draw(pixmaptex, rectangle1.x, rectangle1.y);
        batch.draw(pixmaptex2, rectangle2.x, rectangle2.y);
        batch.draw(pixmaptex3, rectangle3.x, rectangle3.y);
        batch.draw(pixmaptex4, rectangle4.x, rectangle4.y);

        //Draw the intersecting rectangles
        for(int i = 0; i < intersectionRectangles.size(); i++){
            batch.draw(intersections.get(i), intersectionRectangles.get(i).x, intersectionRectangles.get(i).y);
        }

        if(hasDoubleIntersection){
            for(int i = 0; i < doubleIntersectionRectangles.size(); i++){
                batch.draw(doubleIntersections.get(i), doubleIntersectionRectangles.get(i).x, doubleIntersectionRectangles.get(i).y);
            }
        }

        batch.end();
    }

    public Color colorBlend(Color RGBcolor1, Color RGBcolor2){
        Color tempColor1 = new Color(RGBcolor1);
        Color tempColor2 = new Color(RGBcolor2);
        return tempColor1.mul(0.5f).add(tempColor2.mul(0.5f));

    }

	@Override
	public void dispose () {
		batch.dispose();
	}

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 touchPosition = new Vector3(screenX, screenY, 0);
        camera.unproject(touchPosition);
        for(MyRectangle rectangle: rectangles){
            if(rectangle.contains(touchPosition.x, touchPosition.y)){
                currentRectangle = rectangle;
            }
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        intersectionRectangles.clear();
        intersections.clear();
        doubleIntersectionRectangles.clear();
        doubleIntersections.clear();

        Vector3 touchPosition = new Vector3(screenX, screenY, 0);
        camera.unproject(touchPosition);
        if(currentRectangle != null && currentRectangle.contains(touchPosition.x, touchPosition.y)){
            currentRectangle.setCenter(touchPosition.x, touchPosition.y);
        }

        for(int i = 0; i< rectangles.length; i++){
            for(int j = i+1; j < rectangles.length; j++ ) {
                if (rectangles[i] != rectangles[j] && Intersector.intersectRectangles(rectangles[i], rectangles[j], intersectionRectangle)) {

                    intersectionRectangles.add(new MyRectangle(colorBlend(rectangles[i].getColor(), rectangles[j].getColor()), intersectionRectangle.x, intersectionRectangle.y,
                            intersectionRectangle.width, intersectionRectangle.height));

                    intersectionPixmap = new Pixmap((int) intersectionRectangle.getWidth(), (int) intersectionRectangle.getHeight(), Pixmap.Format.RGBA8888);
                    intersectionPixmap.setColor(intersectionRectangles.get(intersectionRectangles.size() - 1).getColor());
                    intersectionPixmap.fillRectangle(0, 0, (int) intersectionRectangle.getWidth(), (int) intersectionRectangle.getHeight());
                    intersectionPixmapTex = new Texture(intersectionPixmap);
                    intersections.add(intersectionPixmapTex);
                    intersectionPixmap.dispose();


                    if(intersectionRectangles.size() > 1){
                        if(Intersector.intersectRectangles(intersectionRectangles.get(0), intersectionRectangles.get(1), intersectionRectangle2)){
                            hasDoubleIntersection = true;
                            randomint = 2;

                            doubleIntersectionRectangles.add(new MyRectangle(colorBlend(intersectionRectangles.get(0).getColor(), intersectionRectangles.get(1).getColor()), intersectionRectangle2.x, intersectionRectangle2.y,
                                    intersectionRectangle2.width, intersectionRectangle2.height));

                            //Create the pixmap and corrisponding Texture for the double blended colors
                            doubleintersectionPixmap = new Pixmap((int) intersectionRectangle2.getWidth(), (int) intersectionRectangle2.getHeight(), Pixmap.Format.RGBA8888);
                            doubleintersectionPixmap.setColor(doubleIntersectionRectangles.get(doubleIntersectionRectangles.size() - 1).getColor());
                            doubleintersectionPixmap.fillRectangle(0, 0, (int) intersectionRectangle2.getWidth(), (int) intersectionRectangle2.getHeight());
                            doubleintersectionPixmapTex = new Texture(doubleintersectionPixmap);
                            doubleIntersections.add(doubleintersectionPixmapTex);
                            doubleintersectionPixmap.dispose();
                        }
                    }
                }
            }
        }


        return false;
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
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
