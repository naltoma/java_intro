# Gitによるバージョン管理 on IntelliJ
## 復習: (前期資料) Gitによるバージョン管理入門
- [Gitによるバージョン管理入門](https://ie.u-ryukyu.ac.jp/~tnal/2016/prog1/Git.html)

## Gitlabへのログイン
- ここでは学科で用意しているGitLabサーバの利用を想定しているが、[Githb](https://github.com)を利用したいのであればそちらでも構わない。ただし、誰のリポジトリなのか分かるようにすること。
- [学科のgitlabサーバ](https://gitlab.ie.u-ryukyu.ac.jp/gitlab/users/sign_in)へアクセス。
  - LDAP Login の状態で、自身の学科アカウント＆パスワードでログインする。

## ssh-keyの登録
- ブラウザ内、右上の花マークっぽいやつをクリック。
- 「Profile Settings」を選択。
  - 上部メニューの「SSH Keys」を選択。
    - リンクになってる「generate it」を選択。
      - ターミナルで ``cat ~/.ssh/id_rsa.pub`` を実行する。
        - もし、 **ssh-rsa** というキーワードを含む長い文字列が出てきたら、次の手順に進む。
        - 文字列が出力されないなら、
          - ``cd ~/.ssh`` でディレクトリを移動。
          - ``ssh-keygen -t rsa -C "tnal@ie.u-ryukyu.ac.jp" -f gitlab`` のように実行して、ssh-keyを生成する。
            - パスフレーズを聞かれるが、何も入力せずにEnterを2回。
            - 上記コマンドは當間の場合。皆さんの場合は generate it のページに記載されてるように実行しよう。（メアド変更するだけ）
          - 想定通りに実行できていれば、次の2つのファイルが生成されているはず。
            - ~/.ssh/gitlab # 秘密鍵
            - ~/.ssh/gitlab.pub # 公開鍵（外部サービスへ登録するための鍵）
          - ssh-keygen で鍵を生成したら、 ``pbcopy < ~/.ssh/gitlab.pub`` を実行。公開鍵の方。これで、公開鍵がキャッシュにコピーされる。
- ターミナルで、``emacs ~/.ssh/config`` を実行して、設定ファイルを編集する。

```
Host gitlab.ie.u-ryukyu.ac.jp
  RSAAuthentication yes
  IdentityFile ~/.ssh/gitlabie
  User tnal
```

- ブラウザで、SSH Keys の登録ページに戻る。
  - 先ほどコピーした公開鍵を、「Key」欄に貼り付ける。
  - Title欄に「プログラミング2での登録」とか、どの鍵を登録したのか分かるようにコメントを書こう。自分がわかるコメントであれば何でもOK。


## Gitlab上にプロジェクトを作成
- 左上の「三」みたいなアイコンをクリック。
  - 「Projects」を選択。
  - 「New Project」を選択。
    - Project name を「ExampleUnitTest」とする。
    - Project description に「プログラミング2、UnitTestの練習」ぐらいの説明を書いておく。
    - Visibility Level を「public」にする。
    - 「Create project」をクリック。
    - 生成されたプロジェクトページにある、「https」右側のリンクをクリックし、コピー。
      - 私の場合、「https://gitlab.ie.u-ryukyu.ac.jp/gitlab/tnal/ExampleUnitTest.git」というURLになる。これがリポジトリへ作業するためのURL。

## PC側で作業してる既存プロジェクトを、上記で作成したプロジェクトに登録する。
- IntelliJで作成したプロジェクトのウィンドウに戻る。
  - VCSメニューから「Enable Version Control Integration」を選択。
    - 「Git」を選択し、OK。
  - VCSメニューから「Git -> + Add」を選択。
  - VCSメニューから「Git -> Commit File...」を選択。
    - 登録したいファイルがすべて選択されていることを確認。
      - 今回は、以下のファイルを一通り選択しよう。
        - src/main/jp.ac.uryukyu.ie.tnal/Enemy.java
        - src/main/jp.ac.uryukyu.ie.tnal/Hero.java
        - src/main/jp.ac.uryukyu.ie.tnal/Main.java
    - 上記3つのファイルを選択した状態で、commit message を記入し、「Commit」をクリック。
      - コミットメッセージは、ユニットテスト用の初期コードであることが分かるように書こう。
  - Code Analysis が起動するので、「Review」をクリック。
    - 自動コードチェッカ。不必要にpublicにしてないか、テンプレートのままになっていないか等の簡易的なチェックをしてくれる。
    - 普段なら、ここで指摘された事項は一通り無くなるまで対応しよう。今回は一つだけ対応することにして、残りは見なかったことにしよう。
      - 1つ目の指摘事項は「Main.javaの1行目が Default File template」らしい。その指摘項目をダブルクリックすると該当箇所に移動してくれる。
      - 該当箇所は、テンプレートが自動生成したコメント行。今は不要なので削除することにしよう。
      - 該当コメントを削除したら、もう一度 Git -> Commit File -> Commit。そこから Review を確認。
        - 対応箇所に関する指摘が消えているはずだ。この状態でリポジトリに登録しよう。
          - もう一度 Git -> Commit File -> Commit。
          - Commit message を記入し、Commit。
          - 今度は Review ではなく、Commit を選択。
            - エラーが出なければ、「* files committed」といった出力が出るはず。これでPC側のリポジトリに作業記録を残すことができた。ただし、この時点では「IntelliJが作成している、PC側のフォルダに登録しているだけ」であることに注意。
  - 学科サーバgitlabのリポジトリに登録する。
    - gitlab上の作業用URLを確認する。
      - ブラウザに戻り、作成したExampleUnitTestプロジェクトのページを開く。
      - HTTPSをクリックしてSSHに変更。
        - 右欄をクリックして、コピー。これがリポジトリ作業用URL。
    - IntelliJに戻る。
      - VCS -> Git -> Pushを選択。
      - Define remote をクリック。
      - URLに、学科サーバに用意したリポジトリの作業用URLを貼り付けよう。
      - 貼り付けたら、OK。
        - Confirm SSH Server Key と聞かれるので、Yes。
