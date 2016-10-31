* コンパイル＆実行の方法
- package名に注意。
  - prompt> javac -d . *.java
  - prompt> java jp/ac/uryukyu/tnal/Main

* Javadoc生成方法
- prompt> javadoc -charset "UTF-8" -private -d apidoc *.java

** Javadocオプション説明
- -charset "UTF-8": UTF-8で記述されたソースファイルに対応。
- -private: private指定されてるメンバもドキュメントに含める。
- -d <directory_name>: -d で指定したディレクトリにドキュメントを生成する。
  - 上記の例では apidoc というディレクトリに生成している。
- *.java: ドキュメント生成時に参照するソースコード。
