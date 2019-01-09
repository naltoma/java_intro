# 正規表現（Regular Expression; Regex）入門

- ＜目次＞
  - <a href="#intro">正規表現とは</a>
  - <a href="#example">正規表現の例</a>
    - <a href="#ex1">例1: 郵便番号</a>
    - <a href="#ex2">例2: アカウント</a>
    - <a href="#ex3">例3: メールアドレス</a>
  - <a href="#ref">参考ページ</a>

<hr>

## <a name="intro">正規表現とは</a>
- [The Java™ Tutorialsより](https://docs.oracle.com/javase/tutorial/essential/regex/intro.html)
  - Regular expressions are a way to describe a set of strings based on common characteristics shared by each string in the set. They can be used to search, edit, or manipulate text and data.
  - ある文字列の集合に、共通する文字を記述するための表現。
    - 例えば、「郵便番号」を入力して欲しい欄を用意したとしよう。入力された文字列は本当に郵便番号だろうか？
    - 例えば、「知能情報コースの学生」であるアカウントはどう表現できるか？
    - 例えば、「メールアドレス」はどう表現できるか？

<hr>

## <a name="example">正規表現の例</a>
### <a name="ex1">例1: 郵便番号</a>
- ここでは「903-0213」or「9030213」のように、「数字3桁-数字4桁」or「数字7桁」のいずれかであれば郵便番号として適切だと仮定しよう。
- 「任意の数字1桁（1つ）」ことを表現するには、**[0-9]** か **\d** と表記する。\dは、正確には「任意の10進数(decimal)1桁」。
- 「直前に指定したパターンをN回繰り返す」ことを表現するには、**{N}** と表記する。
  - **[0-9]{3}** なら、「任意の数字を3回繰り返す」になる。
- 「指定した文字」であることを表現するには、その文字をそのまま表記する。
  - 今回の「数字3桁が出現した後でハイフンが現れる」ことを表現するには、**[0-9]{3}-** と表記する。
- 後半の4桁数字については前述同様。
  - ただし、これでは「903-0213」のみの対応。これに加えて、「9030213」にも対応させたい。これは、「数字3桁の後にハイフンが0回以上1回未満出現する」と表現することで対応できる。
    - 具体的には **[0-9]{3}-{0,1}[0-9]{4}** となる。
- 他に記述できる表記一覧
  - [java.util.regex.Pattern](https://docs.oracle.com/javase/10/docs/api/java/util/regex/Pattern.html)

```Java
//例1-1: 任意の数字1桁
String[] inputs = {"1", "234-5678", "2345678", "9", "ab3", "ほげ"};
System.out.println("#任意の数字1桁");
for(String target : inputs){
    System.out.println(target + ": " + target.matches("[0-9]"));
}

//例1-2: 任意の数字3桁
System.out.println("#任意の数字3桁");
for(String target : inputs){
    System.out.println(target + ": " + target.matches("[0-9]{3}"));
}

//例1-3: 郵便番号その1
System.out.println("#郵便番号その1");
for(String target : inputs){
    System.out.println(target + ": " + target.matches("[0-9]{3}-[0-9]{4}"));
}

//例1-4: 郵便番号その2
System.out.println("#郵便番号その2");
for(String target : inputs){
    System.out.println(target + ": " + target.matches("[0-9]{3}-{0,1}[0-9]{4}"));
}
```

<hr>

### <a name="ex2">例2: アカウント</a>
- 知能情報コースのアカウントは、e185701〜e185799や、e175701〜同99のような形で提供されている。
  - どこが共通しているだろうか？
  - 共通している部分を踏まえ、どのように正規表現すると良いだろうか？

```
//検証コード例
String[] accounts = {"e185701", "e1857xy", "y185701", "e135799"};
String pattern = "e[0-9]{2}57[0-9]{2}"; //ここに正規表現を入力
for(String target : accounts){
    System.out.println(target + ": " + target.matches(pattern));
}
```

<hr>

### <a name="ex3">例3: メールアドレス</a>
- [Wikipedia: Email address](https://en.wikipedia.org/wiki/Email_address)
  - [Email Address Regular Expression That 99.99% Works.](https://emailregex.com)
  - （実際には自前で用意することは殆どないはず）

<hr>

## <a name="ref">参考ページ</a>
- [The Java™ Tutorials, Lesson: Regular Expressions](https://docs.oracle.com/javase/tutorial/essential/regex/index.html)
- [java.util.regex.Pattern](https://docs.oracle.com/javase/10/docs/api/java/util/regex/Pattern.html)
- [Wikipedia: Email address](https://en.wikipedia.org/wiki/Email_address)
  - [Email Address Regular Expression That 99.99% Works.](https://emailregex.com)
