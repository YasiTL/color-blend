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

	private Rectangle rectangle;
	private ShapeRenderer shapeRenderer;
	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	private Pixmap pixmap, pixmap2;
	private Texture pixmaptex, pixmaptex2;
    Vector3 spritePosition = new Vector3();
    Vector3 spritePosition2 = new Vector3();

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();

		img = new Texture(Gdx.files.internal("badlogic.jpg"));

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

        Gdx.app.log( "WIDTH", w + " ");
        Gdx.app.log( "HEIGHT", h + " ");


//		shapeRenderer = new ShapeRenderer();
//		rectangle = new Rectangle();
//		rectangle.x = 100;
//		rectangle.y = 100;
//		rectangle.width = w;
//		rectangle.height = h/5;

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
        Gdx.input.setInputProcessor(this);

        int rectWidth = 800;
        int rectHeight = 200;

        pixmap = new Pixmap( rectWidth, rectHeight, Pixmap.Format.RGBA8888 );
        pixmap.setColor( 0, 1, 0, 0.75f );
        pixmap.fillRectangle(0, 0, rectWidth, rectHeight);
        pixmaptex = new Texture( pixmap );
        pixmap.dispose();

        pixmap2 = new Pixmap( rectWidth, rectHeight, Pixmap.Format.RGBA8888 );
        pixmap2.setColor( 0, 0, 1, 0.75f );
        pixmap2.fillRectangle(0, 0, rectWidth, rectHeight);
        pixmaptex2 = new Texture(pixmap2);
        pixmap2.dispose();

		camera.update();

	}

	@Override
	public void render () {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(pixmaptex, spritePosition.x, spritePosition.y);
        batch.draw(pixmaptex2, spritePosition2.x, spritePosition2.y);
		batch.end();

        camera.unproject(spritePosition.set(0, Gdx.input.getY(), 0));
        //camera.unproject(spritePosition2.set(0, Gdx.input.getY(), 0));

    }
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}


    public static int HSBtoRGBA8888(float hue, float saturation, float brightness) {
        int r = 0, g = 0, b = 0;
        if (saturation == 0) {
            r = g = b = (int)(brightness * 255.0f + 0.5f);
        } else {
            float h = (hue - (float)Math.floor(hue)) * 6.0f;
            float f = h - (float)java.lang.Math.floor(h);
            float p = brightness * (1.0f - saturation);
            float q = brightness * (1.0f - saturation * f);
            float t = brightness * (1.0f - (saturation * (1.0f - f)));
            switch ((int)h) {
                case 0:
                    r = (int)(brightness * 255.0f + 0.5f);
                    g = (int)(t * 255.0f + 0.5f);
                    b = (int)(p * 255.0f + 0.5f);
                    break;
                case 1:
                    r = (int)(q * 255.0f + 0.5f);
                    g = (int)(brightness * 255.0f + 0.5f);
                    b = (int)(p * 255.0f + 0.5f);
                    break;
                case 2:
                    r = (int)(p * 255.0f + 0.5f);
                    g = (int)(brightness * 255.0f + 0.5f);
                    b = (int)(t * 255.0f + 0.5f);
                    break;
                case 3:
                    r = (int)(p * 255.0f + 0.5f);
                    g = (int)(q * 255.0f + 0.5f);
                    b = (int)(brightness * 255.0f + 0.5f);
                    break;
                case 4:
                    r = (int)(t * 255.0f + 0.5f);
                    g = (int)(p * 255.0f + 0.5f);
                    b = (int)(brightness * 255.0f + 0.5f);
                    break;
                case 5:
                    r = (int)(brightness * 255.0f + 0.5f);
                    g = (int)(p * 255.0f + 0.5f);
                    b = (int)(q * 255.0f + 0.5f);
                    break;
            }
        }
        return (r << 24) | (g << 16) | (b << 8) | 0x000000ff;
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Gdx.app.log( "MOVED",  screenX + " " + screenY);
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
