# 演習3: IntelliJ, Git, GitHubに慣れよう

＜目次＞
- 演習3: IntelliJ, Git, GitHubに慣れよう
  - <a href="#howto">ペア・プログラミングの目的とやり方</a>
  - <a href="#goal">達成目標</a>
  - <a href="#ex3_1">演習3_1: GitHubにペアプロ用リポジトリを用意しよう</a>
  - <a href="#ex3_2">演習3_2: 共用リポジトリへ互いにcommit&pushしてみよう（mergeとconflicts解消の練習）</a>

<hr>

## <a name="howto">ペア・プログラミングの目的とやり方</a>
- 目的
  - **課題解決に関するノウハウの共有。**
    - 「早く解くこと」ではない。
- やり方
  - **問題文が理解できない場合にはすぐに質問すること。（ここで悩むのは時間の無駄）**
  - driverとobserverを決める。（10~15分程度で交代しよう）
    - driver
      - 実際に作業する（手を動かす）人。それ以外の人は直接作業してはいけない。
      - driverは作業する際に考えていることを説明しながら作業すること。分からないなら相談すること。
      - 例
        - 「まず問題読んでみよっか。〜〜（問題文）〜〜。ん、どういうことだろう？」
    - observer
      - driverの作業を眺め、気づいたこと・分からないことを質問すること。作業を中断させて構わない。
      - driverのやり方よりも良い方法があると感じたら、その旨を話すこと。


<hr>

## <a name="goal">達成目標</a>
- Gitによるバージョン管理と、GitHubを通したリモートリポジトリの利用に慣れよう。
- 考えてることを説明できるようになろう。
- 分からない時には作業を止めて質問できるようになろう。

<hr>

