package com.xima.puissance4;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.xima.puissance4.views.World;

public class MyGestureListener implements GestureListener {
	
	public static final int N = 0;
	public static final int E = 1;
	public static final int S = 2;
	public static final int O = 3;
	
	World world;
	
	public MyGestureListener(World world) {
		this.world = world;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		Vector3 vec = new Vector3(x, y, 0);
		vec = world.unproject(vec);
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		int[] value = new int[1];
		if (!world.isOnTouchArea(x, y, value))
			world.drag(-deltaX, deltaY);
		else world.drawLine(x, y);
		return true;
	}

   @Override
   public boolean zoom (float originalDistance, float currentDistance) {
	   world.zoom((double)0.001*(originalDistance-currentDistance));
      return true;
   }

   @Override
   public boolean pinch (Vector2 initialFirstPointer, Vector2 initialSecondPointer, Vector2 firstPointer, Vector2 secondPointer) {
      return false;
   }

	
	}