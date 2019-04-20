package com.relmaps.magalaxy.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.relmaps.magalaxy.InitGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1024;
		config.height = 640;
		config.foregroundFPS = 60;
		new LwjglApplication(new InitGame(), config);
	}
}
