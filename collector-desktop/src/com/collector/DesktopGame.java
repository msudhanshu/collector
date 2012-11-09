package com.collector;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.collector.GameMain ;

public class DesktopGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
     new LwjglApplication(new GameMain(), "Collector Game",Config.SCREEN_HEIGHT,Config.SCREEN_WIDTH, true);

	}

}
