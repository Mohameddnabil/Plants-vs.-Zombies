package Actors;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public class Sunflower_Plant extends Plants{
    float delay;
	ArrayList<sunflower_sun> flower_suns;
	sunflower_sun flower_sun;
    public Sunflower_Plant(){
        super(50,25,"sunflowersheet.png",7
				,7,1f,15f);
        flower_suns=new ArrayList<sunflower_sun>();
        delay=20;

    }
    @Override
    int damage() {
        return 0;
    }
	@Override
	public void act(float delta) {
		super.act(delta);
		delay-=delta;
		for(int i=0;flower_suns.size()>0&&i<flower_suns.size();i++) {
			flower_suns.get(i).act(delta);
			if(flower_suns.get(i).istouched==true) {
				flower_suns.get(i).istouched=false;
				flower_suns.get(i).remove();
				flower_suns.remove(i);
			}
		}
		if(delay<=0) {
			flower_sun=new sunflower_sun(this.getX()+20f,this.getY());
			flower_suns.add(flower_sun);
			delay=20;
		}
	}
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		for(int i=0;flower_suns.size()>0&&i<flower_suns.size();i++) {
			flower_suns.get(i).draw(batch, parentAlpha);
		}
	}
}