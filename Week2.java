/*
2週目: Java入門2
https://github.com/naltoma/java_intro/blob/master/Java_intro2.md
演習例のコード例
 */

public class Week2 {
    public static void main(String args[]){

        //演習1-1
        System.out.println("## 演習1-1");
        System.out.println("hoge\nfuga");

        //演習1-2
        System.out.println("## 演習1-2");
        boolean ex1_2_result = true || false;
        System.out.println(ex1_2_result);

        //演習2-1
        System.out.println("## 演習2-1");
        int a = 1;
        double b = a/2;
        System.out.println(b);

        //演習2-2
        System.out.println("## 演習2-2");
        a = 1;
        b = a/2.;
        System.out.println(b);

        //演習3-1
        System.out.println("## 演習3-1");
        System.out.println((int)10.9);

        //演習3-2
        System.out.println("## 演習3-2");
        char c = 'a';
        System.out.println((int)c);

        //演習4-1
        System.out.println("## 演習4-1");
        String[] text = {"This", "is", "test."};
        String ex4_1_result = "";
        for(String item : text){
            ex4_1_result += item;
        }
        System.out.println(ex4_1_result);

        //演習5
        String str = "This is test.";
        //演習5-1
        System.out.println("## 演習5-1");
        String result1 = str.toLowerCase();
        System.out.println(result1);

        //演習5-2
        System.out.println("## 演習5-2");
        String result2[] = str.split(" ");
        for(String item : result2){
            System.out.println(item);
        }

        //演習6-1
        System.out.println("## 演習6-1");
        String data = "3.14";
        double d = Double.parseDouble(data);
        System.out.println(d);
    }
}
