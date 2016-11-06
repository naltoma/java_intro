import jp.ac.uryukyu.ie.tnal.Judge;
import jp.ac.uryukyu.ie.tnal.Player;
import jp.ac.uryukyu.ie.tnal.PoorPlayer; // new line

public class Main2 {
    public static void main(String[] args){
	Judge judge = new Judge();
	PoorPlayer taro = new PoorPlayer("Taro"); // modified
	Player hanako = new Player("Hanako");

	taro.makeHand();
	hanako.makeHand();
	judge.startJanken(taro, hanako);
	judge.printResult(taro, hanako);
    }
}

