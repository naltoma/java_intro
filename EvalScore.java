/* 100点満点採点した学生の成績をチェックし、A~F判定を返す関数。 */
package jp.ac.uryukyu.ie.tnal;

public class EvalScore {
    public static void main(String[] args) {
	int[] scores = {100, 70, 50};
	int i;
	char answer;
	for(i=0; i<scores.length; i++){
	    answer = eval(scores[i]);
	    System.out.println(scores[i] + " -> " + answer);
	}
    }

    public static char eval(int score) {
	char answer = 'F';
	if( score >= 90 ){
	    answer = 'A';
	}else if( score >= 80 ){
	    answer = 'B';
	}else if( score >= 70 ){
	    answer = 'C';
	}else if( score >= 60 ){
	    answer = 'D';
	}else{
	    answer = 'F';
	}
	return answer;
    }
}
