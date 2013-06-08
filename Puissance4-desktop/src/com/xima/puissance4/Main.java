package com.xima.puissance4;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Puissance4";
		cfg.useGL20 = false;
		cfg.width = 800;
		cfg.height = 480;
		cfg.vSyncEnabled = false;
		
		new LwjglApplication(new Puissance4(), cfg);
	}
}
