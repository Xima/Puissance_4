package com.xima.puissance4.views;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRender {

	World 					world;
	OrthographicCamera		cam;
	SpriteBatch 			batch;
	
	public WorldRender(World world) {
		this.world = world;
		
	}
	
	public void render() {
    	
	}
	
	public void dispose() {
		batch.dispose();
	}

}
