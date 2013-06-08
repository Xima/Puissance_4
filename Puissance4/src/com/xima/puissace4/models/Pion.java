package com.xima.puissace4.models;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.xima.puissance4.views.World;

public abstract class Pion {

	protected static float		SPEED = 5;
	
	protected World 			world;
	protected Vector2 			position;
	protected Vector2			velocity;
    protected static float 		size;
	protected Rectangle 		bounds;
	// TODO texture
	
	public Pion(World world, Vector2 position) {
		this.world = world;
		this.position = position;
		bounds = new Rectangle();
	}
	
	public void loop(float delta) {
		bounds.set(world.caseSize*position.x-1, world.caseSize*position.y-1, size+2, size+2);
	}
	
	public abstract void render();
	
	

}
