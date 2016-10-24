# Java入門（いろいろなデータ型、キャスト演算子、命名規則）

＜目次＞
- Java入門（いろいろなデータ型、キャスト演算子、命名規則）
  - <a href="#c3">いろいろなデータ型（3章）</a>
    - <a href="#c3-1">データ型の種類（pp.42-49</a>）
    - <a href="#c3-2">命名規則（pp.51-55）</a>
    - <a href="#c3-3">自動型変換（pp.61-）</a>
  - <a href="#c4">演算子と演算（4章）</a>
    - <a href="#c4-1">Java言語の演算子（pp.72）</a>
    - <a href="#c4-2">キャスト演算子（pp.79-）</a>
    - <a href="#c4-3">文字列の連結（pp.87-）</a>
  - <a href="#c5">基本ライブラリの利用（5章）</a>
    - <a href="#c5-1">Mathクラスのクラスメソッド（pp.104-）</a>
    - <a href="#c5-2">Inputクラスのメソッド（pp.111-）</a>
    - <a href="#c5-3">Stringクラスのメソッド（pp.122-）</a>
    - <a href="#c5-4">Integerクラスのメソッド（教科書に無し）</a>

<hr>

## <a name="c3">いろいろなデータ型（3章）</a>
### <a name="c3-1">データ型の種類（pp.42-49）</a>
- 基本データ型
  - 整数型: byte, short, int, long
    - 具体的な違いはp.47参照。
  - 浮動小数点型: float, double
  - 文字型: char
  - 論理型: boolean
- リテラル（p.43）
  - 具体的な値（e.g., int型の値5）
  - 整数リテラル
  - 浮動小数点リテラル
  - 文字リテラル
    - 1つの文字。シングルクォーテーションで囲う事。
    - ユニコード（Unicode）：世界標準の文字コード体系。
    - エスケープ文字（p.46）：特殊文字。**教科書の円マークはバックスラッシュ**。
      - 改行: \n
      - タブ文字: \t
  - 論理値リテラル
    - ``true``, ``false``
      - ``"true"``と``true``は異なる点に注意。
- 扱える値の範囲（pp.47-48）
  - 型ごとに範囲があることに注意。
  - int型が32bit（-2^31 〜 +2^31-1）
    - その半分16bitがshort。
    - その倍32bitがlong。
  - char型は16bit。

### <a name="c3-2">命名規則（pp.51-55）</a>
- 識別子: クラス名、メソッド名、変数名などのプログラマが自由につけて良い名前のこと。
  - 使える文字: 英数字, _, $
  - 数字から始まってはならない。
  - 大文字小文字は異なる文字として扱われる。
  - 予約語（p.53）は使えない。
  - 名前の長さに事実上制限はない（理論的にはint型整数の最大値と同じ）。
- 慣習上の命名規則
  - クラス名の先頭文字は大文字にする。
  - パッケージ名、変数名、メソッド名は小文字にする。
  - 2つ以上の単語を連結して名前を作る時は、2つ目以降の単語の先頭文字だけを大文字にする。（あるいは_で繋ぐ。）

### <a name="c3-3">自動型変換（pp.61-）</a>
- 変数に代入するリテラルは同じ型である必要があるが、厳密にしすぎるとプログラムが書きづらくなってしまう。
  - ``double x = 15;``
    - 右辺は整数型(int)リテラルだが、15.0に自動型変換されて代入される。
  - ``double y = x + 2``
    - 上記は、xが浮動小数点型ならば自動変換される。
    - 逆に言えば、「xが浮動小数点型でない」ならば、自動変換されない点に注意。
      - ``int a = 1;``
      - ``double b = a / 2;``
        - 上記のケースでは、aも2も整数型なので、割り算は整数型で行う。
        - 1を2で割った答えは0（整数型）。この値が浮動小数点型に自動型変換されて0.0が変数bに代入される。
        - 浮動小数点型で処理して欲しい時にはキャストしよう。
- 自動型変換できないケース
  - ``int n = 15.432;``
    - システム側では「切り捨ててよいのか四捨五入すべきか」どうか判断できない。->コンパイルエラー（文法エラー）

