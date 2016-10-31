import jp.ac.uryukyu.ie.tnal.Player;

public class Main {
    public static void main(String[] args) {
	Player taro = new Player("Taro");
	Player hanako = new Player("Hanako");

	taro.makeHand();
	hanako.makeHand();
	System.out.println("taro's hand: " + taro.getHand());
	System.out.println("hanako's hand: " + hanako.getHand());
    }
}
