# オブジェクト指向2: 参照、継承

- ＜目次＞
  - <a href="#review">復習: クラスとインスタンス、コンストラクタ、カプセル化</a>
  - <a href="#refer">参照とは（教科書15章）</a>
    - <a href="#refer_1">コード例</a>
    - <a href="#refer_2">オブジェクトは参照で操作する（15.1節, pp.340-）</a>
    - <a href="#refer_3">nullはどこにもリンクしていない参照（15.2節, pp.346-）</a>
    - <a href="#refer_4">newを使って配列を作る（15.3節, pp.351-）</a>
    - <a href="#refer_5">オブジェクトの配列（15.4節, pp.358-）</a>
  - <a href="#extend">継承（教科書17章）</a>
    - <a href="#extend_1">継承とは（17.1節, pp.392-）</a>
    - <a href="#extend_2">継承ツリー（17.2節, pp.401-）</a>
    - <a href="#extend_3">protected修飾子（17.3節, pp.406-）</a>

<hr>

## <a name="review">復習: クラスとインスタンス、コンストラクタ、カプセル化</a>
- オブジェクト（モノ）中心に考える。
  - モノが所有するデータをフィールド変数とし、多くの場合、privateにする。（データ隠蔽、実装隠蔽）
  - モノが提供する機能をメソッドとし、public/(デフォルト)/privateいずれかの適切なアクセス修飾子を設定する。
    - アクセサメソッド（getter, setter）は慣習に習った名前にする。
  - オブジェクト生成時の初期化方法をコンストラクタとして用意する。
    - 戻り値を指定できない（オブジェクトへの参照を返す）。
    - 引数構成により異なるコンストラクタを用意できる（オーバーロード）。
- 実装上の流れ
  - クラスを用意する（設計する）。
  - クラスに基づきインスタンスを生成する。
  - 生成したインスタンスを介してオブジェクトを利用する。
    - インスタンスの持つフィールド変数やメソッドを利用するにはメンバ参照演算子(.)を使う。

<hr>

