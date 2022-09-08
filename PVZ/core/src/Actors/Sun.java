package Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.actions.*;

public class Sun extends Actor{
    Sprite sprite;
    boolean istouched;
    float timer=20,cnt=0;
    public static int score=1000;
    double posx= Math.random()*(350f-80f+1f)+70f,RandomFall=Math.random()*(200f-30f+1f)+30f;
    String pathsun="Sun5.png",pathmoon="moon.png";
    public Sun() {
        String curpath=pathsun;
        if(lawn.dark)
            curpath=pathmoon;
    	sprite = new Sprite(new Texture(curpath));
    	sprite.setPosition((float)posx, Gdx.graphics.getHeight()-100f);

    	istouched=false;
    	setTouchable(Touchable.enabled);
    	setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    	
    	MoveByAction mba= new MoveByAction();
		mba.setAmount(0, (float)-RandomFall);
		mba.setDuration(4f);
		Sun.this.addAction(mba);
}
    
	@Override
	protected void positionChanged() {
		sprite.setPosition(this.getX(), this.getY());
		super.positionChanged();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
	@Override
    public void act(float delta) {
        super.act(delta);
        if (Gdx.input.isButtonPressed(Input.Keys.LEFT)){
            if(Gdx.input.getX()>this.getX()&&Gdx.input.getX()<this.getWidth()+this.getX()&&Gdx.graphics.getHeight()-Gdx.input.getY()>this.getY()&&Gdx.graphics.getHeight()-Gdx.input.getY()<this.getY()+this.getHeight()) {
        	  score+=25;
            this.remove(); }
            }
        if(cnt>=timer){
            cnt=0;
            this.remove();
        }
        cnt+=Gdx.graphics.getDeltaTime();
    }
}