## <a name="ex3_1">演習3_1: GitHubにペアプロ用リポジトリを用意しよう</a>
- 誰か代表一人決め、[GitHub](https://github.com/)にログインし、ペアプロ用リポジトリを新規作成しよう。
  - リポジトリ名: prog2-ex3
  - publicにすること。
  - Settingsをクリックし、「Collaborators」をクリック。
  - パスワードを要求されるので、入力。
  - Add collaboratorの欄に、パートナーのGitHubのusernameを入力し、Add collaboratorをクリック。
    - 3人チームの場合は、パートナー2名とも入力しよう。
    - 正しく入力できていれば、「xxx has invited you to collaborate on the yyy/prog2-ex3 repository」といったタイトルのメールが、パートナー側に届いているはず。
      - パートナーは、該当メールを開き、「View invitation」をクリック。
        - 続けて、「Accept invitaion」をクリック。
        - これで、複数人でcommit&pushできる、共用リポジトリを準備できたはず。
- 報告内容
  - 共用リポジトリの作業用URLを報告すること。

<hr>

## <a name="ex3_2">演習3_2: 共用リポジトリへ互いにcommit&pushしてみよう（mergeとconflicts解消の練習）</a>
- **演習3_1の代表者から以下の作業を始めること。**
  - IntelliJを起動し、開いてる作業ウィンドウを閉じる。
  - Check out from Version Control をクリックし、Gitを選択。
    - Git Repository URL に、演習3_1で用意した作業用URLを貼り付ける。
    - Testして問題なければ Clone する。Yes。
    - NextしていってFinish。これで IntelliJ のプロジェクトとして用意できたはずだ。
  - 左側のプロジェクト一覧から「prog2-ex3」を選び、Ctrl+クリック。
    - New -> File と選び、ファイル名を「README.md」としてOK。
    - Git管理下に置くか聞かれるので Yes。
  - 作成した README.md に、次を入力。 ``# プログラミング2の演習3の練習中``
  - README.md を add し、commit & push しよう。（これで一人目の commit&pushを終了）
- **ペア番**
  - IntelliJを起動し、開いてる作業ウィンドウを閉じる。
  - Check out from Version Control をクリックし、Gitを選択。
    - Git Repository URL に、演習3_1で用意した作業用URLを貼り付ける。
    - Testして問題なければ Clone する。Yes。
    - NextしていってFinish。これで IntelliJ のプロジェクトとして用意できたはずだ。
    - 最初に作成した時点ではリポジトリが空だったはずだが、二人目からは最初に登録された README.md が登録済みのはずである。
  - README.md を編集しよう。
    - 2行目に、ペアの人が書いたことが分かるような文面を記入。内容は自由。
    - README.md を add し、commit & push しよう。（これで二人目の commit&pushを終了）
    - チームでやってる場合、3人目も「ペア番」と同じようにやろう。
      - これで commit & push を1巡し終えた状態になるはずだ。
        - 注意点として、この時点では「最初の人、次の人」とで IntelliJ プロジェクトが同期を取れていないので、中身が違っている。これを踏まえて、2巡目の作業に移ろう。
- **2巡目の作業**
  - ここから2巡目に入る。最初にリポジトリを作成した代表者の番。
  - PC (IntelliJ) 側のプロジェクトと、Github上のリポジトリとではファイルの中身が異なっている。ここではそのことを知らないものとして作業を進めよう。（気づかない内に修正されてることもあるので）
  - 古いリポジトリのまま、PC側のプロジェクトで作業しよう。
    - README.md を修正し、「新しい文章を追加」等の更新したことが分かるように編集しよう。
    - 普段通りに README.md を add し、commit しよう。commitはローカルのリポジトリに登録するだけなので、ここまでは問題なく進むはずである。
    - GitHubリポジトリに新しい更新があることに気づかず、pushしてみよう。
      - **Push of current branch master was rejected. Remote changes need to be merged before pushing.** と言われるはずだ。これは、リモートに更新版があり、今pushしようとした版とどちらが正しいのかシステム的に判断できないので、push作業が拒否されたことを示している。また、それに対する対策として「pushする前にmergeしろ」とアドバイスを提示している。mergeとは、両方の版を組み合わせた一時的な版を作成することを意味する。指摘通りmergeしてみよう。
      - 「Merge」をクリック。
      - **Merge conflicts dected. Resolve them before continuing update.** と言われるので、その通りに作業する。つまり、新しく作業する前に衝突（2つのバージョンを併合したことにより生じた矛盾）を解決する必要がある。
      - 「Merge...」をクリック。
      - 3パネルのウィンドウが開いているはずだ。左が **Local Changes(PC側リポジトリでの更新内容)**、右側が **Changes from Server(GitHubリポジトリでの更新内容)** であり、各々赤く色づけられてる行が「衝突」である。中央は **Result()** であり、左右の2つのバージョンを見ながら、中央に正しい版を作成する。
      - ここでは「先にリモートリポジトリの内容を加え、次にPC側の内容を加える」という両方の内容を含めるのが正しい版だとしよう。そのように中央パネルを編集する。
      - 衝突を解消し終えたら Apply。
      - この状態（衝突解決Applyした状態）で、改めて push してみよう。
        - Push commits が表示され、先程のcommitメッセージに加え、mergeした情報も含めて push しようとする様子が見えるはずだ。「Push」をクリック。
        - 今度は push が成功するはずだ。このように、複数人で作業する場合には各々のリポジトリ間における「バージョンの違い」が当然のように生じる。その際には merge(併合) して、生じた conflicts(衝突)をresolve(解消)してから、pushし直そう。この流れを覚えよう。
        - Tips: IntelliJの下段パネルに「Version Control, Local Changes,,,」等のメニューが表示されているはず。ここで「Log」を見ると、更新履歴が可視化表示されている。この様子を分かりやすく説明している例が[**git pull と git pull –rebase の違いって？図を交えて説明します！**](http://kray.jp/blog/git-pull-rebase/)の「git mergeとは」である。前期にも紹介済みだが、今ならより理解が深まっていることを期待する。このような「バージョンを意識して作業する」ことを支援してくれるツールがバージョン管理システムである。
  - 代表者が2回目の push を終えたら、次の人に交代して同じように「編集->commit->push->衝突->merge->resove->push」しよう。これを **全員が3回pushするまで繰り返す** こと。（ペアなら合計6回のpush記録が残るまでやろう。3人なら合計9回のpush記録が残るまでやろう）
- 報告内容
  - 最後の更新をした人が、ターミナルでIntelliJリポジトリに移動。そこで git log した結果を報告すること。
