# 課題レポート3: 「じゃんけん」をオブジェクト指向的に実装してみよう。

- 前期からの変更点
  - 課題説明は「課題概要」のみで十分です。
  - 今回のレポート3についても「最大3ページ」に収めるようにして下さい。
    - ソースファイルも提出してもらうので、レポートに全コードコピペ掲載する必要はありません。特に工夫した点のみを掲載した上で解説・考察しよう。

<ul>
<lh>＜目次＞</lh>
<li><a href="#abst">課題概要</a>
<li><a href="#details">詳細仕様</a>
<li><a href="#output_example">実行例</a>
<li><a href="#hints">補足やヒント</a>
<li><a href="#report">取り組み方</a>
<li><a href="#submit">提出方法</a>
</ul>

<hr>

## <a name="abst">課題概要</a>
「じゃんけん」をオブジェクト指向的に実装してみよう。

<hr>

## <a name="details">詳細仕様</a>

### Playerクラス
- パッケージ名
  - 自身のアカウントにすること。
    - 當間の例: ``package jp.ac.uryukyu.ie.tnal;``
- クラス名: Player（アクセス修飾子はpublic）
  - フィールド変数
    - 下記3つの変数全てを private とすること。
    - **String name**; // プレイヤー名を保存する変数
    - **int hand**; // 取った手を保存する変数。グーなら0、チョキなら1、パーなら2。
    - **int winCount**; // 勝利回数を保存する変数。
  - コンストラクタ: **public Player(String name)**
    - 引数: 1つ。
      - プレイヤー名として保存するための「String型の文字列」。
    - 処理
      - プレイヤー名(this.name)の設定。
      - 取った手(this.hand)の初期化。初期値は-1（まだ手を選んでいない）とする。
      - 勝利回数(this.winCount)を0に初期化。
  - メソッド
    - **public void makeHand()**
      - グー(0)、チョキ(1)、パー(2)のいずれかを毎回ランダムに選択し、this.hand に設定するメソッド。各々の手が選択される確率は同確率とすること。
    - **public int getHand()**
      - アクセサメソッドの一つ。フィールド変数handを返すメソッド。

### Mainクラス（これは自作せずに、下記コードを利用すること）
- 上記 Player クラスを利用するための main() メソッドを記述するクラス。
  - [Main.java](./Main.java)
    - import文は、自作したPlayer.javaに記入したpackage名を参考に修正すること。

```
import jp.ac.uryukyu.ie.tnal.Player;

public class Main {
    public static void main(String[] args) {
        Player taro = new Player("Taro");
        Player hanako = new Player("Hanako");

        taro.makeHand();
        hanako.makeHand();
        System.out.println("taro's hand: " + taro.getHand());
        System.out.println("hanako's hand: " + hanako.getHand());
    }
}
```

- コンパイル方法＆実行方法について
  - コンパイル
    - ``javac -d . Player.java Main.java``
  - 実行方法
    - Main.javaにパッケージ名を指定している場合
      - ``java パッケージ名.Main``
    - Main.javaにパッケージ名を指定していない場合（上記コード例では指定していない）
      - パッケージ名を省略している場合、コンパイルしたディレクトリにclassファイルが生成される。
      - ``java Main``

### 考察項目
- **どのあたりが「オブジェクト指向的」な実装になっているだろうか？　考察しよう。**
  - e.g., カプセル化している実装はどこだろうか？　これに伴う特徴and/orメリットは何だろうか？
  - レポートには下記2点を含めること
    - オブジェクト指向的な実装になっている箇所の明記。（後述の「理由」から判断できる場合には省略可）
    - 上記をオブジェクト指向的な実装だと判断した理由。
  - 考え方の例
    - 教科書における「オブジェクト指向」とはなんだろうか？　そこに合致するのはどの部分だろうか？
    - カプセル化とはなんだろうか？　特徴and/orメリットはなんだろうか？
    - 今回の実装における特徴and/orメリットはなんだろうか？
    - 非オブジェクト指向的な実装はどうなるだろうか？　その場合と比較すると違いが出てこないだろうか？

<hr>

## <a href="output_example">実行例</a>
- 乱数を用いているため、実行する度に結果が異なる点に注意。

```
oct:tnal% java Main
taro's hand: 0
hanako's hand: 1
oct:tnal% java Main
taro's hand: 1
hanako's hand: 0
```

<hr>

## <a name="hints">補足やヒント</a>
- 乱数を用いるためには、[java.util.Random](http://docs.oracle.com/javase/8/docs/api/java/util/Random.html) か [Math.random](http://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#random--) を使おう。どちらも本質的には一緒です。（これ以外を使ってもok）
- 全てをフィールド変数を private にすること。


<hr>

## <a name="report">取り組み方</a>
- ペアや友人らと話し合って取り組んで構わないが、コード解説を加えるなど「自分自身の報告書」となるように取り組むこと。試して分かったこと、自身で解決できなかった部分等についてどう取り組んだか、といった過程がわかるように示すこと。（考えを図表や文章を駆使して表現して報告する練習です）
- レポート作成は好きなツール（ソフトウェア）を使って構わない。ただし下記を含めること。
  - タイトル
    - 今回は「**プログラミング2、レポート課題3: 「「じゃんけん」をオブジェクト指向的に実装してみよう。」**」。
  - 提出日: yyyy-mm-dd
  - 報告者: 学籍番号、氏名
    - 複数人で相談しながらやった場合、相談者らを「**協力者: 学籍番号、氏名**」として示そう。
  - 課題説明（概要のみでOK）
  - 結果と考察
    - **課題への取り組みを通し、課題の意義、課題から分かったこと、今後の展望などを述べる。失敗やつまづきがあれば、それらについての失敗分析を含めること。**
      - **今回は「オブジェクト指向やカプセル化」について考察すること。（詳細は前述参照）**
    - 参考リンク: [実験レポートの書き方](http://www.report.gusoku.net/jikken/jikkenreport.html)
  - その他
    - 通常は感想等をレポートには含めませんが、練習なので課題に取り組みながら何か感じたこと、悩んでいること等、書きたいことがあれば自由に書いてください。（なければ省略OK）

<hr>

## <a name="submit">提出方法</a>
- 提出物は「レポート」、「作成したソースファイル」の2点である。
- レポートは電子ファイルで提出するものとする。
- 提出先:
  - 「<a href="https://drive.google.com/a/ie.u-ryukyu.ac.jp/folderview?id=0B8oAeomiuJo-OFUxYjNyT083OGM&usp=sharing">Google ドキュメント</a>」のreport3。
- 締切: 調整中。
