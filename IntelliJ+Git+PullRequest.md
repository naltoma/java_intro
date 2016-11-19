# IntelliJ + Git + Pull Request（他人のリポジトリに更新リクエストを送ってみよう）
- 参考
  - [Github で Fork してから Pull Request をするまでの流れ](http://kik.xii.jp/archives/179)
  - [GitHub初心者はForkしない方のPull Requestから入門しよう](http://blog.qnyp.com/2013/05/28/pull-request-for-github-beginners/)
  - [3.6 Git のブランチ機能 - リベース](https://git-scm.com/book/ja/v1/Git-のブランチ機能-リベース)
- 目次（今回の流れ）
  - 作業用リポジトリを用意。
  - リポジトリを Fork する（Github上で他人リポジトリを自リポジトリに複製）。
  - 複製リポジトリをPC側に clone する。
  - 個人作業用 branch を作る。
  - 編集する。
  - commit する。（PC内）
  - push する。（Githubの自リポジトリへ）
  - upstream の設定。（オリジナルのリポジトリを設定）

<hr>

## 作業用リポジトリを用意。
- IntelliJで、「PullReq」プロジェクトを新規作成。


- Githubで、「PullReq」リポジトリ(public)を新規作成。
- IntelliJで、PullReq リポジトリから clone してプロジェクトを作成。
  - src/main フォルダを作成し、Sources設定。
  - src/main に先週の授業で利用したコード例[ExampleUnitTest](https://github.com/naltoma/ExampleUnitTest)の Enemy.java, Hero.java, Main.java をコピペ作成。
    - ここではパッケージの指定、EnemyTest.javaの作成はやってもやらなくても良い。
  - Main.java が動作することを確認すること。
  - Git管理下に置く。（Enable Version Control -> Git）
    - 上記3つのファイルを add。
    - commit。
- Githubで、「PullReq」リポジトリを新規作成。
  - リポジトリURL（作業用URL）をコピー。
- IntelliJ作業ウィンドウに戻る。
  - Git -> Pull し、リモートリポジトリとして先程コピーしたURLを設定する。
- この時点で、IntelliJプロジェクトと、Githubリポジトリが同一内容になっているはず。

<hr>

## リポジトリを Fork する（Github上で他人リポジトリを自リポジトリに複製）。

- Githubにて、ペア相手の「PullReq」リポジトリをブラウザで参照する。（ユーザ名から辿れるはず）
- 右上にある「Fork」をクリック。
- この時点で、自分自身のリポジトリに「PullReq-1」という名前で複製されているはず。区別しやすくするため、リポジトリ名を変更しよう。
  - Settingsをクリック。
  - Repository nameを「PullReqCopied」に変更し、Rename。
  - renameされたリポジトリに移動するはずなので、リポジトリURL（作業用URL）をコピー。

<hr>

## 複製リポジトリをPC側に clone する。
- IntelliJに戻り、開いてる作業ウィンドウを閉じる。
- Fileメニューから「New -> Project from Version Control -> Git」。
  - リポジトリURLから clone を作成。
  - 念のため、Main.java が動作すること（予定通り失敗するコードであること）を確認。

<hr>

## 個人作業用 branch を作る。
- ブランチ(branch)とは、「リポジトリのメインストリームに影響を及ぼさないように、仮想的に分岐して管理する」ための機能。pull request を送るか否かに関わらず、お試しで編集したいならまずブランチを作るぐらいの気持ちでやっても良い。
- また、今回は最初から「Enemy.attack()に関する修正をする」つもりでブランチを作ります。このブランチで、他の作業はしないようにしましょう。誤って無関係なcommitを先方に送りつけてしまうと、意味不明なリクエストになります。
  - 参考: [branchのイメージ](http://kray.jp/blog/git-pull-rebase/)
  - 現在どのようなブランチがあるのかを確認するには、VCS -> Git -> branches... を選択。
    - ``origin/master`` とは、リモートリポジトリ(origin)のデフォルトブランチ(master)という意味。
    - ``current/master`` とは、現在masterブランチにいるという意味。
  - 今回は、複製したリポジトリに問題があることが分かっている。特に、Enemy.attack()の問題を解決したい。そのことが伝わるような名称でブランチを作ろう。
  - VCS -> Git -> branches... をもう一度選択し、「+ New Branch」をクリック。「FixEnemyAttack」という名前でブランチを作ろう。（基本的には自由ですが、今回は指定します）
  - もう一度 VCS -> Git -> branches を選択すると、origin/masterとは別に、ローカルにFixEnemyAttackというブランチがあること。また、現在FixEnemyAttackブランチにいることを確認できるはずだ。ここでcommit&pushしても、masterブランチには影響を及ぼさないので、お試し開発等の用途で安心していじり倒すことができる。（邪魔になったらブランチを削除しても良い）

## 作成したブランチ上で編集する。
- 問題があることを検証しやすくするために、テストを追加する。
  - Enemy.java に対するテスト EnemyTest.java を作成しよう。中身は次の通りとする。（package名の整合性を取ろう）
    - [EnemyTest.java](https://github.com/naltoma/ExampleUnitTest/blob/master/src/test/jp/ac/uryukyu/ie/tnal/EnemyTest.java)
    - Sources, Testsの指定。JUnit4ライブラリの追加を忘れずに。
  - EnemyTestを実行し、テストが失敗することを確認しよう。確認できたら add して commit。
- Enemy.attack()の問題を解消しよう。
  - いろんな対応方法が考えられそうだが、今回は「Enemy.deadがtrueの時（死亡している時）には、attack()が呼ばれてもダメージを与えることができない」ようにしよう。
    - 対応例:

```
public void attack(Hero hero){
    if( dead == true ){
        return; // 死亡時には何もせずにメソッドを終了する。
    }
    int damage = (int)(Math.random() * attack);
    System.out.printf("%sの攻撃！%sに%dのダメージを与えた！！\n", name, hero.getName(), damage);
    hero.wounded(damage);
}
```

<hr>

## 修正できたら、commit する。（PC内）
- Enemy.attack()を編集し、EnemyTestのテストが成功することを確認。成功したら、commitしよう。
  - commit message は、Enemy.attack()の問題を解消したことが伝わるようなコメントを書こう。
  - コメント例: [gitにおけるコミットログ/メッセージ例文集100](http://anond.hatelabo.jp/20160725092419)

<hr>

## push する。（Githubの自リポジトリへ）
- pushしよう。EnemyTest.java追加時にpushしていなければその分と、Enemy.attack()修正した分のコミット2件分をまとめてpushすることになる。（先程pushを済ましていたら、1件分pushになる）
- これで、自分のリポジトリ上には問題修正版を置いたことになる。
- ブランチを作成してから作業したが、これがGitHub上でどのように見えるのか、ブラウザで確認してみよう。
  - ``2 branches`` と表示されている箇所をクリック。
    - デフォルトの master ブランチと、作成した FixEnemyAttack ブランチ の2つが見えるはず。また、FixEnemyAttack ブランチの方に「0|2___」というような表示が見えるはずだが、そこにカーソルを合わせると「2 commits ahead of master」と出力されるはずだ。これは、デフォルトのmasterブランチの後に、2つのコミットが存在していることを意味している。
- 今回は、上記1件の修正だけをオリジナルのリポジトリにも伝えたいとしよう。

<hr>

## Pull Request の作成。
- 修正版をpushしたリモートリポジトリに、ブラウザでアクセス。（Github上のPullReqCopiedへアクセス）
  - 「New Pull Request」をクリック。
  - マージしたいブランチ（＝修正版を伝えたいブランチ）を選択。
    - 左側にオリジナルのリポジトリ＋ブランチがあり、右側に自身のリポジトリ＋ブランチが並んでる。今回の例だと、修正版は「自分リポジトリのFixEnemyAttackブランチ」なので、右側の自分のブランチを「compare: FixEnemyAttack」に変更。
    - すると、オリジナルを基準とした差分を確認する画面になるはずだ。今回のケースだと、EnemyTest.javaを追加した点と、Enemy.attack()にif文追加した点の2箇所を確認できるはず。
    - この状態で「Create pull request」をクリック。
    - 相手へ説明するフォームが用意されるので、リクエスト内容について説明しよう。
      - 今回は、例えばタイトル「Fix Enemy.attack()」、内容「After Enemy is dead, he must never attack to opponent.」とか。内容の説明になってればなんでも良いです。
      - 記入し終えたら「Create pull request」をクリック。
- これで、オリジナル作者のリポジトリに、修正を伝えた（pull requestした）ことになる。

<hr>

## オリジナルのリポジトリにはどのように伝わっているか。
- pull requestで通知したら、オリジナルにはどのように伝わっているだろうか。Githubでオリジナルリポジトリを参照してみよう。
  - 「Pull requests」が1になってるはずだ。これをクリック。
  - 先程、ペア相手の人が送ってきたリクエストが届いているはずだ。タイトルをクリックして詳細を確認してみよう。
    - リクエスト内容に関する説明文に加えて、This branch has no conflicts with the base branch. Merging can be performed automatically.（baseブランチとの衝突はない。マージも自動的に実行可能。）という補足文が追加されているはずだ。
    - この状態で Merge pull request の「**右側にある▼**」を選ぶと、このリクエストに対する対応方法（merge, squash, rebase）が列挙される。
      - Create merge commit (All commits from this branch will be added to the base branch via a merge commit.): 自動で merge+commit する（baseブランチに差分を追加する）。
      - Squash and merge (The 2 commits from this branch will be combined into one commit in the base branch.): 2件のコミットをsquashする（1コミットにまとめてbaseブランチにコミットする）。
      - Rebase and merge (The 2 commits from this branch will be rebased and added to the base branch.): 2兼のコミットをrebaseする（2ブランチの共通先祖に移動し、各コミットの差分を取得。取得した差分をrebase先のブランチに順々に実行する）。
        - 参考: [3.6 Git のブランチ機能 - リベース](https://git-scm.com/book/ja/v1/Git-のブランチ機能-リベース)
    - 今回は、演習2で手動でやってるmerge (create merge commit) を選択してみよう。
      - create merge commit を選択。（Merge pull requestsのままになるけど、これでok）
      - Merge pull requests をクリックすると、フォームが編集できるようになる。commit comment がそのままで良いなら、編集せずにそのまま confirm merge をクリック。
      - 「Merged naltoma2 merged 2 commits into naltoma2:master from naltoma:FixEnemyAttack」というように、FixEnemyAttackブランチからmasterブランチへmergeしたというコメントが表示されるはず。これでマージ完了。同時に、pull request の作成者へも「マージしたよ」という趣旨のメールが自動送信されているはず。確認してみよう。
  - この時点で PullReq リポジトリを改めて参照すると、トップ画面に「naltoma2 committed on GitHub Merge pull request #1 from naltoma/FixEnemyAttack」といったコミット履歴が表示されているはず。また、Pull requests が0件になっているはずだ。
