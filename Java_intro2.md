# Java入門2（いろいろなデータ型、キャスト演算子、命名規則、基本ライブラリ、演算子、while文、ジャンプによる制御）

＜目次＞
- Java入門2（いろいろなデータ型、キャスト演算子、命名規則）
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
  - <a href="#c8">条件を書くための演算子（8章）</a>
    - <a href="#c8-1">文字列の比較（pp.178-）</a>
    - <a href="#c8-2">論理演算子（pp.181-）</a>
    - <a href="#c8-3">条件演算子（pp.188-）</a>
    - <a href="#c8-4">演算子一覧表（p.191）</a>
  - <a href="#c9">while文（9章）</a>
    - <a href="#c9-1">while文の例（pp.196-）</a>
    - <a href="#c9-2">do-while文（pp.205-）</a>
  - <a href="#c11">ジャンプによる制御（11章）</a>
    - <a href="#c11-1">switch文（pp.244-）</a>
    - <a href="#c11-2">switch文の規則（pp.249-）</a>

<hr>

## <a name="c3">いろいろなデータ型（3章）</a>
### <a name="c3-1">データ型の種類（pp.42-49）</a>
- 基本データ型
  - 整数型: byte, short, int, long
    - 具体的な違いはp.47参照。
  - 浮動小数点型: float, double
  - 文字型: char
    - char型（=1文字）の場合は ``char c = 'a';`` のように、**シングルクォート** で囲うこと。
  - (文字列型: String)
    - String型の場合は ``String str = "hoge";`` のように、**ダブルクォート** で囲うこと。
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
- 演習例
  - (演習1-1) "\n"を使って改行を含む文字列を出力せよ。文字列は自由に設定して構わない。
  - (演習1-2) trueとfalseの論理和を求め、その結果をboolean型変数として保存し、その変数を標準出力に出力せよ。なお、論理和は ``リテラル || リテラル`` という書式で求めることができる。

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
        - 浮動小数点型で処理して欲しい時には、演算対象を腐造小数点型と明示しよう。
          - 明示方法1: 数値を直接コードに書いてるなら、 ``2.`` や ``2.0`` のように小数点付きで書くと浮動小数点型と解釈される。（「2.」はタイプミスではなく、正しい書き方）。
          - 明示方法2: キャストする（後述）。
- 自動型変換できないケース
  - ``int n = 15.432;``
    - システム側では「切り捨ててよいのか四捨五入すべきか」どうか判断できない。->コンパイルエラー（文法エラー）
- 演習例
  - (演習2-1) ``int a=1; double b=a/2;`` の結果、変数bの値がどのようになるのか標準出力して確認せよ。
  - (演習2-2) 前述の変数bの値を求める際に、浮動小数点型として割り算するようにコードを修正せよ。bの値が0.5となっていることを標準出力で確認せよ。

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
- 演習例
  - (演習3-1) 10.9をint型にキャストした結果を標準出力し、確認せよ。
  - (演習3-2) char型で'a'を保存し、その変数をint型にキャストした結果を標準出力し、確認せよ。

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
- 演習例
  - (演習4-1) ``String[] text = {"This", "is", "test."};`` として保存されたString配列型に対し、全要素を結合した文字列（Thisistest.）を作り、標準出力せよ。　＊できればループ処理を使おう。

<hr>

