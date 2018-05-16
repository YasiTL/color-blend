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


public class MyGdxGame extends ApplicationAdapter implements InputProcessor {
	Texture img;

	private MyRectangle rectangle1, rectangle2, rectangle3, intersectionRectangle, currentRectangle, intersectionRectangle2;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Pixmap pixmap, pixmap2, pixmap3, intersectionPixmap, intersectionPixmap2;
	private Texture pixmaptex, pixmaptex2, pixmaptex3, intersectionPixmapTex, intersectionPixmapTex2;
	private MyRectangle[] rectangles;
	private Color intersectionColor, intersectionColor2;
	List<Texture> intersections;
	List<MyRectangle> intersectionRectangles;
	List<Color> intersectionColors;
    private boolean hasIntersectingRectangles;
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

        rectangle1 = new MyRectangle();
        rectangle1.width = w;
        rectangle1.height = 200;
        rectangle1.setColor(Color.RED);


        rectangle2 = new MyRectangle();
        rectangle2.x = 0;
        rectangle2.y = 300;
        rectangle2.width = w;
        rectangle2.height = 200;
        rectangle2.setColor(Color.BLUE);

        rectangle3 = new MyRectangle();
        rectangle3.x = 0;
        rectangle3.y = 700;
        rectangle3.width = w;
        rectangle3.height = 200;
        rectangle3.setColor(Color.TEAL);


        rectangles = new MyRectangle[]{rectangle1, rectangle2, rectangle3};
        intersectionRectangle = new MyRectangle();
        intersectionRectangle2 = new MyRectangle();

        intersections = new ArrayList<Texture>();
        intersectionRectangles = new ArrayList<MyRectangle>();
        intersectionColors = new ArrayList<Color>();

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

        intersectionPixmap = new Pixmap( (int) intersectionRectangle.getWidth(), (int) intersectionRectangle.getHeight(), Pixmap.Format.RGBA8888 );
        intersectionPixmapTex = new Texture( intersectionPixmap );
        intersectionPixmap.dispose();
        intersectionColor = colorBlend(rectangle1.getColor(), rectangle2.getColor());







        camera.update();

	}

