package Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Plant_Card extends Actor{
    public Sprite sprite ;
    public Boolean Clicked;
    public static int counter=-1;
    public static int card_hight=0;
    public final float cost;
   public Plant_Card(String path,float x, float y,float cost){
	    counter++;
	    card_hight+=46;
        sprite=new Sprite(new Texture(Gdx.files.internal(path)));
        sprite.setPosition(x, y);
        this.cost=cost;
        setBounds(sprite.getX(), sprite.getY(),
               sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
        Clicked=false;
   }
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
    public void act(float delta){
       super.act(delta);
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            if(Gdx.input.getX()>this.getX()&&Gdx.input.getX()<this.getWidth()+this.getX()&&Gdx.graphics.getHeight()-Gdx.input.getY()>this.getY()&&Gdx.graphics.getHeight()-Gdx.input.getY()<this.getY()+this.getHeight()) {
                Clicked= true ;
            }
        }
    }
}
