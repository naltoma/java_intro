# 文字列、文字配列操作のTips
- 課題や演習に取り組む際に困ってる要因
  - 文字列操作が必要っぽいけど良くわからない。

## 前提条件
- Pythonでは、「後から追加したり削除したりできるリスト型・辞書型」があったが、Javaでは現時点ではまだそれに相当する型はまだ教えていない。
- Javaでは、文字列はStringかchar[]で扱うことができる。
  - Stringとchar[]では扱い方に差があるため、型の特性に応じてアプローチを変える必要がある。

## String, char, char[]の特徴
- String型オブジェクト
  - 一度生成すると変更できない。（pythonのstr型オブジェクトと同じ制約）
  - 長さを得るには、[String.length()](http://docs.oracle.com/javase/8/docs/api/java/lang/String.html#length--)を使おう。
- char型オブジェクト（文字列ではなく、1文字のみ格納）
  - 変更可能。
- char[]型オブジェクト
  - 個々の要素はcharなので、変更可能。
  - 配列長を変更することはできない。
  - 長さを得るには、「char[] hoge;」と宣言してるなら ``hoge.length`` で得られます。
  - ただし、利用する前に初期化が必要。（<font color="red">初期化しないと変数を参照することはできない</font>）
    - 初期化方法は2つ。
      - case 1: ``char[] value = "hoge";`` 形式で、要素数を指定せずに直接文字列を指定して初期化する。
      - 2つ目: ``char[] value = new char[4];`` 形式で、要素数を指定して、空の状態で初期化する。（教科書15章）
        - 現時点では2つ目はまだ教えていないが、この方法を使っても良い。

## 今回の処理フロー
- ここでは、次の手順で文字列編集をしてみる。
  - step 1: 編集したい文字列をString型オブジェクトで用意する。
  - step 2: 上記のString型オブジェクトをchar[]型オブジェクトに変換する。（charやchar[]なら直接編集できる）
  - step 3: 変換したchar[]型オブジェクトを目的に合わせて編集する。
  - step 4: （step 3で終了しても良いが、元オブジェクトがString型だったので）編集したchar[]型オブジェクトを、String型に変換する。

## 想定している操作例
- 参考課題: 指定された文字列を逆順に並び替えよ。
  - e.g.,
    - 指定文字列の例: "test string."
    - 操作後の文字列: ".gnirts tset"
- 想定してる回答例（これ以外が誤りという訳ではない）
  - 指定文字列をString型で保存。
  - 編集しながら文字列を作りたいが、String型を直接編集できない。
  - char[]なら編集できるため、操作用の文字列をchar[]で用意。
    - Stringクラスを眺めると、char[]に変換するためのメソッド「toCharArray()」がある。
      - （この存在を知らない場合、どうやって見つけたら良いのだろう？）
    - 同様に、Stringクラスを眺めると、char[]をStringに変換するためのメソッド「valueOf(char[] data)」がある。
      - （この存在を知らない場合、どうやって見つけたら良いのだろう？）
- コード例

```
public class SampleCharArray {
    public static void main(String[] argc) {
        String source = "test string."; // step 1
        char[] target = source.toCharArray(); // step 2
        System.out.println("before: " + source);

        // step 3
        int i, j;
        for(i=source.length()-1, j=0; i>=0; i--){
            char c = source.charAt(i);
            target[j] = c;
            j++;
        }

        // step 4
        String result = String.valueOf(target);
        System.out.println("after: " + result);
    }
}
```

- 実行結果

```
oct:tnal% javac SampleCharArray.java
oct:tnal% java SampleCharArray
before: test string.
after: .gnirts tset
```
