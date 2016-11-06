import jp.ac.uryukyu.ie.tnal.Judge;
import jp.ac.uryukyu.ie.tnal.Player;

public class Main {
    public static void main(String[] args){
	Judge judge = new Judge();
	Player taro = new Player("Taro");
	Player hanako = new Player("Hanako");

	taro.makeHand();
	hanako.makeHand();
	judge.startJanken(taro, hanako);
	judge.printResult(taro, hanako);
    }
}

