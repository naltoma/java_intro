# IntelliJ + JUnit5によるユニットテスト
- 概要
  - 「バグを含むコード例」を用意。目視でバグを確認できるが、これをJUnitで自動確認できるようにしてみよう。また、作業記録をVCSで管理し、リポジトリを公開するためにGitHubも利用してみよう。
- 流れ
  - <a href="#pre">サンプルコードの準備</a>
  - <a href="#github">GitHubへリポジトリ作成、現バージョンを登録</a>
  - <a href="#issue">サンプルコードの問題点</a>
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

## <a name="github">GitHubへリポジトリ作成、現バージョンを登録</a>
- <a href="https://github.com/naltoma/java_intro/blob/master/IntelliJ%2BGit.md">Gitによるバージョン管理 on IntelliJ</a>を参考に、リポジトリ登録しよう。
- Step 1: Gitの設定確認。
  - ターミナルで ``cat ~/.gitconfig`` を実行して、設定ファイルがあるか、設定が記述されているかを確認しよう。以下は當間の例だが、自分自身の name, emailが記載されいればok。
```
oct:tnal% cat ~/.gitconfig
[user]
	name = Naruaki TOMA
	email = tnal@ie.u-ryukyu.ac.jp
```
  - もしファイルが見つからない場合、もしくはname, emailの設定が書かれていない場合、下記2行を実行。名前とメールアドレスは自分自身のものに修正すること。
    - ``git config --global user.name "Naruaki TOMA"``
    - ``git config --global user.email "tnal@ie.u-ryukyu.ac.jp"``
