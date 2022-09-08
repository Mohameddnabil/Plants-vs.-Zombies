package Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

public class sunflower_sun extends Actor{
   Sprite sprite;
   public boolean istouched;
   float timer=20,cnt=0;
    String pathsun="Sun5.png",pathmoon="moon.png";
   public sunflower_sun(float x,float y) {
       String curpath=pathsun;
       if(lawn.dark)
           curpath=pathmoon;
   	sprite = new Sprite(new Texture(curpath));
   	sprite.setPosition(x, y);
   	
   	istouched=false;
   	setTouchable(Touchable.enabled);
   	setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
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
       	   Sun.score+=25 ;
           istouched=true;
           this.remove();
           }
           }
       if(cnt>=timer){
           cnt=0;
           this.remove();
       }
       cnt+=Gdx.graphics.getDeltaTime();
   }  
}
