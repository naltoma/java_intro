import java.util.Scanner;

public class SampleScanner {
    public static void main(String[] args) {
	String input; //読み込んだユーザ入力を格納するStringオブジェクトを用意。
	Scanner in = new Scanner(System.in); // 標準入力から読み込むスキャナを用意
	int num;

	System.out.println("Please input some string: "); // 入力を促す説明文を出力。
	input = in.nextLine(); // inputにユーザ入力を保存する。
	num = input.length(); // 読み込んだ文字数を取得。

	// 正常に読み込めたか出力して確認する。
	System.out.println("Your input is = " + input + ", the length is " + num);
    }
}
