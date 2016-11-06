package jp.ac.uryukyu.ie.tnal;

/**
 * This class requires two Player objects to Judge on Janken game.
 */
public class Judge {
    public void startJanken(Player p1, Player p2){
	System.out.println("ジャンケン始め！");
	Player winner = judgeJanken(p1, p2);
    }

    /**
     * step1: get both palyer's hand.
     * step2: judge winner.
     * step3: if winner, increment of winner.winCount.
     */
    public Player judgeJanken(Player p1, Player p2){
	Player winner = null;
	int p1hand = p1.getHand();
	int p2hand = p2.getHand();
	System.out.println(p1hand + " vs. " + p2hand);
	
	if( (p1hand == Player.STONE && p2hand == Player.SCISSORS) || 
	    (p1hand == Player.SCISSORS && p2hand == Player.PAPER) || 
	    (p1hand == Player.PAPER && p2hand == Player.STONE) ){
	    winner = p1;
	}else if( (p1hand == Player.STONE && p2hand == Player.PAPER) || 
	    (p1hand == Player.SCISSORS && p2hand == Player.STONE) || 
	    (p1hand == Player.PAPER && p2hand == Player.SCISSORS) ){
	    winner = p2;
	}else{
	    winner = null; // draw
	}

	if( winner != null ){
	    winner.setWinCount(winner.getWinCount() + 1);
	}
	return winner;
    }

    /**
     * print the result under their winCount().
     */
    public void printResult(Player p1, Player p2){
	if( p1.getWinCount() > p2.getWinCount() ){
	    System.out.printf("%s Wins!\n",p1.getName());
	}else if( p1.getWinCount() < p2.getWinCount() ){
	    System.out.printf("%s Wins!\n",p2.getName());
	}else{
	    System.out.println("draw game.");
	}
    }
}

