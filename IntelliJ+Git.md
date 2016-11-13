# Gitによるバージョン管理 on IntelliJ

- 目次
- <a href="#review">復習: (前期資料) Gitによるバージョン管理入門</a>
- <a href="#github-login">GitHubへのログイン</a>
- <a href="#ssh-key">ssh-keyの登録</a>
- <a href="#github-project">GitHub上にプロジェクトを作成</a>
- <a href="#github-project-push">PC側で作業してる既存プロジェクトを、上記で作成したプロジェクトに登録する。</a>

<hr>

## <a name="review">復習: (前期資料) Gitによるバージョン管理入門</a>
- [Gitによるバージョン管理入門](https://ie.u-ryukyu.ac.jp/~tnal/2016/prog1/Git.html)

<hr>

## <a name="github-login">GitHubへのログイン</a>
- ここでは[Github](https://github.com)の利用を想定しているが、[学科のgitlabサーバ](https://gitlab.ie.u-ryukyu.ac.jp/gitlab/users/sign_in)を利用したいのであればそちらでも構わない。
- [Github](https://github.com)へアクセス。
  - Sign Up をクリック。
  - username, email address, password を入力して「Create an account」。
    - usernameは自由で構わないが、少なくともemail addressは学科のアドレスを指定すること。（そうしないと誰のリポジトリか判別できない）

<hr>

## <a name="ssh-key">ssh-keyの登録</a>
- ブラウザ内、右上のアイコンをクリックして「Settings」を選択。
  - 左メニューの「SSH and GPG keys」を選択。
    - 「New SSH key」を選択。
    - ターミナルで、次のコマンドを実行。メールアドレスは自分自身のものに変更すること。
      - ``cd ~/.ssh``
      - ``ssh-keygen -t rsa -b 4096 -C "your_email@example.com" -f github``
        - パスフレーズを聞かれるが、何も入力せずにEnterを2回。
        - 想定通りに実行できていれば、次の2つのファイルが生成されているはず。
          - ~/.ssh/github # 秘密鍵
          - ~/.ssh/github.pub # 公開鍵（外部サービスへ登録するための鍵）
      - 生成した鍵を、以下のコマンドでキャッシュにコピーする。公開鍵の方である点に注意。
        - ``pbcopy < ~/.ssh/github.pub``
    - ターミナルで、``emacs ~/.ssh/config`` を実行して、設定ファイルを編集する。

```
Host github.com
  IdentityFile ~/.ssh/github
```

- ブラウザで、SSH Keys の登録ページに戻る。
  - Title欄に「プログラミング2での登録」とか、どの鍵を登録したのか分かるようにコメントを書こう。自分がわかるコメントであれば何でもOK。
  - 先ほどコピーした公開鍵を、「Key」欄に貼り付ける。
  - 「Add SSH key」をクリック。
  - パスワードを要求されるので、Githubへログインするためのパスワードを入力。

<hr>

## <a name="github-project">GitHub上にプロジェクトを作成</a>
- 右上アイコン隣の「+」をクリックし、「New repository」を選択。
  - Project name を「ExampleUnitTest」とする。
  - Project description に「プログラミング2、UnitTestの練習」ぐらいの説明を書いておく。
  - 「public」にする。
  - 「Create project」をクリック。
    - 生成されたプロジェクトページにある、「https」右側のリンクをクリックし、コピー。
      - 私の場合、「 https://github.com/naltoma/ExampleUnitTest.git 」というURLになる。これがリポジトリへ作業するためのURL。

<hr>

## <a name="github-project-push">PC側で作業してる既存プロジェクトを、上記で作成したプロジェクトに登録する。</a>
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
      - Push。
      - Push successfulと出力されたら、Githubリポジトリへのpushが成功。ブラウザに戻り、どのようにリポジトリが見えるか確認してみよう。
