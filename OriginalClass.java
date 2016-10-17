/* 「アカウント名、点数、判定結果」を1まとめにして扱う例。 */
package jp.ac.uryukyu.ie.tnal;

public class OriginalClass {
    public static void main(String[] args) {
	String account;
	int score;
	char eval;
	StudentScore[] scores = new StudentScore[3];
	scores[0] = new StudentScore("e175701", 100, 'A');
	scores[1] = new StudentScore("e175702", 70, 'C');
	scores[2] = new StudentScore("e175703", 50, 'F');

	int i;
	for(i=0; i<scores.length; i++){
	    account = scores[i].account;
	    score = scores[i].score;
	    eval = scores[i].eval;
	    System.out.println(account + "さんは" + score + "点だったので、判定結果は" + eval + "です！");
	}
    }
}
