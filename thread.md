# Thread（スレッド）入門

- ＜目次＞
  - <a href="#intro">Threadとは</a>
  - <a href="#howto">Threadの実装方法、コード例</a>
  - <a href="#ex">実装演習</a> (チャットサーバのマルチクライアント対応)

<hr>

## <a name="intro">Threadとは</a>
- "A thread is a thread of execution in a program. **The Java Virtual Machine allows an application to have multiple threads of execution running concurrently**." by [java.lang.Thread](http://docs.oracle.com/javase/9/docs/api/java/lang/Thread.html)
- "スレッドとは、プログラムを実行している主体（例えて言うなら１人の人間）です。すべてのプログラムはスレッドによって実行されています。１つのスレッドは、ひとりの人間がプログラムを１行ずつ読んでプログラムの流れを追いかけるように、命令を１つずつ解釈し処理を実行していきます。（中略）Javaはマルチスレッドを取り扱うことのできる言語です。マルチスレッドとは複数のスレッドという意味です。つまりJavaでは複数の人が同時に仕事をするように、複数のスレッドを同時に実行することができるのです。" by [マルチスレッドプログラミング 1章　スレッド | TECHSCORE](http://www.techscore.com/tech/Java/JavaSE/Thread/1/)
  - ポイント: 同時に、複数のスレッド実行をアプリケーションに許可する。
- 問題背景
  - 例えばこのテキストを参照しているブラウザ（恐らくSafari）は、以下のような多数の操作を受け付けている。
    - 上下にスクロールすることでウィンドウ内に表示するテキストを調整できる。
    - ダブルクリックでテキスト等を選択できる。
    - トラックパッド操作や、「表示」メニューから拡大縮小できる。
    - Command+Fや「編集」メニューからページ内検索できる。
  - もし、ブラウザが「マルチスレッド」に対応していないのなら、、
    - 「ある瞬間では上下スクロール操作しか受け付けない」
    - 「ある瞬間では拡大縮小しか受け付けない」
  - 上記のような、単一処理しか受け付けないアプリになってしまう。これまでの授業で書いてきたプログラムは、基本的には単一処理しか受け付けないため、インタラクティブな操作（アプリとユーザが相互にやり取りする操作）を実装できなくはないが、不向きである。
  - 一方、そもそもコンピュータにはCPUが一つしかないので、同時に処理できる内容は単一にしか提供できない。
- 解決方法
  - 複数スレッドを同時に処理してるように「見せかける」ことはできる。
    - CPUは一つしか無いが、その速度は人間にとっては尋常じゃないぐらいに早い。（1GHzってどのぐらいの速さだろう？）
    - 一度に一つしか処理していないが、高速に処理できれば擬似的に同時実行してるように見えるよね？
      - その一例がThread。
      - 分かりやすいかもしれない別の説明例: [スレッドとは](http://www.javadrive.jp/applet/thread/index1.html)

<hr>

## <a name="howto">Threadの実装方法、コード例</a>
- 山田先生の資料: [スレッドを作る方法](https://ie.u-ryukyu.ac.jp/~koji/pw/index.php?cmd=read&page=Programming_II%2F08%2F第13回#ucea891e) ＊2016年度資料。
- 実行して観察してみる
  - コード例: [ThreadExample](https://github.com/naltoma/ThreadExample)
    - コード概要
      - NormalCount.java, ExecNormalCount.java: スレッド無しで、数をカウントする例。
      - ThreadCount.java, ExecThreadCount.java: Threadの拡張クラスとしてrun()メソッドを実装し、start()メソッドを呼び出す例。
      - RunnableCount.java, ExecRunnableCount.java: Runnableインタフェースの実装としてrun()メソッドを用意し、Threadクラスのインスタンス生成してからstart()メソッドを呼び出す例。
  - 実行方法

```
git clone https://github.com/naltoma/ThreadExample.git
cd ThreadExample
javac -d . **/*.java

# スレッドなしバージョンの実行
java jp.ac.uryukyu.ie.tnal.ExecNormalCount

# Thread版の実行
java jp.ac.uryukyu.ie.tnal.ExecThreadCount

# Runnable版の実行
java jp.ac.uryukyu.ie.tnal.ExecRunnableCount
```

<hr>

## <a name="ex">実装演習</a>
- コード例: [chat-ex](https://github.com/naltoma/chat-ex)
- Step 1: 実装の前に動作確認
  - [chat-ex](https://github.com/naltoma/chat-ex)のGit URLをコピー。
  - IntelliJ起動し、リポジトリのクローンを作成。
  - Gradle設定画面に移ったら、以下の設定をする。
    - 「Use Gradle wrapper task configuration」を選択。
    - 「Gradle JVM」を Java 9 に設定。
    - 「OK」をクリック。
      - 設定反映が始まるので暫く放置。
  - Runメニューから「Edit Configurations...」を選択。
    - 「Program Arguments」に「1025」と記載し、Apply, OK。
  - RunメニューからChatServerが動作する（クライアント受付状態になる）ことを確認しよう。
    - この時点では、単一クライアントからのみ接続を受け付ける状態になっている。
- Step 2: マルチクライアント対応にしてみよう。
  - 大まかな方針
    - クライアント自体はいじらない。
    - サーバ側を修正し、複数のクライアントアプリが同時に接続できるようにする。
      - サーバ側は「(1)クライアント接続待ちしているスレッド」と「(2)接続後にチャット処理（36行目〜54行目）をするスレッド」を用意する。
      - (1)のスレッドは、JVM起動時のスレッドでやる（特別なことはしない）。
      - (2)のスレッドをサーバプログラム内で用意してやろう。
  - Step 2-1: ChatServerThread.javaを作成。（スレッド実装）
    - (a) Threadを継承する。
    - (b) 取り敢えず「空のコンストラクタ」を作成。
    - (c) public void run()メソッドを作成。
      - ChatServer.javaの36行目〜54行目をコピーし、作成したrunメソッドにペースト。また、ChatServer側のコードはコメントアウトしておこう。
        - IOExceptionのために関連クラスをインポートするか尋ねられるので、OK。
      - この時点で赤字部分（socket, open(), streamIn, close()の4箇所）が未実装状態。Cannot resolve symbol（＝そんな名前の知らないよ）と言われているので、これらを用意しよう。
        - open(), close()のメソッド2つは、ChatServer.javaの60行目〜69行目に実装済み。これをカット＆ペーストして、ChatServerThread.javaにペーストしよう。
          - ペースト時に、メソッドで利用しているクラスBufferedReader, InputStreamReaderをインポートするか尋ねられるので、OK。
    - (d) 赤字 socket に対する処理。
      - 元のコード(ChatServer)では、34行目でsocketを設定している。これをChatServerThreadのインスタンス生成時に渡すようにしよう。
      - (d-1) ChatServerの34行目の後で、以下の2行を記述。
        - ``csThread = new ChatServerThread(socket);``
          - フィールド変数として「private ChatServerThread csThread = null;」を用意しておこう。
        - ``csThread.start();``
      - (d-2) ChatServerThreadにフィールド変数soketを用意。コンストラクタでsocketを受け取り、初期化。
        - ``private Socket socket = null; //フィールド変数``
          - java.net.Socketのインポートを忘れずに。
        - ``this.socket = socket; //コンストラクタ内``
    - (e) 赤字 streamIn に対する処理。
      - 元のコード(ChatServer)では、変数宣言しているだけに見える。そのため、ChatServerThreadでもフィールド変数として宣言しておこう。
    - (f) 赤い波線（open()とclose()）に対する処理。
      - 波線にカーソルを合わせると「Unhandled exception: java.io.IOException」と出る。IOExceptionに対する例外処理が記述されていないために怒られている状態らしい。
      - run()メソッドの中身全体を、try{}で囲い、``catch(IOException e){}``として必要最小限の例外処理もどきで対応しておこう。
  - Step 2-2: ChatServer.javaの修正。（スレッド実行）
    - 現状
      - ChatServer側からは、「server.accept()」でクライアント接続待ち状態になり、接続後にChatServerThreadのインスタンスを生成し、スレッド実行するようになっている。それ自体は問題ないが、スレッド実行（チャット処理）を終えたらサーバプログラムが終了してしまう。
    - 対応策
      - 「接続待ち〜チャット処理」を無限ループ実行するようにする。（1クライアントに対するスレッド実行を終えたら、新しい接続待機状態に移行するようにする）
    - (g) ループ処理の実装。
      - ChatServer.java、33行目付近の「//クライアント接続待ち」から、csThread.start()してる行までを while(true) で囲おう。
  - 動作確認
    - ChatServerを起動。(Run)
    - ターミナルを起動し、IntelliJで作成したプロジェクトに移動。（當間の例: ~/IdeaProjects/chat-ex/）
    - 中間ファイルがあるディレクトリに移動。
      - out/production/classes
    - クライアントを起動。
      - ``java ChatClient IPアドレス ポート番号``
      - IPアドレスとポート番号は、IntelliJで起動したサーバプログラムの出力を参照。
      - クライントからサーバプログラムへ接続したら、何か入力してみよう。それがサーバ側に伝わるならOK。
      - 接続したままの状態で、もう一つ新しくターミナルを開き、同様にクライアントを起動してみよう。同様に伝わるならOK。
