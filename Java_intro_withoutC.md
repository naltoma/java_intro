# 初めてのJava

- 前置き：取り組み方に関する補足
  - 例題1〜4にはコード例が記載されている。
  - まずはそのままのコードを実行してみよう。
  - 実行できたら、一部編集してみてコンパイル＆実行してみよう。
    - e.g.,
      - セミコロンが無かったらどうなるだろうか？
      - インデントがずれてるとどうなるだろうか？
      - どういう時にどういうエラー・警告がでるのか、それとも問題ないのか、を確認しながら取り組んでみよう。この手間を惜しむと、ゼロからコードを書く際に「**コンパイラが出す警告・エラーの意味**」が分からず、コードをどのように修正すべきかを判断できないことになります。
  - Javaに限らず、新しい言語について学ぶ際にはいろいろいじって動作確認する癖を付けよう。
- ＜目次＞
  - <a href="#goal">達成目標</a>
  - <a href="#compiler_interpreter">コンパイラ言語の中でも変わった位置づけのJava言語（教科書1.1節〜）</a>
  - <a href="#static_dynamic_type">静的型付け言語と動的型付け言語</a>
  - <a href="#ex1">例題1: 四則演算結果を標準出力してみよう。</a>
    - Javaコード例の動かし方
    - コード比較を通したJava言語の特徴
    - マニュアルの使い方
  - <a href="#ex2">例題2: シーケンス集合を利用してみよう。（配列とfor文、if文）</a>
    - 配列[]の生成
    - for文の書き方
    - 拡張for文の書き方
    - 条件文の書き方とboolean型
  - <a href="#ex3">例題3: 関数（メソッド）を作成してみよう。（教科書12章）</a>
    - 引数の型
    - 戻り値の型
    - charとStringの違い
  - <a href="#ex4">例題4: 構造体（クラス）を利用してみよう。（教科書13〜14章）</a>
    - フィールド変数の宣言
    - コンストラクタの定義
    - thisキーワード
    - new演算子によるコンストラクタ呼び出し（オブジェクト生成）
  - <a href="#ref">参考サイト</a>

<hr>

## <a name="goal">達成目標</a>
- コード例をコンパイルし、実行できる。
- コード例を編集し、利用できる。
- 静的型付け言語であることを理解して、コードを記述できる。
- 基本構文（if, for, while）とメソッド（≒関数）を利用した、短いプログラムを作成できる。
- 配列を利用することができる。
- クラスを利用することができる。
- リファレンスを参照することができる。

<hr>

