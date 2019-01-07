# Thread（スレッド）入門

- ＜目次＞
  - <a href="#intro">Threadとは</a>
  - <a href="#howto">Threadの実装方法、コード例</a>
  - <a href="#ex">実装演習</a> (会計処理を待つ顧客を、複数のレジスタッフで処理)
  - <a href="#ref">参考ページ</a>

<hr>

## <a name="intro">逐次プログラミングと平行プログラミング</a>
- 参考: [並行・並列・分散プログラミング、マルチスレッド・プログラミング](http://www.cs.tsukuba.ac.jp/~yas/sie/csys-2016/2016-04-18/index.html)
  - 逐次（sequential）: 1度に1つの手続きが実行される
  - 平行（concurrent）: 1度に複数の手続きが実行される
    - 複数の手続きを実行する主体は、「プロセス(process)」や「スレッド (thread)」。
- 参考: [Processes and Threads](https://docs.oracle.com/javase/tutorial/essential/concurrency/procthread.html)
  - プロセスとは、アプリケーションを起動した際の実行環境。
    - プロセスの例
      - アプリケーション → ユーティリティ → アクティビティモニタ
  - スレッドとは、プロセスよりも軽量な実行環境。1プロセス内に複数のスレッドを用意することができる。（1プロセス内に複数プロセス用意することはできない）
    - スレッドの例
      - ブラウザのプラグインやタブ。
    - 別説明
      - "A thread is a thread of execution in a program. **The Java Virtual Machine allows an application to have multiple threads of execution running concurrently**." by [java.lang.Thread](http://docs.oracle.com/javase/9/docs/api/java/lang/Thread.html)
      - "スレッドとは、プログラムを実行している主体（例えて言うなら１人の人間）です。すべてのプログラムはスレッドによって実行されています。１つのスレッドは、ひとりの人間がプログラムを１行ずつ読んでプログラムの流れを追いかけるように、命令を１つずつ解釈し処理を実行していきます。（中略）Javaはマルチスレッドを取り扱うことのできる言語です。マルチスレッドとは複数のスレッドという意味です。つまりJavaでは複数の人が同時に仕事をするように、複数のスレッドを同時に実行することができるのです。" by [マルチスレッドプログラミング 1章　スレッド | TECHSCORE](http://www.techscore.com/tech/Java/JavaSE/Thread/1/)
      - ポイント: 同時に、複数のスレッド実行をアプリケーションに許可する。

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
- 概要
  - レジ前に長蛇の列が出来ている。これを並行して処理してみよう。

- Step 1: お客さんの実装。
  - 今回はスレッド処理が主題のため、簡潔に実装する。具体的には「一人のお客さんの会計処理に要する時間（int）」だけを持つとする。
  - 「お客さんの列」をArrayListで実装する。会計処理時間は乱数で設定。

```Java
import java.util.ArrayList;
import java.util.Random;

public class ThreadExample {
    public static void main(String[] args){
        //step 1: 客の準備
        int numberOfCustomer = 10;
        ArrayList<Integer> customers = initCustomer(numberOfCustomer);
        System.out.println(customers);

        //step 2: レジスタッフの用意

        //step 3: 全顧客の処理を終えるまで待つ

        //step 4: レジスタッフ毎に処理した内容の確認4
    }

    /**
     * 顧客の列を用意。
     * @param num 顧客数。
     * @return 顧客列。顧客毎に処理にかかる時間を値に持つ。
     */
    public static ArrayList<Integer> initCustomer(int num){
        Random generator = new Random(0);
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for(int i=0; i<num; i++){
            int time = (int)(generator.nextDouble() * 10); //処理時間
            temp.add(time);
        }
        return temp;
    }
}
```

- Step1の動作確認。
  - この時点では、乱数で処理時間が設定された顧客列が表示されるのみ。
- Step 2-1: レジスタッフクラスの用意。

```Java
import java.util.ArrayList;

/**
 * レジスタッフクラス。
 * コンストラクタで受け取った顧客（customers）が空になるまで処理し続ける。
 */
public class CashRegister extends Thread {
    String name; //スタッフの名前
    int numberOfCustomer; //処理した顧客数
    int totalTime; //処理した総合時間
    ArrayList<Integer> customers; //顧客の列
    boolean doStop = false; //falseの間、会計処理を続ける。trueになったら処理終了。

    CashRegister(String name, ArrayList<Integer> customers){
        this.name = name;
        numberOfCustomer = 0;
        totalTime = 0;
        this.customers = customers;
    }

    /**
     * 並行処理するメイン部分。
     * 顧客がいることを確認し、いる間は会計処理し続ける。
     * 顧客列が空になったら会計処理を終了。
     */
    @Override
    public void run(){
        int time;
        while( doStop == false ) {
            if (customers.isEmpty() == true) { //空になった場合
                doStop = true;
            } else { //顧客がいる場合
                time = customers.remove(0);
                numberOfCustomer++;
                totalTime += time;
                System.out.println(name + ": time=" + time + ", totalTime=" + totalTime);
            }
        }
    }

    /**
     * クラス概要を確認しやすくするための処理。
     * @return 呼び出された時点での、処理した顧客数と総処理時間。
     */
    @Override
    public String toString(){
        String result = name + ": num=" + numberOfCustomer + ", totalTime=" + totalTime;
        return result;
    }
}
```

- Step2-1の動作確認。
  - CashRegisterクラスにはmainメソッドがない。Step1で用意したThreadExampleクラスのmainメソッドは修正していないため、この時点で動作は変わらない。
- Step 2-2: mainメソッドに、レジスタッフのインスタンスを用意。ついでに、会計処理後の動作確認用出力（Step 4）を追加。
  - まだStep 3は未実装だが、ここでは空のままでOK。

```Java
//step 2: レジスタッフの用意
int numberOfRegisters = 2;
CashRegister[] cashRegisters = new CashRegister[numberOfRegisters];
for(int i=0; i<numberOfRegisters; i++){
    String name = "register_" + i;
    cashRegisters[i] = new CashRegister(name, customers);
    cashRegisters[i].start();
}

//step 3: 全顧客の処理を終えるまで待つ

//step 4: レジスタッフ毎に処理した内容の確認
for(int i=0; i<numberOfRegisters; i++) {
    System.out.println(cashRegisters[i]);
}
```

- Step2-2 + Step 4の動作確認。
  - レジスタッフを用意（new）し、runメソッドの処理を開始（start）させた。これにより全顧客に対する会計処理を実行してくれるはず。
  - だが、実際には以下の3スレッドが並行して動いている。この3スレッドは「別のスレッドが終了するのを待つ」等の条件指定がない。このため、スレッド1（Step4）が終了してからスレッド2や3が終了するといった、順序が想定外になる状況が起こりうる。
    - スレッド1: JVMが動作しているメインスレッド
    - スレッド2: レジスタッフ1
    - スレッド3: レジスタッフ2
  - 想定した処理は、「全ての顧客に対する会計処理（Step2）を終えた後で、動作確認出力」して欲しい。このために、レジスタッフのスレッド処理が終了するまで待つという処理を実装する。
- Step 3: mainメソッドに、全顧客に対する会計処理を終えるまで待つ処理を実装。

```Java
//step 3: 全顧客の処理を終えるまで待つ
try{
    for(Thread th : cashRegisters){
        th.join();
    }
} catch (Exception e){
    e.printStackTrace();
}
```
- Step3の動作確認。
  - メインスレッドにて、レジスタッフによる会計処理終了を待つ（join）ようになる。
  - しかし、場合によっては **競争状態** が起こりうるため、正しく処理できるとは限らない。
    - ref: [競争状態またはレース状態（race condition）](http://www.shudo.net/article/200201-JavaWorld-multithread/#race_condition)を参照。
    - 試しに、顧客数を1万人程度に増やして実行してみよう。どうなるだろうか？
  - これを避ける方法はいろいろあるが、ここでは synchronized によるモニタ処理を導入しよう。
- Step 5: 競争状態にある変数（顧客列）に対してモニタ処理を導入する。
  - CashRegister.run()にて、「**synchronized(操作対象オブジェクト){操作処理}**」を追加。
  - ここで「操作対象オブジェクト」とは、同時にアクセスされる可能性のあるオブジェクトのこと。具体的には「顧客列（customers）」を指定しよう。

```Java
//CashRegister.run()を一部修正しよう。

//before
if (customers.isEmpty() == true) { //空になった場合
    doStop = true;
} else { //顧客がいる場合
    time = customers.remove(0);
    numberOfCustomer++;
    totalTime += time;
    System.out.println(name + ": time=" + time + ", totalTime=" + totalTime);
}


//after
if (customers.isEmpty() == true) { //空になった場合
    doStop = true;
} else { //顧客がいる場合
    synchronized (customers) { //モニタ処理
      time = customers.remove(0);
    }
    numberOfCustomer++;
    totalTime += time;
    System.out.println(name + ": time=" + time + ", totalTime=" + totalTime);
}
```

- Step5の動作確認。
  - 何度実行し直しても、全顧客を正しく処理（モニタ処理）し終え、レジスタッフによる会計処理の終了を待ち（Thread.join）、メインスレッドにて動作確認用の出力が最後に出力されるようになっているはず。
  - だが、これでも不十分な場合がある。何度か実行してみると、顧客列が途切れているにも関わらずArrayList.removeしてしまうことがある。
    - これは、(1)if文で空ではないことを確認して、(2)synchronized処理する、という(1)〜(2)の間に別スレッドが処理してしまい、空になった状態で customers.remove() しようとすることがあるからである。
    - このため、synchronizedする範囲や、タイミングに注意が必要。具体的にどう解決したら良いかは、個々人で検討してみよう。
    - 知能情報コースとしては[オペレーティングシステム](https://ie.u-ryukyu.ac.jp/syllabus/2017/late/601053002.html)や[並列分散処理](https://ie.u-ryukyu.ac.jp/syllabus/2018/early/601548001.html)あたりで学びます。

<hr>

## <a name="ref">参考ページ</a>
- [マルチスレッドプログラムのバグ](http://www.shudo.net/article/200201-JavaWorld-multithread/)
- [並行・並列・分散プログラミング、マルチスレッド・プログラミング](http://www.cs.tsukuba.ac.jp/~yas/sie/csys-2016/2016-04-18/index.html)
- The Java™ Tutorials, [Lesson: Concurrency](https://docs.oracle.com/javase/tutorial/essential/concurrency/index.html)
- 知能情報コース
  - [オペレーティングシステム](https://ie.u-ryukyu.ac.jp/syllabus/2017/late/601053002.html)
  - [並列分散処理](https://ie.u-ryukyu.ac.jp/syllabus/2018/early/601548001.html)