- Step 2: GitHubリポジトリの準備。
  - 下記記事を参考に、GitHubのアカウント作成、ssh-key登録、リポジトリ作成をする。
    - 参考: [Gitによるバージョン管理 on IntelliJ](https://github.com/naltoma/java_intro/blob/master/IntelliJ%2BGit.md)
    - リポジトリ名は「ExampleUnitTest」にしよう。
    - 作成したリポジトリを利用するための **Git URL** を何処かにメモしておこう。後で使います。
      - Git URLの例: https://github.com/naltoma/test.git
      - 上記のように、最後に .git が付いてるURLがGitURL。
- Step 3: IntelliJで作業中のプロジェクトに、VCSを設定する。
  - VCS = Version Control System（バージョン管理システム）のこと。
  - IntelliJで作成したプロジェクトのウィンドウに戻る。
    - VCSメニューから「Enable Version Control Integration」を選択。
      - 「Git」を選択し、OK。
- Step 4: VCS管理対象を指定して、commit。（ローカルリポジトリに作業記録を残す）
  - 作業状況をセーブしたいタイミングで、その都度実行するステップ。
  - VCSメニューから「Git -> + Add」を選択。
    - 「Do you want to add the following file to Git?  /Users/tnal/IdeaProjects/ExampleUnitTest/.idea/vcs.xml」と聞かれるが、Noを選択。（IntelliJ専用のファイルなので、不要）
  - VCSメニューから「Git -> Commit File...」を選択。
    - 登録したいファイルがすべて選択されていることを確認。
      - 今回は、下記4つのファイルを選択しよう。.ideaは不要です。
        - src/main/jp.ac.uryukyu.ie.tnal/Enemy.java
        - src/main/jp.ac.uryukyu.ie.tnal/Hero.java
        - src/main/jp.ac.uryukyu.ie.tnal/Main.java
        - src/test/jp/ac/uryukyu/ie/tnal/EnemyTest.java
    - 上記4つのファイルを選択した状態で、commit message を記入し、「Commit」をクリック。
      - コミットメッセージは、ユニットテスト用の初期コードであることが分かるように書こう。
  - Code Analysis が起動するので、「Review」をクリック。
    - 自動コードチェッカ。不必要にpublicにしてないか、テンプレートのままになっていないか等の簡易的なチェックをしてくれる。
    - 普段なら、ここで指摘された事項は一通り無くなるまで対応しよう。今回は一つだけ対応することにして、残りは見なかったことにしよう。
      - Main.javaに関する指摘事項を眺めてみると、「Warning:(14, 16) 'hero.isDead() == false' can be simplified to '!hero.isDead()'」と書かれている。この行をダブルクリックして、該当箇所を開こう。
      - ここの 「``hero.isDead() == false`` という記述が冗長なので、 ``!hero.isDead()`` と書き直したらどう？」という指摘らしい。指摘通りに修正してみよう。
      - 上記を修正したら、もう一度 Git -> Commit File -> Commit。そこから Review を確認。
        - 対応箇所に関する指摘が消えているはずだ。理想的には全ての指摘が消えた状態で登録すべきだが、今回はこの状態でローカルリポジトリに登録しよう。
          - もう一度 Git -> Commit File -> Commit。
          - Commit message を記入し、Commit。
          - 今度は Review ではなく、Commit を選択。
            - エラーが出なければ、「* files committed」といった出力が出るはず。これでPC側のリポジトリに作業記録を残すことができた。ただし、この時点では「IntelliJが作成している、PC側のフォルダに登録しているだけ」であることに注意。
- Step 5: ローカルリポジトリの作業状況を、GitHubリポジトリに登録。
  - Step 2で作成した GitHub リポジトリのページを開き、Git URLをコピー。
  - IntelliJに戻る。
    - VCS -> Git -> Pushを選択。
    - Define remote をクリック。
    - URLに、GitHubに用意したリポジトリの作業用URL（Git URL）を貼り付けよう。
    - 貼り付けたら、OK。
    - Push。
    - Push successfulと出力されたら、Githubリポジトリへのpushが成功。GitHubページに戻り、ページを再読込みしよう。どのようにリポジトリが見えるか確認してみよう。
- ここまでの作業を整理。
  - 開発中のソースコードをGitでバージョン管理する。これだけならPC内（ローカル）で作業してよい。前期はそうした。
  - 一方で、ペアプロなどファイルを複数人で共有しながら作業したい場合や、リポジトリを公開したい場合には、ベアリポジトリを共有しやすい場所で管理したい。このような目的で使えるのが GitHub。
  - GitHubリポジトリの作成は、プロジェクト毎に作成する。公開プロジェクトであれば無料で無制限に作成可能。
  - IntelliJ側の設定（Git設定, Git URL設定）も、プロジェクト毎にやる。
  - commitは、作業状況を「新しいバージョン」として記録したい単位でやる。記録しておけば、そのバージョンに戻ることができる。ただしこの時点ではPC内でしか記録していない点に注意。
  - pushは、ベアリポジトリに記録する。ベアリポジトリとして GitHubリポジトリを指定すると、そこに記録を残し、公開することができる。

<hr>

## <a name="issue">サンプルコードの問題点</a>
- 問題点
  - Enemy.hitPointがゼロになったとしても、Enemy.attack()を実行したら攻撃が発生してしまう。

<hr>

## <a name="testcode">テストコードの記述</a>
- Enemyクラスのattackメソッドにテストコードを書こう。
  - 前述の問題点を確認するためのコードとして、以下のテストをするようにしてみよう。
    - [処理1] テスト用の Hero, Enemy オブジェクトを生成する。この時のHero.hitPointを戦闘前の値として保存しておく。
    - [処理2] Enemy.hitPointをゼロにする。
    - [処理3] Heroオブジェクトに対し、Enemy.attack()を複数回実行する。（1度じゃないのは、攻撃結果が0ダメージとなる可能性があるため）
    - [処理4] 「3終了後のHero.hitPoint」と「1で保存した値」が等しいならば、attak()を実行しても攻撃が発生していないはずだ。逆にいうと、これらの値が異なる数値になっているならば、誤って攻撃しているはずだ。この2つの値が等しいことを確認する。（等しくなければテスト失敗とみなす）
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
  - テストコードの例: [EnemyTest.java](https://github.com/naltoma/ExampleUnitTest/blob/master/src/test/jp/ac/uryukyu/ie/tnal/EnemyTest.java)

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
- ユニットテストのメリット
  - 動作確認するだけなら、print出力したり、デバッグ実行しながら観察することでも可能。ただし、これらの場合には毎回全てを目視確認する必要がある。
    - 過去にprint確認した際に正しく動いてたとしても、コードのバージョンアップ（バグ修正、機能追加等）した後で、以前の通り動くことは保証されていないため、コード修正する都度すべての箇所を目視確認する必要が出てくる。死ぬ。
  - テストにかかる手間を省力化するためのツールが JUnit 。動作確認したい項目毎にテストコードを書き、それらのコードが想定通りだったか否かを自動で検証する。
    - どのテストで失敗したかが一目瞭然のため、手間を大幅に削減できる。
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
- リポジトリへ作業記録を登録。（テストコードは機能していることを確認できた。このテストコードを記録しよう）
  - (1) PCリポジトリへの登録
    - 今の時点ではEnemyTestのテストは失敗して良い。失敗することを確認できたら、commit & push しよう。
    - EnemyTest.java を開いてる状態で、VCSメニューから Git -> Add。
    - VCSメニュー -> Git -> Commit File
  - (2) githubリポジトリへのへの登録
    - VCSメニュー -> Git -> push
