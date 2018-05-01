package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by mostafay on 4/30/18.
 */

public class ColorBlendGdxGame extends ApplicationAdapter {
    private Stage stage;
    private OrthographicCamera camera;
    private Vector3 spriteposition;

    public void create() {
        Gdx.app.log("START", "STARTED");

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        //stage = new Stage(new ScreenViewport());
        stage = new Stage(new ExtendViewport(800, 800 * h/w));
        Gdx.input.setInputProcessor(stage);
        RectangleActor rectangleActor = new RectangleActor();
        rectangleActor.setColor(Color.CYAN);
        stage.addActor(rectangleActor);
    }

    public void resize(int width, int height) {
        // See below for what true means.
        stage.getViewport().update(width, height, true);
    }

    public void render() {
        Gdx.gl.glClearColor(0.976f, 0.968f, 0.886f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }
}
