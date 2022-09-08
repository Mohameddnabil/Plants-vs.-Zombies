package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import Actors.*;

import javax.swing.*;
import java.awt.*;

public class MainGame implements Screen{
	Stage stage;
	lawn law;
	float delay=6,cnt=0;
    final Game gamescreen;
    public MainGame(final Game game) {
    	gamescreen= game;
		stage= new Stage(new ScreenViewport());
		law=new lawn();
		stage.addActor(law);
    }
	@Override
	public void show () {
		Gdx.input.setInputProcessor(stage);
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(law.lost==true) {
			gamescreen.setScreen(new Gameover(gamescreen));
		}
		stage.act(Gdx.graphics.getDeltaTime());
		cnt+=Gdx.graphics.getDeltaTime();
		if(cnt>=delay) {
			stage.addActor(new Sun());
			cnt=0;
		}
		stage.draw();
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose () {
		stage.dispose();
	}
}
