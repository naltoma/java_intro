# 課題レポート2: チャットボット初号機を拡張してみよう。

- 前期からの変更点
  - 今回のレポート2についても「最大3ページ」に収めるようにして下さい。
  - ソースファイルも提出してもらうので、レポートに全コードコピペ掲載する必要はありません。特に工夫した点のみを掲載した上で解説・考察しよう。

<ul>
<lh>＜目次＞</lh>
<li><a href="#abst">課題概要</a>
<li><a href="#details">詳細仕様</a>
<li><a href="#howto">取り組み方（Step2までは授業中にやります）</a>
<li><a href="#output_example">実行例</a>
<li><a href="#hints">補足やヒント</a>
<li><a href="#report">レポートに含めるべき項目</a>
<li><a href="#submit">提出方法</a>
</ul>

<hr>

## <a name="abst">課題概要</a>
チャットボット初号機を拡張してみよう。
- 主な拡張点
  - 少し抽象化する。
    - 具体的な対話文等を、できるだけコードに直接埋め込まないようにする。
  - 少しオブジェクト指向的に実装する。
    - クラス、インスタンス、（カプセル化）に慣れよう。
  - 参照型クラスの配列生成に慣れる。
    - 後述のヒント参照。

<hr>

## <a name="details">詳細仕様</a>
- コード例: [exercise_chatbot2](https://github.com/naltoma/exercise_chatbot2)
  - 概要
    - [レポート1初号機](https://github.com/naltoma/java_intro/blob/master/report/report1_chatbot/report1.md)をオブジェクト指向的に実装してみた。ただし、下記に示す部分はコードが削除されている。この部分を実装せよ。
      - 削除部分1: Chatbotのコンストラクタ2。（23行付近）
      - 削除部分2: ChatbotのsearchPatternメソッド。（35行付近）
  - 利用方法
    - [exercise_chatbot2](https://github.com/naltoma/exercise_chatbot2)の「Clone or download」からダウンロード。
      - どっちでもOK。
  - ファイル配置 (``find .`` で確認してみよう)
    - outディレクトリ以下に、動作確認用のバイトコードが置かれている。
    - srcディレクトリ以下に、下記3つのソースファイルがある。
      - (1)チャットボットのためのパッケージ(Chatbot.java, ReplyPattern.java)
        - これらは「jp.ac.uryukyu.ie.tnal」パッケージとして用意してある。
        - Chatbot.javaは前述箇所がコード削除済み。
      - (2)チャットボットを実際に起動するためのmainメソッドを含むソース(Main.java)
        - Main.javaはパッケージ指定がない点に注意。
        - 「ChatbotクラスやReplyPatternクラスを外部パッケージとして利用（import）する」ことを想定しつつ、その外部パッケージ開発をしているという状態。（のつもりで課題に取り組もう）
```java
./git/ #省略
./out
./out/jp
./out/jp/ac
./out/jp/ac/uryukyu
./out/jp/ac/uryukyu/ie
./out/jp/ac/uryukyu/ie/tnal
./out/jp/ac/uryukyu/ie/tnal/Chatbot.class
./out/jp/ac/uryukyu/ie/tnal/ReplyPattern.class
./out/Main.class
./README.md
./src
./src/jp
./src/jp/ac
./src/jp/ac/uryukyu
./src/jp/ac/uryukyu/ie
./src/jp/ac/uryukyu/ie/tnal
./src/jp/ac/uryukyu/ie/tnal/Chatbot.java
./src/jp/ac/uryukyu/ie/tnal/ReplyPattern.java
./src/Main.java
```
  - 動作確認方法
    - outディレクトリに移動し、``java Main``

<hr>

## <a name="howto">取り組み方（Step2までは授業中にやります）</a>
- 前提
  - Main.java, ReplyPattern.java は編集する必要はない。
  - 編集が必要なのは Chatbot.java のみ。
- **Step 1: とりあえず動く状態（コンパイルが通り、動作確認できる状態）にする。**
  - Step 1-1: 最低限のソース修正。
    - コンストラクタはとりあえず空のままでも良い。
    - searchPatternメソッドは、戻り値がint型である。何でも良いが、ここでは ``0`` を返すように1行追加しよう。
  - Step 1-2: コンパイルの準備。
    - outディレクトリは動作確認用に残すとしよう。今回の課題のための出力用ディレクトリとして ``out2`` を作成しよう。
  - Step 1-3: コンパイル＆動作確認。
    - out2ディレクトリにて、``javac -d . ../src/**.*.java`` を実行。
      - 問題なければコンパイルが通り、out2ディレクトリ以下に3つのクラスファイルが適切な箇所に配置されるはず。
    - 動かすには、out2ディレクトリ内で ``java Main`` を実行。
      - 以下のようなエラーが出るはず。
        - これは想定通りのエラー。Chatbot.patterns.pattern には「greeting, other, bye.」の応答パターンが登録されている必要があるが、これらがまだ登録されていないために出たエラー。
```java
oct:tnal% java Main
Exception in thread "main" java.lang.NullPointerException
	at jp.ac.uryukyu.ie.tnal.Chatbot.greeting(Chatbot.java:45)
	at Main.main(Main.java:16)
```
- **Step 2: エラーが出ない程度の最小限の編集。** （ここまで授業で補足）
  - Step 2-1: コンストラクタを少し編集する。
    - Chatbotのコンストラクタ2を以下のように編集してみよう。（コメント文は編集箇所を明示するために残しているが、削除ok）

```java
public Chatbot(String botname, String[] dataset){
    /* 実装下さい */
    patterns = new ReplyPattern[3];
    patterns[0] = new ReplyPattern("greeting", "挨拶文");
    patterns[1] = new ReplyPattern("other", "想定外時の応答文");
    patterns[2] = new ReplyPattern("bye.", "終了時の応答文");
}
```

- Step 2の続き。
  - Step 2-2: コンパイル＆動作確認。
    - この時点で、先程のエラー（NullPointerException）はなくなるはず。
      - bye.を入力すると終了してくれるはず。
      - bye.以外については、何を入力しても ``null「挨拶文」`` と返してくるはず。
        - nullの部分は、本来はbot名を出力させたいが、これはまだ実装していない。
        - 「こんにちは」「bye.」以外の時には「想定外時の応答文」を返すようにしたいが、これもまだ実装していない。
- **Step 3: コンストラクタ2を仕様通りに実装。** （ここから各自でやる）
  - コンストラクタ2の仕様は、ソースコードのコメント文を参照。
    - (1) 引数の botname を使って、this.botname を設定する。
    - (2) 引数の dataset を使って、this.patternsを設定する。
  - 補足
    - datasetの行数（パターン数）が変わったとしても、コンストラクタを修正せずにpatternsを設定できるようにしよう。
  - この時点での動作
    - 仕様通りに設定できたなら、茜ちゃんバージョンで「greeting」と「bye.」への応答ができるはず。
    - まだ、「こんにちは」と「other」に対応する応答は、できていないはず。
- **Step 4: searchPatternメソッドを仕様通りに実装。**
  - 現時点では常に 0 を返しているはず。これを、仕様通りに実装し直そう。
    - case 1: 見つかったら、該当インデックスを返す。
    - case 2: 見つからない場合には「other」を検索し、そのインデックス素を返す。
    - case 3: それも見つからない場合には -1 を返す。
  - この時点での動作
    - greeting, こんにちは, other, bye. の4パターンを、想定通りに出力できるはず。
- **Step 5: Main.javaを自分用に修正**
  - mainメソッドのdatasetにて "想定入力\t応答文" のペアを配列にて設定している。これを、課題レポート1で各自が設定した内容に編集し直そう。
  - 編集後にコンパイルし直し、想定通り（課題レポート1の通り）となることを確認しよう。
    - この部分が、課題概要で述べた「少し抽象化する」に相当。
- (おまけ) 余裕がある人向け拡張
  - Chatbotクラス, ReplyPatternクラスのフィールド変数を隠蔽し、カプセル化してみよう。

<hr>

## <a name="output_example">実行例</a>

```java
oct:tnal% java Main
茜ちゃん「じゃ～ん！プロちゃんの驚く顔が見たい野々原茜ちゃん登場ーっ！ね、驚いた？驚いた？茜みたいなダイヤの原石を前にしてビックリしちゃった？」
（入力待ち）
こんにちは
茜ちゃん「プロちゃん元気ないね？　茜ちゃんと一緒にプリン食べる？」
（入力待ち）
hoge
茜ちゃん「そんなことよりプロちゃん、茜ちゃんをどうプロデュースするか考えてる？」
（入力待ち）
bye.
茜ちゃん「茜ちゃん人形作って待ってるね！」
今日はプロちゃんとたった3回しか話してないよ？
oct:tnal%
```

<hr>

## <a name="hints">補足やヒント</a>
- 外部パッケージの利用について。
  - 今回のソースファイル配置とパッケージ指定の関係
    - Mainクラスはパッケージ指定なし。ChatbotとReplyPatternクラスはjp.ac.uryukyu.ie.tnalパッケージ指定あり。
    - 外部パッケージにあるソース（Chatbot, ReplyPattern）は、Mainクラスにおける「importする際のパス」で指定した通りのディレクトリに置かれている必要がある。これは中間ファイルも同様。
  - コンパイル時には中間ファイルを生成したいディレクトリを用意し、そこから「src」ディレクトリ以下を指定すると良い。
    - out2ディレクトリ上で ``javac -d . ../src/**/*.java`` とした際のオプションの意味は以下の通り。
      - ``**/`` は、「今いるディレクトリ以下を全て対象にする」。
      - ``*.java`` は、「今いるディレクトリにある、拡張子がjavaのファイル全て」。
- 参照型クラス配列の利用について。
  - <a href="#howto">取り組み方</a>Step2の通り、2回 new 演算子を使って初期化する必要がある。この理由は、一度目のnewではコンストラクタを実行しておらず、まだオブジェクトを生成していない（オブジェクトの中身がnullのまま）だから。二度目のnewで、初めてコンストラクタが実行され、オブジェクトが生成される。
  - 教科書pp.365-366も参照しよう。

<hr>

## <a name="report">レポートに含めるべき項目</a>
- ペアや友人らと話し合って取り組んで構わないが、コード解説を加えるなど「自分自身の報告書」となるように取り組むこと。試して分かったこと、自身で解決できなかった部分等についてどう取り組んだか、といった過程がわかるように示すこと。（考えを図表や文章を駆使して表現して報告する練習です）
- レポート作成は好きなツール（ソフトウェア）を使って構わない。ただし下記を含めること。
  - タイトル
    - 今回は「**プログラミング2、レポート課題3: 「「じゃんけん」をオブジェクト指向的に実装してみよう。」**」。
  - 提出日: yyyy-mm-dd
  - 報告者: 学籍番号、氏名
    - 複数人で相談しながらやった場合、相談者らを「**協力者: 学籍番号、氏名**」として示そう。
  - 課題説明（概要のみでOK）
  - **ソースコード解説**
    - コンストラクタ2、もしくはsearchPatternメソッドのいずれかについてコードを掲載し、解説すること。
    - おまけ（カプセル化）にも取り組んだ場合には、「フィールド変数、アクセサメソッド」以外の部分で修正した箇所があれば、そこについてコードを掲載し、解説すると良いかな。
  - **結果**
    - greeting, こんにちは, other, bye. の4パターンに対応してることを確認できる結果を掲載すること。
  - **考察**
    - **今回の実装（ほんのり抽象化＋ほんのりオブジェクト指向）において、どのようなメリット・デメリットがあるだろうか？　考察しよう。**
      - 考え方の例
        - 教科書における「オブジェクト指向」とは何だろうか？　そこに合致するのはどの部分だろうか？
        - 今回の実装における特徴は何だろうか？
        - 非オブジェクト指向的な実装だと、どうなるだろうか？　その場合と比較すると違いが出てこないだろうか？
  - その他
    - 通常は感想等をレポートには含めませんが、練習なので課題に取り組みながら何か感じたこと、悩んでいること等、書きたいことがあれば自由に書いてください。（なければ省略OK）

<hr>

## <a name="submit">提出方法</a>
- 提出物は「レポート」、「作成したソースファイル」の2点である。
  - ソースファイルは、Chatbot.java, Main.java を提出すること。ディレクトリ構造は無視して構わない。
  - おまけに取り組んだ場合には、ReplyPattern.java も提出すること。
- レポートは電子ファイルで提出するものとする。
- 提出先:
  - 「<a href="https://drive.google.com/a/ie.u-ryukyu.ac.jp/folderview?id=0B8oAeomiuJo-OFUxYjNyT083OGM&usp=sharing">Google ドキュメント</a>」のreport2。
- 締切: 調整中。
