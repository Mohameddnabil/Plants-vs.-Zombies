package Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class Pea extends Actor{

    public static float distance=500f,speed=150f;
    Sprite PeaSprite;
    Rectangle bounds;
    public MoveToAction mta;
    Sound pea_sound;
    public Pea(float x,float y) {
        PeaSprite  = new Sprite(new Texture(Gdx.files.internal("Pea.png")));
        PeaSprite.setPosition(x, y);
        pea_sound= Gdx.audio.newSound(Gdx.files.internal("pea_sound.OGG"));
        pea_sound.play();
        setTouchable(Touchable.enabled);
        bounds= new Rectangle(PeaSprite.getX(),PeaSprite.getY(),PeaSprite.getWidth(),PeaSprite.getHeight());
        setBounds(PeaSprite.getX(),PeaSprite.getY(),PeaSprite.getWidth(),PeaSprite.getHeight());

        MoveToAction mta = new MoveToAction();

        mta.setPosition(distance,y);
        mta.setDuration((distance-x)/speed);
        Pea.this.addAction(mta);

    }

    public Rectangle getbounds() {
        return bounds;
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        PeaSprite.setPosition(getX(),getY());
        bounds.setX(getX());
        bounds.setY(getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        PeaSprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

}