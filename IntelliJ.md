# IntelliJ IDEA
- 統合環境での開発に慣れよう。
  - 小さなプログラムならターミナルやテキストエディタだけで開発してても問題無いこともあるが、複雑なシステムになってくると辛くなる。
  - 実社会では統合環境の利用が当たり前。
    - 最大派閥は Eclipse だが、情報工学科なので新しい統合環境を使ってみよう。一つに慣れれば別環境へも応用が効きやすいし。
- Java言語向けの統合環境。
  - 公式サイト: [IntelliJ](https://www.jetbrains.com/idea/)
    - Community Edition をダウンロード。
    - ダウンロード終了したら、解答して「アプリケーション (Applications)」フォルダにドラッグ＆ドロップしてインストール。
    - IntelliJ起動
    - SDK (Software Development Kit)として「/Library/Java/JavaVirtualMachines/jdk1.8.0_66.jdk/Contents/Home」のように、jdk1.8が選択されることを確認。（デフォルトでそうなるはず）

<hr>

## ゼロからプロジェクトを作成する場合
- 「Create New Project」を選択。
- 「Java」を選択。
  - プロジェクト名を設定。
    - ここでは FirstClass にしよう。
    - どこにプロジェクトが保存されるのか確認すること。（ターミナルやFinderからでも辿れます）
- デフォルトで作成される空プロジェクトの初期状態
  - 左側のメニューがファイル操作パネル。
    - プロジェクト名の左にある▶をクリックするとプロジェクト内のフォルダ・ファイル一覧を参照できる。
      - 「.idea」は、IDEAアプリで使う管理用のフォルダ。
      - 「src」は、薄い水色になっているはず。ソース(source)を置く場所として設定されている。
      - 「.iml」拡張子imlは、Project Structureで設定した内容が保存されるらしい。
- Hello World と出力するだけのアプリを作成してみる。
  - 「src」を右クリック（Ctrl+クリック）して、New から Java Class を選択。
    - クラス名を「FirstClass」にしてOK。
    - FirstClass.javaのテンプレートが用意されるので、下記のようにmainメソッドを作成しよう。コピペではなく手入力すると分かるが、様々な段階で入力補完をしてくれるはず。

```
public class FirstClass {
    public static void main(String[] args){
        System.out.println("Hello World!");
    }
}
```

  - 作成し終えたら、Runメニューから「Run」を選択し、今作成したクラス FirstClass を選んで実行しよう。
    - この時点でエラーが出たらエラーが出なくなるまで修正。

<hr>

## jarファイルの構成
- JAR: Java Archiveの略で、コンパイルされたバイトコードやそれが必要とするリソースを一つにまとめ、ZIP形式で圧縮されたファイルをjarファイルと呼ぶ。
  - Javaアプリやライブラリの提供等に使う。
  - こちらが提供する場合だけではなく、「ソースは公開したくないが、第三者に使用させたい」場合に jarファイルで公開する選択肢を選ぶケースも多い。（ので、使えるようになろう）
- jarファイルの構成
  - Fileメニューから「Project Structure」を選択。
    - Project Settings 内の「Artifacts」を選択。
      - 中央パネルの上部にある「+」をクリック。
        - JARから「From modules with dependencies」を選択。
          - Main Class の右側にある「...」をクリックし、含めたいクラスを選択。ここでは FirstClass を選択。
          - OKを選択。
          - Applyをクリック。
          - OKを選択。（設定メニューが消える）
  - Buildメニューから「Build Artifact」を選択。
    - Action内の「build」を選択。
      - 特にエラーが出なければ jar ファイルが作成されているはず。
      - 保存される場所は「out->artifacts」

<hr>

## jarファイルの動作確認
- jarファイルの動作確認1（IDEAで実行）
  - プロジェクトパネルで「out->artifacts->FirstClass_jar->FirstClass.jar」と辿り、jarファイルを選択する。
  - jarファイルを右クリック（Ctrl+クリック）し、「Run 'FirstClass.jar'」を選択。
    - 正しく動作したら成功。
- jarファイルの動作確認2（ターミナルで実行）
  - ターミナルを開き、プロジェクトを保存したディレクトリに移動。デフォルのままなら「~/IdeaProjects/FirstClass/」があるはず。
    - jarファイルのあるディレクトリに移動。(out/artifacts/FirstClass_jar/)
    - jarファイルのあるディレクトリ上で ``java -jar FirstClass.jar`` と実行。オプションの指定を忘れないように。
    - 正しく動作したら成功。

<hr>

## 既存プロジェクトをダウンロードして利用する場合
### バージョン管理システム: gitの場合
- プロジェクトを閉じた状態で「Check out from Version Control」を選択。
  - バージョン管理システムを選択。ここではgit。
    - Git Repository URL に、[java_intro](https://github.com/naltoma/java_intro)のリポジトリURLを記入。
      - 上記githubページの「Clone or download」を選択。
        - 「Use Git or checkout with SVN using the web URL.」の下に記述されてるURLをコピー。
      - IntelliJの「Repository URL」にペースト。
      - 右側の「Test」をクリック。テストが通れば（successfulしたら）OK。
      - 「Clone」を選択。
      - IDEA projectとして展開するか聞いてくるので「Yes」。
      - デフォルトの「Create project from existing sources」のままで「Next」を選択。
      - Project name, location をデフォルトのままで「Next」を選択。
      - Project root に加えるか聞かれるので、選んだ状態のまま「Next」を選択。
      - Library を確認する画面になるので、そのまま（からの状態）のまま「Next」を選択。
      - Modules を確認する画面になるので、そのまま（java_introが選ばれた状態）のまま「Next」を選択。
      - SDKを選ぼう。
        - jdk1.8があるはずなので、それを選択して、「Next」を選択。
      - 「Finish」を選択。
- java_intro には複数のソースファイルが置かれている。
  - 例えば、report2にはreport2関連のサンプルソースがある。
    - この状態で「Run」メニューからSampleScannerを選択しても「java: クラスMainが重複してます」と怒られてしまい、実行できない。
    - 実行するには、実行したいソースファイルがあるディレクトリを「Sources」として設定する必要がある。
      - Fileメニューから「Project Structure」を選択。
        - Project Settings 内の「Modules」を選択。
          - 「report2」を選択し、「Sources」をクリック。すると、右側メニューの「Source Folders」にreport2が追加される。
          - java_introが青色になっている（source foldersに設定されている）ので、これを解除するために「java_intro の下、report2の上にあるx」をクリック。
            -　ちゃんと解除できたら、java_introが黄色になるはず。
            - 解除できたら Apply して Ok。
    - Sourcesを実行したいソースファイルを置いているフォルダだけに設定できたら、Runメニューから SampleScanner を選択。今なら実行できるはず。
