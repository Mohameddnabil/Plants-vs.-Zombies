package Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class DarkMode extends Actor {
    Sprite sprite;
    public DarkMode(){
        sprite=new Sprite(new Texture("moon.png"));
        sprite.setPosition(600,240);
        setTouchable(Touchable.enabled);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }
    public void draw(Batch batch, float parentAlpha){
        sprite.draw(batch);
    }
    public void act(float delta) {
        super.act(delta);
        if (Gdx.input.isButtonPressed(Input.Keys.LEFT)){
            if(Gdx.input.getX()>this.getX()&&Gdx.input.getX()<this.getWidth()+this.getX()&&Gdx.graphics.getHeight()-Gdx.input.getY()>this.getY()&&Gdx.graphics.getHeight()-Gdx.input.getY()<this.getY()+this.getHeight()) {
            lawn.dark^=true;
            }
        }
    }
}