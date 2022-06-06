package com.mygdx.dsav.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.dsav.DSAVisualiser;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "DSAVisualiser";
		config.useGL30 = true;
		config.width = 1280;
		config.height = 720;
		config.resizable = false;
		new LwjglApplication(new DSAVisualiser(), config);
	}
}
