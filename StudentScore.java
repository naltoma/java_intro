package jp.ac.uryukyu.ie.tnal;

public class StudentScore {
    String account; //フィールド変数1
    int score; //フィールド変数2
    char eval; //フィールド変数3

    /* コンストラクタ
     　コンストラクタは、
     　クラス（設計図）からインスタンス（オブジェクト）を作成するためのもの。
     　主にフィールド変数初期化のために使用
    */
    public StudentScore(String _account, int score, char eval){
	account = _account;
	this.score = score;
	this.eval = eval;
    }
}
