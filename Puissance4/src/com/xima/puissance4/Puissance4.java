package com.xima.puissance4;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.xima.puissance4.screens.GameScreen;

public class Puissance4 extends Game {

	public static final String 		VERSION 	= 	"0.0.1 Pre-Alpha";
    public static final String 		LOG 		= 	"Puissance 4";
    public static final boolean 	DEBUG 		= 	true;

    FPSLogger 		log; 
    SpriteBatch 	batch;
    BitmapFont 		font;
    ShapeRenderer	shapeRender;
    Rectangle 		glViewport;
    GameScreen		gameScreen;
    
    @Override
    public void create() {
            log = new FPSLogger();
            batch = new SpriteBatch();
            gameScreen = new GameScreen(this);
            setScreen(gameScreen);
            font = new BitmapFont();
            shapeRender = new ShapeRenderer();
           
    }

    @Override
    public void dispose() {
            super.dispose();
            font.dispose();
            batch.dispose();
            shapeRender.dispose();
    }

    @Override
    public void render() {         
            super.render();
            if (Puissance4.DEBUG) {
	           // log.log();
	            batch.begin();
	            font.setColor(1, 1, 1, 1);
	            font.draw(batch, Gdx.graphics.getFramesPerSecond() + " fps", 10, 20);
	            batch.end();
	            shapeRender.begin(ShapeType.Line);
	            shapeRender.setColor(1, 1, 0, .2f);
	            shapeRender.line(Gdx.graphics.getWidth()/2, 0, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
	            shapeRender.line(0, Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/2);
	            shapeRender.end();
            }
    }

    @Override
    public void resize(int width, int height) {
    	super.resize(width, height);
    }

    @Override
    public void pause() {
            super.pause();
    }

    @Override
    public void resume() {
            super.resume();
          //  world.resume();
    }
	
}