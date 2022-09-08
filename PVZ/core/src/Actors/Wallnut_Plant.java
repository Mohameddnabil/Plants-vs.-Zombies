package Actors;

public class Wallnut_Plant extends Plants {

    public Wallnut_Plant(){
        super(300,50,"wallnutsheet.png",4,1,1f,5f);
    }
    @Override
    int damage() {
        return 0;
    }
}