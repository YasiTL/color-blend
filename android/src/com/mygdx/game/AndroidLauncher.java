package com.mygdx.game;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	private float screenHeight, screenWidth;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		screenHeight = getWindowManager().getDefaultDisplay().getHeight();
		screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		Log.i("ANDROID SCREEN", "Width: " + screenWidth + " " + "Height: "+ screenHeight);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		initialize(new MyGdxGame(), config);

		int color1 = Color.argb(1, 1, 0 , 0);
		int color2 = Color.argb(1, 0, 0 , 1);



	}
}
