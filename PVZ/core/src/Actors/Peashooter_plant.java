package Actors;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;

public class Peashooter_plant extends Plants{
    public ArrayList<Pea> Peas;
    float delay;
    Pea pea;
    Peashooter_plant(){
        super(100,100,"peashooterani.png",
                4,3,1f,5f);
        delay=4f;
        Peas=new ArrayList<Pea>();
    }
    int damage(){
        return 0;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for(int i=0;Peas.size()>0&&i<Peas.size();i++){
            Peas.get(i).draw(batch, parentAlpha);
        }
    }
    public void RemovePea(int indx) {
    	Peas.get(indx).remove();
    	Peas.remove(indx);
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        delay-=delta;
        for(int i=0;Peas.size()>0&&i<Peas.size();i++) {
            Peas.get(i).act(delta);
        }
        if(delay<=0) {
            pea=new Pea(this.getX()+40f,this.getY()+20);
            Peas.add(pea);
            delay=4f;
        }
        for(int i=0;i<Peas.size();i++) {
            if(Peas.get(i).getX()==Pea.distance) {
                Peas.get(i).remove();
                Peas.remove(i);
            }
        }
    }
}