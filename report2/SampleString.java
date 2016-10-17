/* Stringオブジェクトに対し、1文字ずつ操作するコード例 */

public class SampleString {
    public static void main(String[] args) {
	String data = "This is test";
	printString(data);
    }
    
    public static void printString(String data){
	int i;
	char c;
	for(i=0; i<data.length(); i++){
	    c = data.charAt(i);
	    System.out.println(i + "番目の文字は" + c + "でした！");
	}
    }
}
