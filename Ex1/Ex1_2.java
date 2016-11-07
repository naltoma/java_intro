/**
 * Ex1_2 on ex1.md
 */
public class Ex1_2 {
    private static String splitAndCombine(String source){
        char[] temp1 = new char[source.length()/2];
        char[] temp2 = new char[source.length()/2];
        String result;
        int i, j, k;

        for(i=0, j=0, k=0; i<source.length(); i++) {
            if( i % 2 == 0 ) {
                temp1[j] = source.charAt(i);
                j++;
            }else{
                temp2[k] = source.charAt(i);
                k++;
            }
        }
        result = new String(temp1) + new String(temp2);
        return result;
    }

    public static void main(String[] args){
        String source = "パタトクカシーー";
        System.out.println("source = " + source);
        String result = splitAndCombine(source);
        System.out.println("result = " + result);
    }
}
