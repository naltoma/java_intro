# 演習2: オブジェクト指向開発してみよう（簡単なクラス設計＆実装の演習）

＜目次＞
- 演習2: オブジェクト指向開発してみよう
  - <a href="#howto">ペア・プログラミングの目的とやり方</a>
  - <a href="#goal">達成目標</a>
  - <a href="#ex2_1">演習2_1: Playerクラスの実装1（クラス、コンストラクタ、メソッド）</a>
  - <a href="#ex2_2">演習2_2: Playerクラスの実装1（カプセル化）</a>
  - <a href="#ex2_3">演習2_3: Judgeクラスの実装</a>

<hr>

## <a name="howto">ペア・プログラミングの目的とやり方</a>
- 目的
  - **課題解決に関するノウハウの共有。**
    - 「早く解くこと」ではない。
- やり方
  - **問題文が理解できない場合にはすぐに質問すること。（ここで悩むのは時間の無駄）**
  - driverとobserverを決める。（10~20分程度で交代しよう）
    - driver
      - 実際に作業する（手を動かす）人。それ以外の人は直接作業してはいけない。
      - driverは作業する際に考えていることを説明しながら作業すること。分からないなら相談すること。
      - 例
        - 「まず問題読んでみよっか。〜〜（問題文）〜〜。ん、どういうことだろう？」
    - observer
      - driverの作業を眺め、気づいたこと・分からないことを質問すること。作業を中断させて構わない。
      - driverのやり方よりも良い方法があると感じたら、その旨を話すこと。

<hr>

## <a name="goal">達成目標</a>
- 実装対象を「モノ（オブジェクト）」として捉え、どんな情報（フィールド）を所有しているか、その情報をどう処理する（メソッド）のかという視点でクラス設計、実装してみよう。
- 考えてることを説明できるようになろう。
- 分からない時には作業を止めて質問できるようになろう。

<hr>

## <a name="ex2_1">演習2_1: Playerクラスの実装1（クラス、コンストラクタ、メソッド）</a>
- 演習2の目的: 「じゃんけん」をオブジェクト指向的に実装してみよう。
  - 従来のプログラミングだと、単にグー・チョキ・パーを出す関数を用意するだけ。
  - ここでは「オブジェクト指向」を意識してクラス設計してみよう。（実装は演習2_2でやる）
  - 設計したクラスの利用方法（mainメソッドからの呼び出し方法）は、以下の通りとする。
    - **pair番号** は適宜修正すること。

```java
// Main.java

import jp.ac.uryukyu.ie.ex2.pair00.*;

public class Main {
    public static void main(String[] args) {
        Player user1 = new Player("Taro");
        Player user2 = new Player("Hanako");

        user1.makeHand();
        user2.makeHand();
        System.out.println(user1.name + "'s hand: " + user1.hand);
        System.out.println(user2.name + "'s hand: " + user2.hand);
    }
}
```

```
# 実行イメージ
oct:tnal% cd ~/prog2/ex2/out
oct:tnal% javac -d . ../src/**/*.java
oct:tnal% java Main
Taro's hand: 3
Hanako's hand: 1
oct:tnal% java Main
Taro's hand: 1
Hanako's hand: 2
```

- じゃんけんの実装方針（≒Main.javaの概要）
  - 「じゃんけんをする人」を実装対象と見做し、それをPlayerクラスとして設計する。Playerクラスをパッケージ指定し、それを利用するMainクラスからは、importにより外部パッケージを読み込むものとする。
  - ファイル配置: ~/prog2/ex2/ 以下に、以下のように配置するものとする。
```
./out/ #中間ファイル生成用のディレクトリ
./src/Main.java #上記ファイルのコピペでok
./src/jp/ac/uryukyu/ie/ex2/pair00/Player.java #これを実装する
```
  - Mainクラス（mainメソッドを含むクラス）
    - 「jp.ac.uryukyu.ie.ex2.pair00」に含まれるクラス（今回はPlayerクラス）をimportし、
  - Playerクラス
    - パッケージとして ``jp.ac.uryukyu.ie.ex2.pair00`` を指定するものとする。
    - フィールド変数として、名前(String name)と手(int hand)を保存するための変数2つを持つものとする。アクセス制限なしの public とする。
      - 手は「1=グー、2=チョキ、3=パー」とする。
    - コンストラクタ
      - プレイヤー名(String型)を引数に取り、フィールド変数 name に保存するものとする。
    - メソッド
      - makeHand(): 引数無しで、自身の手をランダムに設定する。
- 上記を踏まえ、Playerクラスを実装せよ。
- 提出方法
  - Player.javaを自身のペア番号フォルダにアップロードすること。
  - 実行結果を報告すること。書式は自由です。
    - ファイル名等は演習1_1に関する報告だということが分かるように工夫して下さい。
    - Googleドキュメントを新規作成しても良し、PC上で作成したテキストファイルやPDFファイルをアップロードするも良し。

<hr>

## <a name="ex2_2">演習2_2: Playerクラスの実装2（カプセル化）</a>
- 演習2_1では、フィールド変数が public なため、どこからでも参照＆編集ができる状態である。これを隠蔽し、アクセサメソッドを用意することでカプセル化せよ。
- 提出方法
  - Player.javaを自身のペア番号フォルダにアップロードすること。
  - 実行結果を報告すること。書式は自由です。
    - ファイル名等は演習2_2に関する報告だということが分かるように工夫して下さい。
    - Googleドキュメントを新規作成しても良し、PC上で作成したテキストファイルやPDFファイルをアップロードするも良し。

<hr>

## <a name="ex2_3">演習2_3: Judgeクラスの実装</a>
- ここまでで Playerクラスとそれを使用するMainクラスを実装してきた。ジャンケンをするプレイヤーは実装できたが、勝敗を判定する部分が、まだない。これを Judgeクラスとして実装してみよう。なお、Mainクラスを以下のように変更するものとする。

```java
// Main.java (ver.演習2_3)

import jp.ac.uryukyu.ie.ex2.pair00.*;

public class Main {
    public static void main(String[] args) {
        Player user1 = new Player("Taro");
        Player user2 = new Player("Hanako");

        user1.makeHand();
        user2.makeHand();

        Judge judge = new Judge();
        judge.judgement(user1, user2);
    }
}
```

```
# 実行イメージ (ver.演習2_3)
oct:tnal% java Main
player1[Taro] = 1
player2[Hanako] = 2
So, Taro is the winner!
oct:tnal% java Main
player1[Taro] = 2
player2[Hanako] = 1
So, Hanako is the winner!
```

- Judgeクラス
  - パッケージとして ``jp.ac.uryukyu.ie.ex2.pair00`` を指定するものとする。
  - フィールド変数は特に持たないものとする。
  - コンストラクタ
    - 設定なし。
  - メソッド
    - void judgement(Player user1, Player user2)
      - 二人のプレイヤーを引数として受け取り、その手に応じた判定結果を標準出力するメソッド。
- 上記を踏まえ、Judgeクラスを実装せよ。
- 提出方法
  - Judge.javaを自身のペア番号フォルダにアップロードすること。
  - 実行結果を報告すること。書式は自由です。
    - ファイル名等は演習2_3に関する報告だということが分かるように工夫して下さい。
    - Googleドキュメントを新規作成しても良し、PC上で作成したテキストファイルやPDFファイルをアップロードするも良し。
