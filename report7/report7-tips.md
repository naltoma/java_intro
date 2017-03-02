# レポート7総論
- [リポジトリ一覧](https://ie.u-ryukyu.ac.jp/tnal/archives/8685)
- ＜目次＞
  - <a href="#good">Good例</a>
  - <a href="#tips">主な減点ポイントや共通アドバイス</a>
    - <a href="#Gradle">Gradle関連</a>
    - <a href="#JUnit">ユニットテスト関連</a>
    - <a href="#implement">実装関連</a>
    - <a href="#doc">ドキュメント関連</a>

<hr>

## <a name="good">Good例</a>
- **うまいこと例外処理を用いることで「ユーザが変な入力したとしても、適切な入力を要求し、処理を続行するコード」になっている。**
  - e.g.,
    - [ヌメロンゲーム](https://github.com/e165703/report7.git)
    - [簡単な戦闘システム](https://github.com/e165758/report7.git)
- **外部ファイルを読み込む際に、うまいこと相対パスで指定している。**
  - e.g.,
    - [RPG](https://github.com/Koo0/Report7_RPG.git)
- **取り組み方や作業工程、クラス全体像が分かりやすい。（レポート上での話）**
  - e.g.,
    - [三山崩し](https://github.com/chikito84/Report7.git)
    - [RPG ツクーレール](https://github.com/riono210/Report7.git)
    - [囲碁](https://github.com/RyumaRyama/Go_game.git)
    - [RPGのコード解説](https://github.com/bouch18/GameMake_repo7.git)
    - [ウィンドウ出力](https://github.com/e165749/report7.git)
- **課題設定が面白い（適切に動作するとは限らず）**
  - e.g.,
    - [反射神経テスト](https://github.com/seloli/report7.git)
    - [迷路を作り動かそう](https://github.com/e165704G/report7.git)
    - [個人用電子計算機のためのピアノ協奏曲第1番"蠍火"](https://github.com/Fukuzawa3man/Music.git)
    - [誰でもできる文字入力](https://github.com/East6/report7.git)
    - [英単語アプリ](https://github.com/e165719/report7.git)
    - [ハートオブクラウンにおけるランダマイザ](https://github.com/e165725/report7.git)
    - [スライムがぐるぐるするだけ](https://github.com/mani0313/report7.2.git)
    - [ポケベル入力](https://github.com/CyberKerug/Report7.git)
    - [AI にゲームをさせてみる](https://github.com/M-82s7503/Report7-player_AI-.git)
    - [スイカ割り](https://github.com/e165750/Report7.git)

<hr>

## <a name="tips">主な減点ポイントや共通アドバイス</a>

<hr>

### <a name="Gradle">Gradle関連</a>
- 減点ポイント
  - **そもそもbuild.gradleを用意していない。**
    - 対策: build.gradle書こう。
  - **設定ファイルはあるが、jarファイルが生成できない。**
    - **case 1: jarタスクを記載していない。**
      - 対策: build.gradle書こう。
    - **case 2: プロジェクト内のディレクトリ構造とpackageが整合取れていない。**
      - 対策: パッケージ名のスペルミスや、ファイル配置に注意しよう。例えば 「jp.ac.uryukyu.ie.tnal.Exec」なら「src/main/jp/ac/uryukyu/ie/tnal/Exec.java」のように配置する必要があります。
    - case 3: 外部ファイル読み込みに関する記述が抜けている。
      - 対策: 「[gradle add external jar dependency](https://www.google.co.jp/?gws_rd=ssl#q=gradle+add+external+jar+dependency&*)」ぐらいでググってみよう。色んな記述方法がありますが、例えばこんな感じで記述できます。
```
jar {
   manifest {
       attributes  "Main-Class": "jp.ac.uryukyu.ie.tnal.Exec"
       attributes 'Implementation-Title': 'Gradle Quickstart', 'Implementation-Version': version
   }
   from('src/main/java/jp/ac/uryukyu/ie/tnal') {
       include 'image/*.gif', 'map/map.dat'
   }
}
```
  - **jarファイル生成できるが、実行できない（mainメソッドを含むクラスが指定されていない）。**
    - 対策: mainメソッドを記載しているクラスを指定しよう。
- Tips
  - IntelliJでは「Gradleプロジェクト」として生成しないと対応しづらいですが、ターミナルで作業するなら「build.gradle」を書くだけで動作します。
- 参考
  - [授業ページ: ビルドツール gradle の利用](https://github.com/naltoma/java_intro/blob/master/gradle.md)
  - [Gradle official site](https://gradle.org/docs)

<hr>

### <a name="JUnit">ユニットテスト関連</a>
- 減点ポイント
  - **そもそもテストが記述されていない。**
    - 対策: まずは書け。話はそれからだ。
  - **テストっぽいが、想定通りに実行できたか否かを自動採点できるように記述されていない。（開発者自身が目視確認するようになっている）**
    - 前提
      - ユニットテストは「関数（メソッド）が想定通りに動くことを自動採点する」ために使います。例えば100個のメソッドを書いたとして、それらのメソッドが正しく動いてるかどうかを毎回print出力して目視確認するのは大変ですよね。目視確認してると見間違いも起きます。少しでもテストを自動化したいという思いから生まれたのが「ユニットテスト」です。
    - テスト例
      - ボードゲームで、既に何かコマが置かれているマスに対して、間違って上書きされないだろうか？
      - ポーカーで、プレイヤーに対して5枚カードを配ったはずだが本当に5枚あるだろうか？重複してたり、存在しないカードが配られてないだろうか？
      - ゲーム採点時に、プレイヤー側から勝手に操作されないだろうか？
      - ランキングシステムの出力結果は正しいだろうか？　対象者が漏れてたり増えてたりしないだろうか？
      - 乱数を用いた確率は想定通りの発生確率になっているだろうか？
- 参考
  - [授業ページ: IntelliJ + JUnit4によるユニットテスト](https://github.com/naltoma/java_intro/blob/master/IntelliJ%2BJunit4.md)
  - JUnit: [ [公式サイト](http://junit.org/junit4/) | [チュートリアル](https://github.com/junit-team/junit4/wiki/Getting-started) | [API doc](http://junit.org/junit4/javadoc/latest/index.html) ]

<hr>

### <a name="implement">実装関連</a>
- **case 1: クラス・変数等の命名規則**
  - 状況説明
    - クラスなのに頭文字が小文字になってたり、名称から類推するには困難な名前がつけられてたりする。methodという名前のメソッドからは読み取れる情報がゼロです。
  - 対策
    - 適切に命名しよう。
    - 参考
      - [Java入門（いろいろなデータ型、キャスト演算子、命名規則、基本ライブラリ、演算子、while文、ジャンプによる制御）](https://github.com/naltoma/java_intro/blob/master/week4.md#c3-2)
      - 教科書, pp.51-
- **case 2: Main/Execクラス自身に、コンストラクタがある。**
  - 状況説明
    - mainメソッドで実行するだけのMainクラスやExecクラスそのものに、具体的な機能実装がある状態。特に、Main/Execクラス自身にコンストラクタまで用意するのはクラス設計がおかしいです。
  - 対策
    - (1) 適切なクラス名に変更するか、(2) 別のクラスとして実装した上でMain/Execクラスから利用しよう。
  - 参考
    - 教科書, mainメソッドの位置づけ（16.1節）
    - [文法の補足](https://github.com/naltoma/java_intro/blob/master/Java_additional_syntax.md)
- **case 3: コンストラクタで全てが完結している。**
  - 状況説明
    - Exec.main()にて「MyClass instance1 = new MyClass();」としてインスタンス生成するのは良い。だけど「コンストラクタ実行＝すべての処理を終える」という実装になっているのはおかしいです。
  - 対策
    - コンストラクタは該当クラスのインスタンスを生成するだけに留めよう。そうしないと、そのクラスを再利用しようとしても「毎回すべての処理を実行するクラス」になっているので、使いづらいです。
  - 参考
    - 教科書, コンストラクタ（14.1節）
    - [オブジェクト指向1: クラスとインスタンス、コンストラクタ](https://github.com/naltoma/java_intro/blob/master/ObjectOrientedProgramming.md#class)
- **(発展的話題) case 4: hard codingになってるプログラム。**
  - 状況説明
    - ハード・コーディングとは、特定状況に特化した実装になっており、再利用性や拡張性が損なわれている状態。
  - 例
    - ループ文の繰り返し条件やif文の条件分岐を、数字などのリテラルで直接指定している。
    - 変数の都合で特定状況下でしか動かない。
      - e.g., ポーカーを実装する際に、手札を管理するための変数を「card1, card2,,,card5」のように実装しており、それ以外のトランプカードでは利用しづらい。
  - 対策
    - ポーカーの例だと「トランプゲームの一例」のように、実装対象を「一例」として捉えて抽象化できないか考えてみよう。
- **(発展的話題) case 5: チートされるゲーム。**
  - 状況説明
    - 比較的ゲームを実装しているケースが多いのですが、「ポーカーでプレイヤー自身がカード内容を変更できる」、「役をプレイヤー自身で変更できる」ような実装になっているケースが多いようです。
  - 対策
    - オブジェクトごとに操作できる範囲を線引できるようにクラス設計してみよう。
- **(発展的話題) case 6: 実装とコンテンツが混在している。**
  - 状況説明
    - 例えば「選択肢のあるクイズ」を実装することを考えてみよう。設問文と選択肢をコードに直接書いてしまうと、実装できる人しかクイズを増やすことができないし、変更する度にコンパイルが必要になる上、コードを編集するということで新たなバグが発生する可能性も出てきます。
  - 対策
    - 上記では直接コードに埋め込む形でコンテンツを実装した場合の話でした。これに対して、設問文や選択肢を外部ファイルやデータベースとしてプログラム本体から切り離した形で用意できると、そのあたりの手間を省くことができます。
  - 参考
    - [ExampleFileInput](https://github.com/naltoma/ExampleFileInput)
    - 別授業「[データベースシステム](https://ie.u-ryukyu.ac.jp/syllabus/2016/late/60153700.html)」

<hr>

### <a name="doc">ドキュメント関連</a>
- **case 1: レポートの書き方**
  - 必修の共通授業「日本語表現法入門」は、クラスによってはただの作文らしい。そうではなくて、[理科系の作文技術](https://www.amazon.co.jp/dp/4121006240)あたりを指定しているクラスを選択して受講しよう。
  - [一般的なレポートとしてのおかしさ](https://ie.u-ryukyu.ac.jp/tnal/archives/5822)も参考に。特に、段落（パラグラフ）を意識して使うようにしよう。
- **case 2: クラス継承図**
  - ソースファイル数（≒クラス数）が増えてくると、それらがどのように影響（関係）しあっているのかを把握しづらくなります。クラス継承図等、何かしらクラス全体像を把握しやすくなるように工夫してみよう。そうしないと、開発してる時点で把握してたものも、数週間後には「これは何だ」という状態になりがちです。将来の自分のためにも、分かりやすいドキュメント作成を心がけてみよう。
  - 参考
    - 教科書pp.401-, 継承ツリー
    - 別授業「[モデリングと設計](https://ie.u-ryukyu.ac.jp/syllabus/2016/late/60153500.html)」
