# Gitによるバージョン管理 on IntelliJ

- 目次
- <a href="#review">復習: (前期資料) Gitによるバージョン管理入門</a>
- <a href="#github-login">GitHubへのログイン</a>
- <a href="#ssh-key">ssh-keyの登録</a>
- <a href="#github-project">GitHub上にプロジェクトを作成</a>
- <a href="#github-project-push">PC側で作業してる既存プロジェクトを、上記で作成したプロジェクトに登録する。</a>

<hr>

## <a name="review">復習: (前期資料) Gitによるバージョン管理入門</a>
- [Gitによるバージョン管理入門](https://github.com/naltoma/python_intro/blob/master/Git.md)

<hr>

## <a name="github-login">GitHubへのログイン</a>
- ここでは[Github](https://github.com)の利用を想定しているが、[学科のgitlabサーバ](https://gitlab.ie.u-ryukyu.ac.jp/gitlab/users/sign_in)を利用したいのであればそちらでも構わない。
- [Github](https://github.com)へアクセス。
  - Sign Up をクリック。
  - username, email address, password を入力して「Create an account」。
    - usernameは自由で構わないが、少なくともemail addressは学科のアドレスを指定すること。（そうしないと誰のリポジトリか判別できない）
    - Unlimited public repositories for free. を選択。
    - Tailor your experience は説明文読んで回答。

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

（2017年度は、下記作業を別資料でやりますので飛ばして下さい）

## <a name="github-project-push">PC側で作業してる既存プロジェクトを、上記で作成したプロジェクトに登録する。</a>
- Step 1: IntelliJで作業中のプロジェクトを、GitHubリポジトリに登録する。
  - IntelliJプロジェクトにVCSを設定。（プロジェクト毎に1回だけ実行）
    - VCS = Version Control System（バージョン管理システム）のこと。
    - IntelliJで作成したプロジェクトのウィンドウに戻る。
      - VCSメニューから「Enable Version Control Integration」を選択。
        - 「Git」を選択し、OK。
- Step 2: VCS管理対象を指定して、commit。（ローカルリポジトリに作業記録を残す）
  - 作業状況をセーブしたいタイミングで、その都度実行するステップ。
  - VCSメニューから「Git -> + Add」を選択。
    - 「Do you want to add the following file to Git?  /Users/tnal/IdeaProjects/ExampleUnitTest/.idea/vcs.xml」と聞かれるが、Noを選択。（IntelliJ専用のファイルなので、不要）
  - VCSメニューから「Git -> Commit File...」を選択。
    - 登録したいファイル（通常はソースコードや関連ドキュメント）がすべて選択されていることを確認。
    - 登録したいファイルを選択した状態で、commit message を記入し、「Commit」をクリック。
      - コミットメッセージは、ユニットテスト用の初期コードであることが分かるように書こう。
  - Code Analysis が起動するので、「Review」をクリック。
    - 自動コードチェッカ。不必要にpublicにしてないか、テンプレートのままになっていないか等の簡易的なチェックをしてくれる。
    - 普段なら、ここで指摘された事項は一通り無くなるまで対応しよう。
      - 問題なければ、もう一度 Git -> Commit File -> Commit。
      - Commit message を記入し、Commit。
        - 今度は Review ではなく、Commit を選択。
          - エラーが出なければ、「* files committed」といった出力が出るはず。これでPC側のリポジトリに作業記録を残すことができた。ただし、この時点では「IntelliJが作成している、PC側のフォルダに登録しているだけ」であることに注意。
- Step 3: ローカルリポジトリの作業状況を、GitHubリポジトリに登録。
  - GitHub リポジトリのページを開き、Git URLをコピー。
  - IntelliJに戻る。
    - VCS -> Git -> Pushを選択。
    - Define remote をクリック。
    - URLに、GitHubに用意したリポジトリの作業用URL（Git URL）を貼り付けよう。
    - 貼り付けたら、OK。
    - Push。
    - Push successfulと出力されたら、Githubリポジトリへのpushが成功。GitHubページに戻り、ページを再読込みしよう。どのようにリポジトリが見えるか確認してみよう。
- ここまでの作業を整理。
  - 開発中のソースコードをGitでバージョン管理する。これだけならPC内（ローカル）で作業してよい。前期はそうした。
  - 一方で、ペアプロなどファイルを複数人で共有しながら作業したい場合や、リポジトリを公開したい場合には、ベアリポジトリを共有しやすい場所で管理したい。このような目的で使えるのが GitHub。
  - GitHubリポジトリの作成は、プロジェクト毎に作成する。公開プロジェクトであれば無料で無制限に作成可能。
  - IntelliJ側の設定（Git設定, Git URL設定）も、プロジェクト毎にやる。
  - commitは、作業状況を「新しいバージョン」として記録したい単位でやる。記録しておけば、そのバージョンに戻ることができる。ただしこの時点ではPC内でしか記録していない点に注意。
  - pushは、ベアリポジトリに記録する。ベアリポジトリとして GitHubリポジトリを指定すると、そこに記録を残し、公開することができる。