## <a name="c5">基本ライブラリの利用（5章）</a>
- APIドキュメント: [Java® Platform, Standard Edition 9 API Specification](http://docs.oracle.com/javase/9/docs/api/overview-summary.html)

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
- [レポート1](https://github.com/naltoma/java_intro/blob/master/report/report1_chatbot/report1.md)で示したSampleScanner.javaは、標準ライブラリを使用した例。

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
  - String[] split(String regex): 与えられた[正規表現regex](http://docs.oracle.com/javase/9/docs/api/java/util/regex/Pattern.html#sum)に基づき、文字列を分解した配列を返す。
    - 正規表現の例: [正規表現を使う](http://java-reference.com/java_string_regex.html)
- 演習例
  - 前提: ``String str = "This is test."`` というString型変数を用意する。
    - (演習5-1) 上記strの文字列を、toLowerCaseメソッドを使って全てを小文字に変換した文字列を作成し、標準出力せよ。
    - (演習5-2) 上記strの文字列を、splitメソッドを使い、「スペース記号」を区切り文字として分割したString配列を作成し、標準出力せよ。（出力体裁は自由で構わない）

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
- 演習例
  - (演習6-1) ``String data = "3.14";`` と保存されているとし、変数dataをdouble型変数に変換した結果をdouble型変数として保持し、その変数を標準出力せよ。

<hr>

## <a name="c8">条件を書くための演算子（8章）</a>
### <a name="c8-1">文字列の比較（pp.178-）</a>
- 「関係演算子==」は、String型（参照型）には **適用できない**。意味が異なる。
  - 文字列がnullかどうか調べる場合だけ使える。
    - ``object == null`` //objectがnullである（実体がない）時にtrue
- 文字列そのもの値を比較するには、String.equals()メソッドを使う。

```
String str = "Java";
boolean answer = str.equals("Java");
```

### <a name="c8-2">論理演算子（pp.181-）</a>
- &&: 論理積
- ||: 論理和
- !: 否定

### <a name="c8-3">条件演算子（pp.188-）</a>
- 3項演算子の例。
- 条件演算子
  - 条件部がtrueかfalseかで違う値になる式を作る。簡単なif文なら1行で書いてしまった方が全体を俯瞰しやすくなる。（使うことを推奨するわけではない）
  - 以下の例では「aの値が偶数ならnの値を100に、そうでないならnの値を0にする」。

```
int a = 5;
int n = a%2==0 ? 100 : 0;
```

### <a name="c8-4">演算子一覧表（p.191）</a>
省略。

<hr>

## <a name="c9">while文（9章）</a>
### <a name="c9-1">while文の例（pp.196-）</a>
- 条件を満足している間、ループ処理を実行し続ける。

```
int i = 0;
while( i<3 ){
    System.out.println(i);
    i++;
}
```

### <a name="c9-2">do-while文（pp.205-）</a>
- 最初に処理を実行し、その後で条件を確認する。（最低1回は処理を実行する）

```
int i = 0;
do {
    System.out.println(i);
    i++;
} while( i<3 );
```

<hr>

## <a name="c11">ジャンプによる制御（11章）</a>
### <a name="c11-1">switch文（pp.244-）</a>
- swich文は、非常に多くの分岐先がある時に有効な構文。if-else文でも同じ処理を書けるが、switch文の方が高速。

```
import java.util.Random;

public class SampleSwitch {
    public static void main(String[] args) {
        Random rnd = new Random(); // Randomクラスのインスタンスを用意。
        int num = rnd.nextInt(5); // 0〜4の乱数を返す。
        switch (num) {
            case 1:
                System.out.println("numが1のときの処理");
                break;
            case 3:
                System.out.println("numが3のときの処理");
                break;
            default:
                System.out.println("case文のどれにも該当しなかった場合の処理");
        }
        System.out.println("switch()文終了。");
    }
}

```

- caseで指定された値と等しい箇所があれば、そこにジャンプする。
  - ジャンプ先にbreakがあれば、そのブロックを抜ける。（ここではswitch文を抜ける）。
  - **ジャンプ先にbreakがなければ、ブロックを抜けず、処理を継続する点に注意。**
    - 例えば、num = 1時に、上記コードで「case 1」時にbreakがない場合、
      - まず case 1 のコードを実行し、
      - 続けて case 3 には該当しないため無視し、
      - 最後に default にはどの値でも該当するため、defaultのコードも実行する。

### <a name="c11-2">switch文の規則（pp.249-）</a>
- switch() のカッコ内に記述できる変数の型は「整数型、文字列型、列挙型」のみ。
  - caseにはリテラルだけが使用可能。変数や式は使えない。

- 演習のコード例: [week2.java](./week2.java)
