package com.xima.puissance4.screens;

import com.badlogic.gdx.Screen;
import com.xima.puissance4.Puissance4;
import com.xima.puissance4.views.World;
import com.xima.puissance4.views.WorldRender;

public class GameScreen implements Screen {

    Puissance4 			game;
    public World 				world;
    WorldRender 		renderer;
    
    
	public GameScreen(Puissance4 game) {
        this.game = game;
        world = new World();
        //renderer = new WorldRender(world);
	}
	

	@Override
	public void show() {
		world.create();
		
	}
	
	@Override
	public void render(float delta) {
		world.loop(delta);
		world.render();
		
	}

	@Override
	public void resize(int width, int height) {
		world.resize(width, height);
	}

	@Override
	public void hide() {
		dispose();
		
	}

	@Override
	public void pause() {
		world.pause();
		
	}

	@Override
	public void resume() {
		world.resume();
	}
	
	@Override
	public void dispose() {
		world.dispose();
		
	}
}
