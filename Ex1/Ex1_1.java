/**
 * Ex1_1 on ex1.md
 */
public class Ex1_1 {
    private static String reversed(String source){
        int i, j;
        char[] temp = new char[source.length()];
        for(i=source.length()-1, j=0; i>=0; i--, j++){
            temp[j] = source.charAt(i);
        }
        //String target = new String(temp);
        return new String(temp);
    }

    public static void main(String[] args){
        String source = "stressed";
        System.out.println("source = " + source);
        String target = reversed(source);
        System.out.println("target = " + target);
    }
}
