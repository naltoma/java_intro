import java.io.FileWriter;

public class Exception1 {
    public static void main(String[] args){
	// try-catchなしのコード。
	FileWriter fw = new FileWriter("output.txt");
	String[] results = {"ほげ", "ふが"};
	for(String temp: results){
	    fw.write(temp);
	}
	fw.close();
    }
}
