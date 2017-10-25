# IntelliJ + JUnit5によるユニットテスト
- 流れ
  - <a href="#pre">サンプルコードの準備</a>
  - <a href="#consider">（討論）実行結果や、コードを眺めて気になることは無いだろうか？</a>
  - <a href="#testcode">テストコードの記述</a>
  - <a href="#testing">ユニットテストの実行</a>

<hr>

## <a name="pre">サンプルコードの準備</a>
- Step 1: プロジェクト新規作成。
  - Java
  - Project name を「ExampleUnitTest」に。
  - Finish
- JUnitを使う際のTips
  - Pythonのdoctestでは、ソースのドキュメント(docstring)をを利用して動作確認したいコードと、その出力結果を記述した。
    - 参考: [ユニットテスト演習（doctest on Python3）](https://github.com/naltoma/python_intro/blob/master/tutorial-doctest.md)
  - JUnitでは、テストコードとテスト対象とをディレクトリで分けて書くのが流儀。
    - このため、テストコードを保存するディレクトリ、実際に開発中のコードを保存するディレクトリを用意し、設定する必要がある。
- Step 2: ディレクトリ設定。
  - Fileメニューから「Proejct Structure...」を選択。
    - 「Modules」を選択。
      - srcを Ctrl+クリック して、2つのフォルダ「main」と「test」を作成する。
      - src全体がSourcesに割り当てられているのを削除する。
      - src/main を「Sources (青字)」に割り当てる。
      - src/test を「Tests (緑字)」に割り当てる。
      - 右下の Apply を選択して、OK。
- Step 3: サンプルコードの準備。
  - src/mainを Ctrl+クリック して、「New -> Package」を選択。
    - 自分自身のパッケージ名を入力し、OK。
      - 當間の場合は「jp.ac.uryukyu.ie.tnal」
    - 作成したパッケージを Ctrl+クリック して、3つのソースファイル（Hero.java, Enemy.java, Main.java）を追加する。
      - 追加方法
        - Ctrl+クリック -> New -> Java Class を選択。
        - ファイル名を入力。
        - パッケージ名と、クラスブロックがテンプレート作成されるので、その中に以下のコードをコピペ。パッケージ名を修正すること。
          - [Hero.java](https://github.com/naltoma/ExampleUnitTest/blob/master/src/main/jp/ac/uryukyu/ie/tnal/Hero.java)
          - [Enemy.java](https://github.com/naltoma/ExampleUnitTest/blob/master/src/main/jp/ac/uryukyu/ie/tnal/Enemy.java)
          - [Main.java](https://github.com/naltoma/ExampleUnitTest/blob/master/src/main/jp/ac/uryukyu/ie/tnal/Main.java)
- Step 4: 動作確認
  - Runメニューから Run -> Main と選んで、Mainクラスを実行してみよう。特に問題なければ次のような実行結果が出力されるはず。（乱数を用いているので、数値そのものは異なってて構わない）
  - 出力結果とMainクラスを参照しつつ、Enemy, Heroクラスがどのように実装されてるか読み解いてみよう。デバッグ実行を利用しても良い。

```
勇者のHPは10。攻撃力は5です。
スライムのHPは6。攻撃力は3です。
勇者 vs. スライム
1ターン目開始！
勇者の攻撃！スライムに2のダメージを与えた！！
スライムの攻撃！勇者に1のダメージを与えた！！
2ターン目開始！
勇者の攻撃！スライムに0のダメージを与えた！！
スライムの攻撃！勇者に1のダメージを与えた！！
3ターン目開始！
勇者の攻撃！スライムに3のダメージを与えた！！
スライムの攻撃！勇者に0のダメージを与えた！！
4ターン目開始！
勇者の攻撃！スライムに1のダメージを与えた！！
スライムの攻撃！勇者に1のダメージを与えた！！
5ターン目開始！
勇者の攻撃！スライムに2のダメージを与えた！！
モンスタースライムは倒れた。
スライムの攻撃！勇者に2のダメージを与えた！！
戦闘終了

Process finished with exit code 0
```

<hr>

## GitHubへリポジトリ登録
- <a href="https://github.com/naltoma/java_intro/blob/master/IntelliJ%2BGit.md">Gitによるバージョン管理 on IntelliJ</a>を参考に、リポジトリ登録。

<hr>

## <a name="consider">（討論）実行結果や、コードを眺めて気になることは無いだろうか？</a>
- 討論メモ

<hr>

## <a name="testcode">テストコードの記述</a>
- Enemyクラスのattackメソッドにテストコードを書こう。
- Step 1: テストを記述するファイルの準備。
  - Enemy.javaを開く。
  - Enemyクラスを記述しているエリア（どこでも可）にカーソルを置いてる状態で、Ctrl+クリック。
    - Go to」から「Test」を選択。
    - Create New Test...を選択。
    - Testing Library を「Junit5」に指定。
      - JUnit5 library not found と言われる場合は「Fix」をクリック。
        - 「Use 'JUnit5' from IntelliJ IDEA distribution」を選択。
    - Class name を EnemyTest にする。（デフォルトのまま）
      - **テストコードを記述するファイルは「クラス名＋Test」** にしよう。
    - Super class は、ないので空のまま。
    - Destination package は、自身のパッケージ名になってることを確認。
    - Generate test methods から、今回作りたいメソッド attack を選択。（他を追加で選択してもok）
    - OKすると、自動でtestフォルダの下にパッケージが作成され、その中にEnemyTest.javaが作成されているはず。
- Step 2: EnemyTest.javaの全体像を確認。
  - 隠されているが、3〜5行目にテスト用ライブラリのimport文が追加されている。
  - 11行目の「@Test」は **アノテーション** と呼ばれる特殊な注釈。
    - 参考:
      - [Wikipedia:アノテーション](https://ja.wikipedia.org/wiki/アノテーション)
      - [API doc: java.lang.annotation](http://docs.oracle.com/javase/8/docs/api/java/lang/annotation/Annotation.html)
- Step 3: テストコードを作成。
  - 用意されてるコードのままだと、スライムは倒れた後にも攻撃をしてしまう。これはまずい。
  - 「倒れた後（enemy.deadがtrue）なら、attack()を実行しても攻撃できない（＝相手のHPを減らすことができない）」ことを確認するテストを書こう。この時点ではテストコードを書くだけで、Enemy.attack()自体は編集しないため、テストが失敗する（攻撃してしまう）ことを確認したい。
    - 代表的なテスト
      - assertEquals(expecteds, actuals)
        - expecteds: 期待する値
        - actuals: 実際に得られた値
        - assertEquals(): 期待する値通りの結果が得られることを確認するテスト。
          - 上記ではint等の型を省略しているが、「等しいか否か」でテストするため、expectedsがint型ならactualsもint型にする必要がある。
      - assertFalse(boolean condition)
        - falseであることを検証する。
      - assertNotNull(object)
        - nullでないことを検証する。
      - その他JUnit5詳細:
        - [公式サイト](http://junit.org/junit5/)
        - [チュートリアル](https://github.com/junit-team/junit4/wiki/Getting-started)　＊JUnit4版。チュートリアルはそのまま動くはず。
        - [JUnit5 API doc](http://junit.org/junit5/docs/current/api/)
  - テスト例: [EnemyTest.java](https://github.com/naltoma/ExampleUnitTest/blob/master/src/test/jp/ac/uryukyu/ie/tnal/EnemyTest.java)

<hr>

## <a name="testing">ユニットテストの実行</a>
- テストを実行。
  - 左の一覧メニューのEnemyTest.javaをCtrl+クリック。
  - 「Run 'EnemyTest'」を実行。
    - 問題なければ（テストが通れば）、画面下に「1 test passed」という表示とともにグリーン表示になる。テストに失敗したら、失敗したテストの詳細と共にレッド表示になる。
      - この時点では、Enemy.attack()は修正していないため、レッド表示になるのが正しい。以下はテスト結果例。Actualの値は乱数の影響で異なる。Expectedと違う値になっていれば、正しくテストできている（想定通りに失敗している）。
```
org.opentest4j.AssertionFailedError:
Expected :10
Actual   :5
```
- テストを実行できない場合の対応例。
  - 「パッケージorg.junitは存在しません」といった、JUnitパッケージを見つけられない状態だと、EnemyTestを実行できない（コンパイル・エラーになる）。
    - Fileメニューの「Project Structure...」を選択。
    - 「Libraries」を選択。
    - 「+」をクリックして、「From Maven...」を選択。
    - Download Library from Maven Repository に「junit:junit」を入力し、右にある虫眼鏡アイコンをクリックして検索する。
      - junitライブラリが複数見つかるので、JUnitの最新版を選択しよう。「junit:junit:5.0-SNAPSHOT」を選択。
        - beta版は問題があることも少なくないので、「betaではないもの（stable版）」を選択しても良い。
    - junit:junit:5.0-SNAPSHOTを選択して、「OK」。
    - 今作業してるプロジェクトExampleUnitTestに追加するか確認されるので、「OK」。
    - Librariesの画面に戻ったら、「Apply」して「OK」。
    - これでJUnit4がインストールできたはずなので、改めてEnemyTest.javaを実行してみよう。
- リポジトリへ作業記録を登録。
  - (1) PCリポジトリへの登録
    - 今の時点ではEnemyTestのテストは失敗して良い。失敗することを確認できたら、commit & push しよう。
    - EnemyTest.java を開いてる状態で、VCSメニューから Git -> Add。
    - VCSメニュー -> Git -> Commit File
  - (2) githubリポジトリへのへの登録
    - VCSメニュー -> Git -> push
