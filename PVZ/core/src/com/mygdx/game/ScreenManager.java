package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class ScreenManager extends Game{

	public void create () {
		this.setScreen(new MainMenu(this));
	}

	public void render () {
		super.render();
	}
	

	public void dispose () {
	}
}
