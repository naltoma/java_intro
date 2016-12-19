# 抽象クラス(Abstract)，インターフェース(Interface)と多重継承
- 背景
  - 開発者の意図「クラス継承時に、メソッドをオーバーライドして欲しい」
    - 18章のポリモーフィズムまでだと、以下のように実現する。
      - スーパークラスを用意する。
      - それを継承したサブクラスにて、@Overrideアノテーション付きでメソッドをオーバーライドする。
    - 問題点
      - スーパークラスを用意しただけでは、サブクラスでオーバーライドするとは限らない。オーバーライドしなくても、スーパークラスを利用できてしまう。
      - そもそも人間は間違ってしまうもの。
        - 解決案: システム的に強制させよう。これがJavaの抽象クラスとインタフェースの主な役割。
        - 副産物: インタフェースのみ、多重継承も可能。

<hr>

## 抽象クラス（19.1節, pp.454-）、インタフェース（19.2節, pp.459-）
- コード例1: [stack-ex](https://github.com/naltoma/stack-ex)
  - 概要
    - abstractとinterfaceのコード例。最終的に実現していること（stack）はどちらも一緒。
      - AbstractStack <- StackArray <- Main1_abstract
      - InterfaceStack <- StackArray2 <- Main2_interface
  - 共通
    - 実装を省略し、サブクラスに実装を任せることができる。
      - ただのスーパークラス(class)でも似たことは実現できるが、「**オーバーライドによる実装を強制する**」のがabstractやinterface。
      - abstract/interfaceを継承して、新しいabstract/interfaceを作ることも可能。（更にその先のサブクラス作成時に実装を任せる）
      - abstract/interfaceどちらも「実装を任せる」ことを強制しているため、**このままではインスタンスを生成できない。**
      - 型として利用することは可能。
  - abstractとinterfaceの相違点
    - abstract
      - API定義だけでも良いし、実装を含んでも良い。
      - 実装する側は「extends」。
        - e.g., ``class Sub extends Abstract``
    - interface
      - **基本的にAPI定義だけ**を書く。実装は含まない。（**実装を含めることができない**）
      - 実装する側は「**implements**」。
        - インタフェースを継承してインタフェースを作る（実装なし）場合は、「extends」。
          - **extendsを利用する場合（継承）**
            - abstract class <- abstract class
            - interface <- interface
          - **implementsを利用する場合（インタフェースは特別扱い）**
            - interface <- class
      - 複数のinterfaceをまとめて実装することも可能。
      - e.g., ``class Sub implements Interface1, Interface2``

<hr>

## インタフェースを用いた多重継承の例
- コード例2: [interface-ex](https://github.com/naltoma/interface-ex)
  - 概要
    - クラスBlackFighterは、インタフェースJobBlackFighterの実装になっている。
      - 途中までしか実装していないため、このままではコンパイルできない。
    - インタフェースJobBlackFighterは、インタフェースJobBlackMageとJobFighterの多重継承になっている。
