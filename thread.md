# Thread（スレッド）入門

- ＜目次＞
  - <a href="#intro">Threadとは</a>
  - <a href="#howto">Threadの実装方法、コード例</a>

<hr>

## <a name="intro">Threadとは</a>
- "A thread is a thread of execution in a program. **The Java Virtual Machine allows an application to have multiple threads of execution running concurrently**." by [java.lang.Thread](http://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html)
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
- 山田先生の資料: [スレッドを作る方法](https://ie.u-ryukyu.ac.jp/~koji/pw/index.php?cmd=read&page=Programming_II%2F08%2F第13回#ucea891e)
- コード例: [ThreadExample](https://github.com/naltoma/ThreadExample)
  - NormalCount.java: スレッド無しで、数をカウントする例。
  - ThreadCount.java: Threadの拡張クラスとしてrun()メソッドを実装し、start()メソッドを呼び出す例。
  - RunnableCount.java: Runnableインタフェースの実装としてrun()メソッドを用意し、Threadクラスのインスタンス生成してからstart()メソッドを呼び出す例。
