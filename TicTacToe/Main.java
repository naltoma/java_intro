package jp.ac.uryukyu.ie.tnal;

public class Main {
    public static void main(String[] argc) {
	TicTacToe ttt = new TicTacToe();
	ttt.print();
	ttt.handCircle(0, 1); // 'o' がboard[0][1]に置こうとした。
	ttt.handCircle(0, 2); // 'o' が続けてboard[0][2]に置こうとした。
    }
}
