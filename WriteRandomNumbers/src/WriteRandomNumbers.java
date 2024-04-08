import java.io.*;
import java.util.*;
public class WriteRandomNumbers {
	public static void main(String args[]) throws Exception{
		Random r=new Random();
		File file=new File("Exercise12_15.txt");
		if(!file.exists()) {
			file.createNewFile();
		}
		else {
			System.out.println("File already exist!");
			System.exit(1);
		}
		PrintWriter output=new PrintWriter(file);
		for(int i=0;i<100;i++) {
			output.print(r.nextInt()+" ");
		}
		output.close();
		Scanner input=new Scanner(file);
		int[] reader=new int[100];
		for(int i=0;i<100;i++) {
			reader[i]=input.nextInt();
		}
		Arrays.sort(reader);
		for(int i=0;i<100;i++) {
			System.out.println(reader[i]);
		}
	}
}
