# 課題レポート2: シンプルなタイピングゲーム（Java編）

- 前期からの変更点
  - 課題説明は「課題概要」のみで十分です。
  - 今回のレポート2についても「最大3ページ」に収めるようにして下さい。

<ul>
<lh>＜目次＞</lh>
<li><a href="#abst">課題概要</a>
<li><a href="#details">詳細仕様</a>
<li><a href="#hints">ヒント</a>
<li><a href="#goal">課題の達成目標</a>
<li><a href="#report">取り組み方</a>
<li><a href="#submit">提出方法</a>
</ul>

<hr>
## <a name="abst">課題概要</a>
予め用意した文字列とユーザ入力とを見比べ、文字(char)単位での一致率を出力せよ。

<hr>
## <a name="details">詳細仕様</a>
以下では「予め用意した文字列を**answer**」、「ユーザ入力を**input**」と呼ぶこととする。

- **一致率** は次の手順により算出すること。
  - 予め用意した文字列を基準とし、順番と文字が一致している時に「一致した個数 (match_num)」としてカウントする。それ以外の時には「不一致した個数 (mismatch_num)」としてカウントする。
  - ``一致率 = match_num / (match_num + mismatch_num)``
    - 一致率はfloat型とすること。
    - answerとinputの文字数どちらが多いかにより、一致率の分母が変わる点に注意。（下記の計算例1,2参照）
  - 計算例1
    - 予め用意した文字列を **char answer[5] = "tes";** とする。
    - ユーザ入力が **ts** の場合（inputが短い場合）、
      - 1文字目が一致しているので **match_num = 1** となる。
      - 2文字目は誤っており、また3文字目が不足しているので **mismatch_num = 2** となる。
      - これより、一致率は **1/3 = 約0.33 (約33.3%)** となる。
  - 計算例2
    - 予め用意した文字列を **char answer[5] = "tes";** とする。
    - ユーザ入力が **tstt** の場合（inputが長い場合）、
      - 1文字目が一致しているので **match_num = 1** となる。
      - 2文字目、3文字目は誤っており、input4文字目は過剰であるため **mismatch_num = 3** となる。
      - これより、一致率は **1/4 = 0.25 (25.0%)** となる。
- **実行結果** には下記項目を含めること。
  - answerとなる文字列
  - ユーザ入力を促す文
  - 読み込んだinput文字列
  - answerとinputの文字数
  - match_num（一致した文字数）
  - 一致率
- 上記以外の仕様は自由に決めて構わないが、**自作の関数1つ以上を含むこと**。

<hr>
## <a href="output_example">実行例</a>
- 実行例1
```
oct:tnal% javac -d . TypingGame.java
oct:tnal% java jp/ac/uryukyu/ie/tnal/TypingGame
answer = tes
Type some string here:
ts
Your input is = ts
num1(answerの長さ)=3, num2(inputの長さ)=2
match_num = 1, correct_rate = 0.33333334
```

- 実行例2
```
oct:tnal% javac -d . TypingGame.java
answer = tes
Type some string here:
tstt
Your input is = tstt
num1(answerの長さ)=3, num2(inputの長さ)=4
match_num = 1, correct_rate = 0.25
```

<hr>
## <a name="hints">ヒント</a>
- ユーザ入力を読み込むには java.util.Scanner を用いよ。
- 文字列の長さ（ヌル文字が出現するまでの文字数）を取得するには、String.length()メソッドを用いよ。
  - コード例: [SampleScanner.java](SampleScanner.java)
    - コンパイル方法: ``javac -d . SampleScanner.java``
    - 実行方法: ``java SampleScanner``
      - packageは指定していないので、ソースファイルがあるディレクトリにそのままクラスファイルが生成され、その場でパス指定なしに実行できる。
- Scannerで読み込んだデータはString型になる。String型オブジェクトは配列ではないため、data[0] のような書式では文字を走査チェックできない。
  - Stringオブジェクトに対して1文字ずつ走査するには「data.charAt(0)」のようにcharAt()メソッドを利用する。
  - コード例: [SampleString.java](SampleString.java)
    - コンパイル方法: ``javac -d . SampleString.java``
    - 実行方法: ``java SampleString``

<hr>
## <a name="goal">課題の達成目標</a>
- [達成目標](https://github.com/naltoma/c_intro/blob/master/Java_intro.md#goal)
  - 本課題では、構造体は不要です。

<hr>

## <a name="report">取り組み方</a>
- ペアや友人らと話し合って取り組んで構わないが、コード解説を加えるなど「自分自身の報告書」となるように取り組むこと。試して分かったこと、自身で解決できなかった部分等についてどう取り組んだか、といった過程がわかるように示すこと。（考えを図表や文章を駆使して表現して報告する練習です）
- レポート作成は好きなツール（ソフトウェア）を使って構わない。ただし下記を含めること。
  - タイトル
    - 今回は「**プログラミング2、レポート課題2: 「シンプルなタイピングゲーム（Java編）」**」。
  - 提出日: yyyy-mm-dd
  - 報告者: 学籍番号、氏名
    - 複数人で相談しながらやった場合、相談者らを「**協力者: 学籍番号、氏名**」として示そう。
  - <font color="red">課題説明（概要のみでOK）</font>
  - 結果と考察
    - <font color="red">計算例1と計算例2を実現していることを示す実行結果。</font>
    - **課題への取り組みを通し、課題の意義、課題から分かったこと、今後の展望などを述べる。失敗やつまづきがあれば、それらについての失敗分析を含めること。**
    - 参考リンク: [実験レポートの書き方](http://www.report.gusoku.net/jikken/jikkenreport.html)
  - その他
    - 通常は感想等をレポートには含めませんが、練習なので課題に取り組みながら何か感じたこと、悩んでいること等、書きたいことがあれば自由に書いてください。（なければ省略OK）

<hr>

## <a name="submit">提出方法</a>
- 提出物は「レポート」、「作成したソースファイル」の2点である。
- レポートは電子ファイルで提出するものとする。
- 提出先:
  - 「<a href="https://drive.google.com/a/ie.u-ryukyu.ac.jp/folderview?id=0B8oAeomiuJo-OFUxYjNyT083OGM&usp=sharing">Google ドキュメント</a>」のreport2。
- 締切: 調整中。