//	public MyRectangle intersectRectangles (Rectangle rectangle1, Rectangle rectangle2) {
//        MyRectangle someRectangle = new MyRectangle();
//        if (rectangle1.overlaps(rectangle2)) {
//            someRectangle.x = Math.max(rectangle1.x, rectangle2.x);
//            someRectangle.width = Math.min(rectangle1.x + rectangle1.width, rectangle2.x + rectangle2.width) - someRectangle.x;
//            someRectangle.y = Math.max(rectangle1.y, rectangle2.y);
//            someRectangle.height = Math.min(rectangle1.y + rectangle1.height, rectangle2.y + rectangle2.height) - someRectangle.y;
//            return someRectangle;
//        }
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
        for(int i = 0; i < intersectionRectangles.size(); i++){
            batch.draw(intersections.get(i), intersectionRectangles.get(i).x, intersectionRectangles.get(i).y);
        }

        batch.end();
    }

    public Color colorBlend(Color RGBcolor1, Color RGBcolor2){
	    Gdx.app.log("COLOR BLEND METHOD 1", RGBcolor1 + "");
        Gdx.app.log("COLOR BLEND METHOD 2", RGBcolor2 + "");

        Color tempColor1 = new Color(RGBcolor1);
        Color tempColor2 = new Color(RGBcolor2);



        return tempColor1.mul(0.5f).add(tempColor2.mul(0.5f));

    }

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
        for(MyRectangle rectangle: rectangles){
            if(rectangle.contains(touchPosition.x, touchPosition.y)){
                currentRectangle = rectangle;
//                Gdx.app.log("TOUCHED", "IN RECTANGLE");
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

        intersectionRectangles.clear();
        intersections.clear();
        intersectionColors.clear();

//        Color color = new Color();
        hasIntersectingRectangles = false;
        Vector3 touchPosition = new Vector3(screenX, screenY, 0);
        camera.unproject(touchPosition);
        if(currentRectangle != null && currentRectangle.contains(touchPosition.x, touchPosition.y)){
            currentRectangle.setCenter(touchPosition.x, touchPosition.y);
        }
        for(int i = 0; i< rectangles.length; i++){
            for(int j = i+1; j < rectangles.length; j++ ){
                if(rectangles[i] != rectangles[j] && Intersector.intersectRectangles(rectangles[i], rectangles[j], intersectionRectangle)){

                    //intersectionRectangle.setColor(colorBlend(rectangles[i].getColor(), rectangles[j].getColor()));
                    Gdx.app.log("COLOR BLEND RESULT", colorBlend(rectangles[i].getColor(), rectangles[j].getColor()) + "");
                    intersectionRectangles.add(new MyRectangle(colorBlend(rectangles[i].getColor(), rectangles[j].getColor()), intersectionRectangle.x, intersectionRectangle.y,
                            intersectionRectangle.width, intersectionRectangle.height));
                    intersectionPixmap = new Pixmap( (int) intersectionRectangle.getWidth(), (int) intersectionRectangle.getHeight(), Pixmap.Format.RGBA8888 );
                    Gdx.app.log("RECTANGLES", intersectionRectangles.get(intersectionRectangles.size() - 1).getColor() + " ");
                    intersectionPixmap.setColor(intersectionRectangles.get(intersectionRectangles.size()-1).getColor());
                    intersectionPixmap.fillRectangle(0, 0, (int) intersectionRectangle.getWidth(), (int) intersectionRectangle.getHeight());
                    intersectionPixmapTex = new Texture( intersectionPixmap );
                    intersections.add(intersectionPixmapTex);
                    intersectionPixmap.dispose();
//                    Gdx.app.log("INTERSECTING", "ARE INTERSECTING!" + intersectionRectangle.getHeight()
//                            + "y: " + intersectionRectangle.y);
                    hasIntersectingRectangles = true;
                }
//                else{
//                    intersectionPixmap = new Pixmap(0,0,Pixmap.Format.RGBA8888);
//                    intersectionPixmapTex =  new Texture(intersectionPixmap);
//                }

            }
        }
//        if(Intersector.intersectRectangles(rectangle2, rectangle3, intersectionRectangle2)){
//            intersectionPixmap2 = new Pixmap( (int) intersectionRectangle2.getWidth(), (int) intersectionRectangle2.getHeight(), Pixmap.Format.RGBA8888 );
//            intersectionPixmap2.setColor(intersectionColor2);
//            intersectionPixmap2.fillRectangle(0, 0, (int) intersectionRectangle2.getWidth(), (int) intersectionRectangle2.getHeight());
//            intersectionPixmapTex2 = new Texture( intersectionPixmap2 );
//            intersectionPixmap2.dispose();
//            Gdx.app.log("INTERSECTING", "ARE INTERSECTING!" + intersectionRectangle2.getHeight()
//                    + "y: " + intersectionRectangle2.y);
//        }
//        else{
//            intersectionPixmap2 = new Pixmap(0,0,Pixmap.Format.RGBA8888);
//            intersectionPixmapTex2 =  new Texture(intersectionPixmap2);
//        }
//        MyRectangle someRectangle = new MyRectangle();
//        if(rectangle2.overlaps(rectangle1)){
//            if(Intersector.intersectRectangles(rectangle1, rectangle2, someRectangle)){
//                intersectionPixmap = new Pixmap( (int) someRectangle.getWidth(), (int) someRectangle.getHeight(), Pixmap.Format.RGBA8888 );
//                intersectionPixmap.setColor(intersectionColor);
//                intersectionPixmap.fillRectangle(0, 0, (int) someRectangle.getWidth(), (int) someRectangle.getHeight());
//                intersectionPixmapTex = new Texture( intersectionPixmap );
//                intersectionPixmap.dispose();
//                Gdx.app.log("INTERSECTING", "ARE INTERSECTING!" + someRectangle.getHeight()
//                        + "y: " + someRectangle.y);
//            }
//        }
//        MyRectangle intersectionRectangle = intersectRectangles(rectangle1, rectangle2);
//        intersectionPixmap = new Pixmap( (int) intersectionRectangle.getWidth(), (int) intersectionRectangle.getHeight(), Pixmap.Format.RGBA8888 );
//        intersectionPixmap.setColor(intersectionColor);
//        intersectionPixmap.fillRectangle(0, 0, (int) intersectionRectangle.getWidth(), (int) intersectionRectangle.getHeight());
//        intersectionPixmapTex = new Texture( intersectionPixmap );
//        intersectionPixmap.dispose();


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
