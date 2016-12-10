# ビルドツール gradle の利用。オマケでmakeも。
- [Gradle公式サイト](https://gradle.org)

## ビルドツールとは
- ビルド(build)≒コンパイル(compile)。コンパイルとは限らないが「何かを生成するために用意したファイル群から、対象を生成すること」をビルドと呼ぶ。
- ビルドツールは、この作業を支援するためのツール。
  - ファイルが多数あると、（利用側は）どれをコンパイルしたら良いかわからない。
  - コンパイルに順序がある場合、（利用側は）どの順序でコンパイルしたら良いか分からない。
  - 作成者側も逐一手動でコンパイルするのはだるい。
- gradleはビルドツールの一種。[Gradle公式サイト](https://gradle.org)に書かれてるように、Javaに限らず多言語に対応している。

<hr>

## Gradleの環境構築＆動作確認
```
brew install gradle
rehash
gradle -v
# -> Gradle 3.2.1 と出力されたらok
```

<hr>

## Gradleチュートリアル1（jarファイル生成を自動化してみる）
- ゴール
  - gradleでコンパイル済みjarファイルを作成し、動作確認する。
- 手順
  - Gradle Project の作成。
    - IntelliJ で新規プロジェクト作成。作成時に「Gradle」を選択。Additional Libraries にJavaが含まれていることを確認。Next.
    - GroupID/ArtifactId を ExamplePolymorphism に。Next.
    - オプション設定
      - +//「Create directories for empty content roots automatically」にチェック。
      - 「Create separate module per source set」にチェック。
      - 「Use default gradle wrapper (recommended)」にチェック。
      - 上記以外はチェックを外す。
      - Gradel JVM を java 1.8 に。
      - Next.
    - プロジェクト名
      - 特に問題なければそのまま。Finish.
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
    // from configurations.compile.collect { it.isDirectory() ? it : zipTree(it) }
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

## Gradleチュートリアル2（ユニットテストを自動化してみる）
- ゴール
  - gradleでコンパイル済みjarファイルを作成し、動作確認する。
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
