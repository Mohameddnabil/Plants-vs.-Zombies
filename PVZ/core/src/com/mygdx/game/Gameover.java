package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class Gameover implements Screen{
	private Stage stage;
    private Game game;
    BitmapFont Fontbitmap;
    private Sprite sprite;
    private SpriteBatch spritebatch;
    public Gameover(final Game game) {
    	this.game=game;
    	stage= new Stage();
    	Fontbitmap = new BitmapFont();
    	sprite = new Sprite(new Texture("game_over.png"));
    	spritebatch= new SpriteBatch();
    	sprite.setPosition(Gdx.graphics.getWidth()/2-sprite.getWidth()/2, 70f);
		TextButtonStyle textbutton= new TextButtonStyle();
		textbutton.font= Fontbitmap;
        TextButton ExitButton = new TextButton("Exit!",textbutton);
        ExitButton.setWidth(Gdx.graphics.getWidth()/2);
        ExitButton.setPosition(Gdx.graphics.getWidth()/2-ExitButton.getWidth()/2,Gdx.graphics.getHeight()/2-ExitButton.getHeight()/2-100f);
        ExitButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(ExitButton);
    }
	@Override
	public void show() {
        Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        
        spritebatch.begin();
    	spritebatch.draw(sprite,sprite.getX(),sprite.getY());
    	spritebatch.end();
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
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
        stage.dispose();
	}

}
