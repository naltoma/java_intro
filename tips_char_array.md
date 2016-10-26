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
- char型オブジェクト（文字列ではなく、1文字のみ格納）
  - 変更可能。
- char[]型オブジェクト
  - 個々の要素はcharなので、変更可能。
  - ただし、利用する前に初期化が必要。（<font color="red">初期化しないと変数を参照することはできない</font>）
    - 初期化方法は2つ。
      - 1つ目: ``char[] value = "hoge";`` 形式で、要素数を指定せずに直接文字列を指定して初期化する。
      - 2つ目: ``char[] value = new char[4];`` 形式で、要素数を指定して、空の状態で初期化する。（教科書15章）
        - 現時点では2つ目はまだ教えていないが、この方法を使っても良い。

## 想定している操作例
- 参考課題: 指定された文字列を逆順に並び替えよ。
  - e.g.,
    - 指定文字列の例: "これはてすとです"
    - 操作後の文字列: "すでとすてはれこ"
- 想定してる回答例（これ以外が誤りという訳ではない）
  - 指定文字列をString型で保存。
  - 編集しながら文字列を作りたいが、String型を直接編集できない。
  - char[]なら編集できるため、操作用の文字列をchar[]で用意。
    - Stringクラスを眺めると、char[]に変換するためのメソッド「toCharArray()」がある。
      - （この存在を知らない場合、どうやって見つけたら良いのだろう？）
- コード例

```
public class SampleCharArray {
    public static void main(String[] argc) {
        String target = "test string.";
        char[] copy = target.toCharArray();
        char[] copy = new char[target.length()]; //教科書15章の例

        int i, j;
        for(i=target.length()-1, j=0; i>=0; i--,j++){
            copy[j] = target.charAt(i);
        }
        System.out.println(copy);
    }
}
```

- 実行結果

```
.gnirts tset
```
