package jp.ac.uryukyu.ie.tnal;

/**
 * Janken Player
 * The hand was made at random by makeHand().
 */
public class Player {
    public static final int STONE = 0;
    public static final int SCISSORS = 1;
    public static final int PAPER = 2;
    
    private String name;
    private int hand;
    private int winCount;
    
    public Player(String name){
	this.name = name;
    }

    public void makeHand(){
	double rnd = Math.random() * 3;
	if( rnd < 1 ){
	    hand = STONE;
	}else if( rnd < 2 ){
	    hand = SCISSORS;
	}else{
	    hand = PAPER;
	}
    }
    
    public void setHand(int hand){
	this.hand = hand;
    }

    public int getHand(){
	return hand;
    }

    public String getName(){
	return name;
    }

    public int getWinCount(){
	return winCount;
    }

    public void setWinCount(int num){
	winCount = num;
    }
}

