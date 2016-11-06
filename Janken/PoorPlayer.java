package jp.ac.uryukyu.ie.tnal;
import jp.ac.uryukyu.ie.tnal.Player;

public class PoorPlayer extends Player {
    public PoorPlayer(String name){
	super(name);
    }

    public void makeHand(){
	setHand(1); // always
    }
}