## <a name="compiler_interpreter">コンパイラ言語の中でも変わった位置づけのJava言語（教科書1.1節〜）</a>
- コンパイラ
  - プログラミング言語で記述されたプログラムを、機械語（や中間言語）に変換するプログラムのこと。
  - C言語: **「コード作成（編集）」->「コンパイラで機械語に翻訳」->「生成された実行可能ファイルを実行」** という手順を繰り返して開発作業を行う。
  - Java: **「コード作成（編集）」->「コンパイラで<span style="color:red">バイトコード(classファイル)と呼ばれる中間言語</span>に翻訳」->「生成されたバイトコードを<span style="color:red">Java仮想マシン(JVM, Java Virtual Machine)</span>で実行」** という手順を繰り返して開発作業を行う。
    - javac (Java compiler)
      - Javaで記述されたソースコードを[バイトコード](https://en.wikipedia.org/wiki/Java_bytecode)と呼ばれる中間言語に変換。
        - Cコンパイラのように、直接機械語へは翻訳しない。異なる環境でも動く共通フォーマットに従ってバイトコードを生成する。
    - java (java application launcher)
      - javacで生成されたバイトコードを読み込み、機械語に翻訳しながら実行する。
    - "Write once, run anywhere"
      - 一般的なコンパイラ言語（Cとか）だと、環境毎にコンパイルし直さないと動作しない。Javaは生成したバイトコードがあれば、（実行環境があるなら）どこでも動く。
- Javaの種類
  - Java SE (Standard Edition): 基本的なアプリケーション作成用　＊授業で触るのはこっち。
  - Java EE (Enterprise Edition): ウェブアプリケーション等
  - Java ME (Micro Edition): 組み込み等
- JDKとIDEが必要
  - 教科書には「IDEが必要」と書いてるが、実際には不要。IDEがあると便利、ぐらい。どう便利なのかは後日[IntelliJ](https://www.jetbrains.com/idea/)で演習します。

<hr>

## <a name="static_dynamic_type">静的型付け言語と動的型付け言語</a>
- 参考サイト
  - [静的型付き言語プログラマから見た動的型付き言語](http://d.hatena.ne.jp/kazu-yamamoto/20130308/1362724125)
- 動的型付け言語（e.g., Python）
  - 変数の型は実行時に確定する。（動的に型が決定される）
    - メリット: インタプリタのメリットとほぼ同じ？
    - デメリット: 実行するまで型を確定できない（テスト量が増えることが多い）。実行時に型推論をするコストがかかる（から遅くなることが多い）。
- 静的型付け言語（e.g., **Java, C言語**）
  - 予め型を指定しておく。（品質を保証しやすい）

<hr>

### <a name="ex1">例題1: 四則演算結果を標準出力してみよう。</a>
- Pythonでの例: ex1.py

```python
# a+bの結果を標準出力するコード。
a = 1
b = 2
result = a + b
print('{0} + {1} = {2}'.format(a,b,result))
```

- Javaコード例: [ExampleAdd.java](./ExampleAdd.java)

```java
/* a+bの結果を標準出力するコード。 */
package jp.ac.uryukyu.ie.tnal; //「tnal」は自身の学科アカウントに修正すること。

public class ExampleAdd {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int result = a + b;
        System.out.println(a+"+"+b+"="+result);
    }
}
```

- **Javaコード例の動かし方**
  - step 1: ソースコードの準備。
    - 作業用ディレクトリを用意。
      - prog2/week1とか、自分がわかりやすいディレクトリを用意しよう。
    - ExampleAdd.javaというファイル名で上記コードを保存。
      - **注意点**: 「mainメソッドを含むクラス名」と「ソースファイル名」は同一である必要がある。
      - 違いを確認するために手打ち推奨。
  - step 2: コンパイル。
    - ターミナル上で ``javac -d . ExampleAdd.java``と実行。エラーが無ければ「ExampleAdd.class」という名前のバイトコードが生成される。
      - バイトコードが生成される場所は、パッケージ名をディレクトリ名に変換した先。
      - 例えば、パッケージ名＝jp.ac.uryukyu.ie.tnalの場合、「./jp/ac/uryukyu/ie/tnal/」の下に「ExampleAdd.class」が生成される。
      - findコマンドで確認してみよう。（ターミナルで ``find .`` を実行）
    - コンパイル時にエラーが出たら、そのエラーが無くなるまで修正する必要あり。
      - PythonではSyntaxErrorがある場合でもその直前までは実行できる。
      - Java等のコンパイラ言語では、コンパイルで失敗する（＝文法エラー）と、実行そのものができない。
    - 「-d .」は、指定した場所（.=カレント・ディレクトリ）を基準にしてバイトコードを生成するオプション。
  - step 3: 実行。
    - ターミナル上で ``java パッケージ名.ExampleAdd`` と実行。
      - 私の場合は「java jp.ac.uryukyu.ie.tnal.ExampleAdd」になる。
        - 長ったらしいが、 **tabキーによる入力補完** を使えば楽。
    - java (JVM) が読み込むファイルは「ExampleAdd.class」等のバイトコードだが、実行時にはmain関数を含むクラス名（ここではExampleAdd）だけを指定することになる点に注意。

- **コード比較: Python vs Java**
  - ``/* a+bの結果を標準出力するコード。 */``
    - コメント文。複数行に跨って良い。
    - 1行コメントなら ``//``をコメント冒頭に付与する形式も可能。
  - ``package jp.ac.uryukyu.ie.tnal;``
    - パッケージ名の指定。
      - 「パッケージ」とはソースコードをまとめる単位の一つ。
      - よく用いられるパッケージ名は「メールアドレスを逆順にする」。これで全開発者がユニークなパッケージ名となるようにしている。（@マークとハイフン(-)は削除すること。）
      - パッケージ指定は省略可能だが、現場ではパッケージ名を指定することが通例なので今のうちに慣れておこう。
    - 最後に **セミコロン(;)** がある。
      - Javaでは、原則として文末にセミコロンが必要。例外はブロック{〜}など少数。
  - ``public class ExampleAdd {〜〜〜}``
    - Javaでは「クラス」という単位でプログラムを作成する（ので、コードには1つ以上のクラスが含まれる必要がある）。クラスの詳細は後日解説。
    - この例でのクラス名はファイル名と同じにしているが、必ずしも同一である必要はない。ただし、main関数を含むクラスの名前と、ファイル名は一致させるようにしよう。
    - **クラス名の命名規則は「英単語の組み合わせ＋各単語の1文字目を大文字に」。アンダースコア _ は使わない。**
  - ``public static void main(String[] args) {〜〜〜}``
    - Javaでは関数とは呼ばずにメソッドと呼ぶ。
      - **mainメソッド** は、Pythonでいうところの「[if __name__ == '__main__':](https://github.com/naltoma/python_intro/blob/master/Others.md#__name__)」に相当。
      - 実行するコードは、mainメソッド内に書く必要がある。main()メソッドの外にあるコードは、main()メソッドから呼ばれた時だけ実行される。（main()メソッドからしか実行されない）
    - **メソッドは戻り値の型を指定する必要がある。**
      - Java言語では、main()メソッドの戻り値は **void型**。
        - void型は、戻り値がない時に使う型。
    - **ブロックは ``{}`` （brace; ブレース)で囲う。**
      - Pythonではインデントでブロックを表現したが、Javaでは明示的に{でブロック開始を、}でブロック終了を記述する。
    - ``int a = 1;``
      - **変数を利用する前に型を指定して宣言** する必要がある。
      - 上記の文は「宣言」した上で「初期化」している。これを2つに分解して記述することも可能。
        - ``int a; a = 1;``
        - Java言語は、インデントを比較的自由に記述できる。
          - 上記コード例は「2文」を「1行」で書いた例。
          - インデントがばらばらなコードは「可読性が低い」ため、原則として禁止。適切にインデントを入れよう。（インデントが書かれていないだけで大幅減点する先生もいます。人間が読みやすいように作られている言語だから、可読性を意識して書こう）
      - **文の終わりには ``;`` (semi-colon; セミコロン)** を書く必要がある。
  - ``System.out.println(a+"+"+b+"="+result);``
    - 標準出力に書き出すためのメソッド。print()メソッドだと改行なし。println()メソッドだと改行あり。
      - println("hoge") のように、基本的には文字列をダブルクォーテーションで囲う。
      - 変数を出力させたい場合、変数名をそのまま記述すると文字列変換した上で出力してくれる。（e.g., println(a)）
        - Pythonだとstr型変換する必要があったが、Javaでは原則不要。
    - **+演算子は、両変数が数値ならば数値演算をするが、どちらか片方が文字列ならば文字列結合になる。**
      - Pythonだと型が不揃いの場合にはTypeErrorになるが、Javaでは文字列結合として解釈してくれる。
      - 演算子が複数並んでる場合、左から順に処理していく。
        - 上記の例では、変数a（int型）と"+"（String型）を+演算子で並べているので、文字列結合になる。
        - その後は「文字列+b」で文字列結合に。
        - 「文字列+"="」で文字列結合に。
        - 「文字列+result」で文字列結合に。
- **マニュアルの使い方**
  - オンラインAPI: [JDK 10 Documentation](https://docs.oracle.com/javase/10/index.html)
  - System.out.println()メソッドについて調べてみよう。
    - 左メニューの「API Documentation」を選択。
    - 試しに、右上の検索窓に「system」を入力。
      - systemを含む文字列が大量に列挙される。
    - この中から該当メソッドについて探すのは難しそうなので、続けて「system.out」まで入力してみよう。
      - 「java.out.System.out」だけが残るので、このページを選択してみよう。
      - この時点で [public static final PrintStream out](http://docs.oracle.com/javase/9/docs/api/java/lang/System.html#out) のページが参照できるはず。
    - 調べているものは「System.out.println()」だが、見つかったのは「public static final PrintStream out」とかいう謎のページだ。だが、このページ内には「System.out.println(data)」とも書かれている。
      - この良くわからない状況を俯瞰するため、このページのトップに遡ってみよう。
    - **Class System（Systemクラス）**
      - クラス説明ページ冒頭には概要文、 **Field Summary（フィールド変数の概要）、Method Summary（メソッドの概要）** が並んでいる。ここで全体像を把握しよう。
        - 同ページ後半には、 **Field Detail、Method Detail** といった詳細が掲載されている。
      - 今みたいのは「System.out.println()」で、このoutは上記によるとフィールド変数らしい。outフィールドをクリック。
    - Systemクラスのoutフィールド。
      - ここにSystem.outの概要と、outフィールドが持つ関連メソッドが列挙されている。
      - 今回の例だと、println()の中身は文字列（String型）となるため、「PrintStream.println(java.lang.String)」をクリック。
    - PrintStream.println(java.lang.String)
      - 正しく参照できているなら「[public void println​(String x)のページ](https://docs.oracle.com/javase/10/docs/api/java/io/PrintStream.html#println(java.lang.String))」が参照できているはず。
      - これがSystem.out.println()の具体的な説明。
  - **Javaのドキュメントは、一つの機能に関する説明が様々なページに散らばっている。これを探して読み取れるようになろう。**

<hr>

### <a name="ex2">例題2: シーケンス集合を利用してみよう。（配列とfor文、if文）</a>
- Pythonでは並びのあるデータ集合を扱う手段として list, tuple等を利用した。
- **Java言語では、配列(array)とリスト(List/ArrayList等)** が用意されているが、今回は配列を使うことにしよう。
  - 配列とリストは「並びのあるデータ集合を扱う手段」という点で共通しているが、提供されている機能には大きな差がある。
    - リストは、「後からデータを追加」「後からデータを削除（して配置し直す）」といった操作ができるデータ構造。
    - 配列は、「並びのあるデータ集合を扱う手段」として最も原始的な道具。予め要素数を指定する必要があったり、「後からデータを追加 (Pythonでいうlist.append(object))」することができなかったりと、いろいろ不便。
      - 使いづらいが、配列はリストと比べて省メモリで動作が高速。（興味のある人は実験してみよう）

- Pythonでのコード例: ex2.py

```python
# 複数のint型要素を用意して、中身が偶数だった時は数値を出力し、奇数だった時は「奇数」と出力するプログラム。
data = [1, 2, 3]
for i in data:
    if i % 2 == 0:
        print(i)
    else:
        print('奇数')
```

- Java言語でのコード例: [CheckOdd.java](./CheckOdd.java)

```java
/* 複数のint型要素を用意して、中身が偶数だった時は数値を出力し、奇数だった時は「奇数」と出力するプログラム。 */
package jp.ac.uryukyu.ie.tnal;

public class CheckOdd {
    public static void main(String[] args) {
        int[] data = {1, 2, 3};
        /*
        int i;
        for(i=0; i<data.length; i++){
            if( data[i] % 2 == 0 ){
                System.out.println(data[i]);
            }else{
                System.out.println("奇数");
            }
        }
        */
        for(int i : data){
            if( i % 2 == 0 ){
                System.out.println(i);
            }else{
                System.out.println("奇数");
            }
        }
    }
}
```


- **コード比較: Python vs Java**
  - ``int[] data = {1, 2, 3};``
    - **配列を作成(用意)する際には、変数宣言時に[]を付けて配列であることを明示** する必要がある。
      - 今回の例では「int型の配列」ということを「int[]」として記述している。
      - 一度宣言した配列の要素数を変更する（追加する）ことはできない。要素数を可変にしたい場合にはリストを使おう。（今は不要なので配列を利用する）
    - 配列内の要素を参照するには ``data[0]`` のように四角カッコでインデックスを指定して参照する必要がある。（ここはPythonのリストと同じ）
      - インデックスは0から始まる。
      - Pythonと異なり、``data[-1]`` で一番最後の要素を参照することはできない。（**後ろからi番目の要素、という指定はNG**）
  - ``for(i=0; i<data.length; i++){〜〜〜}``
    - ループ文の一つ、for文の例。
      - コード例ではコメントアウトされているため実際には処理されないが、基本的なループ文の書き方説明のためここでは解説。
    - for()のカッコ内には、(1)ループブロックを実行し始める際の初期化、(2)継続条件、(3)ループブロックを一度処理し終えた際の処理、の3点を記述する必要がある。
      - ``data.length``は、全ての配列が所有している変数で、要素数が保存されている。data.lengthの範囲外のアクセスすると、エラー。（どんなエラーが出るだろうか？）
      - ``i++``は「i += 1」の意味。
      - 上記3点は省略することも可能。
        - 例えば ``for(;;){〜〜〜}`` と書くと「初期化無し、継続条件なし(=常に継続)、次ループ処理なし」でループブロックを実行する。ブロック内でbreakに達しない限り、永遠と反復処理し続けることになる。
        - **break; continue;** はpython同様に利用できる。
    - ``if (data[i] % 2 == 0){〜〜〜}``
      - ``data[i]`` は、配列内のデータへアクセスする例。（Pythonのリストへのアクセスとほぼ同じ）
      - if文の例。
      - **条件式は必ず丸括弧で囲う** 必要がある。
      - 条件式判定結果はboolean型で返ってくる。「**真ならばtrue**」、「**偽ならばfalse**」となる。全て小文字である点に注意。
        - 複数条件の論理和を指定するなら ``条件式1 || 条件式2`` のように``||``を使う。（orは記述できない）
        - 複数条件の論理積を指定するなら``条件式1 && 条件式2`` のように``&&``を使う。（andは記述できない）
    - ``if(条件式){〜〜〜}else{〜〜〜}``
      - else文の例。
      - else時にif文を記述したい場合には``if(条件式){〜〜〜}else if{〜〜〜}``とする。（**elifではない**）
  - ``for(int i : data){〜〜〜}``
    - 同じfor文だが、カッコ内の記述が異なる例。
      - Javaでは「拡張for文」と呼ばれており、元々は存在しなかった記述。他言語の便利な仕様を取り入れた例。
    - 配列の要素に対する処理はするが、特にインデックスを利用する必要がない場合の書き方。どちらかというとpythonのfor文（for i in data:）に近い書き方。

<hr>

### <a name="ex3">例題3: 関数（メソッド）を作成してみよう。（教科書12章）</a>
- Pythonでは、関数作成のためには「def 関数名(引数):」から開始し、ブロックの中でその処理を記述する。
- Java言語のメソッドでは、そのメソッドを呼び出すスコープ範囲内にメソッドを記述する。

- Pythonでのコード例: ex3.py

```python
# 100点満点採点した学生の成績をチェックし、A~F判定を返す関数。
def eval(score):
    answer = 'F'
    if score >= 90:
        answer = 'A'
    elif score >= 80:
        answer = 'B'
    elif score >= 70:
        answer = 'C'
    elif score >= 60:
        answer = 'D'
    else:
        answer = 'F'
    return answer

scores = [100, 70, 50]
for score in scores:
    answer = eval(score)
    print('{0} -> {1}'.format(score,answer))

```

- Java言語でのコード例: [EvalScore.java](./EvalScore.java)

```java
/* 100点満点採点した学生の成績をチェックし、A~F判定を返す関数。 */
package jp.ac.uryukyu.ie.tnal;

public class EvalScore {
    public static void main(String[] args) {
        int[] scores = {100, 70, 50};
        int i;
        char answer;
        for(i=0; i<scores.length; i++){
            answer = eval(scores[i]);
            System.out.println(scores[i] + " -> " + answer);
        }
    }

    public static char eval(int score) {
        char answer = 'F';
        if( score >= 90 ){
            answer = 'A';
        }else if( score >= 80 ){
            answer = 'B';
        }else if( score >= 70 ){
            answer = 'C';
        }else if( score >= 60 ){
            answer = 'D';
        }else{
            answer = 'F';
        }
        return answer;
    }
}
```

- **コード比較: Python vs Java**
  - mainメソッドから呼び出す **独自メソッドを記述する場合、同じクラス内で定義** する必要がある。
    - 上記の例では、main()メソッドはEvalScoreクラスに記述されているので、同じEvalScoreクラス内に新しいメソッドeval()を記述している。
    - 同じスコープ内であればどこに記述しても良い（例えばmainメソッドの前に自作メソッドを記述しても良い）が、どちらかに統一して記述したほうが可読性的に良い。
  - **メソッドを記述する際には「引数の型、戻り値の型」を明示** する必要がある。
    - 上記の例では、evalメソッドの引数は「int型の変数score」であり、戻り値は「char型」として定義している。
  - ``char answer='F';``
    - **文字（1文字）と文字列は型が異なる。**
      - 1文字だけに限定したいなら **char**。
      - 文字列にしたいなら **String**。
        - **文字の配列 char []** は、あくまでも1文字を順序付けて並べているだけで、文字列ではない。
          - 文字の配列``char[] data1 = "hoge";``と文字列``String data2 = "hoge";``は同一だろうか？異なるだろうか？（確かめてみよう）
      - **1文字を指定するならシングルクォーテーション``'〜'``で囲う**。
        - 例えば、改行文字``'\n'``も、1文字扱いだとシングルクォーテーションで囲う必要がある。
      - **文字列を指定するならダブルクォーテーション``"〜〜"``で囲う**。
        - 例えば、「hello」という5文字を変数の保存したい場合、``"hello"``と記述する必要がある。シングルクォーテーションではNG。

<hr>

### <a name="ex4">例題4: クラスを利用してみよう。（教科書13〜14章）</a>
- int, char等の予め用意されている型とは別の、「新しい独自の型」を定義してみよう。
  - Java言語では「クラス」として表現する。
    - 実際には、クラス機能を利用して記述しているというのが正しい。クラスの詳細は後日解説。

- Pythonでのコード例: ex4.py

```python
# 「アカウント名、点数、判定結果」を1まとめにして扱う例。
dataset = [['e175701', 100, 'A'], ['e175702', 70, 'C'], ['e175703', 50, 'F']]

for data in dataset:
    account = data[0]
    score = data[1]
    eval = data[2]
    print('{0}さんは{1}点だったので、判定結果は{2}です！'.format(account,score,eval))
```

- Java言語でのコード例
  - 2つのファイルに分けて記述
    - 新しい型（クラス）を定義したソース：[StudentScore.java](./StudentScore.java)
    - 上記クラスを使用する、main関数を含むソース: [OriginalClass.java](./OriginalClass.java)
  - コンパイル方法
    - step 1: ``javac -d . StudentScore.java``（この時点でエラーが出たら、デバッグしよう）
    - step 2: ``javac -d . OriginalClass.java``
  - 実行方法
    - 実行したいmainメソッドを記述しているクラスを指定する。
    - 當間の例: ``java jp.ac.uryukyu.ie.tnal.OriginalClass``

```java
// 新しい型（クラス）を定義したソース：StudentScore.java
package jp.ac.uryukyu.ie.tnal;

public class StudentScore {
    String account; //フィールド変数1
    int score; //フィールド変数2
    char eval; //フィールド変数3

    /* コンストラクタ
     　コンストラクタは、
     　クラス（設計図）からインスタンス（オブジェクト）を作成するためのもの。
     　主にフィールド変数初期化のために使用
    */
    public StudentScore(String _account, int score, char eval){
        account = _account;
        this.score = score;
        this.eval = eval;
    }
}

```
```
// 上記クラスを使用する、main関数を含むソース: OriginalClass.java
/* 「アカウント名、点数、判定結果」を1まとめにして扱う例。 */
package jp.ac.uryukyu.ie.tnal;

public class OriginalClass {
    public static void main(String[] args) {
        String account;
        int score;
        char eval;
        StudentScore[] scores = new StudentScore[3];
        scores[0] = new StudentScore("e175701", 100, 'A');
        scores[1] = new StudentScore("e175702", 70, 'C');
        scores[2] = new StudentScore("e175703", 50, 'F');

        int i;
        for(i=0; i<scores.length; i++){
            account = scores[i].account;
            score = scores[i].score;
            eval = scores[i].eval;
            System.out.println(account + "さんは" + score + "点だったので、判定結果は" + eval + "です！");
        }
    }
}

```


- **コード比較: Python vs Java**
  - StudentScore.java
    - ``public class StudentScore {〜〜〜}``
      - 新しいpublicクラスを定義する場合、そのクラスをファイル名として記述する必要がある。
        - **1つのファイルに定義できるpublicクラスは1つのみ。**
        - 今回の例では「新しい型」を定義するためのpublicクラス「StudentScore」と、そのクラスを利用するmainメソッドを含むクラス「OriginalClass」の2つを別々に用意した。
          - StudentScoreクラスにmainメソッドを含める書き方可能。今回は「独自の型を作る」に焦点を当てているため、別クラスとして用意した。
    - ``フィールド変数``の宣言。
      - クラスが保持する変数を「**フィールド変数**」と呼ぶ。
      - 今回の例では、account, score, eval の3変数を持つクラスとして定義している。
    - ``コンストラクタ``の定義。
      - **コンストラクタ** は、フィールド変数を初期化し、実体のある（メモリ上に展開された）オブジェクトとして使用するための記述。
      - メソッドに似ているが、**return文は書けない（戻り値の型も、宣言してはいけない）**。
      - **コンストラクタは、必ずそのクラス名と同一の名前で記述** する必要がある。
      - **複数のコンストラクタ** を記述できる。（詳細は後日）
    - ``this.score = score;``
      - 右辺のscoreは、コンストラクタ呼び出し時の引数。
      - 左辺の **this.scoreは「このオブジェクトのフィールド変数score」** を指す。
  - OriginalClass.java
    - mainメソッドを記述しているクラス。
      - mainメソッドを記述するクラスは、publicクラスである必要がある。
    - ``StudentScore[] scores = new StudentScore[3];``
      - StudentScore.javaで定義した新しいクラス「StudentScore」を用いて、変数 scores を宣言。
      - この時点では、StudentScoreのオブジェクトを3つ分確保する配列を用意しているだけ。（コンストラクタは呼び出されていないので、オブジェクトそのものはまだ用意されていない）
    - ``scores[0] = new StudentScore("e175701", 100, 'A');``
      - **「new クラス名()」で、そのクラスに記述されたコンストラクタが呼び出され、オブジェクトを生成** する。生成されたオブジェクトが左辺に代入される。
        - コンストラクタ呼び出し時には丸括弧()である点に注意。配列を意味する四角括弧[]と混同しないこと。
      - Stringとcharとでクォーテーションが異なる点に注意。

<hr>

## <a name="ref">参考サイト</a>
- 公式ドキュメント
  - APIドキュメント: [Java® Platform, Standard Edition 9
API Specification](http://docs.oracle.com/javase/9/docs/api/overview-summary.html)
  - ドキュメント(言語仕様等): [Oracle JDK 9 Documentation](http://docs.oracle.com/javase/9/index.html)
    - ダウンロードも可能。チュートリアルやサンプルコードも多数。
- Java動画学習
  - [Progate: Java I, II](https://prog-8.com/languages/java)（初めてプログラミングする人向けの解説＋簡易問題のあるサイト。webサイト上で実行もできるので、一歩ずつ確認していきたい人には向いてます。）
  - [paiza動画学習: Java入門編](https://paiza.jp/works/java/primer)（初めてプログラミングする人向けの動画講義。1つのコンテンツは数分程度に細分化されてて、かつ、コードを自動採点するシステムが提供されています。独習の進め方としては「数分動画閲覧して、自動採点される課題に取り組む」を繰り返すことになります。）
  - [ドットインストール: Java 8入門 (全43回)](http://dotinstall.com/lessons/basic_java_v2)（paiza同様、1つのコンテンツが数分程度の動画に細分化されて提供されてます。）
- 課題例
  - [CodingBat](http://codingbat.com/)（課題が多数例示されています。サイト上でコードの動作確認をすることも可能。）
  - [言語処理100本ノック 2015](http://www.cl.ecei.tohoku.ac.jp/nlp100/)（特定分野に偏っていますが、課題が難易度別に提供されてます。UNIX思想も含まれてます。）
