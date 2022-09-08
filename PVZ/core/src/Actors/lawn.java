package Actors;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class lawn extends Actor{
	// Sounds
	Sound background,plant_sound,shovel_sound;
	Sound lawnmower_sound,eating_sound,bleep_sound;
	Sound losemusic_sound,winmusic_sound;
	public boolean eating=false,lost=false,won=false;

	//modes
    DarkMode darkMode;

    // Sprites
	Sprite lawnsprite,Sunsprite;

	// plane coordinates
	float gardenX[][];
	float gardenY[][];
	Plants plant;
	float elapsed,b =15f;
	public static boolean dark=false;
	float WinDelay=0;
	int count = 0;
	boolean planted_already[][],check=false;
	public Plant_Card Cards[];

	// Plane containers
	ArrayList<Plants> plants;
	ArrayList<Zombie> zombies;
	ArrayList<Lowanmover> lowanmovers;
	ArrayList<Peashooter_plant> Peashooters;

	// boundaries
	float Xstart,Ystart,CellWidth=40,CellHeight=48;
	private String ScoreName;
	BitmapFont Fontbitmap;
    void init_plane(float Xcnt,float Ycnt){
		for(int i=0;i<5;i++) {
			for(int j=0;j<9;j++) {
				gardenX[i][j]=Xcnt;
				Xcnt+=CellWidth;
				gardenY[i][j]=Ycnt;
				planted_already[i][j]=false;
			}
			Ycnt+=CellHeight;
			Xcnt=Xstart;
		}
	}
	void draw_charters(Batch batch, float parentAlpha){
		for(int i=0;i<=Plant_Card.counter;i++) {
			Cards[i].draw(batch, parentAlpha);
		}
		for(int i=0;i<lowanmovers.size();i++) {
			lowanmovers.get(i).draw(batch, parentAlpha);
		}
		for(int i=0;check&&i<plants.size();i++) {
			plants.get(i).draw(batch, parentAlpha);
		}
		for(int i=0;zombies.size()>0&&i<zombies.size();i++) {
			zombies.get(i).draw(batch, parentAlpha);
		}
	}
	public lawn() {
		darkMode=new DarkMode();
		lawnsprite= new Sprite(new Texture("Lawn3.jpg"));
		Sunsprite= new Sprite(new Texture("Sun5.png"));
		// Lawn Actors
		plants= new ArrayList<Plants>();
		zombies=new ArrayList<Zombie>();
		lowanmovers= new ArrayList<Lowanmover>();


		for(int i=0;i<5;i++)
			lowanmovers.add(new Lowanmover());
		lawnsprite.setPosition(0,0);
		Sunsprite.setPosition(7,240);
		Sunsprite.setScale(1.3f);
		gardenX=new float[5][9];
		gardenY=new float[5][9];
		Cards= new Plant_Card[4];
		planted_already=new boolean[5][9];

		// Plants cards
		Cards[0]=new Plant_Card("Peashooter_Card.png",0,Plant_Card.card_hight,100);
		Cards[1]=new Plant_Card("sunflowerCard.png",0,Plant_Card.card_hight,50);
		Cards[2]=new Plant_Card("wallnutCard.png",0,Plant_Card.card_hight,50);
		Cards[3]=new Plant_Card("Shovel.png",50,230,0);

		// Garden initialization  width and height.
		Xstart=117; Ystart=10;
		float Xcnt=Xstart,Ycnt=Ystart;
		init_plane(Xcnt,Ycnt);

		this.setBounds(lawnsprite.getX(), lawnsprite.getY(),
				lawnsprite.getWidth(), lawnsprite.getHeight());
		ScoreName="score: ";
		Fontbitmap = new BitmapFont();

	// Sounds
		background= Gdx.audio.newSound(Gdx.files.internal("background_sound.wav"));
		background.loop(0.08f, 1, 0);
		shovel_sound= Gdx.audio.newSound(Gdx.files.internal("shovel_sound.OGG"));
		plant_sound= Gdx.audio.newSound(Gdx.files.internal("plant_sound.OGG"));
		lawnmower_sound= Gdx.audio.newSound(Gdx.files.internal("lawnmower_sound.OGG"));
		eating_sound= Gdx.audio.newSound(Gdx.files.internal("eating_sound.OGG"));
		bleep_sound= Gdx.audio.newSound(Gdx.files.internal("bleep_sound.OGG"));
		losemusic_sound= Gdx.audio.newSound(Gdx.files.internal("losemusic_sound.OGG"));
		winmusic_sound= Gdx.audio.newSound(Gdx.files.internal("win_sound.OGG"));
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
    	// draw mode
		if(dark)
			lawnsprite= (new Sprite(new Texture("nightmode.jpg")));
		else
			lawnsprite= (new Sprite(new Texture("Lawn3.jpg")));

		lawnsprite.draw(batch);
		Sunsprite.draw(batch);
		darkMode.draw(batch,parentAlpha);

		// draw chartres
		draw_charters(batch,parentAlpha);

		// score font
		Fontbitmap.setColor(256.0f,1.0f, 1.0f, 1.0f);
		ScoreName="score: "+String.valueOf(Sun.score);
		Fontbitmap.draw(batch, ScoreName, 50, 270);

    }
	public boolean Inside_Garden() {
		float mouseX=Gdx.input.getX();
		float mouseY=Gdx.graphics.getHeight()-Gdx.input.getY();
		if(mouseX>117f&&mouseX<472&&mouseY>10&&mouseY<250)
			return true;
		else
			return false;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		darkMode.act(delta);
		if(zombies.size()==0&&count>=20&&won==false) {
			won=true;
			background.stop();
			winmusic_sound.play(0.5f);
		}
		for(int i=0;i<3;i++) {
			if(Sun.score<Cards[i].cost)
				Cards[i].Clicked=false;
		}
		for(int i=0;i<=Plant_Card.counter;i++)
			Cards[i].act(delta);
		for(int i=0;i<lowanmovers.size();i++) {
			lowanmovers.get(i).act(delta);
		}
		for(int i=0;zombies.size()>0&&i<zombies.size();i++) {
			zombies.get(i).act(delta);
		}
		elapsed+=Gdx.graphics.getDeltaTime();
		for(int i=0;i<plants.size();i++) {
			if(plants.get(i) instanceof Peashooter_plant) {
				Peashooter_plant peashooter=(Peashooter_plant)plants.get(i);
				for(int j=0;j<peashooter.Peas.size();j++) {
					for(int k=0;k<zombies.size();k++) {
						if(peashooter.Peas.get(j).getbounds().overlaps(zombies.get(k).getbounds())||peashooter.Peas.get(j).getbounds().contains(zombies.get(k).getbounds())) {
							peashooter.RemovePea(j);
							bleep_sound.play();
							if(zombies.get(k).TakeDamage()) {
								eating_sound.stop();
								zombies.get(k).remove();
								zombies.remove(k);
							}
							break;
						}
					}
				}
			}
		}
		for(int i=0;check&&i<plants.size();i++) {
			plants.get(i).act(delta);
		}
		start:{
			for(int i=0;i<plants.size();i++) {
				for(int j=0;j<zombies.size();j++) {
					if(plants.get(i).getbounds().overlaps(zombies.get(j).getbounds())||plants.get(i).getbounds().contains(zombies.get(j).getbounds())) {
						zombies.get(j).Stop();
						if(!eating){
							eating_sound.loop(0.4f, 1, 0);
							eating=true;
						}
						if(plants.get(i).IsEaten()) {
							eating=false;
							eating_sound.stop();
							planted_already[plants.get(i).cellx][plants.get(i).celly]=false;
							plants.get(i).remove();
							plants.remove(i);
							for(int k=0;k<zombies.size();k++) {
								if(zombies.get(k).getY()==zombies.get(j).getY()) {
									zombies.get(k).Move();
								}
							}
							break start;
						}
					}
				}
			}
		}

		for(int i=0;i<lowanmovers.size();i++) {
			for(int j=0;j<zombies.size();j++) {
				if(lowanmovers.get(i).getbounds().overlaps(zombies.get(j).getbounds())||lowanmovers.get(i).getbounds().contains(zombies.get(j).getbounds())) {
					lawnmower_sound.play(0.1f);
					lowanmovers.get(i).isHit(true);
					zombies.get(j).remove();
					zombies.remove(j);
				}
			}
		}
		for (Zombie  cur:zombies){
			if(cur.getX()<=100f){
				lost=true;
				losemusic_sound.play(0.05f);
			}
		}
		for(int i=0;i<lowanmovers.size();i++) {
			if(lowanmovers.get(i).getX()==700f) {
				lowanmovers.get(i).remove();
				lowanmovers.remove(i);
			}
		}

		if(elapsed >=b&& count <20){
			if (count <10 )
				zombies.add(new zombie1());

			else
				zombies.add(new zombie2());

			elapsed = 0;
			count++;
			b-=0.5;

		}
		if(Cards[3].Clicked){
			if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)&&Inside_Garden()) {
				int ClickedY=(int) ((Gdx.input.getX()-Xstart)/CellWidth);
				int ClickedX=(int) ((Gdx.graphics.getHeight()-Gdx.input.getY()-Ystart)/CellHeight);
				if(planted_already[ClickedX][ClickedY]) {
					for(int i=0;i<plants.size();i++){
						if(plants.get(i).cellx==ClickedX&&plants.get(i).celly==ClickedY) {
							shovel_sound.play();
							plants.get(i).remove();
							plants.remove(i);
							planted_already[ClickedX][ClickedY]=false;
							Cards[3].Clicked=false;
						}
					}
				}
			}
		}
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)&&Inside_Garden()) {
			int ClickedY=(int) ((Gdx.input.getX()-Xstart)/CellWidth);
			int ClickedX=(int) ((Gdx.graphics.getHeight()-Gdx.input.getY()-Ystart)/CellHeight);
			if(planted_already[ClickedX][ClickedY])
				  return;
			if(Cards[0].Clicked){
				plant=new Peashooter_plant();
				Sun.score-=Cards[0].cost;
			}
			else if(Cards[1].Clicked){
				plant=new Sunflower_Plant();
				Sun.score-=Cards[1].cost;
			}
			else if(Cards[2].Clicked){
				plant=new Wallnut_Plant();
				Sun.score-=Cards[2].cost;
			}
			else
				return;
			if(!planted_already[ClickedX][ClickedY]) {
				plant_sound.play(0.2f);
				plant.setPosition(gardenX[ClickedX][ClickedY], gardenY[ClickedX][ClickedY]+5f);
				plant.cellx=ClickedX;
				plant.celly=ClickedY;
				plants.add(plant);
				check=true;
				planted_already[ClickedX][ClickedY]=true;
				for(int i=0;i<=Plant_Card.counter;i++) {
					Cards[i].Clicked=false;
				}
			}
		}
	}
}