package Actors;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

public class Zombie extends Actor {
    protected float health=50;
    Sprite sprite;
    Rectangle bounds;
    double startrandom = Math.random()*(10f*55f);
    int RandPostion[] = {15,63,111,159,207};
    float speed=120f;
    public MoveToAction mta;
    private  final int FRAME_COLS, FRAME_ROWS;
    Animation<TextureRegion> walkAnimation;
    Texture walkSheet;
    SpriteBatch spriteBatch;
    float stateTime,frame,persec;
    TextureRegion currentFrame;
    float frameforani,secondforani;
    public Zombie(int health,float frame,float persec,String path,
                  int FRAME_COLS,int FRAME_ROWS){
        this.health=health;
        this.frame=frame;
        this.persec=persec;
        this.FRAME_COLS=FRAME_COLS;
        this.FRAME_ROWS=FRAME_ROWS;
        walkSheet=new Texture(Gdx.files.internal(path));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation<TextureRegion>(
                frame/persec, walkFrames);
        spriteBatch = new SpriteBatch();
        stateTime = 0f;
        sprite=new Sprite((TextureRegion) tmp[0][0]);
        Random r = new Random();
        int randomNumber=r.nextInt(RandPostion.length);
        sprite.setPosition(700,(float)RandPostion[randomNumber]);

        bounds= new Rectangle(sprite.getX(),sprite.getY(),sprite.getWidth(),36);
        setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),36);
        setTouchable(Touchable.enabled);

        MoveToAction mta = new MoveToAction();
        
        mta.setPosition(0f,(float)RandPostion[randomNumber]);
        mta.setDuration(this.getX()/speed);
        Zombie.this.addAction(mta);
    }
    public void Stop() {
    	this.clearActions();
    }
    public boolean TakeDamage() {
    	health-=50f;
    	if(health<=0){
    	   return true;
    	}
    	return false;
    }
    public void Move() {
        float xdist=this.getX();
    	float duration=xdist/speed;
        MoveToAction mta = new MoveToAction();
        
        mta.setPosition(0f,this.getY());
        mta.setDuration(duration);
        this.addAction(mta);
    }
    public Rectangle getbounds() {
    	return bounds;
    }
    @Override
    protected void positionChanged() {
        super.positionChanged();
        sprite.setPosition(getX(),getY());
        bounds.setX(getX());
        bounds.setY(getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        stateTime+=Gdx.graphics.getDeltaTime();
        currentFrame = walkAnimation.getKeyFrame(
                stateTime, true);
        sprite=new Sprite((TextureRegion) currentFrame);
        positionChanged();
        sprite.draw(batch);
    }
    @Override
    public void act(float delta) {
        super.act(delta);
    }}