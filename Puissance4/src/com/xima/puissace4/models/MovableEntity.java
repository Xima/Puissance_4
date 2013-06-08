package com.xima.puissace4.models;

import com.badlogic.gdx.math.Vector2;
import com.xima.puissance4.views.World;

public abstract class MovableEntity extends Entity {
	
	protected Vector2					velocity;

	public MovableEntity(World world, Vector2 position, Vector2 velocity) {
		super(world, position);
		this.velocity = velocity;
	}

	@Override
	public void loop(float delta) {
		position.ad

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

}
