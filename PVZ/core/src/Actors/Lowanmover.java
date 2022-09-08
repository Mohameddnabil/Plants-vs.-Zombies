package Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class Lowanmover extends Actor {

    Sprite lownmover;
    int CarPostion[] = {10,58,106,154,202};
    float distance=700f,speed=70f;
    static int i=0;
    Rectangle bounds;

    public Lowanmover(){
        lownmover  = new Sprite(new Texture(Gdx.files.internal("Lawnmower2.png")));
        lownmover.setPosition(75f,CarPostion[i]);
        
        bounds= new Rectangle(lownmover.getX(),lownmover.getY(),lownmover.getWidth(),lownmover.getHeight());
        setBounds(lownmover.getX(),lownmover.getY(),lownmover.getWidth(),lownmover.getHeight());
        setTouchable(Touchable.enabled);

      // Lowanmover.this.addAction(Actions.moveTo(500f, (float)RandPostion[i], 5));

        i++;
        if(i==5)
        	i=0;

    }
    public Rectangle getbounds() {
    	return bounds;
    }
    public void isHit(boolean hit) {
    	if(hit==true) {
            MoveToAction mta = new MoveToAction();
            
            mta.setPosition(distance,this.getY());
            mta.setDuration((distance-this.getX())/speed);
            this.addAction(mta);
    	}
    }
    protected void positionChanged() {
        lownmover.setPosition(getX(),getY());
		bounds.setX(this.getX());
		bounds.setY(this.getY());
        super.positionChanged();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        lownmover.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
    
}
