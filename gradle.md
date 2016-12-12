# ビルドツール gradle の利用。オマケでmakeも。
- [Gradle公式サイト](https://gradle.org)
- ＜目次＞
  - <a href="#whatis">ビルドツールとは</a>
  - <a href="#ready">Gradleの環境構築＆動作確認</a>
  - <a href="#tutorial1">Gradleチュートリアル1（jarファイル生成を自動化してみる）</a>
  - <a href="#tutorial2">Gradleチュートリアル2（ユニットテストを自動化してみる）</a>
  - <a href="#make">Makefile概要</a>
  - <a href="#make-tutorial">Makefileチュートリアル</a>

<hr>

## <a name="whatis">ビルドツールとは</a>
- ビルド(build)≒コンパイル(compile)。コンパイルとは限らないが「何かを生成するために用意したファイル群から、対象を生成すること」をビルドと呼ぶ。
- ビルドツールは、この作業を支援するためのツール。
  - ファイルが多数あると、（利用側は）どれをコンパイルしたら良いかわからない。
  - コンパイルに順序がある場合、（利用側は）どの順序でコンパイルしたら良いか分からない。
  - 作成者側も逐一手動でコンパイルするのはだるい。
- gradleはビルドツールの一種。[Gradle公式サイト](https://gradle.org)に書かれてるように、Javaに限らず多言語に対応している。

<hr>

## <a name="ready">Gradleの環境構築＆動作確認</a>
```
brew install gradle
rehash
gradle -v
# -> Gradle 3.2.1 と出力されたらok
```

<hr>

## <a name="tutorial1">Gradleチュートリアル1（jarファイル生成を自動化してみる）</a>
- ゴール
  - gradleでコンパイル済みjarファイルを作成し、動作確認する。
- 手順
  - Gradle Project の作成。
    - IntelliJ で新規プロジェクト作成。作成時に「Gradle」を選択。Additional Libraries にJavaが含まれていることを確認。Next.
    - GroupID/ArtifactId を GradleExample に。Next.
    - オプション設定
      - 「Create directories for empty content roots automatically」にチェック。
      - 「Create separate module per source set」にチェック。
      - 「Use default gradle wrapper (recommended)」にチェック。
      - 上記以外はチェックを外す。
      - Gradel JVM を java 1.8 に。
      - Next.
    - プロジェクト名
      - 特に変更不要であればそのまま。Finish.
  - 設定確認。
    - FileメニューからProject Structures...を選択。
      - src/main/javaをSourcesに「なっていることを確認」。
      - src/test/javaをTestsに「なっていることを確認」。
      - 上記のとおりならそのまま閉じる.
        - 上記通りになっていないなら、プロジェクト作成時のオプションチェックが間違っている可能性あり。プロジェクト作成からやり直そう。
  - 通常の動作確認。
    - src/main/javaにpackageを追加。package nameは「jp.ac.uryukyu.ie.tnal」形式で。
    - 作成したパッケージ内に「Example.java」を追加。
      - ファイル内に``System.out.println("hoge");``するだけのmainメソッドを追加。
    - Run で Example を選び、実行できることを確認。
      - 実行できない場合、プロジェクト作成時のオプションがおかしい可能性あり。
  - gradleで jar ファイル作成の設定。
    - build.gradleを開く。
      - sourceCompatibility を 1.8 に変更。
      - 最後尾に以下の記述を追加。atttributes内のパッケージ名は、自身のものに修正すること。
```
jar {
    manifest {
        attributes  "Main-Class": "jp.ac.uryukyu.ie.tnal.Example"
        attributes 'Implementation-Title': 'Gradle Quickstart', 'Implementation-Version': version
    }
}
```
  - gradleでjarファイル作成。
    - 方法その1: IntelliJからgradle実行。
      - Viewメニュー -> Tool Window -> Gradle を選択。
        - Tasks -> build -> jar をダブルクリック。
          - ログ画面でビルドが動き、最後に「:jar」と出力されたら成功。
            - 確認事項1: 左側のファイル一覧パネルにて、build->libsを開き、そこに「プロジェクト名-1.0-SNAPSHOT.jar」が生成されているはず。
            - 確認事項2: この jar ファイルは、Example.javaをコンパイルしたものになっている。（そうなるようにbuild.gradleで設定した）。このjarファイルを動かすには、
              - case 1: IntelliJからは、このjarファイルを Ctrl+クリック し、Runを選択することで実行できる。
              - case 2: ターミナルでこのjarファイルがある場所に移動しよう。~/IdeaProjects/ 以下にあるはず。
                - jarファイルがある場所に移動したら「java -jar ファイル名.jar」を実行してみよう。これで実行できるはず。
                - 他の人に「ソースは見せたくないが、実行させたい」場合にはこの jar ファイルを渡せば良い。
    - 方法その2: ターミナルからgradle実行。
      - ターミナルで、今回作成したプロジェクトのディレクトリに移動。
      - build/libsの下に既に jarファイル があるはずなので、それを削除。e.g., ``rm build/libs/*.jar``
      - ターミナルで、プロジェクトのトップディレクトリにいることを確認。lsすると、build.gradleがある状態。
        - ここで ``gradle jar`` を実行。すると、先程IntelliJでgralde実行したときと同じログが出力され、jarファイルが生成されるはず。BUILD SUCCESSFULと出力されたら成功。
          - ここで気づいてほしいメリットは次の通り。
            - 実際にソースファイルを書いたファイルは、src/main/java/package/以下にある。
            - どこにあるかは気にせず、``gradle jar`` と実行するだけで指定されたファイルを探し出し、jarファイルを生成してくれている。
        - build/libsに移動し、jarファイルが生成されてることを確認しよう。
        - 確認できたら、「java -jar ファイル名.jar」で動作確認。

<hr>

## <a name="tutorial2">Gradleチュートリアル2（ユニットテストを自動化してみる）</a>
- ゴール
  - gradleでユニットテストを実行する。（gradle自体の追加設定はなし）
- 手順
  - Example.javaに、次のメソッドを追加。（「a - b」にしているのは、意図的です）
```
    public static int add(int a, int b){
        return a - b;
    }
```
  - ユニットテストを追加。
    - addメソッドを記述してる行（どこでも良い）で、Ctrl+クリック。
    - Go to -> Test -> Create New Test... を選択。
    - 「Generate test methods for」から add(a:int, b:int):int にチェックを入れ、OK．
      - 自動で src/test/java/パッケージ名/ExampleTest.java が生成されるはず。
        - 「public void add() throws Exception」を次のように修正。
```
    @Test
    public void testAdd() {
        assertEquals(3, Example.add(1, 2));
    }
```
    - ユニットテストの実行。
      - case 1: IntelliJからgradleで実行。
        - Tasks -> testClasses をダブルクリック。
          - 分かりにくいが、ログ画面にはビルド結果のみが出力されている。ログ画面の下に「Tests Failed: 0 passed, 1 failed」のようなテスト結果が出力されているはず。
      - case 2: ターミナルからgradle実行。
        - ターミナルで、今回作成したプロジェクトのディレクトリに移動。lsすると、build.gradleがある状態。
        - ``gradle test``を実行。すると、自動でコンパイルされ、ユニットテストの実行結果が出力されるはず。
          - ここで気づいてほしいメリットは次の通り。
            - 実際にユニットテストを書いたファイルは、src/test/java/package/ 以下にある。
            - どこにあるかは気にせず、``gradle test`` と実行するだけでそのファイルを実行してくれている。

<hr>

## <a name="make">Makefile概要</a>
- これもビルドツールの一種。古いけど簡単なことならこれで十分。

## <a name="make-tutorial">Makefileチュートリアル</a>
- ゴール
  - makeでサンプルコードをビルドし、動作確認する。
  - Makefileの読み方（概要）を理解する。
- 手順
  - 前準備。
    - ターミナルを開き、適当な作業ディレクトリを用意。ディレクトリ名は prog2-temp でも何でも良い。
    - 作業ディレクトリに移動。
    - [make-sample](https://github.com/naltoma/make-sample) を開き、リポジトリURLをコピー。
    - ``git clone リポジトリURL`` を実行。これでクローンを用意できるはず。用意できたらmake-sampleに移動。
      - この時点で、lsするとMakefileを含むいくつかのファイルが見える状態になっているはず。前準備はこれで終了。
  - Makefileの利用。
    - ``make``を実行。
      - 特に問題なければ、次のような出力が流れてくるはず。
```
oct:tnal% make
gcc -c list.c
gcc -c main.c
gcc list.o main.o -o test
```
      - ソースファイルの中身も知らず、コンパイル手順も知らない状態だが、makeコマンドを実行するだけで自動的にビルドしてくれた。
      - 上記は、gccを用いてtestという名前の実行可能ファイルを生成している。（ことが出力から読み取れる）
    - ``./test``を実行。正常に実行できれば次のような出力が得られるはず。
```
oct:tnal% ./test
list[0] = 3
list[1] = 1
```
  - Makefileの読み方。
    - emacsでMakefileを開いてみよう。
      - Makefileの基本形。
```
ターゲット名: [ソース]
  [コマンド]
```
        - []付きのソースやコマンドは省略可能。ソースを省略した場合、常にコマンドが実行される。
        - コマンド前には必ず「タブ文字」が必要。
    - 「ターゲット名: ソース集合」が、1ビルド対象に関する書き出し。
      - 今回の例では、test, list.o, main.o, clean, newsie の5つのターゲット名が用意されている。これは5つのビルドを用意しているという意味。
        - 試しに``make newsie``を実行してみよう。
          - 後述する「Makefile読み方」を踏まえてMakefileを読むと分かるが、これは「ターゲットnewsieを探し、指定されてるコマンドcurlとgrepを実行」した結果。このように、コマンドにはターミナル上で実行できるあらゆるコマンドを列挙することができる。
          - このように、ビルドツールは必ずしもコンパイルするとは限らない。実際、makeコマンドのドキュメントを``man make``でで調べてみると次のように記述されている。「maintain groups of programs」。プログラム集合を管理するのがmakeコマンドであり、この一例がビルドや、前述のnewsieである。
            - 「make - GNU make utility to maintain groups of programs」
    - 単に「make」と実行すると、Makefileを1行目から参照してはじめに見つかったターゲットを実行しようとする。
      - 今回の例では test を実行しようとする。
    - testのソース集合には「list.o main.o」と記述されている。これは、ターゲットtestを実行するにはlist.oとmain.oが必要であることを指定している。それらが既に存在しているならば、次の行から始まるコマンドを実行する。
      - 今回の例では、list.oとmain.oはどちらも最初は存在していない。この場合、list.oとmain.oを何とかして用意する必要がある。その用意方法がターゲットlist.o（4行目）と、ターゲット名main.o（7行目）として記述されている。
        - 4行目のターゲットlist.oを参照すると、ソースとしてlist.cが指定されている。これは存在するので、次の行に書かれているコマンド「**gcc -c list.c**」を実行する。これでlist.oを生成できた。
        - 7行目のターゲットmain.oについても同様。「**gcc -c main.c**」を実行する。
        - 本来のターゲットtestの実行に必要なソース「list.o main.o」を用意できたので、次の行に書かれているコマンド「**gcc list.o main.o -o test**」を実行する。これでターゲットtestに関する動作を終了する。
        - makeコマンドだけで、ここまでのgcc3回実行をまとめてやってくれた。
  - makeコマンドの特徴（大抵のビルドツールに共通）
    - ビルド対象とソースファイルのタイムスタンプを比較し、ソースファイルが古ければ（＝ソースが更新されていないならば）、ビルドを省略する。
      - 大規模なソースだと全体のビルドに数十分かかることも珍しくない。そのままだとソースを微修正して動作確認するだけでも毎回同じだけの時間がかかってしまい、開発が辛い。
      - これに対し、1度コンパイルした後は更新部分だけを再コンパイルすることでビルド時間を短縮できる。これを支援してるのがビルドツール。
      - 確認のため、もう一度``make``を実行してみよう。今度はgccが実行されずに、「make: `test' is up to date.」と出力されるはずだ。
    - **Makefileの要注意点**
      - コマンドを記述する欄は、必ず冒頭に「タブ文字」を加える必要がある。スペースでは駄目。
