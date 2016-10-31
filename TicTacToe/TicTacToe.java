package jp.ac.uryukyu.ie.tnal;

/**
 * 3目並べ（oxゲーム）
 *
 * private char[][] board; ボードの状態。
 *   何置かれていない状態: 'e' (empty)
 *   oを置いた状態: 'o'
 *   xを置いた状態: 'x'
 *
 * private boolean turn; 手番管理用の変数。
 *   true: 'o'
 *   false: 'x'
 */
public class TicTacToe {
    private char[][] board;
    private boolean turn; //true: o, false: x

    /* コンストラクタ。初期化するだけ。 */
    public TicTacToe() {
	initialize();
    }

    /**
     * 初期化用メソッド。
     * 手番を'o'に設定し、ボードを初期化。
     */
    public void initialize() {
	int i, j;

	turn = true;
	board = new char[3][3];
	for(i=0; i<3; i++){
	    for(j=0; j<3; j++){
		board[i][j] = 'e';
	    }
	}
	System.out.println("# board was initialized.");
    }

    /**
     * 現在の手番と、ボードを出力する。
     */
    public void print() {
	int i, j;
	printTurn();
	for(i=0; i<3; i++){
	    for(j=0; j<3; j++){
		System.out.print(board[i][j]);
	    }
	    System.out.print("\n");
	}
	System.out.print("\n");
    }

    /**
     * 現在の手番を出力する。
     */
    public void printTurn() {
	if( turn == true ){
	    System.out.println("# Turn: o");
	}else{
	    System.out.println("# Turn: x");
	}
    }

    /**
     * ユーザ'o'がボードに手を置く際に使用するメソッド。
     */
    public void handCircle(int x, int y){
	if( turn == true && board[x][y] == 'e' ){
	    board[x][y] = 'o';
	    System.out.printf("# player 'o' pointed at [%d][%d]\n",x,y);
	    print();
	    turn = false;
	}else{
	    System.out.println("# Current turn: 'x'");
	    print();
	}
    }

    /**
     * ユーザ'x'がボードに手を置く際に使用するメソッド。
     */
    public void handCross(int x, int y){
	if( turn == false && board[x][y] == 'e' ){
	    board[x][y] = 'x';
	    System.out.printf("# player 'x' pointed at [%d][%d]\n",x,y);
	    print();
	    turn = true;
	}else{
	    System.out.println("# Current turn: 'o'");
	    print();
	}
    }
}

