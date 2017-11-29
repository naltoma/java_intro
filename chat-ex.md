# シンプルなチャットサーバ＆クライアントのコード例
- github: [chat-ex](https://github.com/naltoma/chat-ex)
  - 理解できなくても大丈夫です。
    - 2年次の必修「情報ネットワーク1、オペレーティングシステム」あたりの知識が必要。
    - **今回は「以下のようなことが必要っぽい」ぐらいの理解で十分** です。後日、通信話が出た時に振り返ってコードを読み解いてみよう。
      - 興味がある人は、[Creating a simple Chat Client/Server Solution](http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/Virtual/Lectures/chat-client-server.html)を読んでみよう。level1から少しずつ機能追加する形でチャットアプリを作成しています。
    - コード概要
      - 「サーバ・クライアント型の通信アプリ」を作成するには、IPアドレスとポート番号が必要。
        - サーバー側: ServerSocket(port) -> server.accept()
        - クライアント側: Socket(IP, port)
      - 様々な入出力（下記）を処理するために「入出力ストリーム」を設定＆運用する。
        - サーバー側で、クライアントからの読み込み（streamInに対応）
        - クライアント側で、ユーザからの入力読み込み（consoleに対応）
        - クライアント側で、サーバへの出力（streamOutに対応）

<hr>

## 使い方
- コンパイル
  - ターミナルでクローン作成。
    - 作業用ディレクトリ上で以下を実行。
      - ``git clone https://github.com/naltoma/chat-ex.git``
  - build.gradleがあるディレクトリに移動して、``gradle compileJava``
  - build/classes/java/mainの下にclassファイルが生成されるので、そこに移動。
- 実行方法
  - サーバ側
    - ``java ChatServer ポート番号``
      - 「ポート番号」は、例えば1025。
        - 1024までは一般的なアプリで使用されてる事が多いので、それを避けよう。
        - 参考: [wikipedia:ポート番号](https://ja.wikipedia.org/wiki/ポート番号)
  - クライアント側
    - ``java ChatClient サーバのIPアドレス ポート番号``
      - 「サーバのIPアドレス」は、ChatServer起動時に出力されるはず。
      - 「ポート番号」は、サーバ起動時に指定したポート番号を入力しよう。
    - サーバに接続した後は、「.bye」が入力されるまで自由にテキスト入力できる。

<hr>

## 実装概要
- まずは必要最小限の機能に。
  - サーバ側
    - ポート番号指定してServerSocket.accept()
    - クライアントの接続待ち。
    - 接続確立後、終了フラグ立つまで行単位で文字列読み込み。
    - 終了フラグ立ったら切断処理。
  - クライアント側
    - サーバのIP, portを指定してサーバに接続。
    - 接続確立後、終了フラグ立つまで行単位で文字列読み込み(stdin)し、サーバに送信。
    - 終了フラグ立ったら切断処理。
- 先送り機能
  - クライアント側への出力？
  - マルチクライアント対応（スレッド例）
  - 簡易GUI例？

<hr>

## 利用してるAPIへのリンク
- 入出力周り
  - [java.io.InputStreamReader](http://docs.oracle.com/javase/8/docs/api/javax/sound/sampled/AudioInputStream.html); //入力ストリーム
  - [java.io.BufferedReader](http://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html); //効率よく入力ストリームを処理するためのバッファ
  - [java.io.IOException](http://docs.oracle.com/javase/8/docs/api/java/io/IOException.html);
  - [java.io.PrintWriter](http://docs.oracle.com/javase/8/docs/api/java/io/PrintWriter.html); //サーバへの出力ストリームを処理するライブラリ
- サーバ、通信周り
  - [java.net.ServerSocket](http://docs.oracle.com/javase/8/docs/api/java/net/ServerSocket.html); //サーバ
  - [java.net.Socket](http://docs.oracle.com/javase/8/docs/api/java/net/Socket.html); //ソケット(通信を行う際の端点)
  - [java.net.InetAddress](http://docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html); //ローカルIP取得
  - [java.net.UnknownHostException](http://docs.oracle.com/javase/8/docs/api/java/net/UnknownHostException.html);

## 参考サイト
- [Creating a simple Chat Client/Server Solution](http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/Virtual/Lectures/chat-client-server.html)
- [演習3-1 サーバとクライアント](http://yoslab.net/netprog/index.php?%B1%E9%BD%AC3-1%20%A5%B5%A1%BC%A5%D0%A4%C8%A5%AF%A5%E9%A5%A4%A5%A2%A5%F3%A5%C8)
