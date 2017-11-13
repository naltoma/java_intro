# 例外補足

- ＜目次＞
  - <a href="#abst">例外クラスの概要</a>
  - <a href="#FileWriter1">java.io.FileWriterクラスの例</a>
  - <a href="#FileWriter2">コード例</a>
  - <a href="#multi_exception">複数例外があるケース</a>
  - <a href="#finally">後片付け処理があるケース</a>
  - <a href="#exception_instance">例外インスタンスの例</a>

<hr>

## <a name="abst">例外クラスの概要</a>
- 標準APIで提供されてる例外クラスの継承ツリー（一部）
  - [Throwable](http://docs.oracle.com/javase/9/docs/api/java/lang/Throwable.html): 何らかの例外的状況
    - (サブクラス) [Error](http://docs.oracle.com/javase/9/docs/api/java/lang/Error.html): 回復見込みがない致命的な状況
      - e.g., OutOfMemoryError
      - **catchする必要がない**（catchしても良いが、通常はここから回復する手段はないことが多い）
    - (サブクラス) [Exception](http://docs.oracle.com/javase/9/docs/api/java/lang/Exception.html): 回復見込みがある状況
      - e.g., IOException
      - **catchすべき例外**。例外発生を想定してコードを記述することが望ましい or 記述することを強制していることがある。
      - (サブクラス) [RuntimeException](http://docs.oracle.com/javase/9/docs/api/java/lang/RuntimeException.html): 回復が必須ではない状況
        - e.g., IndexOutOfBoundsException
        - **catchしても良いが、細かい例外**。例外処理するというよりは、例外が起きないようにユニットテスト等の形でチェックすることが望ましい。
- 「どのメソッドを呼び出したら、どのような例外が発生する可能性があるか」を予め知っておく必要がある。
  - これらの情報は[APIリファレンス](https://docs.oracle.com/javase/9/docs/api/overview-summary.html)に掲載されている。

<hr>

## <a name="FileWriter1">java.io.FileWriterクラスの例</a>
- [java.io.FileWriter](http://docs.oracle.com/javase/9/docs/api/java/io/FileWriter.html#FileWriter-java.io.File-)
  - コンストラクタを参照すると、IOExceptionがthrowsされることが明示されている。
    - throws宣言 = 宣言されてる例外が発生する可能性を示唆している。ただし、示唆するだけで例外発生時の対応は記述していない（呼び出し元で記述することを強制する）。
    - つまりthrows宣言されてる場合、このクラスを利用する側（呼び出し元）で try-catch する必要がある。
```
public FileWriter(File file)
           throws IOException
```

<hr>

## <a name="FileWriter2">コード例</a>
- 例1: [Exception1.java](./Exception1.java)
  - 例外処理を用意していないケース。
  - コンパイル結果
    - 3件のエラー。
    - FileWriterクラスのコンストラクタやメソッドが「throws IOException」付きで記述されているため、利用側で補足する（try-catch構文で例外処理する）か、throws宣言する必要がある。（手を抜くプログラマが多いので、システムの頑健性を高めるために強制的に記述させている）
```
oct:tnal% javac Exception1.java
Exception1.java:6: エラー: 例外IOExceptionは報告されません。スローするには、捕捉または宣言する必要があります
	FileWriter fw = new FileWriter("output.txt");
	                ^
Exception1.java:9: エラー: 例外IOExceptionは報告されません。スローするには、捕捉または宣言する必要があります
	    fw.write(temp);
	            ^
Exception1.java:11: エラー: 例外IOExceptionは報告されません。スローするには、捕捉または宣言する必要があります
	fw.close();
	        ^
エラー3個
```

- 例2: [Exception2.java](./Exception2.java)
  - 例外処理を用意しているケース。

<hr>

## <a name="multi_exception">複数例外があるケース</a>
- 処理したい例外が複数ある場合は、catch文を並列に並べると良い。

```
try {
    //処理したいコード
} catch (IOException e) {
    //IOExceptionが起きたときの処理
} catch (NullPointerException e) {
    //NullPointerExceptionが起きたときの処理
}
```

<hr>

## <a name="finally">後片付け処理があるケース</a>
- tryブロック処理中に例外が発生したかどうかに関わらず、実行したい処理がある場合にはfinally構文を使う。
  - 必ず書くべき構文ではない。後片付けがある場合に使う。
- 状況例
  - File I/Oや、データベース接続、ネットワーク接続等のように、途中で例外が起きたとしても「JVMが終了する前に必ず接続を正常切断させたい」ケース。

```
try {
    //処理したいコード
} catch (Exception e) {
    //Exceptionが起きたときの処理
} finally {
    //例外発生の有無に関わらず、必ず実行させたいコード
}
```

<hr>

## <a name="exception_instance">例外インスタンスの例</a>
- 例外インスタンスは、``catch (IOException e)`` のようにtry-catch構文にて生成することができる。
  - 例外インスタンスが持つ情報の例: [Throwable](http://docs.oracle.com/javase/9/docs/api/java/lang/Throwable.html)
    - ``String getMessage()``: Returns the detail message string of this throwable.
    - ``void printStackTrace()``: Prints this throwable and its backtrace to the standard error stream.
    - ``String toString()``: Returns a short description of this throwable.
- getMessage() を眺めてみよう。
  - step 1: output.txtのパーミッションを変更し、「onwer自身が読み書きできない」ようにする。
  - step 2: Exception2を実行してみる。
```
oct:tnal% ls -l output.txt
-rw-r--r--  1 tnal  staff  12 11 13 10:56 output.txt
oct:tnal% chmod u-rw output.txt
oct:tnal% ls -l output.txt
----r--r--  1 tnal  staff  12 11 13 10:56 output.txt
oct:tnal% java Exception2
output.txt (Permission denied)
oct:tnal%
```