<hr>

## <a name="c4">演算子と演算（4章）</a>
### <a name="c4-1">Java言語の演算子（pp.72）</a>
- よく使うものは覚えておこう
  - メソッド呼び出し
  - 配列要素の参照
  - オブジェクトのメンバ参照
  - インクリメント、デクリメント
    - i++ と ++i は動作が異なる。（pp.74-75）
  - 論理否定
  - オブジェクトの生成
  - 四則演算
  - 関係演算子
  - 論理演算子
- 優先順位も覚えておくべきだが、それ以上に「誤読されにくいコードを書く」方が良い。
  - 優先順位は()で変更（丸括弧付きを優先的に処理させる）できる。

### <a name="c4-2">キャスト演算子（pp.79-）</a>
- プログラマの責任で強制的に型変換する操作のこと。
  - ``int number = (int)10.5;`` // 小数点以下を切り捨ててint型に変換。
  - ``float x = (float)1.234;`` // 小数点型はデフォルトでdoubleになるが、float精度(1.234f)で代入される。
- char型とint型の間での型変換（pp.81-）
  - char型は厳密にはユニコードであるため、int型との型変換が可能。

### <a name="c4-3">文字列の連結（pp.87-）</a>
- 例1
```
String s = "こんにちは" + "太郎"+ "さん";
```
- 例2
```
String name = "太郎";
System.out.println("こんにちは" + name + "さん");
```
- Stringではない型の変数を連結する（pp.89-）

<hr>

## <a name="c5">基本ライブラリの利用（5章）</a>
- APIドキュメント: [Java™ Platform, Standard Edition 8 API Specification](http://docs.oracle.com/javase/8/docs/api/)

### <a name="c5-1">Mathクラスのクラスメソッド（pp.104-）</a>
- 平方根の計算
```
double x = Math.sqrt(2.0);
System.out.println("2.0の平方根=" + x);
```
  - Math.sqrt(): Mathクラスのsqrtメソッド
- MathクラスのAPI（pp.107-）
  - 絶対値、三角関数（ラジアン単位、角度単位）、自然対数、べき乗、max/min、0.0以上~1.0未満乱数、平方根、四捨五入、、、

### <a name="c5-2">Inputクラスのメソッド（pp.111-）</a>
- 教科書にあるのは標準ライブラリではない。
  - 著者が独自に用意したライブラリ。興味のある人は使ってみよう。
- [レポート2](https://github.com/naltoma/java_intro/blob/master/report2/report2.md)で示したSampleScanner.java（下記）は、標準ライブラリを使用した例。

```
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
```

### <a name="c5-3">Stringクラスのメソッド（pp.122-）</a>
- 文字列の長さ
  - String.length()メソッド
- Stringクラスの代表的なAPI
  - boolean equals(String str): 文字列が、別の文字列strを等しい時trueを返す
  - char charAt(int i): 先頭からi番目の文字(char)を返す。
  - int length(): 文字列の長さ（文字数）を返す。
  - String toLowerCase(): 英字を小文字にした文字列を返す。
  - String toUpperCase(): 英字を大文字にした文字列を返す。
  - String trim(): 先頭と末尾の空白を削除した文字列を返す。
  - String[] split(String regex): 与えられた[正規表現regex](http://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html#sum)に基づき、文字列を分解した配列を返す。

### <a name="c5-4">Integerクラスのメソッド（教科書に無し）</a>
- APIドキュメントからIntegerクラスを探してみよう。
- Integerクラスのフィールド変数
  - MAX_VALUE
  - MIN_VALUE
- Integerクラスの代表的なAPI
  - int max(int a, int b)
  - int min(int a, int b)
  - int parseInt(String s): 文字列sをint(符号付き10進数整数)型に解析し直す（パースする）。
    - 浮動小数点型、ブーリアン型へ変換するには下記を使おう。
      - ``Float.parseFloat(String s)``
      - ``Double.parseDouble(String s)``
      - ``Boolean.parseBoolean(String s)``
  - String toString(): Stringに変換。