## <a name="refer">参照とは（教科書15章）</a>
### <a name="refer_1">コード例</a>
- Diceクラス (ver.3): [前回のコード例3](https://github.com/naltoma/java_intro/blob/master/ObjectOrientedProgramming.md#class_3)
  - フィールド変数隠蔽、アクセサメソッド実装済み。
- Execクラス (ver.2)

```java
//mainメソッドを別のファイルで用意: Exec.java
public class Exec {
  public static void main(String[] args){
    Dice dice1 = new Dice(3);
    Dice dice2 = dice1; //参照の代入(コピーではない)
    System.out.println("dice1="+dice1.getVal()+", dice2="+dice2.getVal());

    dice1.setVal(2); //dice1.valの値を上書き
    System.out.println("dice1="+dice1.getVal()+", dice2="+dice2.getVal());
  }
}
```

```
# 動作例
#  1回目の出力では、dice1, dice2共にvalの値が3になっている。
#  その後、dice1.valだけを修正したはずだが、
#  2回目の出力では、両方共に2になっている点に注意。
oct:tnal% javac -d . *.java
oct:tnal% java Exec
dice1=3, dice2=3
dice1=2, dice2=2
```


### <a name="refer_2">オブジェクトは参照で操作する（15.1節, pp.340-）</a>
- 参照型の変数で``a = b;``としても変数aには変数bを同じ参照がコピーされるだけ。（同一オブジェクトを参照している）
- Java言語では``new演算子``によってのみオブジェクトを新規作成できる。
- 「参照」のイメージ。
  - 参照型変数は、「new演算子でヒープメモリに生成されたオブジェクトへの参照」を保存している。
    - ここでいう「参照」は、メモリ上における番地を想像すると良い。
      - 例えば、``Dice dice1 = new Dice(3);`` で生成したオブジェクト本体は、ヒープメモリ上の53番地に置かれている。
      - ここで、``Dice dice2 = dice1;`` とすると、オブジェクト本体ではなく、「53番地を参照せよ」という情報だけが代入される。dice1もdice2も「同じ番地にある同じオブジェクト」を参照してることになる。
- 「参照」のメリット
  - コピーするサイズが小さい（本体へのリンク情報のみ）ので、高速に動作する。
  - 補足: **基本データ型** の場合は「=演算子」で値を複製できる。（データ型は教科書 p.42 参照）
- 「参照」のデメリット
  - 「オブジェクトを複製する」には、明示的にオブジェクト複製するコードを書く必要がある。
    - 参考: [Javaのcloneメソッドの正しい実装方法](https://qiita.com/SUZUKI_Masaya/items/8da8c0038797f143f5d3)
      - 普段は使わずに済むことが多いので、授業では特に解説まではしません。

### <a name="refer_3">nullはどこにもリンクしていない参照（15.2節, pp.346-）</a>
- 変数宣言しただけの変数は、何も参照していない空の状態（初期化されていない）。
  - 例えば ``Dice dice;`` だと、変数宣言しただけの状態。この変数diceは、参照すべきリンク情報が明示されていないため、nullを参照している。
  - nullと参照していないとは意味が違う。

### <a name="refer_4">newを使って配列を作る（15.3節, pp.351-）</a>
- 配列の作り方
  - (1) 初期化リストによる方法。
  - (2) newでオブジェクトを作る方法。

- Execクラス (ver.4)
  - Tips: [java.util.Arrays.toString](http://docs.oracle.com/javase/9/docs/api/java/util/Arrays.html#toString-int:A-)
    - 配列要素の文字列出力を簡潔にするメソッド。標準では標準で用意されてる基本データ型やString型ぐらいしか対応していない。
    - 独自クラスについてもtoStringを @Override すると、利用可能。（後日やる予定）

```java
//mainメソッドを別のファイルで用意: Exec.java
import java.util.Arrays;

public class Exec {
  public static void main(String[] args){
    //(1)初期化リストによる方法1: 基本データ型の場合
    int[] values1 = {1, 2, 3};
    System.out.println(values1[0]);
    System.out.println(values1[1]);
    System.out.println(values1[2]);

    //(2) newでオブジェクトを作る方法1: 基本データ型の場合
    int[] values2 = new int[3];
    for(int i=0; i<values2.length; i++){
      values2[i] = i+1;
    }
    System.out.println( Arrays.toString(values2) );

    //(1)初期化リストによる方法2: 参照型の場合
    Dice[] dice3 = {new Dice(), new Dice(2)};
    System.out.println("dice3[0].val=" + dice3[0].getVal());
    System.out.println("dice3[1].val=" + dice3[1].getVal());

    //(2) newでオブジェクトを作る方法2: 参照型の場合
    Dice[] dice4 = new Dice[2];
    dice4[0] = new Dice();
    dice4[1] = new Dice(2);
    System.out.println("dice4[0].val=" + dice4[0].getVal());
    System.out.println("dice4[1].val=" + dice4[1].getVal());
  }
}
```

### <a name="refer_5">オブジェクトの配列（15.4節, pp.358-）</a>
- 前述のコード例参照。
  - (1)初期化リストによる方法2: 参照型の場合
  - (2) newでオブジェクトを作る方法2: 参照型の場合

<hr>

## <a name="extend">継承（教科書17章）</a>
- 似たクラスを一つのクラスにまとめ、既存設計図を再利用して、差分だけを新たに作ることで類似クラスを作成する手法。
### <a name="extend_1">継承とは（17.1節, pp.392-）</a>
- 用語
  - スーパークラス (super class): 親クラス、基底クラス。継承元のクラスのこと。
    - **final修飾子** を付けられたクラスは、継承できない。（p.399）
      - final修飾子を付けられたメソッドは、オーバーロードできない。
      - final修飾子を付けられた変数は、定数になる（変更できない）。
  - サブクラス (sub class): 子クラス。extendsでスーパークラスを継承して作成したクラスのこと。
    - クラスメンバを新規に追加することも、オーバーロードすることもできる。
    - **クラスメンバではないコンクトラクタは、継承されない。**
      - ただし、親クラスのコンクトラクタを利用することはできる。: **super()**
    - 親クラスでprivate指定されてるクラスメンバにはアクセスできない。
    - **is-a関係: 「A is a B（AはBの一種）」** という関係を守ろう。(pp.398)
- Diceクラスを継承して「多面ダイス(ExDice)」クラスを実装した例。
  - ExDiceクラス (ver.1), Execクラス (ver.5)

```java
//Diceクラスを継承した「多面ダイス」の例: ExDice.java

public class ExDice extends Dice {
    private int valMax; //多面ダイスの「面の数」
    public ExDice(int valMax){
        super(valMax); //親クラスのDice(int)コンストラクタを利用
        this.valMax = valMax;
    }

    public int getValMax(){ return valMax; }

    //play()メソッドを上書き(Override)して、
    //ExDiceオブジェクト生成時の最大値valMaxを元に、乱数生成。
    // @Override: 教科書p.426。詳細は後日。
    @Override
    public void play(){
        int value = (int)(Math.random() * this.getValMax()) + 1;

        //親クラスのメソッドを利用して、値を保存。
        //今回のコードでは super. で「親クラスのメソッド」であることを明示しているが、
        // 継承したExDiceクラスでも同じメソッドを使えるので、
        // 省略して書いてもかまわない。
        super.setVal(value);
        //setVal(value); //これもok。
        //this.setVal(value); //これもok。
    }
}
```
```java
//Exec.java (ver.5)
public class Exec {
    public static void main(String[] args){
        ExDice exdice = new ExDice(100);
	exdice.play();
        System.out.println(exdice.getVal());
    }
}
```

- 実行例

```
# 実行例
#   ExDice(100)で100面ダイス（1〜100の面を持つダイス）を生成。
oct:tnal% javac -d . *.java
oct:tnal% java Exec
46
```

<hr>

### <a name="extend_2">継承ツリー（17.2節, pp.401-）</a>
- 継承ツリーの頂点にあるのは、（暗黙の内に継承される）Objectクラス。
  - p.401の表: Objectクラスのメソッド（全てのオブジェクトが持たねばならない機能）
  - 継承ツリー: 継承関係（クラス継承図）
    - Java言語では、クラスはスーパークラスを1つだけしか持てない。（単一継承）
      - 多重継承は、Java言語では禁止。（可能な言語もある）
        - 代替案としてインタフェース（19章）が用意されている。
  - 継承に寄るコンストラクタの連鎖
    - サブクラスのコンストラクタは、最初にスーパークラスのコンストラクタを実行して、スーパークラスのオブジェクトを初期化する必要がある。
    - サブクラスにてスーパークラスのコンストラクタ実行が省略されている場合、**引数なしのsuper()が省略されている** ものとして処理される。（super()が実行される）

<hr>

### <a name="extend_3">protected修飾子（17.3節, pp.406-）</a>
- protected修飾子の機能
  - (1) 同じパッケージのクラスからアクセスできるようにする（デフォルトアクセスと同じ）。
  - (2) サブクラスからアクセスできるようにする。
- p.409の表: アクセス修飾子の適用場所
