/* 複数のint型要素を用意して、中身が偶数だった時は数値を出力し、奇数だった時は「奇数」と出力するプログラム。 */
package jp.ac.uryukyu.ie.tnal;

public class CheckOdd {
    public static void main(String[] args) {
	int[] data = {1, 2, 3};
	/*
	int i;
	for(i=0; i<data.length; i++){
	    if( data[i] % 2 == 0 ){
		System.out.println(data[i]);
	    }else{
		System.out.println("奇数");
	    }
	}
	*/
	for(int i : data){
	    if( i % 2 == 0 ){
		System.out.println(i);
	    }else{
		System.out.println("奇数");
	    }
	}
    }
}
