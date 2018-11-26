# IntelliJからのJavaDoc利用
- ＜目次＞
  - <a href="#write">JavaDoc形式によるドキュメント記述</a>
  - <a href="#generate">IntelliJからのドキュメント生成＆閲覧</a>

<hr>

## <a name="write">JavaDoc形式によるドキュメント記述</a>
- ドキュメント例: [TicTacToe.java](https://github.com/naltoma/java_intro/blob/master/TicTacToe/TicTacToe.java)
  - クラスについて説明を書くなら「クラスの直前」に、指定書式で書く。
  - メソッドや変数についても各場所は同様に「直前」に書く。
- **クラス・フィールド変数・コンストラクタ・メソッドの前に、下記書式でコメントを書く。**
  - 必ず ``/**`` という行から書き始めること。javadoc はこの行を参考にドキュメントを生成する。（``//``で書いたコメントは無視される）
- **よく見る（使われる）アノテーション**
  - @author @version クラス・インタフェース専用
  - @param パラメータ
  - @return 戻り値
  - @exception 例外
  - @see 関連するクラス、メソッド等を列挙。もしくは外部ページ等の参照情報
  - @deprecated 非推奨（「近い将来削除等の理由で使えなくなる」ということを明示するためのオプション）
  - その他: [How to Write Doc Comments for the Javadoc Tool](https://www.oracle.com/technetwork/java/javase/documentation/index-137868.html#tag)

```
/**
* 1行目: 1行コメント。
* 2行目以降: 複数行コメント。（省略可）
* パラメータ・戻り値等については、以下のようにアノテーション付きで記述。
* @param a 足したい値
* @param b 足したい値
* @return 足した結果
*/
public int add(int a, int b){
  return a + b;
}
```

<hr>

## <a name="generate">IntelliJからのドキュメント生成＆閲覧</a>
- Step 1: JavaDoc形式で、ソースファイルにコメントを記述。
- Step 2: ドキュメント生成
  - case 1: IntelliJから生成。
    - Tools -> Generate JavaDoc... を選択。
    - 中段の「Output directory」を設定するために、右端のアイコンをクリック。
      - 生成したドキュメントを保存するためのディレクトリを用意。今回は、IntelliJプロジェクト内に docs ディレクトリを用意するとしよう。
      - IntelliJプロジェクトを開く。
      - サブウィンドウ内の左下「New Folder」を選び、プロジェクト内に docs ディレクトリを用意。
      - 作成したディレクトリを選んだ状態で、右下「Open」をクリック。
    - 右下「OK」をクリック。
    - 特に問題なければ用意したディレクトリにドキュメントが生成され、ブラウザで自動的に開いてくれるはず。
  - case 2: ターミナルから生成。
    - プロジェクトのトップディレクトリに移動。
    - ``javadoc -charset "UTF-8" -private -d docs src/main/**/*.java``
      - オプションの意味
        - ``-charset "UTF-8"``: ドキュメントファイルの文字コードを指定。
        - ``-private``: 全てのクラスとクラスメンバをドキュメントに含める。（privateな指定も含める）
        - ``-d docs``: ドキュメント生成先を指定。
        - ``src/main/**/*.java``: ソースコードを指定。
