package Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
public abstract class Plants extends Actor {
    protected final int cost;
    protected float health;
    Rectangle bounds;
    private  final int FRAME_COLS , FRAME_ROWS ;
    Animation<TextureRegion> walkAnimation;
    Texture walkSheet;
    SpriteBatch spriteBatch;
    float stateTime;
    TextureRegion currentFrame;
    public int cellx,celly;
    Sprite sprite;
    float frameforani,secondforani;
    Plants(int health,int cost,String path,
            int FRAME_COLS,int FRAME_ROWS,float frameforani,float secondforani  ){
        this.frameforani=frameforani;
        this.secondforani=secondforani;
        this.health=health;
        this.cost=cost;
        this.FRAME_COLS=FRAME_COLS;
        stateTime=0.0f;
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
                frameforani/secondforani, walkFrames);
        spriteBatch = new SpriteBatch();
        stateTime = 0f;
        sprite=new Sprite((TextureRegion) tmp[0][0]);
        setBounds(sprite.getX(), sprite.getY(),
                sprite.getWidth(), sprite.getHeight());
        bounds= new Rectangle((int)this.getX(),(int)this.getY(),(int)this.getWidth(),(int)this.getHeight());
    }
    public float getHealth() {
        return health;
    }
    public void setHealth(float hp) {
    	health=hp;
    }
    public int getCost() {
        return cost;
    }
    public Rectangle getbounds() {
    	return bounds;
    }
    public boolean IsEaten() {
    	health-=0.5f;
    	if(health<=0){
    	   return true;
    	}
    	return false;
    }
	@Override
	protected void positionChanged() {
		sprite.setPosition(this.getX(), this.getY());
		bounds.setX(this.getX());
		bounds.setY(this.getY());
		super.positionChanged();
	}
    public void draw(Batch batch, float parentAlpha) {
        stateTime+=Gdx.graphics.getDeltaTime();
         currentFrame = walkAnimation.getKeyFrame(
                stateTime, true);
        sprite=new Sprite((TextureRegion) currentFrame);
        positionChanged();
        sprite.draw(batch);
    }
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
    @Override
	public void act(float delta) {
        super.act(delta);
	}
    abstract int damage();
    public void dispose () {
        spriteBatch.dispose();
        walkSheet.dispose();
    }
}